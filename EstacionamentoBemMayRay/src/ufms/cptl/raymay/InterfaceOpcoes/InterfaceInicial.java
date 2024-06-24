/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
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
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuCadastroGeral;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuInicial;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ErroDigitacaoException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.TarifaException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.TicketException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.VeiculoException;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTarifa;
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
    OperacoesTarifa opTarifa = new OperacoesTarifa();
    InterfaceException ex = new InterfaceException();
    
    InterfaceOpcaoCliente interCliente = new InterfaceOpcaoCliente();
    InterfaceOpcaoVaga interVaga = new InterfaceOpcaoVaga();
    InterfaceOpcaoEstacionamento interEstaciona = new InterfaceOpcaoEstacionamento();
    
    AuxiliarInterfarceListaSemanas listasVS = new AuxiliarInterfarceListaSemanas();
    
    int opcao;
    int opcao3;
    
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
   
    
    /* Método geral das opções do menu que será chamado na Classe Main Estacionamento e permitirá que todo o
    menu seja exibido ao usuário */
    public void realizarOpcoesIniciais(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {  
        do {
                              
            opTicket.verificarMensalista30dias(tickets);
            
            opcao = inter.imprimirMenu(InterMenuInicial.class, "Menu Inicial");
                    
            switch (opcao) {
                case 1: /*Gerenciar clientes*/
                    interCliente.realizarOpcoesCliente(clientes, vagas, tickets, tarifas, inter);
                break;    
                case 2: /*Gerenciar vagas*/
                    interVaga.realizarOpcoesVaga(clientes, vagas, tickets, tarifas, inter);
                break;
                case 3: /*Gerenciar estacionamento*/
                    interEstaciona.realizarOpcoesEstacionamento(clientes, vagas, tickets, tarifas, inter);
                break;
                case 4: /*Cadastros gerais*/
                     realizarOpcoesCadastroGeral(clientes, vagas, tickets, tarifas, inter);                                                                 
                break;        
                case 5:                    
                    inter.imprimirMensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                    LocalDateTime inicio = inter.receberData("Data inicial:", LocalTime.MIDNIGHT);
                    LocalDateTime fim = inter.receberData("Data Final:", LocalTime.of(23,59,59));
                    double resultado = opTicket.calcularTotalFaturadoPeriodo(tickets, inicio, fim);
                    inter.imprimirMensagem("\nNesse período foi/foram faturado/s: "  + dinheiro.format(resultado) + "\n");                                     
                break;

                case 6:
                    List<String> listaVeiculosFaturado = opTicket.calcularTotalFaturadoVeiculo(clientes);
                    for(String c : listaVeiculosFaturado) {
                        inter.imprimirMensagem(c);
                    }   
                break;  
                case 7:
                break;
                default:
                     inter.imprimirException("Insira uma opção válida!");              
                break;
            }
        }while (opcao != 7);
    }

    
    private void realizarOpcoesCadastroGeral(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {
        do {                      
            opTicket.verificarMensalista30dias(tickets);
            opcao3 = inter.imprimirMenu(InterMenuCadastroGeral.class, "Menu Cadastro Geral"); 
            try {
                switch(opcao3) { /*ticket tarifa*/
                    case 1:                               
                        int cod;
                        String codigo = inter.receberString("Digite o codigo do ticket:");
                        cod = Integer.parseInt(codigo);                      
                        Ticket testeT = opTicket.buscar(tickets, cod);
                        if(testeT == null) {  
                            throw ex.new TicketException("Ticket não encontrado!");
                        } 
                        if(testeT.getStatus().equals(Operando.DESATIVO)) {
                            throw ex.new TicketException("Não é permitido testar um ticket finalizado!");                 
                        }
                        double lucro;
                        if(testeT instanceof TicketHorista){
                            String data = inter.receberString("Digite a data de finalização do ticket (em dia/mês/ano horas:minutos:segundos):");                                
                            LocalDateTime dataFinal = LocalDateTime.parse(data, dataBonitinhaComSegundos); 
                            testeT.setFim(dataFinal); 
                            testeT.faturar();
                            lucro = ((TicketHorista)testeT).getFaturado();
                            testeT.setFim(null); 
                        }
                        else {
                            lucro = ((TicketMensalista)testeT).getFaturado();
                        }

                        inter.imprimirMensagem("O lucro desse ticket foi de " + dinheiro.format(lucro));                                                                  
                    break;
                    case 2: /*consultar veiculo*/
                        String placa = inter.receberString("Digite a placa do veículo:");

                        Veiculo veicule = opCliente.buscarVeiculo(clientes, placa);
                        if(veicule == null) {
                            throw ex.new VeiculoException("Veiculo não encontrado!");
                        }

                        inter.imprimirMensagem(veicule.toString());                                                           
                    break; 
                    case 3: /*consultar Tarifa*/
                        String tipe = inter.receberString("Digite o tipo de tarifa (Horista ou Mensalista): ");;
                        String data = inter.receberString("Digite a data de início da tarifa (em dia/mês/ano horas:minutos):");

                        if(tipe.equalsIgnoreCase("HORISTA") ){
                            List<DiaSemana> dias = new ArrayList<>();                        
                            listasVS.GerenciarListaDiasSemanas(dias, inter);
                            TarifaHorista tarife = opTarifa.buscarHorista(tarifas, data, dias);
                            if(tarife == null){                                       
                                throw ex.new TarifaException("Tarifa não encontrada!");
                            }
                            String diaSemana = "\nDia/s da Semana: ";
                            for(DiaSemana ds : tarife.getDiasSemana()){
                                diaSemana = diaSemana + (ds.toString() + " ");
                            }                                     
                            inter.imprimirMensagem(tarife.toString() + diaSemana);                              
                        } 
                        else if(tipe.equalsIgnoreCase("MENSALISTA")){
                            TarifaMensalista tarife = opTarifa.buscarMensalista(tarifas, data);
                            if(tarife == null){
                                throw ex.new TarifaException("Tarifa não encontrada!");
                            }
                            inter.imprimirMensagem(tarife.toString());
                        } else {
                            throw ex.new ErroDigitacaoException("São valídas somente as palavras horista ou mensalista!");
                        }

                    break; 
                    case 4: /*consultar Ticket*/                                                            
                        codigo = inter.receberString("Digite o código do ticket:");
                        cod = Integer.parseInt(codigo);
                        Ticket tickete = opTicket.buscar(tickets, cod);                                                              
                        if(tickete == null) {
                            throw ex.new TicketException("Ticket não encontrado!"); 
                        }  
                        inter.imprimirMensagem(tickete.toString());
                        if(tickete.getFim() != null){                                     
                            inter.imprimirMensagem("Fim do ticket: " + tickete.getFim().format(dataBonitinha));
                        }
                    break;
                    case 5: /* Listar tickets ativos */
                        List<String> lista = opTicket.listarAtivos(tickets);
                        for(String s : lista){
                            inter.imprimirMensagem(s);
                        }
                    break; 
                    case 6: /* Listar vagas cadastradas */                               
                        List<String> listaV = opVaga.listarCadastros(vagas);
                        for(String s : listaV){
                            inter.imprimirMensagem(s);                                   
                        }
                    break; 
                    case 7:
                    break;
                    default:
                        inter.imprimirException("Insira uma opção válida!");                              
                    break;                      
                }  
            } catch (TicketException | VeiculoException | ErroDigitacaoException | TarifaException e){
                inter.imprimirException(e.getMessage());
            }    
        }while(opcao3 != 7);        
    }
}
