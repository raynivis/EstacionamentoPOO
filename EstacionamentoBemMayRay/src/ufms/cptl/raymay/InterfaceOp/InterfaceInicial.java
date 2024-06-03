/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.InterfaceOp.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceGrafica;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceTerminal;
import ufms.cptl.raymay.Interface.UserInterface.UserInterface;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceInicial{
    
    OperacoesTicket opTicket = new OperacoesTicket();
    OperacoesVagas opVaga = new OperacoesVagas();
    OperacoesCliente opCliente = new OperacoesCliente();
    
    
    InterfaceOpcaoCliente interCliente = new InterfaceOpcaoCliente();
    InterfaceOpcaoVaga interVaga = new InterfaceOpcaoVaga();
    InterfaceOpcaoEstacionamento interEstaciona = new InterfaceOpcaoEstacionamento();
    ItensMenu menui = new ItensMenu(); /* menui = Menu Inicial */
    ItensMenu menucg = new ItensMenu(); /* menucg = Menu de Cadastros Gerais */
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    int opcao;
    int opcao3;
    
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
   
    
    /* Método geral das opções do menu que será chamado na Classe Main Estacionamento e permitirá que todo o
    menu seja exibido ao usuário */
    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter, int face) {  
        do {
                              
            opTicket.verificarTicketsMensalista30dias(tickets);
            
            if(face == 0){
                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                opcao = interfaces.imprimeInicio();
            }
            else {
                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                opcao = interfaces.imprimeInicio();
            }
                    
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
                        opTicket.verificarTicketsMensalista30dias(tickets);
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            opcao3 = interfaces.imprimeCadastroGeral();
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            opcao3 = interfaces.imprimeCadastroGeral();
                        }                      
                        switch(opcao3) {
                            case 1:
                                
                                int cod;
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    String codigo = interfaces.receberString("Digite o codigo do ticket:");
                                    cod = Integer.parseInt(codigo);
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    String codigo = interfaces.receberString("Digite o codigo do ticket");
                                    cod = Integer.parseInt(codigo);
                                }        
                                                                
                                Ticket testeT = opTicket.buscarTicket(tickets, cod);
                                 if(testeT == null) {
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("Erro: Ticket não encontrado!");               
                                    }
                                    else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                       interfaces.mensagem("Erro: Ticket não encontrado!");                               
                                    }                           
                                    break;
                                } 
                                if(testeT.getStatus().equals(Operando.DESATIVO)) {
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("Erro: Não é permetido testar um ticket finalizado!");               
                                    }
                                    else {
                                       InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                       interfaces.mensagem("Erro: Não é permetido testar um ticket finalizado!");                               
                                    }                           
                                    break;
                                    interMensagem("\n\n");
                                    break;
                                }
                                double lucro;
                                if(testeT instanceof TicketHorista){
                                    String data;
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        data = interfaces.receberString("Digite a data de finalização do ticket (em dia/mês/ano horas:minutos:segundos):");
                
                                    }
                                    else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        data = interfaces.receberString("Digite a data de finalização do ticket (em dia/mês/ano horas:minutos:segundos)");
                                
                                    }                                          
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
                                opVaga.listarVagasCadastradas(vagas, 0);

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
                      fimS = fimS + " 23:59:59";
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
