/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceGrafica;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceTerminal;
import ufms.cptl.raymay.Interface.UserInterface.UserInterface;
import static ufms.cptl.raymay.InterfaceOp.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
/**
 *
 * @author maymi
 */
public class InterfaceOpcaoCliente{
    OperacoesCliente opCliente = new OperacoesCliente();
    
    ItensMenu menuc = new ItensMenu(); /* menuc = Menu de gerencia de Clientes */
    ItensMenu menuve = new ItensMenu(); /*menuve = Menu de gerencia de Veículos */
    
    AuxiliarInterfaceCadastraVeiculo InVeiculo = new AuxiliarInterfaceCadastraVeiculo();
    
    int opcao2;
    int opcao3;
    Scanner scanner = new Scanner(System.in);
    
    /* Método geral das opções do cliente que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao cliente */
    public void opcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter, int face) { 
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            if(face == 0){
                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                opcao2 = interfaces.imprimeCliente();
            }
            else {
                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                opcao2 = interfaces.imprimeCliente();
            }
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        String nome, cpf;
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            nome = interfaces.receberString("Digite o nome");
                            cpf = interfaces.receberString("DIgite o cpf");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            nome = interfaces.receberString("Digite o nome");
                            cpf = interfaces.receberString("DIgite o cpf");
                        }
                        
                        if(opCliente.verificarCliente(clientes, cpf) != null) {
                            if(face == 0) {
                                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                interfaces.mensagem("Erro ao cadastrar: CPF já existente no sistema!");
                            }else {
                                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                interfaces.mensagem("Erro ao cadastrar: CPF já existente no sistema!");
                            }
                            break;
                        }
                        
                        String telefone;
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            telefone = interfaces.receberString("Digite o telefone");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            telefone = interfaces.receberString("Digite o telefone");
                        }
                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);
                         
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Adicione informações do veículo do cliente!");
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Adicione informações do veículo do cliente!");
                        }
                        Veiculo Novoveiculo = InVeiculo.receberVeiculo(clientes, novoCliente, inter, face);                      
                        
                         if(Novoveiculo != null) {
                            novoCliente.addVeiculo(Novoveiculo);
                            if(face == 0) {
                                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                interfaces.mensagem("Cadastro:\n" + novoCliente.toString() + "\nFinalizado com sucesso!");
                            }else {
                                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                interfaces.mensagem("Cadastro:\n" + novoCliente.toString() + "\nFinalizado com sucesso!");
                            }
                            clientes.add(novoCliente);
                         }
                         else {
                            /*limpar a variavel novo cliente*/
                            if(face == 0) {
                                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                interfaces.mensagem("Erro ao cadastrar: Placa já existente no sistema.");
                            }else {
                                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                interfaces.mensagem("Erro ao cadastrar: Placa já existente no sistema.");
                            }
                         }                   
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            cpf = interfaces.receberString("Digite o CPF");
                            if(opCliente.relatorioCliente(clientes, cpf) == null){
                                interfaces.mensagem("\nCliente não encontrado!\n");
                            }  
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            cpf = interfaces.receberString("Digite o CPF");
                            if(opCliente.relatorioCliente(clientes, cpf) == null){
                                interfaces.mensagem("\nCliente não encontrado!\n");
                            }  
                        }    
                    break;   
                    case 3:
                        /*excluir cliente*/
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            cpf = interfaces.receberString("Digite o CPF");
                        
                            Cliente clieteEx = opCliente.verificarCliente(clientes, cpf);
                            if(clieteEx == null) {
                                interfaces.mensagem("\nErro: Cliente nao encontrado!\n");
                                break;
                            }
                            if(opCliente.excluirCliente(clientes, clieteEx, tickets) == true) {
                                clieteEx = null;
                                interfaces.mensagem("\nCliente excluído com sucesso!\n");
                            }else {
                                interfaces.mensagem("\nErro: Não é possivel excluir o cliente, pois existe um Ticket em seu nome!\n");
                                break;
                            }
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            cpf = interfaces.receberString("Digite o CPF");
                        
                            Cliente clieteEx = opCliente.verificarCliente(clientes, cpf);
                            if(clieteEx == null) {
                                interfaces.mensagem("\nErro: Cliente nao encontrado!\n");
                                break;
                            }
                            if(opCliente.excluirCliente(clientes, clieteEx, tickets) == true) {
                                clieteEx = null;
                                interfaces.mensagem("\nCliente excluído com sucesso!\n");
                            }else {
                                interfaces.mensagem("\nErro: Não é possivel excluir o cliente, pois existe um Ticket em seu nome!\n");
                                break;
                            }
                        }    
                    break;    
                    case 4:
                        /*editar cliente*/
                        String novoNome, novoTelefone;
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            cpf = interfaces.receberString("Digite o CPF:");
                            if(opCliente.verificarCliente(clientes, cpf) == null) {
                               interfaces.mensagem("\nErro: cliente não econtrado!\n");
                                break; 
                            }                                                  
                            interfaces.mensagem("Digite o novo nome:");
                            novoNome = scanner.nextLine();
                            interMensagem("Digite o novo telefone:");
                            novoTelefone = scanner.nextLine();
                        }else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            cpf = interfaces.receberString("Digite o CPF:");
                            if(opCliente.verificarCliente(clientes, cpf) == null) {
                               interfaces.mensagem("\nErro: cliente não econtrado!\n");
                                break; 
                            }                                                  
                            interfaces.mensagem("Digite o novo nome:");
                            novoNome = scanner.nextLine();
                            interMensagem("Digite o novo telefone:");
                            novoTelefone = scanner.nextLine();
                        }    
                        opCliente.editarCliente(opCliente.verificarCliente(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veículos do cliente*/
                        Cliente operador;
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            cpf = interfaces.receberString("Digite o CPF do cliente que deseja gerenciar os veículos");
                            operador = opCliente.verificarCliente(clientes, cpf);
                            if(operador == null) {
                               interfaces.mensagem("\nErro: Cliente não econtrado!\n");
                                break; 
                            }
                        
                            interfaces.mensagem("Os veículos do cliente " + operador.getNome() + " são:\n");
                        }else{
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            cpf = interfaces.receberString("Digite o CPF do cliente que deseja gerenciar os veículos");
                            operador = opCliente.verificarCliente(clientes, cpf);
                            if(operador == null) {
                               interfaces.mensagem("\nErro: Cliente não econtrado!\n");
                                break; 
                            }
                        
                            interfaces.mensagem("Os veículos do cliente " + operador.getNome() + " são:\n");
                        }
                        opCliente.mostraVeiculos(clientes, cpf); /* ARRUMAR */
                        do{
                            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                            de linhas das Classes da interface */
                            if(face == 0){
                                InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                opcao3 = interfaces.imprimeVeiculo();
                            }
                            else {
                                InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                opcao3 = interfaces.imprimeVeiculo();
                            }    
                            switch(opcao3){
                                case 1: /*Adicionar um veículo*/
                                    Veiculo veiculoAdicional = InVeiculo.receberVeiculo(clientes, operador, inter, face);
                                    if(face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                         
                                        if(veiculoAdicional != null) {
                                            operador.addVeiculo(veiculoAdicional);
                                            interfaces.mensagem("Veículo cadastrado com sucesso!!");
                                        }
                                        else {
                                            interfaces.mensagem("Erro: Placa já registrada no sistema!");
                                        }
                                    }else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                         
                                        if(veiculoAdicional != null) {
                                            operador.addVeiculo(veiculoAdicional);
                                            interfaces.mensagem("Veículo cadastrado com sucesso!!");
                                        }
                                        else {
                                            interfaces.mensagem("Erro: Placa já registrada no sistema!");
                                        }
                                    }
                                break;
                                case 2: /*Remover um veiculo*/
                                    String placa;
                                    if(face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        placa = interfaces.receberString("Digite a placa:");
                                        if(opCliente.apagaVeiculo(clientes, placa, tickets) == true) {
                                            interfaces.mensagem("Veículo excluído com sucesso!");
                                        }    
                                    }else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        placa = interfaces.receberString("Digite a placa:");
                                        if(opCliente.apagaVeiculo(clientes, placa, tickets) == true) {
                                            interfaces.mensagem("Veículo excluído com sucesso!");
                                        }   
                                    }
                                break;
                                case 3: /*Editar um veiculo*/
                                    String cor, descricao;
                                    if(face == 0) {
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        placa = interfaces.receberString("Digite a placa:");
                                        if(opCliente.verificarVeiculo(clientes, placa) != null) {                                   
                                            Veiculo auxVeiculo = opCliente.verificarVeiculo(clientes, placa);

                                            cor = interfaces.receberString("Digite a nova cor");
                                            descricao = interfaces.receberString("Digite a nova descrição");
                                            Cor ediColor = new Cor(cor, descricao);
                                            auxVeiculo.setColor(ediColor);

                                            interfaces.mensagem("Veículo editado com sucesso!");
                                        } else {
                                            interfaces.mensagem("\nVeículo não encontrado!\n");
                                        }
                                    }else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        placa = interfaces.receberString("Digite a placa:");
                                        if(opCliente.verificarVeiculo(clientes, placa) != null) {                                   
                                            Veiculo auxVeiculo = opCliente.verificarVeiculo(clientes, placa);

                                            cor = interfaces.receberString("Digite a nova cor");
                                            descricao = interfaces.receberString("Digite a nova descrição");
                                            Cor ediColor = new Cor(cor, descricao);
                                            auxVeiculo.setColor(ediColor);

                                            interfaces.mensagem("Veículo editado com sucesso!");
                                        } else {
                                            interfaces.mensagem("\nVeículo não encontrado!\n");
                                        }
                                    }    
                                break;
                                case 4:
                                break;
                                default:
                                    if(face == 0){
                                        InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                                        interfaces.mensagem("Insira uma opção válida!");               
                                    }
                                    else {
                                        InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                                        interfaces.mensagem("Insira uma opção válida!");                               
                                    } 
                                                break;
                            }
                        }while(opcao3 != 4);
                    break;    
                    case 6: 
                        /*listar todos os cadastros de cliente*/
                        List<String> listaC = opCliente.relatorioCliente(clientes);
                        if(face == 0) {
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            for(String c : listaC) {
                                interfaces.mensagem(c);
                            }
                        } else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            for(String c : listaC) {
                                interfaces.mensagem(c);
                            }    
                        }
                    break;
                    case 7:
                    break;
                    default:
                        if(face == 0){
                            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
                            interfaces.mensagem("Insira uma opção válida!");               
                        }
                        else {
                            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
                            interfaces.mensagem("Insira uma opção válida!");                               
                        } 
                    break;
                }
                
        }while(opcao2 != 7);
        
    }
}
