/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Enum.Operando;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceInicial {
    
    OperacoesTicket opTicket = new OperacoesTicket();
    OperacoesVagas opVaga = new OperacoesVagas();
    OperacoesCliente opCliente = new OperacoesCliente();
    
    InterfaceOpcaoCliente interCliente = new InterfaceOpcaoCliente();
    InterfaceOpcaoVaga interVaga = new InterfaceOpcaoVaga();
    InterfaceOpcaoEstacionamento interEstaciona = new InterfaceOpcaoEstacionamento();
    ItensMenu menui = new ItensMenu(); /* menui = Menu Inicial */
    ItensMenu menucg = new ItensMenu(); /* menucg = Menu de Cadastros Gerais */
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    byte opcao;
    byte opcao3;
    
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
   
    
    /* Método geral das opções do menu que será chamado na Classe Main Estacionamento e permitirá que todo o
    menu seja exibido ao usuário */
    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {  
        do {
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            menui.imprimeInicio();
            opTicket.verificarTicketsMensalista(tickets);
            
            opcao = scanner.nextByte();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    interCliente.opcoesCliente(clientes, vagas, tickets, tarifas);
                break;    
                case 2:
                    interVaga.opcoesVaga(clientes, vagas, tickets, tarifas);
                break;
                case 3:
                    interEstaciona.opcoesEstacionamento(clientes, vagas, tickets, tarifas);
                break;
                case 4:
                    do {
                        /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                        de linhas das Classes da interface */
                        menucg.imprimeCadastroGeral();
                        opTicket.verificarTicketsMensalista(tickets);
                        
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3) {
                            case 1:
                                interMensagem("Digite o código do ticket:");
                                int codigo = scanner.nextInt();
                                scanner.nextLine();
                                
                                Ticket testeT = opTicket.buscarTicket(tickets, codigo);
                                 if(testeT == null) {
                                    interMensagem("\nErro: Ticket não encontrado!\n");
                                    break;
                                } 
                                if(testeT.getStatus().equals(Operando.DESATIVO)) {
                                    interMensagem("\nErro: Não é permetido testar um ticket finalizado!\n");
                                    break;
                                }
                                double lucro;
                                if(testeT instanceof TicketHorista){
                                    interMensagem("Digite a data de finalização do ticket (em dia/mês/ano horas:minutos:segundos):");
                                    String data = scanner.nextLine();
                                    LocalDateTime dataFinal = LocalDateTime.parse(data, dataBonitinhaComSegundos);                                                             
                                    testeT.setFim(dataFinal); 
                                    lucro = ((TicketHorista)testeT).totalFaturadoTicket();
                                    testeT.setFim(null); 
                                }
                                else {
                                    lucro = ((TicketMensalista)testeT).totalFaturadoTicket();
                                }
                                
                                interMensagem("\nO lucro desse ticket foi de " + dinheiro.format(lucro) + "\n");
                                
                            break;
                            case 2: /*consultar veiculo*/
                                interMensagem("Digite a placa do veículo:");
                                String placa = scanner.nextLine();
                                Veiculo veicule = opCliente.verificarVeiculo(clientes, placa);
                                if(veicule == null) {
                                    interMensagem("Erro: Veículo não encontrado!!");
                                    break;
                                }
                                interMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(veicule.toString());
                                interMensagem("///////////////////////////////////////////////////\n");
                            break; 
                            case 3: /*consultar Tarifa*/
                                interMensagem("Digite o tipo de tarifa (Horista ou Mensalista): ");
                                String tipe = scanner.nextLine();
                                interMensagem("Digite a data de início da tarifa (em dia/mês/ano horas:minutos):");
                                String data = scanner.nextLine();                              
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                                            
                                if(tipe.equalsIgnoreCase("HORISTA") ){
                                    TarifaHorista tarife = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                    if(tarife == null){
                                        interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                    }
                                    interMensagem("\n///////////////////////////////////////////////////");
                                    interMensagem(tarife.toString());                                   
                                    for(DiaSemana ds : tarife.getDiasSemana()){
                                    System.out.print(ds.toString() + " ");
                                    }
                                    interMensagem("\nTipo/s de veículo:");
                                    for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                        System.out.print(tv.toString() + " ");
                                    }
                                    interMensagem("\n///////////////////////////////////////////////////\n");
                                    
                                } 
                                else{
                                    TarifaMensalista tarife = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                    if(tarife == null){
                                        interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                    }
                                    interMensagem("\n///////////////////////////////////////////////////");
                                    interMensagem(tarife.toString());
                                    for(DiaSemana ds : tarife.getDiasSemana()){
                                    System.out.print(ds.toString() + " ");
                                    }
                                    interMensagem("\nTipo/s de veículo:");
                                    for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                        System.out.print(tv.toString() + " ");
                                    }
                                    interMensagem("\n///////////////////////////////////////////////////\n");
                                }  
                                          
                            break; 
                            case 4: /*consultar Ticket*/
                                interMensagem("Digite o código do ticket:");
                                codigo = scanner.nextInt(); 
                                Ticket tickete = opTicket.buscarTicket(tickets, codigo);
                                if(tickete == null){
                                    interMensagem("\nErro: Ticket não encontrado!\n");
                                    break;
                                }
                                interMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(tickete.toString());
                                if(tickete.getFim() != null){
                                    interMensagem("Fim do ticket: " + tickete.getFim().format(dataBonitinha));
                                }
                                interMensagem("///////////////////////////////////////////////////\n");
                            break;
                            case 5: /* Listar tickets ativos */
                                opTicket.ListarTicketAtivo(tickets);
                            break; 
                            case 6: /* Listar vagas cadastradas */
                                opVaga.listarVagasCadastradas(vagas);
                            break; 
                            case 7:
                            break;
                            default:
                                interMensagem("\nInsira uma opção válida!\n");
                            break;
                        }                        
                    }while(opcao3 != 7);                                                                        
                break;        
                case 5:
                      interMensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                      interMensagem("Data inicial: ");
                      String iniS = scanner.nextLine();
                      iniS = iniS + " 00:00:00";
                      interMensagem("Data final: ");
                      String fimS = scanner.nextLine();
                      fimS = fimS + " 00:00:00";
                      LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                      LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                      double resultado = opTicket.FaturadoPeriodo(tickets, inicio, fim);
                      interMensagem("\nNesse período foi/foram faturado/s: "  + dinheiro.format(resultado) + "\n");                     
                break;
                case 6:
                break;                    
                default:
                    interMensagem("\nInsira uma opção válida!\n");
                break;
            }
        }while (opcao != 6);
        scanner.close();
    }    
}
