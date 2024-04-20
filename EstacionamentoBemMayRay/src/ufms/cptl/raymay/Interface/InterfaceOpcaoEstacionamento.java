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
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoEstacionamento.imprimeEstacionamento;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoTarifa.imprimeTarifa;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoEstacionamento {
    OperacoesVagas vag = new OperacoesVagas(); 
    OperacoesCliente clie = new OperacoesCliente();
    OperacoesTicket tic = new OperacoesTicket();
    byte opcao2;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public void opcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {
        do{
            /* Utiliza o método criado em OpcaoEstacionamento no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            imprimeEstacionamento();
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
            switch (opcao2) {
                case 1:
                    /*estacionar*/                    
                    if(tarifas.isEmpty() == true) {
                        interMensagem("\nCadastre uma tarifa primeiro!\n");
                        break;
                    }
                    
                    interMensagem("Digite o número e a rua da vaga que pretende ser estacionada:");
                    int numeroRua = scanner.nextInt();
                    scanner.nextLine(); 
                    String ruaVaga = scanner.nextLine();
                    
                    Vaga vaga = vag.consultarVaga(vagas, numeroRua, ruaVaga);
                    if(vaga == null || vaga.getStatus() != VagaStatus.DISPONIVEL) {
                        /* talvez seria bom colocar essa especificacao nas oprecaoes? */
                        interMensagem("\nErro: Vaga não econtrada ou indisponível!\n");
                        break;
                    }
                    vaga.setStatus(VagaStatus.OCUPADA);
                    
                    interMensagem("Digite a placa do veículo:");
                    String placa = scanner.nextLine();
                    Veiculo veiculo = clie.verificarVeiculo(clientes, placa);
                    
                    if(veiculo == null || 
                    veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {
                        /*Ja comentando que bagunça eh essa ai em cima no if, eh literalmente vendo se o veiculo
                        e vaga sao do mesmo tipo para poder estacionar*/
                        interMensagem("\nErro: Veículo não econtrado ou o tipo de veículo não é compatível a o tipo de vaga!\n");
                        break;
                    }
                    /*Achar a tarifa que pertence ao ticket*/
                    Tarifa atual = tic.tarifaProxima(tarifas, LocalDateTime.now(), veiculo);
                    if(atual == null){
                        interMensagem("Erro: Não existe uma tarifa para esse tipo de vaga nesse período!");
                        break;
                    }
                    
                    Ticket novoTicket = new Ticket(numeroRua, ruaVaga, placa, atual);
                    
                    
                    novoTicket.setStatus(Operando.ATIVO);
                    novoTicket.setInicio(LocalDateTime.now());
                    
                    tickets.add(novoTicket);
                    
                    interMensagem("\nTicket criado com sucesso!\n");                   
                break;    
                case 2:
                    /*retirar*/
                    interMensagem("Digite o número e a rua que deseja retirar o veículo:");
                    int numero = scanner.nextInt();
                    scanner.nextLine();
                    String rua = scanner.nextLine();
                     
                    if(tic.retirar(tickets, vagas, numero, rua) == false) {
                        interMensagem("\nVaga não encontrada!\n");
                    } else {
                        interMensagem("\nTicket liberado e vaga liberada!\n");
                    }
                break;    
                case 3:
                    /*Listar todas as vagas disponíveis do estacionamento*/
                    vag.listarVagasDisponiveis(vagas);
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                    do{ 
                        /* Utiliza o método criado em OpcaoTarifa no package InterfaceEnumOpcao, reduzindo o tamanho
                        de linhas das classes da interface */
                        imprimeTarifa();
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3){
                            case 1: /*adicionar tarifa*/
                                interMensagem("Digite a data que deseja iniciar tarifa:");
                                interMensagem("Se deseja cadastrar uma tarifa instantânea, digite: Agora");
                                String data = scanner.nextLine();
                                LocalDateTime inicio;
                                
                                if(data.toUpperCase().equals("AGORA")) {
                                    inicio = LocalDateTime.now();
                                } else {
                                    inicio = LocalDateTime.parse(data, dataBonitinha);
                                    if(inicio.isBefore(LocalDateTime.now())) {
                                        interMensagem("Erro: Nao é possível cadastrar uma tarifa no passado!");
                                        break;
                                    }
                                }                                                              
                                                                            
                                interMensagem("Digite o valor da primeira hora:");
                                double precoPrimeira = scanner.nextDouble();
                                scanner.nextLine();
                                
                                interMensagem("Digite o valor das horas subsequentes:");
                                double precoHora = scanner.nextDouble();
                                scanner.nextLine();
                                
                                interMensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
                                interMensagem("Caso queira para todos os dias da Semana, digite: Todos");
                                List<DiaSemana> diaSmns = new  ArrayList<>();
                                String dias = scanner.nextLine();
                                dias = dias.toUpperCase();
                    
                                if(dias.contains("DOMINGO") || dias.contains("TODOS")) 
                                   diaSmns.add(DiaSemana.DOMINGO);
                                if(dias.contains("SEGUNDA") || dias.contains("TODOS"))
                                   diaSmns.add(DiaSemana.SEGUNDA); 
                                if(dias.contains("TERCA") || dias.contains("TODOS"))
                                   diaSmns.add(DiaSemana.TERCA); 
                                if(dias.contains("QUARTA") || dias.contains("TODOS"))
                                   diaSmns.add(DiaSemana.QUARTA); 
                                if(dias.contains("QUINTA") || dias.contains("TODOS"))
                                   diaSmns.add(DiaSemana.QUINTA); 
                                if(dias.contains("SEXTA") || dias.contains("TODOS"))
                                   diaSmns.add(DiaSemana.SEXTA); 
                                if(dias.contains("SABADO") || dias.contains("TODOS"))
                                   diaSmns.add(DiaSemana.SABADO); 
                                
                                interMensagem("Digite o/s veículo/s dessa tarifa (MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
                                interMensagem("Caso queira para todos os tipos de veículo, digite: Todos");
                                List<TipoVeiculo> listaTps = new  ArrayList<>();
                                String tipos = scanner.nextLine();
                                tipos = tipos.toUpperCase();
                    
                                if(tipos.contains("MOTOCICLETA") || tipos.contains("TODOS"))
                                   listaTps.add(TipoVeiculo.MOTOCICLETA);
                                if(tipos.contains("MEDIOPORTE") || tipos.contains("TODOS"))
                                   listaTps.add(TipoVeiculo.MEDIOPORTE); 
                                if(tipos.contains("GRANDEPORTE") || tipos.contains("TODOS"))
                                   listaTps.add(TipoVeiculo.GRANDEPORTE); 
                                                                              
                                Tarifa novaTarifa = new Tarifa(inicio, precoPrimeira, precoHora, diaSmns, listaTps);
                                tarifas.add(novaTarifa);

                                interMensagem("\nTarifa de " + data + " cadastrada com sucesso!\n");                                                     
                            break; 
                            case 2: /*excluir tarifa*/

                            break;
                            case 3: /*editar tarifa*/

                            break;
                        }
                    }while(opcao3 != 4);
                break;    
            }    
        }while(opcao2 != 5);
       
    }
}
