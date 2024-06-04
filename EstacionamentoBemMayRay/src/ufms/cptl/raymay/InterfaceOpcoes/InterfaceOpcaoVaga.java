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
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoVaga{
    OperacoesVagas opVaga = new OperacoesVagas();   
    int opcao2;
    
    /* Método geral das opções da vaga que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas a vaga */
    public void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {      
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            if(inter instanceof InterfaceTerminal){
                inter = (InterfaceTerminal) inter;
                opcao2 = inter.imprimeVaga();
            }
            else {
                inter = (InterfaceGrafica) inter;
                opcao2 = inter.imprimeVaga();
            }
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    int numero;
                    String rua;
                    
                    String numeroS = inter.receberString("Digite o número da vaga a ser cadastrada");
                    numero = Integer.parseInt(numeroS);
                        
                    rua = inter.receberString("Digite o nome da rua da vaga a ser cadastrada");          
                                   
                    String tipo;
                    tipo = inter.receberString("Digite o tipo de vaga(MOTO, CARRO, ONIBUS)");
                    
                    /* Transforma a String inserida em maiúsculo para fazer a comparação */
                    TipoVeiculo tipoV = TipoVeiculo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, tipoV);
                    
                    /* O método cadastrarVaga já adiciona na lista de vagas se retornar true */
                    if (opVaga.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        inter.mensagem("Vaga cadastrada com sucesso!");
                    }
                    else{
                        inter.mensagem("Vaga já existente!");                       
                    }   
                break;                 
                case 2:
                    /*consultar vaga por número*/
                    numeroS = inter.receberString("Digite o número da vaga que você deseja consultar");
                    numero = Integer.parseInt(numeroS);
                        
                    rua = inter.receberString("Digite a rua da vaga que você deseja consultar");  
                    Vaga vaga = opVaga.consultarVaga(vagas, numero, rua); 
                    if(vaga == null){
                        inter.mensagem("Vaga inexistente!");
                        break;
                    }
                    inter.mensagem(vaga.toString());
               break;    
    
                case 3:
                    /*excluir vaga*/
                    numeroS = inter.receberString("Digite o número da vaga a ser excluída");
                    numero = Integer.parseInt(numeroS);

                    rua = inter.receberString("Digite a rua da vaga a ser excluída"); 
                        
                    /* O método excluirVaga realiza as verificações necessárias para a exclusão da vaga*/
                    if(opVaga.excluirVaga(vagas, tickets, rua, numero) == null) {
                        inter.mensagem("Vaga rua:" + rua + " número:" + numero + " excluída com sucesso!");
                    }
                    else {
                         inter.mensagem(opVaga.excluirVaga(vagas, tickets, rua, numero));
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    numeroS = inter.receberString("Digite a número da vaga que você deseja editar");
                    numero = Integer.parseInt(numeroS);

                    rua = inter.receberString("Digite a rua para a vaga que você deseja editar");  
                    int numeroNovo;
                    String ruaNova;
                    
                    String numeroSi = inter.receberString("Digite o novo número da vaga");
                    numeroNovo = Integer.parseInt(numeroSi);

                    ruaNova = inter.receberString("Digite a nova rua da vaga");      
                    
                    if(opVaga.editarVaga(vagas, rua, numero, ruaNova, numeroNovo) == true) {
                        inter.mensagem("Vaga editada com sucesso!");
                    }
                    else {
                        inter.mensagem("Não é possivel editar essa Vaga!");                    
                    }
                break;                 
                case 5:
                    /*alterar disponibilidade da vaga*/
                    String status;
                    String numeroSii = inter.receberString("Digite o número da vaga para alterar sua disponibilidade");
                    numero = Integer.parseInt(numeroSii);

                    rua = inter.receberString("Digite a rua da vaga para alterar sua disponibilidade"); 

                    status = inter.receberString("Digite o novo status da vaga (DISPONIVEL ou INDISPONIVEL)");
                    VagaStatus statusV = VagaStatus.valueOf(status.toUpperCase());
                    
                    if(statusV == VagaStatus.OCUPADA) {                      
                        inter.mensagem("Não é possível deixar a vaga OCUPADA!");
                        break;
                    }                 
                    if(opVaga.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        inter.mensagem("Disponibilidade da vaga alterada com sucesso!");
                    }
                break;
                case 6:
                break;
                default:
                    inter.mensagem("Insira uma opção válida!");
                break;
            }    
        }while(opcao2 != 6);
               
    }
}