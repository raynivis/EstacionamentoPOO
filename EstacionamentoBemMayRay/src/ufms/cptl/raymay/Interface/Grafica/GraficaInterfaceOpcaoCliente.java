/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Grafica;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.Terminal.AuxiliarInterfaceCadastraVeiculo;
import ufms.cptl.raymay.Interface.Terminal.ItensMenu;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
/**
 *
 * @author maymi
 */
public class GraficaInterfaceOpcaoCliente {
    OperacoesCliente opCliente = new OperacoesCliente();
    
    ItensMenu menuc = new ItensMenu(); /* menuc = Menu de gerencia de Clientes */
    ItensMenu menuve = new ItensMenu(); /*menuve = Menu de gerencia de Veículos */
    
    AuxiliarInterfaceCadastraVeiculo InVeiculo = new AuxiliarInterfaceCadastraVeiculo();
    
    byte opcao2;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    
    
    /* Método geral das opções do cliente que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao cliente */
    public void opcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {       
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            menuc.imprimeCliente(1);
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        String nome = JOptionPane.showInputDialog(
                        null,
                        "Digite o nome",
                        "Cadastro de cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(nome == null) {
                            break;
                        }
                        
                        String cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF",
                        "Cadastro de cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(cpf == null) {
                            break;
                        }
                        
                        if(opCliente.verificarCliente(clientes, cpf) != null) {
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: CPF já existente no sistema.",
                            "Cadastro de cliente", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        String telefone = JOptionPane.showInputDialog(
                        null,
                        "Digite o telefone",
                        "Cadastro de cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(telefone == null) {
                            break;
                        }
                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);
                        
                        JOptionPane.showMessageDialog(null, "Adicione informações do veículo do cliente",
                        "Cadastro de cliente", JOptionPane.INFORMATION_MESSAGE);
                        Veiculo Novoveiculo = InVeiculo.receberVeiculo(clientes, novoCliente);                      
                        
                         if(Novoveiculo != null) {
                             novoCliente.addVeiculo(Novoveiculo);

                             clientes.add(novoCliente);
                             JOptionPane.showMessageDialog(null, "Cadastro:" + novoCliente.toString() + "Finalizado com sucesso!",
                            "Cadastro de cliente", JOptionPane.INFORMATION_MESSAGE);
                         }
                         else {
                             /*limpar a variavel novo cliente*/
                             JOptionPane.showMessageDialog(null, "Erro ao cadastrar: Placa já existente no sistema.",
                            "Cadastro de cliente", JOptionPane.ERROR_MESSAGE);
                         }                   
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                        cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF",
                        "Consultar cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(cpf == null) {
                            break;
                        }
                        if(opCliente.relatorioCliente(clientes, cpf) == null){
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado!",
                            "Consultar cliente", JOptionPane.ERROR_MESSAGE);
                        }                        
                    break;   
                    case 3:
                        /*excluir cliente*/
                        cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF",
                        "Excluir cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(cpf == null) {
                            break;
                        }
                        Cliente clieteEx = opCliente.verificarCliente(clientes, cpf);
                        if(clieteEx == null) {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado!",
                            "Excluir cliente", JOptionPane.ERROR_MESSAGE);
                        }
                        if(opCliente.excluirCliente(clientes, clieteEx, tickets) == true) {
                            clieteEx = null;
                            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!",
                            "Excluir cliente", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Não é possível excluir o cliente, pois existe um Ticket em seu nome!",
                            "Excluir cliente", JOptionPane.ERROR_MESSAGE);
                        }
                    break;    
                    case 4:
                        /*editar cliente*/
                        cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF",
                        "Editar cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(cpf == null) {
                            break;
                        }
                        if(opCliente.verificarCliente(clientes, cpf) == null) {
                           JOptionPane.showMessageDialog(null, "Cliente não encontrado!",
                            "Editar cliente", JOptionPane.ERROR_MESSAGE);
                            break; 
                        }                                                  
                        String novoNome = JOptionPane.showInputDialog(
                        null,
                        "Digite o novo nome",
                        "Editar cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(novoNome == null) {
                            break;
                        }
                        String novoTelefone = JOptionPane.showInputDialog(
                        null,
                        "Digite o novo telefone",
                        "Editar cliente",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(novoTelefone == null) {
                            break;
                        }
                        opCliente.editarCliente(opCliente.verificarCliente(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veículos do cliente*/
                        cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF que deseja gerenciar os veículos",
                        "Gerenciar veículos",
                        JOptionPane.PLAIN_MESSAGE
                        );                   
                        if(cpf == null) {
                            break;
                        }
                        cpf = scanner.nextLine();
                        Cliente operador = opCliente.verificarCliente(clientes, cpf);
                        if(operador == null) {
                           JOptionPane.showMessageDialog(null, "Cliente não encontrado!",
                            "Gerenciar veículos", JOptionPane.ERROR_MESSAGE);
                            break; 
                        }
                        
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado!",
                        "Gerenciar veículos", JOptionPane.ERROR_MESSAGE);

                        opCliente.mostraVeiculos(clientes, cpf, 1);
                        do{
                            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                            de linhas das Classes da interface */
                            menuve.imprimeVeiculo(1);
                            opcao3 = scanner.nextByte();
                            scanner.nextLine();
                            switch(opcao3){
                                case 1: /*Adicionar um veículo*/
                                    Veiculo veiculoAdicional = InVeiculo.receberVeiculo(clientes, operador); 
                                    if(veiculoAdicional != null) {

                                        operador.addVeiculo(veiculoAdicional);

                                        JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!",
                                        "Adicionar veículo", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "Placa já cadastrada no sistema!",
                                        "Adicionar veículo", JOptionPane.ERROR_MESSAGE);
                                    }
                                break;
                                case 2: /*Remover um veiculo*/
                                    String placa = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a placa",
                                    "Excluir veículo",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(placa == null) {
                                        break;
                                    }
                                    if(opCliente.apagaVeiculo(clientes, placa, tickets) == true) {
                                        JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso!",
                                        "Excluir veículo", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                break;
                                case 3: /*Editar um veiculo*/
                                    placa = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a placa",
                                    "Editar veículo",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(placa == null) {
                                        break;
                                    }
                                    if(opCliente.verificarVeiculo(clientes, placa) != null) {                                   
                                        Veiculo auxVeiculo = opCliente.verificarVeiculo(clientes, placa);

                                    String cor = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a nova cor",
                                    "Editar veículo",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(cor == null) {
                                        break;
                                    }
                                    String descricao = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a nova descrição",
                                    "Editar veículo",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(descricao == null) {
                                        break;
                                    }
                                        Cor ediColor = new Cor(cor, descricao);

                                        auxVeiculo.setColor(ediColor);

                                        JOptionPane.showMessageDialog(null, "Veículo editado com sucesso!",
                                        "Editar veículo", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Veículo não encontrado!",
                                        "Editar veículo", JOptionPane.ERROR_MESSAGE);
                                    }
                                break;
                                case 4:
                                break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                                    "Alerta", JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                        }while(opcao3 != 4);
                    break;    
                    case 6: 
                        /*listar todos os cadastros de cliente*/
                        opCliente.relatorioCliente(clientes);
                    break;
                    case 7:
                    break;
                    default:
                        JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                        "Alerta", JOptionPane.WARNING_MESSAGE);
                    break;
                }
        }while(opcao2 != 7);
        
    }
}
