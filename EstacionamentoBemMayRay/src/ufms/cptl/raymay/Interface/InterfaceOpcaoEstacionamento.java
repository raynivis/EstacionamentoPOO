/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.Operando;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Classes.Interno.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoEstacionamento {
    OperacoesVagas opVaga = new OperacoesVagas(); 
    OperacoesCliente opCliente = new OperacoesCliente();
    OperacoesTicket opTicket = new OperacoesTicket();
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    ItensMenu menue = new ItensMenu(); /* menue = Menu de gerencia do Estacionamento */
    ItensMenu menut = new ItensMenu(); /*menut = Menu de gerencia das Tarifas */
    
    byte opcao2;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    
    /* Método geral das opções do estacionamento que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao estacionamento */
    public void opcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {
        do{
            /* Utiliza o método criado em OpcaoEstacionamento no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            menue.imprimeEstacionamento();
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
            switch (opcao2) {
                case 1:
                    /*estacionar*/
                    
                    if(tarifas.isEmpty() == true) {
                        interMensagem("\nCadastre uma tarifa primeiro!\n");
                        break;
                    }
                    
                    interMensagem("Digite o número da vaga que pretende ser estacionada:");
                    int numeroRua = scanner.nextInt();
                    scanner.nextLine(); 
                    interMensagem("Digite a rua da vaga que pretende ser estacionada:");
                    String ruaVaga = scanner.nextLine();
                    
                    
                    Vaga vaga = opVaga.consultarVaga(vagas, numeroRua, ruaVaga);
                    if(vaga == null) {                       
                        interMensagem("\nErro: Vaga não econtrada!\n");
                        break;
                    }
                    
                    if(vaga.getStatus() != VagaStatus.DISPONIVEL) {
                        if(vaga.getStatus() == VagaStatus.OCUPADA) { 
                            interMensagem("\nErro: A vaga possui um ticket de estacionamento ATIVO (OCUPADA)!\n");
                            break;
                        }
                        interMensagem("\nErro: Vaga indisponível!\n");
                        break;
                    }
                    
                    interMensagem("Vaga disponível!\nDigite a placa do veículo a ser estacionado:");
                    String placa = scanner.nextLine();
                  
                    Veiculo veiculo = opCliente.verificarVeiculo(clientes, placa);
                    
                    if(veiculo == null) {
                       interMensagem("\nErro: Veículo não econtrado!\n"); 
                       break;
                    }
                    
                    Ticket veriTicket = opTicket.verificaTicketVeiculo(clientes, placa, tickets);
                    if(veriTicket != null){                       
                        interMensagem("\nErro: O veículo já possui um ticket de estacionamento ATIVO (está estacionado)!\n");
                        break;
                    }
                        
                    if(veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {                       
                        interMensagem("\nErro: O tipo de veículo não é compatível com o tipo de vaga!\n");
                        break;
                    }
                   
                    /*Achar a tarifa que pertence ao ticket*/
                    Tarifa atual = opTicket.tarifaProxima(tarifas, LocalDateTime.now(), veiculo);
                    if(atual == null){
                        interMensagem("\nErro: Não existe uma tarifa para esse tipo de vaga nesse período!\n");
                        break;
                    }
                    
                    Ticket novoTicket = new Ticket(atual, veiculo, vaga);
                    vaga.setStatus(VagaStatus.OCUPADA);
                    
                    novoTicket.setStatus(Operando.ATIVO);
                    novoTicket.setInicio(LocalDateTime.now());
                    
                    tickets.add(novoTicket);
                    
                    interMensagem("\nTicket de código " + novoTicket.getCodigo() + " criado com sucesso!\n");                   
                break;    
                case 2:
                    /*retirar*/
                    interMensagem("Digite a placa do veículo que deseja retirar:");
                    placa = scanner.nextLine();
                    
                    Veiculo retirar = opCliente.verificarVeiculo(clientes, placa);
                    if(retirar == null) {
                        interMensagem("\nVeiculo não encontrado nos Clientes!\n");
                    }
                    
                    if(opTicket.retirar(tickets, retirar) == false) {
                        interMensagem("\nVeiculo não está estacionado!\n");
                    } else {
                        interMensagem("\nTicket liberado e vaga liberada!\n");
                    }
                break;    
                case 3:
                    /*listar todas as vagas disponíveis do estacionamento*/
                    opVaga.listarVagasDisponiveis(vagas);
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                    do{ 
                        /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                        de linhas das Classes da interface */
                        menut.imprimeTarifa();
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3){
                            case 1: /*adicionar tarifa*/
                                interMensagem("Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos) :");
                                interMensagem("Se deseja cadastrar uma tarifa instantânea, digite: Agora");
                                String data = scanner.nextLine();
                                LocalDateTime inicio;
                                
                                if(data.toUpperCase().equals("AGORA")) {
                                    inicio = LocalDateTime.now();
                                } else {
                                    inicio = LocalDateTime.parse(data, dataFormata);
                                    if(inicio.isBefore(LocalDateTime.now())) {
                                        interMensagem("\nErro: Nao é possível cadastrar uma tarifa no passado!\n");
                                        break;
                                    }
                                }                                                              
                                                                            
                                interMensagem("Digite o valor da primeira hora:");
                                double precoPrimeira = scanner.nextDouble();
                                scanner.nextLine();
                                
                                interMensagem("Digite o valor das horas subsequentes:");
                                double precoHora = scanner.nextDouble();
                                scanner.nextLine();
                                
                             
                                List<DiaSemana> diaSmns = new  ArrayList<>();                        
                                List<TipoVeiculo> listaTps = new  ArrayList<>();
                                listasVS.OperacaoListaTVDS(diaSmns, listaTps);
                                
                                if(opTicket.buscarTarifa(tarifas, inicio.format(dataFormata), diaSmns, listaTps) != null){
                                    interMensagem("\nErro: Você ja cadastrou uma Tarifa desse tipo para essa data!\n");
                                    break;
                                }
                                                                              
                                Tarifa novaTarifa = new Tarifa(inicio, precoPrimeira, precoHora, diaSmns, listaTps);
                                tarifas.add(novaTarifa);

                                interMensagem("\nTarifa de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");                                                     
                            break; 
                            case 2: /*excluir tarifa*/
                                interMensagem("Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos) :");
                                data = scanner.nextLine();                              
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                Tarifa tarifaEx = opTicket.buscarTarifa(tarifas, data, dias, tps);
                                if(tarifaEx == null){
                                    interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                }
                                
                                if(opTicket.procuraTarifaEmTicket(tarifaEx, tickets) == true) {
                                    interMensagem("\nA tarifa não pode ser excluída pois ela possui um ticket cadastrado!\n");
                                    break;
                                }
                                    
                                tarifas.remove(tarifaEx);  
                                interMensagem("\nTarifa removida com sucesso!\n");
                            break;
                            case 3: /*editar tarifa*/
                                interMensagem("Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos) :");
                                data = scanner.nextLine();
                                
                                dias = new ArrayList<>();                        
                                tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                tarifaEx = opTicket.buscarTarifa(tarifas, data, dias, tps);
                                if(tarifaEx == null){
                                    interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                }
                                interMensagem("Digite a nova data (em dia/mês/ano horas:minutos):");
                                String novaData = scanner.nextLine();
                                tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                interMensagem("Digite o novo valor da primeira hora:");
                                double novaPH = scanner.nextDouble();
                                tarifaEx.setValorPrimeiraHora(novaPH);
                                interMensagem("Digite o novo valor da horas subsequentes");
                                double novaHS = scanner.nextDouble();
                                tarifaEx.setValorHoraSubsequente(novaHS);               
                                interMensagem("\nTarifa editada com sucesso!\n");
                            break;
                            case 4: /*imprimir tarifas*/
                                opTicket.relatorioTarifa(tarifas);
                            break;
                            case 5:
                            break;
                            default:
                                interMensagem("\nInsira uma opção válida!\n");
                            break;
                        }
                    }while(opcao3 != 5);
                break;
                case 5:
                break;
                default:
                    interMensagem("\nInsira uma opção válida!\n");
                break;
            }    
        }while(opcao2 != 5);
       
    }
}
