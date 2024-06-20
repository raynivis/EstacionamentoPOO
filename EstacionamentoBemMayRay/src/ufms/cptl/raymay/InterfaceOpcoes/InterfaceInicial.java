/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.text.NumberFormat;
import java.time.LocalDateTime;
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
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
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
    
    AuxiliarInterfarceListaSemanas listasVS = new AuxiliarInterfarceListaSemanas();
    
    int opcao;
    int opcao3;
    
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
   
    
    /* Método geral das opções do menu que será chamado na Classe Main Estacionamento e permitirá que todo o
    menu seja exibido ao usuário */
    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {  
        do {
                              
            opTicket.verificarMensalista30dias(tickets);
            
            opcao = inter.imprimeInicio();
                    
            switch (opcao) {
                case 1: /*Gerenciar clientes*/
                    interCliente.opcoesCliente(clientes, vagas, tickets, tarifas, inter);
                break;    
                case 2: /*Gerenciar vagas*/
                    interVaga.opcoesVaga(clientes, vagas, tickets, tarifas, inter);
                break;
                case 3: /*Gerenciar estacionamento*/
                    interEstaciona.opcoesEstacionamento(clientes, vagas, tickets, tarifas, inter);
                break;
                case 4: /*Cadastros gerais*/
                     cadastroGeraisOpcoes(clientes, vagas, tickets, tarifas, inter);                                                                 
                break;        
                case 5:                    
                    inter.mensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                    String iniS = inter.receberString("Data inicial:");
                    iniS = iniS + " 00:00:00";
                    String fimS = inter.receberString("Data Final:");
                    fimS = fimS + " 23:59:59";
                    LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                    LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                    double resultado = opTicket.calcularTotalFaturadoPeriodo(tickets, inicio, fim);
                    inter.mensagem("\nNesse período foi/foram faturado/s: "  + dinheiro.format(resultado) + "\n");                                     
                break;
                case 6:
                break;                    
                default:
                     inter.mensagem("Insira uma opção válida!");              
                break;
            }
        }while (opcao != 6);
    }

    
    private void cadastroGeraisOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter){
        do {                      
            opTicket.verificarMensalista30dias(tickets);
            opcao3 = inter.imprimeCadastroGeral();          
            switch(opcao3) { /*ticket tarifa*/
                case 1:                               
                    int cod;
                    String codigo = inter.receberString("Digite o codigo do ticket:");
                    cod = Integer.parseInt(codigo);                      
                    Ticket testeT = opTicket.buscar(tickets, cod);
                    if(testeT == null) {                                   
                        inter.mensagem("Erro: Ticket não encontrado!");                         
                        break;
                    } 
                    if(testeT.getStatus().equals(Operando.DESATIVO)) {
                        inter.mensagem("Erro: Não é permetido testar um ticket finalizado!");                           
                        break;                                   
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

                    inter.mensagem("O lucro desse ticket foi de " + dinheiro.format(lucro));                                                                  
                break;
                case 2: /*consultar veiculo*/
                    String placa = inter.receberString("Digite a placa do veículo:");

                    Veiculo veicule = opCliente.buscarVeiculo(clientes, placa);
                    if(veicule == null) {
                        inter.mensagem("Veiculo não encontrado!");     
                        break;
                    }

                    inter.mensagem(veicule.toString());                                                           
                break; 
                case 3: /*consultar Tarifa*/
                    String tipe = inter.receberString("Digite o tipo de tarifa (Horista ou Mensalista): ");;
                    String data = inter.receberString("Digite a data de início da tarifa (em dia/mês/ano horas:minutos):");

                    if(tipe.equalsIgnoreCase("HORISTA") ){
                        List<DiaSemana> dias = new ArrayList<>();                        
                        listasVS.OperacaoListaDiasSemanas(dias, inter);
                        TarifaHorista tarife = opTicket.buscarTarifaHorista(tarifas, data, dias);
                        if(tarife == null){                                       
                            inter.mensagem("Erro: Tarifa não encontrada!"); 
                            break;
                        }
                        String diaSemana = "\nDia/s da Semana: ";
                        for(DiaSemana ds : tarife.getDiasSemana()){
                            diaSemana = diaSemana + (ds.toString() + " ");
                        }                                     
                        inter.mensagem(tarife.toString() + diaSemana);                              
                    } 
                    else{
                        TarifaMensalista tarife = opTicket.buscarTarifaMensalista(tarifas, data);
                        if(tarife == null){
                            inter.mensagem("Erro: Tarifa não encontrada!");
                            break;
                        }
                        inter.mensagem(tarife.toString());
                    }  

                break; 
                case 4: /*consultar Ticket*/                                                            
                    codigo = inter.receberString("Digite o código do ticket:");
                    cod = Integer.parseInt(codigo);
                    Ticket tickete = opTicket.buscar(tickets, cod);                                                              
                    if(tickete == null) {
                        inter.mensagem("Erro: Ticket não encontrado!");                      
                        break;
                    }  
                    inter.mensagem(tickete.toString());
                    if(tickete.getFim() != null){                                     
                        inter.mensagem("Fim do ticket: " + tickete.getFim().format(dataBonitinha));
                    }
                break;
                case 5: /* Listar tickets ativos */
                    List<String> lista = opTicket.listarAtivos(tickets);
                    for(String s : lista){
                            inter.mensagem(s);
                    }
                break; 
                case 6: /* Listar vagas cadastradas */                               
                    List<String> listaV = opVaga.listarCadastros(vagas);
                    for(String s : listaV){
                            inter.mensagem(s);                                   
                    }
                break; 
                case 7:
                break;
                default:
                    inter.mensagem("Insira uma opção válida!");                              
                break;                      
            }                        
        }while(opcao3 != 7);        
    }
}
