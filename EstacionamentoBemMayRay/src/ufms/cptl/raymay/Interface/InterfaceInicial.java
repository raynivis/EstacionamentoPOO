/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoInicial.imprimeInicio;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
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
    
    byte opcao;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    

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
                        interMensagem("2 - Voltar");
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3) {
                            case 1:
                                interMensagem("Digite o código do ticket:");
                                int codigo = scanner.nextInt();
                                scanner.nextLine();

                                interMensagem("Digite a data de finalização do ticket:");
                                String data2 = scanner.nextLine();
                                LocalDateTime dataFinal = LocalDateTime.parse(data2, dataBonitinha);
                                for(Ticket t : tickets) {
                                    if(t.getCodigo() == codigo) {
                                        t.setFim(dataFinal);                          
                                        double lucro = tic.totalFaturadoTicket(t, vagas, tarifas);
                                        System.out.println(lucro);
                                        break;
                                    }
                                }
                                interMensagem("Ticket não encontrado!");
                            break;
                        }                        
                    }while(opcao3 != 2);                                                                        
                break;    
      
                case 5:
                break;
                                      
            }
        }while (opcao != 6);
        scanner.close();
    }    
}
