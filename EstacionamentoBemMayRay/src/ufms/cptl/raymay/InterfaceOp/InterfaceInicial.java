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
                                
                                if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("O lucro desse ticket foi de " + dinheiro.format(lucro));               
                                }
                                else {
                                       InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                       interfaces.mensagem("O lucro desse ticket foi de " + dinheiro.format(lucro));                             
                                }                                                                   
                            break;
                            case 2: /*consultar veiculo*/
                                String placa;
                                if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        placa = interfaces.receberString("Digite a placa do veículo:");
                
                                }
                                    else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        placa = interfaces.receberString("Digite a placa do veículo:");
                                
                                }                      
                                Veiculo veicule = opCliente.verificarVeiculo(clientes, placa);
                                if(veicule == null) {
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("Veiculo não encontrado!");               
                                    }
                                    else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Veiculo não encontrado!");                                 
                                    }    
                                    break;
                                }
                                
                                if(face == 0){
                                        interMensagem("\n///////////////////////////////////////////////////");
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem(veicule.toString()); 
                                        interMensagem("///////////////////////////////////////////////////\n");
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    interfaces.mensagem(veicule.toString());                                 
                                }                                                              
                            break; 
                            case 3: /*consultar Tarifa*/
                                String tipe, data;
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    tipe = interfaces.receberString("Digite o tipo de tarifa (Horista ou Mensalista): ");
                                    data = interfaces.receberString("Digite a data de início da tarifa (em dia/mês/ano horas:minutos):");
                
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    tipe = interfaces.receberString("Digite o tipo de tarifa (Horista ou Mensalista): ");  
                                    data = interfaces.receberString("Digite a data de início da tarifa (em dia/mês/ano horas:minutos):");
                                }                                
                                                                                        
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps, inter, face);
                                                            
                                if(tipe.equalsIgnoreCase("HORISTA") ){
                                    TarifaHorista tarife = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                    if(tarife == null){                                       
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Tarifa não encontrada!");               
                                        }
                                        else {
                                               InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                               interfaces.mensagem("Erro: Tarifa não encontrada!");                                 
                                        }   
                                        break;
                                    }
                                    if(face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("\n///////////////////////////////////////////////////");
                                        interfaces.mensagem(tarife.toString());                                   
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            interfaces.mensagem(ds.toString() + " ");
                                        }
                                        interfaces.mensagem("\nTipo/s de veículo:");
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            interfaces.mensagem(tv.toString() + " ");
                                        }
                                        interfaces.mensagem("\n///////////////////////////////////////////////////\n");
                                    } else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        interfaces.mensagem(tarife.toString());                                   
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            interfaces.mensagem(ds.toString() + " ");
                                        }
                                        interfaces.mensagem("\nTipo/s de veículo:");
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            interfaces.mensagem(tv.toString() + " ");
                                        }
                                    }                                   
                                } 
                                else{
                                    TarifaMensalista tarife = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                    if(tarife == null){
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Tarifa não encontrada!");               
                                        }
                                        else {
                                               InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                               interfaces.mensagem("Erro: Tarifa não encontrada!");                                 
                                        }   
                                        break;
                                    }
                                    if(face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("\n///////////////////////////////////////////////////");
                                        interfaces.mensagem(tarife.toString());
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            interfaces.mensagem(ds.toString() + " ");
                                        }
                                        interfaces.mensagem("\nTipo/s de veículo:");
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            interfaces.mensagem(tv.toString() + " ");
                                        }
                                        interfaces.mensagem("\n///////////////////////////////////////////////////\n");
                                    } else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;                                      
                                        interfaces.mensagem(tarife.toString());
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            interfaces.mensagem(ds.toString() + " ");
                                        }
                                        interfaces.mensagem("\nTipo/s de veículo:");
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            interfaces.mensagem(tv.toString() + " ");
                                        }
                                    }
                                }  
                                          
                            break; 
                            case 4: /*consultar Ticket*/                              
                                String codigo;
                                if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        codigo = interfaces.receberString("Digite o código do ticket:");
                                }
                                else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        codigo = interfaces.receberString("Digite o código do ticket:");                              
                                }  
                                cod = Integer.parseInt(codigo);
                                Ticket tickete = opTicket.buscarTicket(tickets, cod);                                                              
                                if(tickete == null) {
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
                                if(face == 0) { 
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    interfaces.mensagem("\n///////////////////////////////////////////////////");
                                    interfaces.mensagem(tickete.toString());
                                    if(tickete.getFim() != null){
                                        interfaces.mensagem("Fim do ticket: " + tickete.getFim().format(dataBonitinha));
                                    }
                                    interfaces.mensagem("///////////////////////////////////////////////////\n");
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    interfaces.mensagem(tickete.toString());
                                    if(tickete.getFim() != null){                                     
                                        interfaces.mensagem("Fim do ticket: " + tickete.getFim().format(dataBonitinha));
                                    }
                                }
                            break;
                            case 5: /* Listar tickets ativos */
                                List<String> lista = opTicket.ListarTicketAtivo(tickets);
                                if(face == 0) {
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    for(String s : lista){
                                        interfaces.mensagem(s);
                                        interfaces.mensagem("///////////////////////////////////////////////////");
                                    }
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    for(String s : lista){
                                        interfaces.mensagem(s);
                                    } 
                                }
                            break; 
                            case 6: /* Listar vagas cadastradas */                               
                                List<String> listaV = opVaga.listarVagasCadastradas(vagas);
                                if(face == 0) {
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    for(String s : listaV){
                                        interfaces.mensagem(s);
                                        interfaces.mensagem("///////////////////////////////////////////////////");
                                    }
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    for(String s : listaV){
                                        interfaces.mensagem(s);
                                    } 
                                }
                            break; 
                            case 7:
                            break;
                            default:
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    interfaces.mensagem("Insira uma opção válida!");               
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    interfaces.mensagem("Insira uma opção válida!");                               
                                 }                           
                            break;                      
                        }                        
                    }while(opcao3 != 7);                                                                        
                break;        
                case 5:
                    String iniS, fimS;
                    if(face == 0){
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        interfaces.mensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                        iniS = interfaces.receberString("Data inicial:");
                        iniS = iniS + " 00:00:00";
                        fimS = interfaces.receberString("Data Final:");
                        fimS = fimS + " 23:59:59";
                        LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                        LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                        double resultado = opTicket.FaturadoPeriodo(tickets, inicio, fim);
                        interfaces.mensagem("\nNesse período foi/foram faturado/s: "  + dinheiro.format(resultado) + "\n");                      
                    }
                    else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        interfaces.mensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                        iniS = interfaces.receberString("Data inicial:");
                        iniS = iniS + " 00:00:00";
                        fimS = interfaces.receberString("Data Final:");
                        fimS = fimS + " 23:59:59";
                        LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                        LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                        double resultado = opTicket.FaturadoPeriodo(tickets, inicio, fim);
                        interfaces.mensagem("\nNesse período foi/foram faturado/s: "  + dinheiro.format(resultado) + "\n");                              
                    }                                         
                break;
                case 6:
                break;                    
                default:
                    if(face == 0){
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        interfaces.mensagem("Insira uma opção válida!");               
                    }
                    else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        interfaces.mensagem("Insira uma opção válida!");                               
                    }               
                break;
            }
        }while (opcao != 6);
        scanner.close();
    }    
}
