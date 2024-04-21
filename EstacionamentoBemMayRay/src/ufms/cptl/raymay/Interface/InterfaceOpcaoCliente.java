/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Externo.Automovel.Cor;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoCliente.imprimeCliente;
import static ufms.cptl.raymay.Interface.InterfaceEnumOpcao.OpcaoVeiculo.imprimeVeiculo;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoCliente {
    OperacoesCliente clie = new OperacoesCliente();
    
    InterfaceCadastraVeiculo InVeiculo = new InterfaceCadastraVeiculo();
    
    byte opcao2;
    byte opcao3;
    Scanner scanner = new Scanner(System.in);
    
    public void opcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {       
        do{
            /* Utiliza o método criado em OpcaoCliente no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            imprimeCliente();
            opcao2 = scanner.nextByte();
            scanner.nextLine();  
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        interMensagem("Digite o nome:");
                        String nome = scanner.nextLine();
                        interMensagem("Digite o CPF:");
                        String cpf = scanner.nextLine();
                        
                        if(clie.verificarCliente(clientes, cpf) != null) {
                            interMensagem("\nErro ao cadastrar: CPF já existente no sistema.\n");
                            break;
                        }
                        interMensagem("Digite o telefone:");
                        String telefone = scanner.nextLine();
                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);
                         
                        Veiculo Novoveiculo = InVeiculo.receberVeiculo(clientes, novoCliente);                      
                        
                         if(Novoveiculo != null) {
                             novoCliente.setVeiculoNaLista(Novoveiculo);
                             clientes.add(novoCliente);
                             interMensagem("\nCliente cadastrado com sucesso!\n");
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
                        if(clie.relatorioCliente(clientes, cpf) == null){
                            interMensagem("\nCliente não encontrado!\n");
                        }                        
                    break;   
                    case 3:
                        /*excluir cliente*/
                        interMensagem("Digite o CPF:");
                        cpf = scanner.nextLine();
                        if(clie.excluirCliente(clientes, cpf) == true) {
                            interMensagem("\nCliente excluído com sucesso!\n");
                        } else {
                            interMensagem("\nCliente não encotrado!\n");
                        }
                    break;    
                    case 4:
                        /*editar cliente*/
                        interMensagem("Digite o CPF:");
                        cpf = scanner.nextLine();
                        if(clie.verificarCliente(clientes, cpf) == null) {
                           interMensagem("\nErro: cliente não econtrado!\n");
                            break; 
                        }                                                  
                        interMensagem("Digite o novo nome:");
                        String novoNome = scanner.nextLine();
                        interMensagem("Digite o novo telefone:");
                        String novoTelefone = scanner.nextLine();
                        clie.editarCliente(clie.verificarCliente(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veiculos do cliente*/
                        interMensagem("Digite o CPF do cliente que deseja gerenciar os veículos:");
                        cpf = scanner.nextLine();
                        Cliente operador = clie.verificarCliente(clientes, cpf);
                        if(operador == null) {
                           interMensagem("\nErro: Cliente não econtrado!\n");
                            break; 
                        } 
                        do{
                            /* Utiliza o método criado em OpcaoVeiculo no package InterfaceEnumOpcao, reduzindo o tamanho
                            de linhas das classes da interface */
                            imprimeVeiculo();
                            opcao3 = scanner.nextByte();
                            scanner.nextLine();
                            switch(opcao3){
                                case 1: /*Adicionar um veiculo*/
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
                                    if(clie.apagaVeiculo(clientes, placa) == true) {
                                        interMensagem("\nVeículo excluído com sucesso!\n");
                                    } else {
                                        interMensagem("\nVeículo não encontrado!\n");
                                    }
                                break;
                                case 3: /*Editar um veiculo*/
                                    interMensagem("Digite a placa:");
                                    placa = scanner.nextLine();
                                    if(clie.verificarVeiculo(clientes, placa) != null) {                                   
                                        Veiculo auxVeiculo = clie.verificarVeiculo(clientes, placa);

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
                            }
                        }while(opcao3 != 4);
                    break;    
                    case 6: 
                        /*listar todos os cadastros de cliente*/
                        clie.relatorioCliente(clientes);
                    break;    
                }
        }while(opcao2 != 7);
        
    }
}
