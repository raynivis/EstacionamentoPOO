/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Classes.Interno.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
/**
 *
 * @author maymi
 */
public class InterfaceOpcaoCliente {
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
            menuc.imprimeCliente();
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        interMensagem("Digite o nome:");
                        String nome = scanner.nextLine();
                        interMensagem("Digite o CPF:");
                        String cpf = scanner.nextLine();
                        
                        if(opCliente.verificarCliente(clientes, cpf) != null) {
                            interMensagem("\nErro ao cadastrar: CPF já existente no sistema.\n");
                            break;
                        }
                        interMensagem("Digite o telefone:");
                        String telefone = scanner.nextLine();
                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);
                         
                        interMensagem("\nAdicione informações do veículo do cliente");
                        Veiculo Novoveiculo = InVeiculo.receberVeiculo(clientes, novoCliente);                      
                        
                         if(Novoveiculo != null) {
                             novoCliente.setVeiculoNaLista(Novoveiculo);
                             clientes.add(novoCliente);
                             interMensagem("\nCadastro:");
                             interMensagem(novoCliente.toString());
                             interMensagem("Finalizado com sucesso!\n");
                         }
                         else {
                             /*limpar a variavel novo cliente*/
                             interMensagem("\nErro ao cadastrar: Placa já existente no sistema.\n");
                         }                   
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                        interMensagem("Digite o CPF:");
                        cpf = scanner.nextLine();
                        if(opCliente.relatorioCliente(clientes, cpf) == null){
                            interMensagem("\nCliente não encontrado!\n");
                        }                        
                    break;   
                    case 3:
                        /*excluir cliente*/
                        interMensagem("Digite o CPF:");
                        cpf = scanner.nextLine();
                        Cliente clieteEx = opCliente.verificarCliente(clientes, cpf);
                        if(clieteEx == null) {
                            interMensagem("\nErro: Cliente nao encontrado!\n");
                        }
                        if(opCliente.excluirCliente(clientes, clieteEx, tickets) == true) {
                            clieteEx = null;
                            interMensagem("\nCliente excluído com sucesso!\n");
                        }
                        else {
                            interMensagem("\nErro: Não é possivel excluir o cliente, pois existe um Ticket em seu nome!\n");
                        }
                    break;    
                    case 4:
                        /*editar cliente*/
                        interMensagem("Digite o CPF:");
                        cpf = scanner.nextLine();
                        if(opCliente.verificarCliente(clientes, cpf) == null) {
                           interMensagem("\nErro: cliente não econtrado!\n");
                            break; 
                        }                                                  
                        interMensagem("Digite o novo nome:");
                        String novoNome = scanner.nextLine();
                        interMensagem("Digite o novo telefone:");
                        String novoTelefone = scanner.nextLine();
                        opCliente.editarCliente(opCliente.verificarCliente(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veículos do cliente*/
                        interMensagem("Digite o CPF do cliente que deseja gerenciar os veículos:");
                        cpf = scanner.nextLine();
                        Cliente operador = opCliente.verificarCliente(clientes, cpf);
                        if(operador == null) {
                           interMensagem("\nErro: Cliente não econtrado!\n");
                            break; 
                        }
                        
                        interMensagem("Os veículos do cliente de CPF " + cpf + " são:\n");
                        opCliente.mostraVeiculos(clientes, cpf);
                        do{
                            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                            de linhas das Classes da interface */
                            menuve.imprimeVeiculo();
                            opcao3 = scanner.nextByte();
                            scanner.nextLine();
                            switch(opcao3){
                                case 1: /*Adicionar um veículo*/
                                    Veiculo veiculoAdicional = InVeiculo.receberVeiculo(clientes, operador); 
                                    if(veiculoAdicional != null) {
                                        operador.setVeiculoNaLista(veiculoAdicional);
                                        interMensagem("\nVeículo cadastrado com sucesso!!\n");
                                    }
                                    else {
                                        interMensagem("\nErro: Placa já registrada no sistema!\n");
                                    }
                                break;
                                case 2: /*Remover um veiculo*/
                                    interMensagem("Digite a placa:");
                                    String placa = scanner.nextLine();
                                    if(opCliente.apagaVeiculo(clientes, placa, tickets) == true) {
                                        interMensagem("\nVeículo excluído com sucesso!\n");
                                    }
                                break;
                                case 3: /*Editar um veiculo*/
                                    interMensagem("Digite a placa:");
                                    placa = scanner.nextLine();
                                    if(opCliente.verificarVeiculo(clientes, placa) != null) {                                   
                                        Veiculo auxVeiculo = opCliente.verificarVeiculo(clientes, placa);

                                        interMensagem("Digite a nova cor e descrição:");
                                        String cor = scanner.nextLine();
                                        String descricao = scanner.nextLine();
                                        Cor ediColor = new Cor(cor, descricao);

                                        auxVeiculo.setColor(ediColor);

                                        interMensagem("\nVeículo editado com sucesso!\n");
                                    } else {
                                        interMensagem("\nVeículo não encontrado!\n");
                                    }
                                break;
                                default:
                                    interMensagem("\nInsira uma opção válida!\n");
                                break;
                            }
                        }while(opcao3 != 4);
                    break;    
                    case 6: 
                        /*listar todos os cadastros de cliente*/
                        opCliente.relatorioCliente(clientes);
                    break; 
                    default:
                        interMensagem("\nInsira uma opção válida!\n");
                    break;
                }
        }while(opcao2 != 7);
        
    }
}
