/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Terminal;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.Terminal.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Classes.Interno.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.Userinterface.UserInterface;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceInicial implements UserInterface{
    
    OperacoesTicket ticke = new OperacoesTicket();
    OperacoesVagas vag = new OperacoesVagas();
    OperacoesCliente clie = new OperacoesCliente();
    
    InterfaceOpcaoCliente opCliente = new InterfaceOpcaoCliente();
    InterfaceOpcaoVaga opVaga = new InterfaceOpcaoVaga();
    InterfaceOpcaoEstacionamento opEstaciona = new InterfaceOpcaoEstacionamento();
    ItensMenu menui = new ItensMenu(); /* menui = Menu Inicial */
    ItensMenu menucg = new ItensMenu(); /* menucg = Menu de Cadastros Gerais */
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    byte opcao;
    byte opcao3;
    
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dataBonitinhaComSegundos = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
   
    
    /* Método geral das opções do menu que será chamado na Classe Main Estacionamento e permitirá que todo o
    menu seja exibido ao usuário */
  
    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {  
        do {
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            menui.imprimeInicio();
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
                        /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                        de linhas das Classes da interface */
                        menucg.imprimeCadastroGeral();
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
                                Ticket testeT = ticke.buscarTicket(tickets, codigo);
                                if(testeT == null) {
                                    interMensagem("\nErro: Ticket não encontrado!\n");
                                }                               
                                testeT.setFim(dataFinal); 
                                double lucro = ticke.totalFaturadoTicket(testeT);
                                interMensagem("\nO lucro desse ticket foi de " + dinheiro.format(lucro) + "\n");
                                testeT.setFim(null); 
                            break;
                            case 2: /*consultar veiculo*/
                                interMensagem("Digite a placa do veículo:");
                                String placa = scanner.nextLine();
                                Veiculo veicule = clie.verificarVeiculo(clientes, placa);
                                if(veicule == null) {
                                    interMensagem("Erro: Veículo não encontrado!!");
                                    break;
                                }
                                interMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(veicule.toString());
                                interMensagem("///////////////////////////////////////////////////\n");
                            break; 
                            case 3: /*consultar Tarifa*/
                                interMensagem("Digite a data de início da tarifa:");
                                data = scanner.nextLine();                              
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                Tarifa tarife = ticke.buscarTarifa(tarifas, data, dias, tps);
                                if(tarife == null){
                                    interMensagem("\nErro: Tarifa não encontrada!\n");
                                    break;
                                }
                                interMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(tarife.toString());
                                for(DiaSemana ds : tarife.getDiasSemana()){
                                    System.out.print(ds.toString() + " ");
                                }
                                interMensagem("\nTipo/s de veículo:");
                                for(TipoVeiculo tv : tarife.getTarifaVeiculos()){
                                    System.out.print(tv.toString() + " ");
                                }
                                interMensagem("\n///////////////////////////////////////////////////\n");
                            break; 
                            case 4: /*consultar Ticket*/
                                interMensagem("Digite o código do ticket:");
                                codigo = scanner.nextInt(); 
                                Ticket tickete = ticke.buscarTicket(tickets, codigo);
                                if(tickete == null){
                                    interMensagem("\nErro: Ticket não encontrado!\n");
                                    break;
                                }
                                interMensagem("\n///////////////////////////////////////////////////");
                                interMensagem(tickete.toString());
                                if(tickete.getFim() != null){
                                    interMensagem("Fim do ticket: " + tickete.getFim().format(dataBonitinha));
                                }
                                interMensagem("///////////////////////////////////////////////////\n");
                            break;
                            case 5: /* Listar tickets ativos */
                                ticke.ListarTicketAtivo(tickets);
                            break; 
                            case 6: /* Listar vagas cadastradas */
                                vag.listarVagasCadastradas(vagas);
                            break; 
                            case 7:
                            break;
                            default:
                                interMensagem("\nInsira uma opção válida!\n");
                            break;
                        }                        
                    }while(opcao3 != 7);                                                                        
                break;        
                case 5:
                      interMensagem("Digite as datas que você deseja visualizar o valor que foi faturado em reais (em dia/mês/ano):"); 
                      interMensagem("Data inicial: ");
                      String iniS = scanner.nextLine();
                      iniS = iniS + " 00:00:00";
                      interMensagem("Data final: ");
                      String fimS = scanner.nextLine();
                      fimS = fimS + " 00:00:00";
                      LocalDateTime inicio = LocalDateTime.parse(iniS, dataBonitinhaComSegundos);
                      LocalDateTime fim = LocalDateTime.parse(fimS, dataBonitinhaComSegundos);
                      double resultado = ticke.FaturadoPeriodo(tickets, inicio, fim);
                      interMensagem("\nNesse período foi/foram faturado/s: "  + dinheiro.format(resultado) + "\n");                     
                break;
                case 6:
                break;                    
                default:
                    interMensagem("\nInsira uma opção válida!\n");
                break;
            }
        }while (opcao != 6);
        scanner.close();
    }    
}
