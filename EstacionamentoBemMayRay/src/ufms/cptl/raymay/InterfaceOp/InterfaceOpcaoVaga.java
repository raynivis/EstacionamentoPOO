/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import static ufms.cptl.raymay.InterfaceOp.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoVaga{
    OperacoesVagas opVaga = new OperacoesVagas(); 
    ItensMenu menuva = new ItensMenu(); /*menuva = Menu de gerencia das Vagas*/
    byte opcao2;
    Scanner scanner = new Scanner(System.in);
    
    /* Método geral das opções da vaga que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas a vaga */
    public void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {      
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            menuva.imprimeVaga(0);
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    interMensagem("Digite o número da vaga a ser cadastrada:");
                    int numero = scanner.nextInt();
                    scanner.nextLine();
                    interMensagem("Digite o nome da rua da vaga a ser cadastrada:");
                    String rua = scanner.nextLine();                                      
                                  
                    /* Vaga acabou de ser cadastrada, portando está disponível até algum estacionamento de veículo
                    ou até que se torne indisponível */
                    VagaStatus vagastatus = VagaStatus.DISPONIVEL;
                    
                    interMensagem("Digite o tipo de vaga(MOTO, CARRO, ONIBUS):");
                    String tipo = scanner.nextLine();
                    /* Transforma a String inserida em maiúsculo para fazer a comparação */
                    TipoVeiculo tipoV = TipoVeiculo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, tipoV);
                    
                    /* O método cadastrarVaga já adiciona na lista de vagas se retornar true */
                    if (opVaga.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        interMensagem("\nVaga cadastrada com sucesso!\n");
                    }
                    else{
                        interMensagem("\nVaga já existente!\n");
                    }   
                break;  
                
                case 2:
                    /*consultar vaga por número*/
                    interMensagem("Digite o número da vaga que você deseja consultar:");
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    interMensagem("Digite a rua da vaga que você deseja consultar:");
                    rua = scanner.nextLine();
                    
                    Vaga vaga = opVaga.consultarVaga(vagas, numero, rua); 
                    if(vaga == null){
                        interMensagem("\nVaga inexistente!\n");
                        break;
                    }
                    System.out.println(vaga.toString()); 
               break;    
    
                case 3:
                    /*excluir vaga*/
                    interMensagem("Digite o número da vaga a ser excluída:");
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    interMensagem("Digite a rua da vaga a ser excluída:");
                    rua = scanner.nextLine();
                        
                    /* O método excluirVaga realiza as verificações necessárias para a exclusão da vaga*/
                    if(opVaga.excluirVaga(vagas, tickets, rua, numero) == true) {
                        interMensagem("\nVaga rua:" + rua + " número:" + numero + " excluída com sucesso!\n");
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    interMensagem("Digite a número da vaga que você deseja editar:");
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    
                    interMensagem("Digite a rua para a vaga que você deseja editar:");
                    rua = scanner.nextLine();                 
                    
                    interMensagem("Agora insira a nova rua, e logo em seguida o novo número:");
                    String ruaNova = scanner.nextLine();
                    int numeroNovo = scanner.nextInt();
                    scanner.nextLine();
                    
                    if(opVaga.editarVaga(vagas, rua, numero, ruaNova, numeroNovo) == true) {
                        interMensagem("\nVaga editada com sucesso!\n");
                    }
                    else {
                        interMensagem("\nVaga não existente!\n");
                    }
                break;                 
                case 5:
                    /*alterar disponibilidade da vaga*/
                    interMensagem("Digite o número da vaga para alterar sua disponibilidade:");
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    
                    interMensagem("Digite a rua da vaga para alterar sua disponibilidade:");
                    rua = scanner.nextLine();   
                    interMensagem("Digite o novo status da vaga (DISPONIVEL ou INDISPONIVEL)");
                            
                    String status = scanner.nextLine();
                    VagaStatus statusV = VagaStatus.valueOf(status.toUpperCase());
                    
                    if(statusV == VagaStatus.OCUPADA) {
                        interMensagem("\nErro: Não é possivel deixar a vaga Ocupada!\n");
                        break;
                    }                 
                    if(opVaga.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        interMensagem("\nDisponibilidade da vaga alterada com sucesso!\n");
                    }
                break;
                case 6:
                break;
                default:
                    interMensagem("\nInsira uma opção válida!\n");
                break;
            }    
        }while(opcao2 != 6);
               
    }
}
