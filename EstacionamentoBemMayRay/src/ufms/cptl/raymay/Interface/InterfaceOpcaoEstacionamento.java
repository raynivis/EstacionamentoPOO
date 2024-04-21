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
    InterfarceListaTipoSemanas listasVS = new InterfarceListaTipoSemanas();
    byte opcao2;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
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
                    if(vaga == null) {                       
                        interMensagem("\nErro: Vaga não econtrada!\n");
                        break;
                    }
                    if(vaga.getStatus() != VagaStatus.DISPONIVEL) {
                        
                        interMensagem("\nErro: Vaga indisponível!\n");
                        break;
                    }
                    
                    vaga.setStatus(VagaStatus.OCUPADA);
                    
                    interMensagem("Digite a placa do veículo:");
                    String placa = scanner.nextLine();
                    Veiculo veiculo = clie.verificarVeiculo(clientes, placa);
                    
                    if(veiculo == null) {
                       interMensagem("\nErro: Veículo não econtrado!\n"); 
                       break;
                    }
                        
                    if( veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {                       
                        interMensagem("\nErro: O tipo de veículo não é compatível a o tipo de vaga!\n");
                        break;
                    }
                   
                    /*Achar a tarifa que pertence ao ticket*/
                    Tarifa atual = tic.tarifaProxima(tarifas, LocalDateTime.now(), veiculo);
                    if(atual == null){
                        interMensagem("\nErro: Não existe uma tarifa para esse tipo de vaga nesse período!\n");
                        break;
                    }
                    
                    Ticket novoTicket = new Ticket(numeroRua, ruaVaga, placa, atual);
                    
                    
                    novoTicket.setStatus(Operando.ATIVO);
                    novoTicket.setInicio(LocalDateTime.now());
                    
                    tickets.add(novoTicket);
                    
                    interMensagem("\nTicket de código " + novoTicket.getCodigo() + " criado com sucesso!\n");                   
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
                                                                            
                                interMensagem("Digite o valor da Primeira Hora:");
                                double precoPrimeira = scanner.nextDouble();
                                scanner.nextLine();
                                
                                interMensagem("Digite o valor das Horas Subsequentes:");
                                double precoHora = scanner.nextDouble();
                                scanner.nextLine();
                                
                             
                                List<DiaSemana> diaSmns = new  ArrayList<>();                        
                                List<TipoVeiculo> listaTps = new  ArrayList<>();
                                listasVS.OperacaoListaTVDS(diaSmns, listaTps);
                                                                              
                                Tarifa novaTarifa = new Tarifa(inicio, precoPrimeira, precoHora, diaSmns, listaTps);
                                tarifas.add(novaTarifa);

                                interMensagem("\nTarifa de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");                                                     
                            break; 
                            case 2: /*excluir tarifa*/
                                interMensagem("Digite a data da tarifa que deseja excluir iniciar tarifa (em dia/mês/ano horas:minutos) :");
                                data = scanner.nextLine();                              
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                Tarifa tarifaEx = tic.buscarTarifa(tarifas, data, dias, tps);
                                if(tarifaEx == null){
                                    interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                }
                                tarifas.remove(tarifaEx);  
                                interMensagem("\nTarifa Removida com sucesso!\n");
                            break;
                            case 3: /*editar tarifa*/
                                interMensagem("Digite a data da tarifa que deseja excluir iniciar tarifa (em dia/mês/ano horas:minutos) :");
                                data = scanner.nextLine();
                                
                                dias = new ArrayList<>();                        
                                tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                tarifaEx = tic.buscarTarifa(tarifas, data, dias, tps);
                                if(tarifaEx == null){
                                    interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                }
                                interMensagem("Digite a nova data (em dia/mês/ano horas:minutos):");
                                String novaData = scanner.nextLine();
                                tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                interMensagem("Digite o novo valor da Primeira Hora:");
                                double novaPH = scanner.nextDouble();
                                tarifaEx.setValorPrimeiraHora(novaPH);
                                interMensagem("Digite o novo valor da Horas Subsequentes");
                                double novaHS = scanner.nextDouble();
                                tarifaEx.setValorHoraSubsequente(novaHS);               
                                interMensagem("\nTarifa Editada com sucesso!\n");
                            break;
                            case 4: /*imprimir tarifas*/
                                tic.relatorioTarifa(tarifas);
                            break;
                        }
                    }while(opcao3 != 5);
                break;    
            }    
        }while(opcao2 != 5);
       
    }
}
