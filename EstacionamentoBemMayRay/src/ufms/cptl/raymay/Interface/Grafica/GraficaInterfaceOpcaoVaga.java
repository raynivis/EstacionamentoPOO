/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Grafica;

import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.Terminal.ItensMenu;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class GraficaInterfaceOpcaoVaga {
    OperacoesVagas opVaga = new OperacoesVagas(); 
    ItensMenu menuva = new ItensMenu(); /*menuva = Menu de gerencia das Vagas*/
    int opcao2;
    Scanner scanner = new Scanner(System.in);
    
    /* Método geral das opções da vaga que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas a vaga */
    public void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {      
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            opcao2 = menuva.imprimeVaga(1);  
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    String numeroJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da vaga a ser cadastrada",
                    "Cadastro de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(numeroJO == null) {
                        break;
                    }
                    int numero = Integer.parseInt(numeroJO);
                    
                    String rua = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome rua da vaga a ser cadastrada",
                    "Cadastro de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    if(rua == null) {
                        break;
                    }
                                      
                                  
                    /* Vaga acabou de ser cadastrada, portando está disponível até algum estacionamento de veículo
                    ou até que se torne indisponível */
                    VagaStatus vagastatus = VagaStatus.DISPONIVEL;
                    
                    String tipo = JOptionPane.showInputDialog(
                    null,
                    "Digite o tipo de vaga(MOTO, CARRO, ONIBUS)",
                    "Cadastro de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                    
                    if(tipo == null) {
                        break;
                    }
                    /* Transforma a String inserida em maiúsculo para fazer a comparação */
                    TipoVeiculo tipoV = TipoVeiculo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, tipoV);
                    
                    /* O método cadastrarVaga já adiciona na lista de vagas se retornar true */
                    if (opVaga.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        JOptionPane.showMessageDialog(null, "Vaga cadastrada com sucesso!",
                        "Cadastro de vaga", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Vaga já existente! Cadastre outra ou escolha uma nova opção no menu.",
                        "Cadastro de vaga", JOptionPane.WARNING_MESSAGE);
                    }   
                break;  
                
                case 2:
                    /*consultar vaga por número*/
                    numeroJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da vaga que você deseja consultar",
                    "Consulta de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                    
                    if(numeroJO == null) {
                        break;
                    }
                    numero = Integer.parseInt(numeroJO);
                    
                    rua = JOptionPane.showInputDialog(
                    null,
                    "Digite a rua da vaga que você deseja consultar",
                    "Consulta de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(rua == null) {
                        break;
                    }
                    
                    Vaga vaga = opVaga.consultarVaga(vagas, numero, rua); 
                    if(vaga == null){
                        JOptionPane.showMessageDialog(null, "Vaga inexistente!",
                        "Consulta de vaga", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    JOptionPane.showMessageDialog(null, vaga.toString(),
                    "Consulta de vaga", JOptionPane.INFORMATION_MESSAGE); 
               break;    
    
                case 3:
                    /*excluir vaga*/
                    numeroJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da vaga a ser excluída",
                    "Exclusão de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    
                    if(numeroJO == null) {
                        break;
                    }
                    numero = Integer.parseInt(numeroJO);
                    
                    rua = JOptionPane.showInputDialog(
                    null,
                    "Digite a rua da vaga a ser excluída",
                    "Exclusão de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    
                    if(rua == null) {
                        break;
                    }
                        
                    /* O método excluirVaga realiza as verificações necessárias para a exclusão da vaga*/
                    if(opVaga.excluirVaga(vagas, tickets, rua, numero) == true) {
                        JOptionPane.showMessageDialog(null, "Vaga\nrua: " + rua + "\nnúmero: " + numero + "\nexcluída com sucesso!",
                        "Exclusão de vaga", JOptionPane.INFORMATION_MESSAGE);
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    numeroJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da vaga que você deseja editar",
                    "Editar vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    
                    if(numeroJO == null) {
                        break;
                    }
                    numero = Integer.parseInt(numeroJO);
                    
                    rua = JOptionPane.showInputDialog(
                    null,
                    "Digite a rua da vaga que você deseja editar",
                    "Editar vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    
                    if(rua == null) {
                        break;
                    }                 
                    
                    String numeroNovoJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da nova vaga",
                    "Editar vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    
                    if(numeroNovoJO == null) {
                        break;
                    }
                    int numeroNovo = Integer.parseInt(numeroJO);
                    
                    String ruaNova = JOptionPane.showInputDialog(
                    null,
                    "Digite a rua da nova vaga",
                    "Editar vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );
                    
                    if(ruaNova == null) {
                        break;
                    }
                    
                    if(opVaga.editarVaga(vagas, rua, numero, ruaNova, numeroNovo) == true) {
                        JOptionPane.showMessageDialog(null, "Vaga editada com sucesso!",
                        "Editar vaga", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Vaga não existente!",
                    "Editar vaga", JOptionPane.ERROR_MESSAGE);
                    }
                break;                 
                case 5:
                    /*alterar disponibilidade da vaga*/
                    numeroJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da vaga para alterar sua disponibilidade",
                    "Disponibilidade de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(numeroJO == null) {
                        break;
                    }
                    numero = Integer.parseInt(numeroJO);
                    
                    rua = JOptionPane.showInputDialog(
                    null,
                    "Digite a rua da vaga para alterar sua disponibilidade",
                    "Disponibilidade de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(rua == null) {
                        break;
                    }  
  
                    String status = JOptionPane.showInputDialog(
                    null,
                    "Digite o novo status da vaga (DISPONIVEL ou INDISPONIVEL)",
                    "Disponibilidade de vaga",
                    JOptionPane.PLAIN_MESSAGE
                    );                    
                    if(rua == null) {
                        break;
                    } 
                    VagaStatus statusV = VagaStatus.valueOf(status.toUpperCase());
                    
                    if(statusV == VagaStatus.OCUPADA) {
                        JOptionPane.showMessageDialog(null, "Não é possivel deixar a vaga Ocupada!",
                        "Disponibilidade de vaga", JOptionPane.ERROR_MESSAGE);
                        break;
                    }                 
                    if(opVaga.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        JOptionPane.showMessageDialog(null, "Disponibilidade da vaga alterada com sucesso!",
                        "Disponibilidade de vaga", JOptionPane.INFORMATION_MESSAGE);
                    }
                break;
                case 6:
                break;
                default:
                    JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
                break;
            }    
        }while(opcao2 != 6);
               
    }
}
