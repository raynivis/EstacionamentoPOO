/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import static ufms.cptl.raymay.InterfaceOpcoes.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoVaga{
    OperacoesVagas opVaga = new OperacoesVagas(); 
    ItensMenu menuva = new ItensMenu(); /*menuva = Menu de gerencia das Vagas*/
    int opcao2;
    Scanner scanner = new Scanner(System.in);
    
    /* Método geral das opções da vaga que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas a vaga */
    public void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter, int face) {      
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            if(face == 0){
                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                opcao2 = interfaces.imprimeVaga();
            }
            else {
                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                opcao2 = interfaces.imprimeVaga();
            }
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    int numero;
                    String rua;
                    
                    if(face == 0) {
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        String numeroS = interfaces.receberString("Digite o número da vaga a ser cadastrada");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite o nome da rua da vaga a ser cadastrada");                                    
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        String numeroS = interfaces.receberString("Digite o número da vaga a ser cadastrada");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite o nome da rua da vaga a ser cadastrada");
                    }              
                    /* Vaga acabou de ser cadastrada, portando está disponível até algum estacionamento de veículo
                    ou até que se torne indisponível */
                    VagaStatus vagastatus = VagaStatus.DISPONIVEL;
                    
                    String tipo;
                    if(face == 0){
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        tipo = interfaces.receberString("Digite o tipo de vaga(MOTO, CARRO, ONIBUS)");
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        tipo = interfaces.receberString("Digite o tipo de vaga(MOTO, CARRO, ONIBUS)");
                    }
                    /* Transforma a String inserida em maiúsculo para fazer a comparação */
                    TipoVeiculo tipoV = TipoVeiculo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, tipoV);
                    
                    /* O método cadastrarVaga já adiciona na lista de vagas se retornar true */
                    if (opVaga.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Vaga cadastrada com sucesso!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Vaga cadastrada com sucesso!");
                        }
                    }
                    else{
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Vaga já existente!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Vaga já existente!");
                        }
                        break;
                    }   
                break;  
                
                case 2:
                    /*consultar vaga por número*/
                    if(face == 0) {
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        String numeroS = interfaces.receberString("Digite o número da vaga que você deseja consultar");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite a rua da vaga que você deseja consultar");                                    
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        String numeroS = interfaces.receberString("Digite o número da vaga que você deseja consultar");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite a rua da vaga que você deseja consultar");
                    }
                    
                    Vaga vaga = opVaga.consultarVaga(vagas, numero, rua); 
                    if(vaga == null){
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Vaga inexistente!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Vaga Inexistente!");
                        }
                        break;
                    }
                    if(face == 0){
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        interfaces.mensagem(vaga.toString());
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        interfaces.mensagem(vaga.toString());
                    }
               break;    
    
                case 3:
                    /*excluir vaga*/
                    if(face == 0) {
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        String numeroS = interfaces.receberString("Digite o número da vaga a ser excluída");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite a rua da vaga a ser excluída");                                    
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        String numeroS = interfaces.receberString("Digite o número da vaga a ser excluída");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite a rua da vaga a ser excluída");  
                    }
                        
                    /* O método excluirVaga realiza as verificações necessárias para a exclusão da vaga*/
                    if(opVaga.excluirVaga(vagas, tickets, rua, numero) == true) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Vaga rua:" + rua + " número:" + numero + " excluída com sucesso!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Vaga rua:" + rua + " número:" + numero + " excluída com sucesso!");
                        }
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    if(face == 0) {
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        String numeroS = interfaces.receberString("Digite a número da vaga que você deseja editar");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite a rua para a vaga que você deseja editar");                                    
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        String numeroS = interfaces.receberString("Digite a número da vaga que você deseja editar");
                        numero = Integer.parseInt(numeroS);
                        
                        rua = interfaces.receberString("Digite a rua para a vaga que você deseja editar");  
                    }     
                    int numeroNovo;
                    String ruaNova;
                    
                    if(face == 0) {
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        String numeroSi = interfaces.receberString("Digite o novo número da vaga");
                        numeroNovo = Integer.parseInt(numeroSi);
                        
                        ruaNova = interfaces.receberString("Digite a nova rua da vaga");                                    
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        String numeroSi = interfaces.receberString("Digite o novo número da vaga");
                        numeroNovo = Integer.parseInt(numeroSi);
                        
                        ruaNova = interfaces.receberString("Digite a nova rua da vaga");  
                    }     
                    
                    if(opVaga.editarVaga(vagas, rua, numero, ruaNova, numeroNovo) == true) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Vaga editada com sucesso!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Vaga editada com sucesso!");
                        }
                    }
                    else {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Vaga não existente!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Vaga não existente!");
                        }
                    }
                break;                 
                case 5:
                    /*alterar disponibilidade da vaga*/
                    String status;
                    if(face == 0) {
                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                        String numeroSii = interfaces.receberString("Digite o número da vaga para alterar sua disponibilidade");
                        numero = Integer.parseInt(numeroSii);
                        
                        rua = interfaces.receberString("Digite a rua da vaga para alterar sua disponibilidade"); 
                        
                        status = interfaces.receberString("Digite o novo status da vaga (DISPONIVEL ou INDISPONIVEL)");
                    }else {
                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                        String numeroSii = interfaces.receberString("Digite o número da vaga para alterar sua disponibilidadea");
                        numero = Integer.parseInt(numeroSii);
                        
                        rua = interfaces.receberString("Digite a rua da vaga para alterar sua disponibilidade");
                        
                        status = interfaces.receberString("Digite o novo status da vaga (DISPONIVEL ou INDISPONIVEL)");
                    }
                    VagaStatus statusV = VagaStatus.valueOf(status.toUpperCase());
                    
                    if(statusV == VagaStatus.OCUPADA) {
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Não é possível deixar a vaga OCUPADA!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Não é possível deixar a vaga OCUPADA!");
                        }
                        break;
                    }                 
                    if(opVaga.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Disponibilidade da vaga alterada com sucesso!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Disponibilidade da vaga alterada com sucesso!");
                        }
                    }
                break;
                case 6:
                break;
                default:
                    if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Insira uma opção válida!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Insira uma opção válida!");
                        }
                break;
            }    
        }while(opcao2 != 6);
               
    }
}
