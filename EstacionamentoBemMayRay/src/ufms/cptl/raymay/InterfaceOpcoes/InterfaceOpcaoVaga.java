/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.util.List;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuVaga;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ErroDigitacaoException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.VagaException;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoVaga{
    OperacoesVagas opVaga = new OperacoesVagas();   
    InterfaceException ex = new InterfaceException();
    int opcao2;
    
    /* Método geral das opções da vaga que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas a vaga */
    public void realizarOpcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {      
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            opcao2 = inter.imprimirMenu(InterMenuVaga.class, "Menu Vaga");
            try {
                switch (opcao2) {
                    case 1:
                        /*cadastrar vaga*/

                        int numero = inter.receberInteiro("Digite o número da vaga a ser cadastrada");
                        String rua = inter.receberStringFormat("Digite o nome da rua da vaga a ser cadastrada", "^[\\p{L}]+$", "rua");          

                        String tipo;
                        tipo = inter.receberString("Digite o tipo de vaga(MOTO, CARRO, ONIBUS)");
                        if(!tipo.equalsIgnoreCase("MOTO") || !tipo.equalsIgnoreCase("CARRO") || !tipo.equalsIgnoreCase("ONIBUS")) {
                            throw ex.new ErroDigitacaoException("São válidas somente as palavras moto, carro ou onibus!");
                        } 
                        /* Transforma a String inserida em maiúsculo para fazer a comparação */
                        TipoVeiculo tipoV = TipoVeiculo.valueOf(tipo.toUpperCase());

                        Vaga novaVaga = new Vaga(numero, rua, tipoV);

                        /* O método cadastrarVaga já adiciona na lista de vagas se retornar true */
                        if (opVaga.cadastrar(vagas, novaVaga, rua, numero) == true){
                            inter.imprimirMensagem("Vaga cadastrada com sucesso!");
                        }
                        else{
                            throw ex.new VagaException("Vaga já existente!");                     
                        }   
                    break;                 
                    case 2:
                        /*consultar vaga por número*/
                        numero = inter.receberInteiro("Digite o número da vaga que você deseja consultar");

                        rua = inter.receberStringFormat("Digite a rua da vaga que você deseja consultar", "^[\\p{L}]+$", "rua");  
                        Vaga vaga = opVaga.consultar(vagas, numero, rua); 
                        if(vaga == null){
                            throw ex.new VagaException("Vaga inexistente!"); 
                        }
                        inter.imprimirMensagem(vaga.toString());
                   break;    
    

                    case 3:
                        /*excluir vaga*/
                        numero = inter.receberInteiro("Digite o número da vaga a ser excluída");
                        rua = inter.receberStringFormat("Digite a rua da vaga a ser excluída", "^[\\p{L}]+$", "rua"); 

                        /* O método excluirVaga realiza as verificações necessárias para a exclusão da vaga*/
                        if(opVaga.excluir(vagas, tickets, rua, numero) == null) {
                            inter.imprimirMensagem("Vaga rua:" + rua + " número:" + numero + " excluída com sucesso!");
                        }
                        else {
                             inter.imprimirMensagem(opVaga.excluir(vagas, tickets, rua, numero));
                        }
                    break;  

                    case 4:
                        /*editar vaga*/
                        numero = inter.receberInteiro("Digite o número da vaga a ser editada");
                        rua = inter.receberStringFormat("Digite a rua da vaga a ser editada", "^[\\p{L}]+$", "rua"); 
                        
                        int numeroNovo;
                        String ruaNova;

                        numeroNovo = inter.receberInteiro("Digite o novo número da vaga");
                        ruaNova = inter.receberStringFormat("Digite a nova rua da vaga", "^[\\p{L}]+$", "rua");    

                        if(opVaga.editar(vagas, rua, numero, ruaNova, numeroNovo) == true) {
                            inter.imprimirMensagem("Vaga editada com sucesso!");
                        }
                        else {
                            throw ex.new VagaException("Não é possível editar essa vaga!");                     
                        }
                    break;                 
                    case 5:
                        /*alterar disponibilidade da vaga*/
                        String status;
                        numero = inter.receberInteiro("Digite o número da vaga para alterar a disponibilidade");
                        rua = inter.receberStringFormat("Digite a rua da vaga para alterar a disponibilidade", "^[\\p{L}]+$", "rua"); 

                        status = inter.receberString("Digite o novo status da vaga (DISPONIVEL ou INDISPONIVEL)");
                        if(!status.equalsIgnoreCase("DISPONIVEL") || !status.equalsIgnoreCase("INDISPONIVEL") || !status.equalsIgnoreCase("OCUPADA")) {
                            throw ex.new ErroDigitacaoException("São válidas somente as palavras disponivel ou indisponivel!");
                        } 
                        VagaStatus statusV = VagaStatus.valueOf(status.toUpperCase());

                        if(statusV == VagaStatus.OCUPADA) {                      
                            throw ex.new VagaException("Não é possível deixar a vaga OCUPADA!"); 
                        }                 
                        if(opVaga.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                            inter.imprimirMensagem("Disponibilidade da vaga alterada com sucesso!");
                        }
                    break;
                    case 6:
                    break;
                    default:
                        inter.imprimirException("Insira uma opção válida!"); 
                    break;
                }  
            }catch (ErroDigitacaoException | VagaException e) {
                inter.imprimirException(e.getMessage());
            }    
        }while(opcao2 != 6);
               
    }
}
