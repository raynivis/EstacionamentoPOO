/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Grafica;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Interface.Terminal.AuxiliarInterfarceListaTipoSemanas;
import ufms.cptl.raymay.Interface.Terminal.ItensMenu;
import ufms.cptl.raymay.Interface.UserInterface.UserInterface;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class GraficaInterfaceInicial implements UserInterface{
    
    OperacoesTicket opTicket = new OperacoesTicket();
    OperacoesVagas opVaga = new OperacoesVagas();
    OperacoesCliente opCliente = new OperacoesCliente();

    
    GraficaInterfaceOpcaoCliente interCliente = new GraficaInterfaceOpcaoCliente();
    GraficaInterfaceOpcaoVaga interVaga = new GraficaInterfaceOpcaoVaga();
    GraficaInterfaceOpcaoEstacionamento interEstaciona = new GraficaInterfaceOpcaoEstacionamento();
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
    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {  
        do {
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            opcao = menui.imprimeInicio(1);
            opTicket.verificarTicketsMensalista30dias(tickets);
            
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
                        menucg.imprimeCadastroGeral(1);
                        opTicket.verificarTicketsMensalista30dias(tickets);
                        
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3) {
                            case 1:
                                String codigoJO = JOptionPane.showInputDialog(
                                null,
                                "Digite o codigo do ticket",
                                "Ticket Tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(codigoJO == null) {
                                    break;
                                }
                                int codigo = Integer.parseInt(codigoJO);
                               
                                Ticket testeT = opTicket.buscarTicket(tickets, codigo);
                                 if(testeT == null) {
                                    JOptionPane.showMessageDialog(null, "Ticket não encontrado!",
                                "Ticket Tarifa", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } 
                                if(testeT.getStatus().equals(Operando.DESATIVO)) {
                                    JOptionPane.showMessageDialog(null, "Não é permetido testar um ticket finalizado!",
                                    "Ticket Tarifa", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                double lucro;
                                if(testeT instanceof TicketHorista){
                                    String data = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a data de finalização do ticket (em dia/mês/ano horas:minutos:segundos)",
                                    "Ticket Tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(data == null) {
                                        break;
                                    }
                                    LocalDateTime dataFinal = LocalDateTime.parse(data, dataBonitinhaComSegundos);                                                             
                                    testeT.setFim(dataFinal); 
                                    lucro = ((TicketHorista)testeT).totalFaturadoTicket();
                                    testeT.setFim(null); 
                                }
                                else {
                                    lucro = ((TicketMensalista)testeT).totalFaturadoTicket();
                                }
                                
                                JOptionPane.showMessageDialog(null, "O lucro desse ticket foi de " + dinheiro.format(lucro),
                                "Ticket Tarifa", JOptionPane.PLAIN_MESSAGE);
                                
                            break;
                            case 2: /*consultar veiculo*/
                                String placa = JOptionPane.showInputDialog(
                                null,
                                "Digite a placa do veículo",
                                "Consultar veículo",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(placa == null) {
                                    break;
                                }
                                Veiculo veicule = opCliente.verificarVeiculo(clientes, placa);
                                if(veicule == null) {
                                    JOptionPane.showMessageDialog(null, "Veículo não encontrado!",
                                    "Consultar veículo", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                JOptionPane.showMessageDialog(null, veicule.toString(),
                                    "Consultar veículo", JOptionPane.PLAIN_MESSAGE);
                            break; 
                            case 3: /*consultar Tarifa*/
                                String tipe = JOptionPane.showInputDialog(
                                null,
                                "Digite o tipo de tarifa (Horista ou Mensalista)",
                                "Consultar tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(tipe == null) {
                                    break;
                                }
                                
                                String data = JOptionPane.showInputDialog(
                                null,
                                "Digite a data de início da tarifa (em dia/mês/ano horas:minutos)",
                                "Consultar tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(data == null) {
                                    break;
                                }                            
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                                            
                                if(tipe.equalsIgnoreCase("HORISTA") ){
                                    TarifaHorista tarife = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                    if(tarife == null){
                                        JOptionPane.showMessageDialog(null, "Tarifa não encontrada!",
                                        "Consultar tarifa", JOptionPane.ERROR_MESSAGE);

                                    break;
                                    }
                                    JOptionPane.showMessageDialog(null, tarife.toString(),
                                    "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);                                  
                                    for(DiaSemana ds : tarife.getDiasSemana()){
                                        JOptionPane.showMessageDialog(null, ds.toString(),
                                        "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    JOptionPane.showMessageDialog(null, "Tipo/s de veículos",
                                    "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                        JOptionPane.showMessageDialog(null, tv.toString(),
                                        "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    
                                } 
                                else{
                                    TarifaMensalista tarife = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                    if(tarife == null){
                                        JOptionPane.showMessageDialog(null, "Tarifa não encontrada!",
                                        "Consultar tarifa", JOptionPane.ERROR_MESSAGE);
                                    break;
                                    }
                                    JOptionPane.showMessageDialog(null, tarife.toString(),
                                    "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    for(DiaSemana ds : tarife.getDiasSemana()){
                                    JOptionPane.showMessageDialog(null, ds.toString(),
                                    "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    JOptionPane.showMessageDialog(null, "Tipo/s de veículos",
                                    "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                        JOptionPane.showMessageDialog(null, tv.toString(),
                                        "Consultar tarifa", JOptionPane.PLAIN_MESSAGE);
                                    }
                                }  
                                          
                            break; 
                            case 4: /*consultar Ticket*/
                                codigoJO = JOptionPane.showInputDialog(
                                null,
                                "Digite o codigo do ticket",
                                "Ticket Tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(codigoJO == null) {
                                    break;
                                }
                                codigo = Integer.parseInt(codigoJO);
                                Ticket tickete = opTicket.buscarTicket(tickets, codigo);

                                if(tickete == null){
                                    JOptionPane.showMessageDialog(null, "Ticket não encontrada!",
                                    "Consultar ticket", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                JOptionPane.showMessageDialog(null, tickete.toString(),
                                "Consultar ticket", JOptionPane.ERROR_MESSAGE);
                                if(tickete.getFim() != null){
                                    JOptionPane.showMessageDialog(null, "Fim do ticket: " + tickete.getFim().format(dataBonitinha),
                                    "Consultar ticket", JOptionPane.ERROR_MESSAGE);
                                }
                            break;
                            case 5: /* Listar tickets ativos */
                                opTicket.ListarTicketAtivo(tickets);
                            break; 
                            case 6: /* Listar vagas cadastradas */
                                opVaga.listarVagasCadastradas(vagas, 1);

                            break; 
                            case 7:
                            break;
                            default:
                                 JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                                "Alerta", JOptionPane.WARNING_MESSAGE);
                            break;
                        }                        
                    }while(opcao3 != 7);                                                                        
                break;        
                case 5: /*total faturado*/
                    String iniS = JOptionPane.showInputDialog(
                    null,
                    "Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano)" +
                    "Data inicial",
                    "Total faturado",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(iniS == null) {
                        break;
                    }
                    iniS = iniS + " 00:00:00";
                    
                    String fimS = JOptionPane.showInputDialog(
                    null,
                    "Data final",
                    "Total faturado",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(fimS == null) {
                        break;
                    }
                      fimS = fimS + " 23:59:59";
                      LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                      LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                      double resultado = opTicket.FaturadoPeriodo(tickets, inicio, fim);

                      JOptionPane.showMessageDialog(null, "Nesse período foi/foram faturado/s: "  + dinheiro.format(resultado),
                      "Total faturado", JOptionPane.INFORMATION_MESSAGE);                    
                break;
                case 6:
                break;                    
                default:
                    JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }while (opcao != 6);
        scanner.close();
    }    
}
