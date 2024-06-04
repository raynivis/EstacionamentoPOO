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
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
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
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    int opcao;
    int opcao3;
    
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
   
    
    /* Método geral das opções do menu que será chamado na Classe Main Estacionamento e permitirá que todo o
    menu seja exibido ao usuário */
    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {  
        do {
                              
            opTicket.verificarTicketsMensalista30dias(tickets);
            
            if(inter instanceof InterfaceTerminal){
                inter = (InterfaceTerminal) inter;
                opcao = inter.imprimeInicio();
            }
            else {
//              inter = (InterfaceGrafica) inter;
                opcao = inter.imprimeInicio();
            }
                    
            switch (opcao) {
                case 1:
                    interCliente.opcoesCliente(clientes, vagas, tickets, tarifas, inter);
                break;    
                case 2:
                    interVaga.opcoesVaga(clientes, vagas, tickets, tarifas, inter);
                break;
                case 3:
                    interEstaciona.opcoesEstacionamento(clientes, vagas, tickets, tarifas, inter);
                break;
                case 4:
                    do {                      
                        opTicket.verificarTicketsMensalista30dias(tickets);
                        opcao3 = inter.imprimeCadastroGeral();          
                        switch(opcao3) {
                            case 1:                               
                                int cod;
                                String codigo = inter.receberString("Digite o codigo do ticket:");
                                cod = Integer.parseInt(codigo);                      
                                Ticket testeT = opTicket.buscarTicket(tickets, cod);
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
                                    lucro = ((TicketHorista)testeT).totalFaturadoTicket();
                                    testeT.setFim(null); 
                                }
                                else {
                                    lucro = ((TicketMensalista)testeT).totalFaturadoTicket();
                                }
                                
                                inter.mensagem("O lucro desse ticket foi de " + dinheiro.format(lucro));                                                                  
                            break;
                            case 2: /*consultar veiculo*/
                                String placa = inter.receberString("Digite a placa do veículo:");
                                                    
                                Veiculo veicule = opCliente.verificarVeiculo(clientes, placa);
                                if(veicule == null) {
                                    inter.mensagem("Veiculo não encontrado!");     
                                    break;
                                }
                                
                                if(inter instanceof InterfaceTerminal){                                                                           
                                        inter.mensagem("\n///////////////////////////////////////////////////");
                                        inter.mensagem(veicule.toString()); 
                                        inter.mensagem("///////////////////////////////////////////////////\n");
                                }
                                else {                                   
                                    inter.mensagem(veicule.toString());                                 
                                }                                                              
                            break; 
                            case 3: /*consultar Tarifa*/
                                String tipe = inter.receberString("Digite o tipo de tarifa (Horista ou Mensalista): ");;
                                String data = inter.receberString("Digite a data de início da tarifa (em dia/mês/ano horas:minutos):");
                                                                                                                                               
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps, inter);
                                                            
                                if(tipe.equalsIgnoreCase("HORISTA") ){
                                    TarifaHorista tarife = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                    if(tarife == null){                                       
                                        inter.mensagem("Erro: Tarifa não encontrada!"); 
                                        break;
                                    }
                                     if(inter instanceof InterfaceTerminal) {                                     
                                        inter.mensagem("\n///////////////////////////////////////////////////");
                                        inter.mensagem(tarife.toString());
                                        inter.mensagem("\nDia/s da Semana: ");
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            inter.mensagem(ds.toString() + " ");
                                        }
                                        inter.mensagem("\nTipo/s de veículo:");
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            inter.mensagem(tv.toString() + " ");
                                        }
                                     
                                        inter.mensagem("///////////////////////////////////////////////////\n");
                                    } else {                                                                                                                 
                                        String diaSemana = "\nDia/s da Semana: ";
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            diaSemana = diaSemana + (ds.toString() + " ");
                                        }
                                       
                                        String veiculoo = "\nTipo/s de veículo:";                                      
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            veiculoo = veiculoo + (tv.toString() + " ");
                                        }
                                        inter.mensagem(tarife.toString() + diaSemana + veiculoo);
                                    }                               
                                } 
                                else{
                                    TarifaMensalista tarife = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                    if(tarife == null){
                                        inter.mensagem("Erro: Tarifa não encontrada!");
                                        break;
                                    }
                                    if(inter instanceof InterfaceTerminal) {
                                        
                                        inter.mensagem("\n///////////////////////////////////////////////////");
                                        inter.mensagem(tarife.toString());
                                        inter.mensagem("\nDia/s da Semana: ");
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            inter.mensagem(ds.toString() + " ");
                                        }
                                        inter.mensagem("\nTipo/s de veículo:");
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            inter.mensagem(tv.toString() + " ");
                                        }
                                        inter.mensagem("///////////////////////////////////////////////////\n");
                                    } else {
                                        String diaSemana = "\nDia/s da Semana: ";
                                        for(DiaSemana ds : tarife.getDiasSemana()){
                                            diaSemana = diaSemana + (ds.toString() + " ");
                                        }
                                       
                                        String veiculoo = "\nTipo/s de veículo:";                                      
                                        for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                            veiculoo = veiculoo + (tv.toString() + " ");
                                        }
                                        inter.mensagem(tarife.toString() + diaSemana + veiculoo);
                                    }
                                }  
                                          
                            break; 
                            case 4: /*consultar Ticket*/                                                            
                                codigo = inter.receberString("Digite o código do ticket:");
                                cod = Integer.parseInt(codigo);
                                Ticket tickete = opTicket.buscarTicket(tickets, cod);                                                              
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
                                List<String> lista = opTicket.ListarTicketAtivo(tickets);
                                for(String s : lista){
                                        inter.mensagem(s);
                                }
                            break; 
                            case 6: /* Listar vagas cadastradas */                               
                                List<String> listaV = opVaga.listarVagasCadastradas(vagas);
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
                break;        
                case 5:                    
                    inter.mensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                    String iniS = inter.receberString("Data inicial:");
                    iniS = iniS + " 00:00:00";
                    String fimS = inter.receberString("Data Final:");
                    fimS = fimS + " 23:59:59";
                    LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                    LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                    double resultado = opTicket.FaturadoPeriodo(tickets, inicio, fim);
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
}