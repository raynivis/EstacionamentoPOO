/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Interno.Vaga.VagaStatus;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class Interface {
    
    OperacoesVagas vag = new OperacoesVagas(); 
    int opcao;
    int opcao2;
    Scanner op = new Scanner(System.in);

    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, Tarifa tarifa) {  
        do {
            System.out.println("1 - Gerenciar cliente");
            System.out.println("2 - Gerenciar vagas");
            System.out.println("3 - Gerenciar estacionamento");
            System.out.println("4 - Cadastros gerais");
            System.out.println("5 - Consultar total faturado em um periodo");
            System.out.println("6 - Sair do programa");
            opcao = op.nextInt();
            op.nextLine();
            
            switch (opcao) {
                case 1:
                    opcoesCliente(clientes, vagas, tickets, tarifa);
                break;    
                case 2:
                    opcoesVaga(clientes, vagas, tickets, tarifa);
                break;
                case 3:
                    opcoesEstacionamento(clientes, vagas, tickets, tarifa);
                break;
                case 4:  
                break;    
      
                case 5:
                break;
                                      
            }
        }while (opcao != 6);
        op.close();
    }
    
    public void opcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, Tarifa tarifa) {       
        do{
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por documento");
            System.out.println("3 - Excluir");
            System.out.println("4 - Editar");
            System.out.println("5 - Gerenciar veiculos");
            System.out.println("6 - Listar todos os cadastros");
            System.out.println("7 - Voltar");
            opcao2 = op.nextInt();
            op.nextLine();  
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                    break;   
                    case 3:
                        /*excluir cliente*/
                    break;    
                    case 4:
                        /*editar cliente*/
                    break;    
                    case 5:
                        /*gerenciar veiculos do cliente*/
                    break;    
                    case 6: 
                        /*listar todos os cadastros do(s)? cliente*/
                    break;    
                }
        }while(opcao2 != 7);
        
    }
    
    public  void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, Tarifa tarifa) {      
        do{
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por numero");
            System.out.println("3 - Excluir");
            System.out.println("4 - Editar");
            System.out.println("5 - Alterar disponibilidade");
            System.out.println("6 - Voltar"); 
            opcao2 = op.nextInt();
            op.nextLine();  
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    System.out.println("Digite a Rua, Numero e Tipo de Vaga(MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
                    String rua = op.nextLine();                   
                    
                    int numero = op.nextInt();
                    op.nextLine();
                    
                    
                    Vaga.VagaStatus vagastatus = Vaga.VagaStatus.DISPONIVEL;
                    
                    String tipo = op.nextLine();
                    Modelo.Tipo tipoV = Modelo.Tipo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, vagastatus, tipoV);
                    
                    if (vag.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        System.out.println("Vaga cadastrada com Sucesso!");
                    }
                    else{
                        System.out.println("Vaga ja existente!");
                    }   
                break;  
                
                case 2:
                    /*consultar vaga por numero*/
                    System.out.println("Insira o numero da vaga a ser consultada:");
                    numero = op.nextInt();
                    op.nextLine();
                    if(vag.consultarVaga(vagas, numero) == null){
                        System.out.println("Vaga nao existente!");
                    }
                break;    
    
                case 3:
                    /*excluir vaga*/
                    System.out.println("Insira a rua e o numero da vaga a ser excluida:");
                    rua = op.nextLine();
                    numero = op.nextInt();
                    op.nextLine();
                    if(vag.excluirVaga(vagas, rua, numero) == true) {
                        System.out.println("Vaga rua:" + rua + " numero:" + numero + " excluida com sucesso!");
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    System.out.println("Insira a rua e o numero da vaga a ser editada:");
                    rua = op.nextLine();
                    numero = op.nextInt();
                    op.nextLine();
                    
                    System.out.println("Agora insira a nova rua, o novo numero e o novo tipo da vaga (MOTOCICLETA, MEDIOPORTE, GRANDEPORTE): ");
                    String ruaNova = op.nextLine();
                    int numeroNovo = op.nextInt();
                    op.nextLine();
                    
                    tipo = op.nextLine();
                    Modelo.Tipo tipoN = Modelo.Tipo.valueOf(tipo.toUpperCase());
                    if(vag.editarVaga(vagas, rua, numero, ruaNova, numeroNovo, tipoN) == true) {
                        System.out.println("Vaga editada com sucesso!");
                    }
                    else {
                        System.out.println("Vaga nao existente!");
                    }
                break;  
                
                case 5:
                    /*alterar disponibilidade da vaga*/
                    System.out.println("Insira a rua, numero e o novo status da vaga (DISPONIVEL, OCUPADA ou INDISPONIVEL)");
                    rua = op.nextLine();
                    numero = op.nextInt();
                    op.nextLine();
                    
                    String status = op.nextLine();
                    Vaga.VagaStatus statusV = Vaga.VagaStatus.valueOf(status.toUpperCase());
                    
                    if(vag.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        System.out.println("Disponibilidade da vaga alterada com sucesso!");
                    }
                break;    
            }    
        }while(opcao2 != 6);
               
    }
    
    public void opcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, Tarifa tarifa) {
        do{
            System.out.println("1 - Estacionar");
            System.out.println("2 - Retirar");
            System.out.println("3 - Listar todas as vagas disponiveis");
            System.out.println("4 - Gerenciar tarifas");
            System.out.println("5 - Voltar");
            opcao2 = op.nextInt();
            op.nextLine();  
            switch (opcao2) {
                case 1:
                    /*estacionar*/
                break;    
                case 2:
                    /*retirar*/
                break;    
                case 3:
                    /*Listar todas as vagas disponíveis do estacionamento*/
                    vag.listarVagasDisponiveis(vagas);
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                break;    
            }    
        }while(opcao2 != 5);
       
    }
}
