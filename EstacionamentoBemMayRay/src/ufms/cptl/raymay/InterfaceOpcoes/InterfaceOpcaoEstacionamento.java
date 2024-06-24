/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.time.LocalDateTime;
import java.util.List;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuEstacionamento;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ClienteException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ErroDigitacaoException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.TarifaException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.VagaException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.VeiculoException;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTarifa;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoEstacionamento{
    OperacoesVagas opVaga = new OperacoesVagas(); 
    OperacoesCliente opCliente = new OperacoesCliente();
    OperacoesTicket opTicket = new OperacoesTicket();
    OperacoesTarifa opTarifa = new OperacoesTarifa();
    InterfaceException ex = new InterfaceException();

    int opcao2;

    /* Método geral das opções do estacionamento que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao estacionamento */
    public void realizarOpcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {
        do{
            /* Utiliza o método criado em OpcaoEstacionamento no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            opcao2 = inter.imprimirMenu(InterMenuEstacionamento.class, "Menu Estacionamento");
            try {
                if(clientes.isEmpty() == true) {
                    throw ex.new ClienteException("Lista de clientes vazia! Cadastre algum antes!");
                }
                if(vagas.isEmpty() == true) {
                    throw ex.new VagaException("Lista de vagas vazia! Veículos não podem ser estacionados se não há vagas!");
                }
                switch (opcao2) {
                    case 1:
                        /*estacionar*/

                        if(tarifas.isEmpty() == true) { /*verificando se tem tarifa*/
                            throw ex.new TarifaException("Cadastre uma tarifa primeiro!!");
                        }
                        String placa = inter.receberString("Digite a placa do veículo a ser estacionado:");

                        Veiculo veiculo = opCliente.buscarVeiculo(clientes, placa);

                        if(veiculo == null) {
                           throw ex.new VeiculoException("Veiculo não encontrado!");
                        }

                        Ticket veriTicket = opTicket.verificarUtilizacaoParaVeiculo(clientes, placa, tickets);
                        if(veriTicket != null){
                           throw ex.new VeiculoException("O Veiculo já esta estacionado!");                    
                        }                                      

                        if(opTicket.verificarMensalistaParaVeiculo(tickets, veiculo)){ /*verificando o veiculo possui um ticket mensalista*/
                            inter.imprimirMensagem("Veículo Mensalista estacionado com sucesso!");                        
                            break;
                        }
                        String ruaVaga;
                        Vaga vaga;
                        int numeroRua = inter.receberInteiro("Digite o número da vaga que pretende ser estacionada:");
                        ruaVaga = inter.receberStringFormat("Digite a rua da vaga que pretende ser estacionada:", "^[\\p{L}]+$", "rua");
                        vaga = opVaga.consultar(vagas, numeroRua, ruaVaga);


                        if(vaga == null) { 
                           throw ex.new VagaException("Vaga não econtrada!");
                        }

                        if(veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {
                            throw ex.new VeiculoException("O tipo de veículo não é compatível com o tipo de vaga!");
                        }

                        if(vaga.isDisponivel() == false) {
                            if(vaga.isOcupada()) { 
                                throw ex.new VagaException("A vaga possui um ticket de estacionamento ATIVO (OCUPADA)!");
                           }
                           throw ex.new VagaException("Vaga indisponível!");
                        }                
                        String tipoTi; 
                        tipoTi = inter.receberString("O cliente deseja estacionar como Horista ou Mensalista?"); 
                        /*Achar a tarifa que pertence ao ticket*/                                     

                        if(tipoTi.equalsIgnoreCase("HORISTA")){
                            TarifaHorista atualH = opTarifa.buscarHoristaMaisProxima(tarifas, LocalDateTime.now());

                            if(atualH == null){
                                throw ex.new TarifaException("Não existe uma tarifa para esse tipo de vaga nesse período!");
                            }
                            TicketHorista novoTicket = new TicketHorista(atualH, veiculo, vaga);
                            tickets.add(novoTicket);
                            inter.imprimirMensagem("Ticket Horista de código " + novoTicket.getCodigo() + " criado com sucesso!");                           
                        }
                        else if(tipoTi.equalsIgnoreCase("MENSALISTA")){
                            TarifaMensalista atualM = opTarifa.buscarMensalistaMaisProxima(tarifas, LocalDateTime.now());

                            if(atualM == null){
                                throw ex.new TarifaException("Não existe uma tarifa para esse tipo de vaga nesse período!");
                            }
                            TicketMensalista novoTicket = new TicketMensalista(atualM, veiculo, vaga);
                            tickets.add(novoTicket);
                            inter.imprimirMensagem("Ticket Mensalista de código " + novoTicket.getCodigo() + " criado com sucesso!");                  
                        } else {
                            throw ex.new ErroDigitacaoException("São valídas somente as palavras horista ou mensalista!");
                        }                                                   
                    break;    

                    case 2:
                        /*retirar*/
                        placa = inter.receberString("Digite a placa do veículo que deseja retirar:");

                        Veiculo veiculoRetirar = opCliente.buscarVeiculo(clientes, placa);
                        if(veiculoRetirar == null) {
                            throw ex.new VeiculoException("Veiculo não encontrado nos Clientes!");
                        }

                        if(opTicket.retirar(tickets, veiculoRetirar) == false) {
                             throw ex.new VeiculoException("Veiculo não pode ser retirado!");      
                        } else {
                            inter.imprimirMensagem("Veiculo Retirado com sucesso!");                 
                        }
                    break;    
                    case 3:
                        /*listar todas as vagas disponíveis do estacionamento*/
                        List<String> lista = opVaga.listarDisponiveis(vagas);
                        for(String s : lista){
                            inter.imprimirMensagem(s);
                        }
                    break;    
                    case 4:
                        /*Gerenciar tarifas*/
                        new InterfaceOpcaoTarifa().realizarOpcoesTarifa(tickets, tarifas, inter);
                    break;
                    case 5:
                    break;
                    default:
                       inter.imprimirException("Insira uma opção válida!");     
                    break;
                }    
            } catch(TarifaException | VeiculoException | VagaException | ErroDigitacaoException | ClienteException e) {
                inter.imprimirException(e.getMessage());
            }        
        }while(opcao2 != 5);     
    }   
}
