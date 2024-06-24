/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuTarifa;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ErroDigitacaoException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.TarifaException;
import ufms.cptl.raymay.Operacoes.OperacoesTarifa;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoTarifa {
    /* método para a opção selecionada de gerenciamento de tarifas. Foi criada uma nova classe devido ao tamanho
    da classe InterfaceOpcaoEstacionamento, para melhorar a vizualização do codigo */
    
    OperacoesTicket opTicket = new OperacoesTicket();
    OperacoesTarifa opTarifa = new OperacoesTarifa();
    InterfaceException ex = new InterfaceException();
    AuxiliarInterfarceListaSemanas listasVS = new AuxiliarInterfarceListaSemanas();
    DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    int opcao3;
    
    public void realizarOpcoesTarifa(List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {
        do{ 
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */                       
            opTicket.verificarMensalista30dias(tickets);
            opcao3 = inter.imprimirMenu(InterMenuTarifa.class, "Menu Tarifa");
            try {
                switch(opcao3){
                    case 1: /*adicionar tarifa*/  
                        inter.imprimirMensagem("Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos) :");
                        LocalDateTime inicio = inter.receberData("Se deseja cadastrar uma tarifa instantânea, digite: Agora", ":00"); 
                                                                                                          
                        if(inicio.isBefore(LocalDateTime.now())) { 
                            throw ex.new TarifaException("Não é possível cadastrar uma tarifa no passado!");
                        }
                        
                        String tipo; 
                        tipo = inter.receberString("Digite o Tipo de Tarifa que deseja cadastrar (Horista ou Mensalista):");

                        if(tipo.equalsIgnoreCase("HORISTA")){    
                            List<DiaSemana> diaSmns = new  ArrayList<>();                                                     
                            if(listasVS.GerenciarListaDiasSemanas(diaSmns, inter) == 1) {
                                double precoPrimeira = inter.receberDouble("Digite o valor da primeira hora: ");
                                double precoHora = inter.receberDouble("Digite o valor das horas subsequentes: ");

                                if(opTarifa.buscarHorista(tarifas, inicio, diaSmns) != null){                                      
                                    throw ex.new TarifaException("Você ja cadastrou uma Tarifa desse tipo para essa data!");
                                }
                                TarifaHorista novaTarifa = new TarifaHorista(precoPrimeira, precoHora, inicio, diaSmns);
                                tarifas.add(novaTarifa);
                                inter.imprimirMensagem("Tarifa Horista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");   
                            }   
                        }                               
                        else if(tipo.equalsIgnoreCase("MENSALISTA")) {      
                            double preco = inter.receberDouble("Digite o valor da tarifa: ");

                            if(opTarifa.buscarMensalista(tarifas, inicio) != null){
                                throw ex.new TarifaException("Você ja cadastrou uma Tarifa desse tipo para essa data!");
                            }
                            TarifaMensalista novaTarifa = new TarifaMensalista(preco, inicio);
                            tarifas.add(novaTarifa);
                            inter.imprimirMensagem("Tarifa Mensalista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");     
                        } else {
                            throw ex.new ErroDigitacaoException("São valídas somente as palavras horista ou mensalista!");
                        }                                                           
                    break; 
                    case 2: /*excluir tarifa*/       
                        LocalDateTime dataExc = inter.receberData("Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos):", ":00");                                      
                        tipo = inter.receberString("Digite o Tipo de Tarifa que deseja excluir (Horista ou Mensalista):");

                        if(tipo.equalsIgnoreCase("HORISTA")){ 
                            List<DiaSemana> dias = new ArrayList<>();                           
                            if(listasVS.GerenciarListaDiasSemanas(dias, inter) == 0){
                                TarifaHorista tarifaEx = opTarifa.buscarHorista(tarifas, dataExc, dias);
                                if(tarifaEx == null){                                        
                                    throw ex.new TarifaException("Tarifa não encontrada!");
                                }

                                if(opTarifa.procurar(tarifaEx, tickets) == true) {        
                                    throw ex.new TarifaException("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");
                                }
                                tarifas.remove(tarifaEx); 
                            }
                        }                               
                        else if(tipo.equalsIgnoreCase("MENSALISTA")) {                            
                            TarifaMensalista tarifaEx = opTarifa.buscarMensalista(tarifas, dataExc);
                             if(tarifaEx == null){
                                throw ex.new TarifaException("Tarifa não encontrada!");
                             }
                             /*Ver se ta fufando*/
                             if(opTarifa.procurar(tarifaEx, tickets) == true) {
                                throw ex.new TarifaException("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");
                             }
                             tarifas.remove(tarifaEx);
                        } else {
                            throw ex.new ErroDigitacaoException("São valídas somente as palavras horista ou mensalista!");
                        }

                        inter.imprimirMensagem("Tarifa removida com Sucesso!");    

                    break;
                    case 3: /*editar tarifa*/                  
                        LocalDateTime dataEdit = inter.receberData("Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos):", ":00");                                    

                        tipo = inter.receberString("Digite o Tipo de Tarifa que deseja editar (Horista ou Mensalista):"); 
                        if(tipo.equalsIgnoreCase("HORISTA")){   
                            List<DiaSemana> dias = new ArrayList<>();                           
                            if(listasVS.GerenciarListaDiasSemanas(dias, inter) == 0){
                                TarifaHorista tarifaEx = opTarifa.buscarHorista(tarifas, dataEdit, dias);
                                if(tarifaEx == null){
                                    throw ex.new TarifaException("Tarifa não encontrada!");
                                }
                                String novaData = inter.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                                tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));

                                double novaPH = inter.receberDouble("Digite o novo valor da primeira hora: ");                                       
                                tarifaEx.setValorPrimeiraHora(novaPH);

                                double novaHS = inter.receberDouble("Digite o novo valor das horas subsequentes: "); 
                                tarifaEx.setValorHoraSubsequente(novaHS); 
                            }    
                        }                               
                        else if(tipo.equalsIgnoreCase("MENSALISTA")){                            
                            TarifaMensalista tarifaEx = opTarifa.buscarMensalista(tarifas, dataEdit);
                            if(tarifaEx == null){
                                throw ex.new TarifaException("Tarifa não encontrada!");
                            }

                            String novaData = inter.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                            tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
    
                            double novaH = inter.receberDouble("Digite o novo valor da tarifa: ");
                            tarifaEx.setValorUnico(novaH);                                  

                        }  else {
                            throw ex.new ErroDigitacaoException("São valídas somente as palavras horista ou mensalista!");
                        }                                                                                                                         
                        inter.imprimirMensagem("Tarifa editada com Sucesso!");  
                    break;
                    case 4: /*imprimir tarifas*/                                
                        List<String> lista = opTarifa.listarCadastradas(tarifas);
                        for(String s : lista){
                            inter.imprimirMensagem(s);
                        }
                    break;
                    case 5:
                    break;
                    default:
                        inter.imprimirException("Insira uma opção válida!"); 
                    break;
                }
            } catch (ErroDigitacaoException | TarifaException e){
                inter.imprimirException(e.getMessage());
            }         
        }while(opcao3 != 5);
    }
}
