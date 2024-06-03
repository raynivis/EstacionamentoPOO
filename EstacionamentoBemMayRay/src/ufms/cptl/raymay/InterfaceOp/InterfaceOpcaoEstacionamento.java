/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
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
public class InterfaceOpcaoEstacionamento{
    OperacoesVagas opVaga = new OperacoesVagas(); 
    OperacoesCliente opCliente = new OperacoesCliente();
    OperacoesTicket opTicket = new OperacoesTicket();
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    ItensMenu menue = new ItensMenu(); /* menue = Menu de gerencia do Estacionamento */
    ItensMenu menut = new ItensMenu(); /*menut = Menu de gerencia das Tarifas */
    
    int opcao2;
    int opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    
    /* Método geral das opções do estacionamento que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao estacionamento */
    public void opcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter, int face) {
        do{
            /* Utiliza o método criado em OpcaoEstacionamento no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            if(face == 0){
                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                opcao2 = interfaces.imprimeEstacionamento();
            }
            else {
                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                opcao2 = interfaces.imprimeEstacionamento();
            }
            switch (opcao2) {
                case 1:
                    /*estacionar*/
                    
                    if(tarifas.isEmpty() == true) { /*verificando se tem tarifa*/
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Cadastre uma tarifa primeiro!!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Cadastre uma tarifa primeiro!");                               
                        }                         
                        break;
                    }
                    String placa;
                    if(face == 0){
                       InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                       placa = interfaces.receberString("Digite a placa do veículo a ser estacionado:");
                         
                    }
                    else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        placa = interfaces.receberString("Digite a placa do veículo a ser estacionado");                   
                    }    
                                
                    Veiculo veiculo = opCliente.verificarVeiculo(clientes, placa);
                    
                    if(veiculo == null) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Erro: Veículo não econtrado!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Erro: Veículo não econtrado!");                               
                        }                       
                       break;
                    }
                    
                    Ticket veriTicket = opTicket.verificaTicketVeiculo(clientes, placa, tickets);
                    if(veriTicket != null){
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Erro: O veículo já está estacionado!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Erro: O veículo já está estacionado!");                               
                       }                       
                       break;                     
                    }                                      
                    
                    if(opTicket.verificarEstacionarTicketMensalistaParaVeiculo(tickets, veiculo)){ /*verificando o veiculo possui um ticket mensalista*/
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Veículo Mensalista estacionado com sucesso!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Veículo Mensalista estacionado com sucesso!");                               
                       }                            
                        break;
                    }
                    String numeroRua, ruaVaga;
                    Vaga vaga;
                    if(face == 0){
                       InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                       numeroRua = interfaces.receberString("Digite o número da vaga que pretende ser estacionada:");
                       int rua = Integer.parseInt(numeroRua);
                       ruaVaga =   interfaces.receberString("Digite a rua da vaga que pretende ser estacionada:");
                       vaga = opVaga.consultarVaga(vagas, rua, ruaVaga);
                    }
                    else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        numeroRua = interfaces.receberString("Digite o número da vaga que pretende ser estacionada:");
                        int rua = Integer.parseInt(numeroRua);
                        ruaVaga = interfaces.receberString("Digite a rua da vaga que pretende ser estacionada:");
                        vaga = opVaga.consultarVaga(vagas, rua, ruaVaga);
                    }   
                    
                    if(vaga == null) { 
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Erro: Vaga não econtrada!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Erro: Vaga não econtrada!");                               
                       }                                    
                       break;
                    }
                    
                     if(veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Erro: O tipo de veículo não é compatível com o tipo de vaga!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Erro: O tipo de veículo não é compatível com o tipo de vaga!");                               
                       }                          
                       break;
                    }
                    
                    if(vaga.getStatus() != VagaStatus.DISPONIVEL) {
                        if(vaga.getStatus() == VagaStatus.OCUPADA) { 
                            if(face == 0){
                                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                interfaces.mensagem("Erro: A vaga possui um ticket de estacionamento ATIVO (OCUPADA)!");               
                            }
                            else {
                                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                interfaces.mensagem("Erro: A vaga possui um ticket de estacionamento ATIVO (OCUPADA)!");                               
                           }                             
                            break;
                        }
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Erro: Vaga indisponível!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Erro: Vaga indisponível!");                               
                       }                          
                       break;
                    }                
                    String tipoTi; 
                    if(face == 0){
                       InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                       tipoTi = interfaces.receberString("O cliente deseja estacionar como Horista ou Mensalista?");
                         
                    }
                    else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        tipoTi = interfaces.receberString("O cliente deseja estacionar como Horista ou Mensalista?");                   
                    }    
                    /*Achar a tarifa que pertence ao ticket*/                  
                    Tarifa atual = opTicket.tarifaProxima(tarifas, LocalDateTime.now(), veiculo, tipoTi);

                    if(atual == null){
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Erro: Não existe uma tarifa para esse tipo de vaga nesse período!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Erro: Não existe uma tarifa para esse tipo de vaga nesse período!");                               
                        }  
                        break;
                    }
                    
                    if(tipoTi.equalsIgnoreCase("HORISTA")){
                        TicketHorista novoTicket = new TicketHorista(atual, veiculo, vaga);
                        tickets.add(novoTicket);
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Ticket Horista de código " + novoTicket.getCodigo() + " criado com sucesso!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                           interfaces.mensagem("Ticket Horista de código " + novoTicket.getCodigo() + " criado com sucesso!");                              
                        }                        
                    }
                    else {
                        TicketMensalista novoTicket = new TicketMensalista(atual, veiculo, vaga);
                        tickets.add(novoTicket);
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Ticket Mensalista de código " + novoTicket.getCodigo() + " criado com sucesso!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                           interfaces.mensagem("Ticket Mensalista de código " + novoTicket.getCodigo() + " criado com sucesso!");                              
                        }                        
                    }                                                     
                break;    
    
                case 2:
                    /*retirar*/
                    if(face == 0){
                       InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                       placa = interfaces.receberString("Digite a placa do veículo que deseja retirar:");
                         
                    }
                    else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        placa = interfaces.receberString("Digite a placa do veículo que deseja retirar:");                   
                    } 
                    
                    Veiculo veiculoRetirar = opCliente.verificarVeiculo(clientes, placa);
                    if(veiculoRetirar == null) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Veiculo não encontrado nos Clientes!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                           interfaces.mensagem("Veiculo não encontrado nos Clientes!");                              
                        }   
                        break;
                    }
                    
                    if(opTicket.retirar(tickets, veiculoRetirar) == false) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("O Veiculo não pode ser retirado!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                           interfaces.mensagem("O Veiculo não pode ser retirado!");                              
                        }                         
                    } else {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Veiculo Retirado com sucesso!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                           interfaces.mensagem("Veiculo Retirado com sucesso!");                              
                        }                
                    }
                break;    
    
                case 3:
                    /*listar todas as vagas disponíveis do estacionamento*/
                     List<String> lista = opVaga.listarVagasDisponiveis(vagas);
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            for(String s : lista){
                                interfaces.mensagem(s);
                            }
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            for(String s : lista){
                                interfaces.mensagem(s);
                            } 
                        }
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                    do{ 
                        /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                        de linhas das Classes da interface */                       
                        opTicket.verificarTicketsMensalista30dias(tickets);
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            opcao3 = interfaces.imprimeTarifa();
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            opcao3 = interfaces.imprimeTarifa();
                        }
                        switch(opcao3){
                            case 1: /*adicionar tarifa*/                                                      
                                String tipo; 
                                if(face == 0){
                                   InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                   tipo = interfaces.receberString("Digite o Tipo de Tarifa que deseja cadastrar (Horista ou Mensalista):");

                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    tipo = interfaces.receberString("Digite o Tipo de Tarifa que deseja cadastrar (Horista ou Mensalista)");                   
                                }
                                
                                String data;
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    interfaces.mensagem("Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos) :");
                                    data = interfaces.receberString("Se deseja cadastrar uma tarifa instantânea, digite: Agora");
                                 }
                                 else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    interfaces.mensagem("Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos) :");
                                    data = interfaces.receberString("Se deseja cadastrar uma tarifa instantânea, digite: Agora");
                                }       
                  
                                LocalDateTime inicio;
                                
                                if(data.toUpperCase().equals("AGORA")) {
                                    inicio = LocalDateTime.now();
                                } else {
                                    inicio = LocalDateTime.parse(data, dataFormata);
                                    if(inicio.isBefore(LocalDateTime.now())) {                                       
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Nao é possível cadastrar uma tarifa no passado!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Nao é possível cadastrar uma tarifa no passado!");                              
                                        }    
                                        break;
                                    }
                                }                                                              
                                List<DiaSemana> diaSmns = new  ArrayList<>();                        
                                List<TipoVeiculo> listaTps = new  ArrayList<>();
                                listasVS.OperacaoListaTVDS(diaSmns, listaTps, inter, face);
                                 
                                if(tipo.equalsIgnoreCase("HORISTA")){                                                                                                                                  
                                    String priHora;
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;                                       
                                        priHora = interfaces.receberString("Digite o valor da primeira hora:");
                                     }
                                     else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;                               
                                        priHora = interfaces.receberString("Digite o valor da primeira hora:");
                                    }    
                                    double precoPrimeira =  Double.parseDouble(priHora);
                                    
                                    
                                    String subHora;
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;                                       
                                        subHora = interfaces.receberString("Digite o valor das horas subsequentes:");
                                     }
                                     else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;                               
                                        subHora = interfaces.receberString("Digite o valor das horas subsequentes:");
                                    }    
                                    double precoHora = Double.parseDouble(subHora);
                                    
                                    if(opTicket.buscarTarifaHorista(tarifas, inicio.format(dataFormata), diaSmns, listaTps) != null){                                      
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");                              
                                        }    
                                    break;
                                    }
                                    TarifaHorista novaTarifa = new TarifaHorista(precoPrimeira, precoHora, inicio, diaSmns, listaTps);
                                    tarifas.add(novaTarifa);
                                    if(face == 0){
                                       InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                       interfaces.mensagem("Tarifa Horista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");              
                                    }
                                    else {
                                       InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                       interfaces.mensagem("Tarifa Horista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");                              
                                    }      
                                }                               
                                else{                                                              
                                    String Hora;
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;                                       
                                        Hora = interfaces.receberString("Digite o valor da Tarifa:");
                                     }
                                     else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;                               
                                        Hora = interfaces.receberString("Digite o valor da Tarifa:");
                                    }    
                                    double preco = Double.parseDouble(Hora);
                                    
                                    if(opTicket.buscarTarifaMensalista(tarifas, inicio.format(dataFormata), diaSmns, listaTps) != null){
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");                              
                                        }    
                                    break;
                                    }
                                    TarifaMensalista novaTarifa = new TarifaMensalista(preco, inicio, diaSmns, listaTps);
                                    tarifas.add(novaTarifa);
                                    if(face == 0){
                                       InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                       interfaces.mensagem("Tarifa Mensalista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");              
                                    }
                                    else {
                                       InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                       interfaces.mensagem("Tarifa Mensalista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");                              
                                    }      
                                }                                                              
                            break; 
                            case 2: /*excluir tarifa*/                                                            
                                if(face == 0){
                                   InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                   tipo = interfaces.receberString("Digite o Tipo de Tarifa que deseja excluir (Horista ou Mensalista):");

                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    tipo = interfaces.receberString("Digite o Tipo de Tarifa que deseja excluir (Horista ou Mensalista)");                   
                                }   
                                
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;                                   
                                    data = interfaces.receberString("Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos) :");
                                 }
                                 else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;                                  
                                    data = interfaces.receberString("Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos)");
                                }                                      
                                                         
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps, inter, face);
                                
                                if(tipo.equalsIgnoreCase("HORISTA")){                            
                                     TarifaHorista tarifaEx = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                     if(tarifaEx == null){                                        
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Tarifa não encontrada!!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Tarifa não encontrada!!");                               
                                        }    
                                        break;
                                     }
                                     
                                     if(opTicket.procuraTarifaEmTicket(tarifaEx, tickets) == true) {                                     
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");                               
                                        }    
                                        break;
                                     }
                                     tarifas.remove(tarifaEx);  
                                }                               
                                else{                            
                                    TarifaMensalista tarifaEx = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                     if(tarifaEx == null){
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Tarifa não encontrada!!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Tarifa não encontrada!!");                               
                                        }    
                                        break;
                                     }
                                     /*Ver se ta fufando*/
                                     if(opTicket.procuraTarifaEmTicket(tarifaEx, tickets) == true) {
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");                               
                                        }    
                                        break;
                                     }
                                     tarifas.remove(tarifaEx);
                                }
                                                            

                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    interfaces.mensagem("Tarifa removida com Sucesso!");               
                                }
                                else {
                                   InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                   interfaces.mensagem("Tarifa removida com Sucesso!");                               
                                }    
                                        
                            break;
                            case 3: /*editar tarifa*/
                                if(face == 0){
                                   InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                   tipo = interfaces.receberString("Digite o Tipo de Tarifa que deseja editar (Horista ou Mensalista):");

                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    tipo = interfaces.receberString("Digite o Tipo de Tarifa que deseja editar (Horista ou Mensalista)");                   
                                }   
                                
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;                                   
                                    data = interfaces.receberString("Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos) :");
                                 }
                                 else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;                                  
                                    data = interfaces.receberString("Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos)");
                                }                                      
                                                         
                                dias = new ArrayList<>();                        
                                tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps, inter, face);                                   
                                
                                if(tipo.equalsIgnoreCase("HORISTA")){                            
                                    TarifaHorista tarifaEx = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                    if(tarifaEx == null){
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Tarifa não encontrada!!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Tarifa não encontrada!!");                               
                                        }    
                                        break;
                                    }
                                    if( face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        String novaData = interfaces.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                                        tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                                                              
                                        String ph =interfaces.receberString("Digite o novo valor da primeira hora:");
                                        double novaPH = Double.parseDouble(ph);                                       
                                        tarifaEx.setValorPrimeiraHora(novaPH);
                                                                             
                                        String sh =interfaces.receberString("Digite o novo valor da horas subsequentes");
                                        double novaHS = Double.parseDouble(sh);
                                        tarifaEx.setValorHoraSubsequente(novaHS);  
                                    } else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        String novaData = interfaces.receberString("Digite a nova data (em dia/mês/ano horas:minutos)");
                                        tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                                                              
                                        String ph = interfaces.receberString("Digite o novo valor da primeira hora");
                                        double novaPH = Double.parseDouble(ph);                                       
                                        tarifaEx.setValorPrimeiraHora(novaPH);
                                                                             
                                        String sh = interfaces.receberString("Digite o novo valor da horas subsequentes");
                                        double novaHS = Double.parseDouble(sh);
                                        tarifaEx.setValorHoraSubsequente(novaHS);  
                                    }
                                }                               
                                else{                            
                                    TarifaMensalista tarifaEx = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                    if(tarifaEx == null){
                                        if(face == 0){
                                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                            interfaces.mensagem("Erro: Tarifa não encontrada!!");               
                                        }
                                        else {
                                           InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                           interfaces.mensagem("Erro: Tarifa não encontrada!!");                               
                                        }    
                                        break;
                                    }
                                    
                                    if( face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        String novaData = interfaces.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                                        tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                                                              
                                        String vh = interfaces.receberString("Digite o novo valor da tarifa");
                                        double novaH = Double.parseDouble(vh);                                       
                                        tarifaEx.setValorUnico(novaH);
                                                                             
                                       
                                    } else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        String novaData = interfaces.receberString("Digite a nova data (em dia/mês/ano horas:minutos)");
                                        tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                                                              
                                        String vh = interfaces.receberString("Digite o novo valor da tarifa");
                                        double novaH = Double.parseDouble(vh);                                       
                                        tarifaEx.setValorUnico(novaH);
                                      }                                       
                                        
                                }                                                                                                                           
                                if(face == 0){
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    interfaces.mensagem("Tarifa editada com Sucesso!");               
                                }
                                else {
                                   InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                   interfaces.mensagem("Tarifa editada com Sucesso!");                               
                                }    
                            break;
                            case 4: /*imprimir tarifas*/                                
                                lista = opTicket.relatorioTarifa(tarifas);
                                if(face == 0) {
                                    InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                    for(String s : lista){
                                        interfaces.mensagem(s);
                                    }
                                }
                                else {
                                    InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                    for(String s : lista){
                                        interfaces.mensagem(s);
                                    } 
                                }
                            break;
                            case 5:
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
                    }while(opcao3 != 5);
                break;
                case 5:
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
        }while(opcao2 != 5);
       
    }
}
