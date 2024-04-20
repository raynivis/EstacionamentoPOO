/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoVaga.imprimeVaga;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoVaga {
    OperacoesVagas vag = new OperacoesVagas(); 
    byte opcao2;
    Scanner scanner = new Scanner(System.in);
    
    public  void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {      
        do{
            /* Utiliza o método criado em OpcaoVaga no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            imprimeVaga();
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    interMensagem("Digite a rua, número e tipo de vaga(MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
                    String rua = scanner.nextLine();                   
                    
                    int numero = scanner.nextInt();
                    scanner.nextLine();
                    
                    
                    VagaStatus vagastatus = VagaStatus.DISPONIVEL;
                    
                    String tipo = scanner.nextLine();
                    TipoVeiculo tipoV = TipoVeiculo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, vagastatus, tipoV);
                    
                    if (vag.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        interMensagem("\nVaga cadastrada com sucesso!\n");
                    }
                    else{
                        interMensagem("\nVaga já existente!\n");
                    }   
                break;  
                
                case 2:
                    /*consultar vaga por numero*/
                    interMensagem("Insira o número da vaga e rua a ser consultada:");
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    rua = scanner.nextLine();
                    Vaga vaga = vag.consultarVaga(vagas, numero, rua); 
                    if(vaga == null){
                        interMensagem("\nVaga não existente!\n");
                        break;
                    }
                    System.out.println(vaga.toString()); 
               break;    
    
                case 3:
                    /*excluir vaga*/
                    interMensagem("Insira a rua e o número da vaga a ser excluída:");
                    rua = scanner.nextLine();
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    if(vag.excluirVaga(vagas, rua, numero) == true) {
                        interMensagem("Vaga rua:" + rua + " número:" + numero + " excluída com sucesso!");
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    interMensagem("Insira a rua e o número da vaga a ser editada:");
                    rua = scanner.nextLine();
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    
                    interMensagem("Agora insira a nova rua, o novo número e o novo tipo da vaga (MOTOCICLETA, MEDIOPORTE, GRANDEPORTE): ");
                    String ruaNova = scanner.nextLine();
                    int numeroNovo = scanner.nextInt();
                    scanner.nextLine();
                    
                    tipo = scanner.nextLine();
                    TipoVeiculo tipoN = TipoVeiculo.valueOf(tipo.toUpperCase());
                    if(vag.editarVaga(vagas, rua, numero, ruaNova, numeroNovo, tipoN) == true) {
                        interMensagem("\nVaga editada com sucesso!\n");
                    }
                    else {
                        interMensagem("\nVaga não existente!\n");
                    }
                break;  
                
                case 5:
                    /*alterar disponibilidade da vaga*/
                    interMensagem("Insira a rua, número e o novo status da vaga (DISPONIVEL, OCUPADA ou INDISPONIVEL)");
                    rua = scanner.nextLine();
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    
                    String status = scanner.nextLine();
                    VagaStatus statusV = VagaStatus.valueOf(status.toUpperCase());
                    
                    if(vag.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        interMensagem("\nDisponibilidade da vaga alterada com sucesso!\n");
                    }
                break;    
            }    
        }while(opcao2 != 6);
               
    }
}
