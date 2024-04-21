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
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoInicial.imprimeInicio;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import static ufms.cptl.raymay.Interface.OperacaoMostraMensagem.operacaoMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;

/**
 *
 * @author maymi
 */
public class InterfaceInicial {
    
    OperacoesTicket tic = new OperacoesTicket();
    
    InterfaceOpcaoCliente opCliente = new InterfaceOpcaoCliente();
    InterfaceOpcaoVaga opVaga = new InterfaceOpcaoVaga();
    InterfaceOpcaoEstacionamento opEstaciona = new InterfaceOpcaoEstacionamento();
    
    OperacoesCliente clie = new OperacoesCliente();
    InterfarceListaTipoSemanas listasVS = new InterfarceListaTipoSemanas();
    
    byte opcao;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter dataBonitinhaCalendario = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
    

    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {  
        do {
            /* Utiliza o método criado em OpcaoInicial no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            imprimeInicio();
            opcao = scanner.nextByte();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    opCliente.opcoesCliente(clientes, vagas, tickets, tarifas);
                break;    
                case 2:
                    opVaga.opcoesVaga(clientes, vagas, tickets, tarifas);
                break;
                case 3:
                    opEstaciona.opcoesEstacionamento(clientes, vagas, tickets, tarifas);
                break;
                case 4:
                    do {
                        /* Será que criamos um Enum aqui também? */
                        interMensagem("1 - Teste de Ticket com Tarifa");
                        interMensagem("2 - Consultar Veiculo");                  
                        interMensagem("3 - Consultar Tarifa");
                        interMensagem("4 - Consultar Ticket");
                        interMensagem("5 - Listar Tickets Ativos");
                        interMensagem("6 - Voltar");
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3) {
                            case 1:
                                interMensagem("Digite o código do ticket:");
                                int codigo = scanner.nextInt();
                                scanner.nextLine();

                                interMensagem("Digite a data de finalização do ticket (em dia/mês/ano horas:minutos:segundos):");
                                String data = scanner.nextLine();
                                LocalDateTime dataFinal = LocalDateTime.parse(data, dataBonitinhaComSegundos);
                                Ticket testeT = tic.buscarTicket(tickets, codigo);
                                if(testeT == null) {
                                    interMensagem("\nErro: Ticket não encontrado!\n");
                                }                               
                                testeT.setFim(dataFinal);                          
                                double lucro = tic.totalFaturadoTicket(testeT);
                                interMensagem("O Lucro desse ticket foi de " + lucro + "\n");
                            break;
                            case 2: /*consultar veiculo*/
                                interMensagem("Digite a placa do veiculo:");
                                String placa = scanner.nextLine();
                                Veiculo veicule = clie.verificarVeiculo(clientes, placa);
                                if(veicule == null) {
                                    interMensagem("Erro: Veículo não encontrado!!");
                                    break;
                                }
                                operacaoMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(veicule.toString());
                                operacaoMensagem("///////////////////////////////////////////////////\n");
                            break; 
                            case 3: /*consultar Tarifa*/
                                interMensagem("Digite a data de inicio da Tarifa:");
                                data = scanner.nextLine();                              
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                Tarifa tarife = tic.buscarTarifa(tarifas, data, dias, tps);
                                if(tarife == null){
                                    interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                }
                                operacaoMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(tarife.toString());
                                for(DiaSemana ds : tarife.getDiasSemana()){
                                    System.out.print(ds.toString() + " ");
                                }
                                operacaoMensagem("\nTipo/s de Veiculo:");
                                for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                    System.out.print(tv.toString() + " ");
                                }
                                operacaoMensagem("\n///////////////////////////////////////////////////\n");
                            break; 
                            case 4: /*consultar Ticket*/
                                interMensagem("Digite o codigo do ticket:");
                                codigo = scanner.nextInt(); 
                                Ticket tickete = tic.buscarTicket(tickets, codigo);
                                if(tickete == null){
                                    interMensagem("\nErro: Ticket não encontrada!\n");
                                    break;
                                }
                                operacaoMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(tickete.toString());
                                if(tickete.getFim() != null){
                                    operacaoMensagem("Status:" + tickete.getStatus());
                                    operacaoMensagem("Fim do Ticket: " + tickete.getFim().format(tickete.getTarifaAtual().getDataBonitinha()));
                                }
                                operacaoMensagem("///////////////////////////////////////////////////\n");
                            break;
                            case 5: /*listar tickets ativo*/
                                tic.ListarTicketAtivo(tickets);
                            break; 
                        }                        
                    }while(opcao3 != 6);                                                                        
                break;        
                case 5:
                      operacaoMensagem("Digite as datas que você pretende ver quantos Reais foi faturado (em dia/mês/ano horas:minutos:segundos):"); 
                      String iniS = scanner.nextLine();
                      String fimS = scanner.nextLine();
                      LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                      LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                      double resultado = tic.FaturadoPeriodo(tickets, inicio, fim);
                      operacaoMensagem("\nNesse período foi/foram faturado/s: "  + resultado + "\n");                     
                break;                                    
            }
        }while (opcao != 6);
        scanner.close();
    }    
}
