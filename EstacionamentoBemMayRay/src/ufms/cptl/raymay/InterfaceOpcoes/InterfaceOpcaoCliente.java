/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;


import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
/**
 *
 * @author maymi
 */
public class InterfaceOpcaoCliente{
    OperacoesCliente opCliente = new OperacoesCliente();
    
    AuxiliarInterfaceCadastraVeiculo InVeiculo = new AuxiliarInterfaceCadastraVeiculo();
    
    int opcao2;
    int opcao3;
    
    /* Método geral das opções do cliente que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao cliente */
    public void opcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) { 
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            if(inter instanceof InterfaceTerminal){
                inter = (InterfaceTerminal) inter;
                opcao2 = inter.imprimeCliente();
            }
            else {
               inter = (InterfaceGrafica) inter;
                opcao2 = inter.imprimeCliente();
            }
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        String nome = inter.receberString("Digite o nome");
                        String cpf = inter.receberString("DIgite o cpf");
                        
                        if(opCliente.verificarCliente(clientes, cpf) != null) {
                            inter.mensagem("Erro ao cadastrar: CPF já existente no sistema!");
                            break;
                        }
                        
                        String telefone = inter.receberString("Digite o telefone");
                        
                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);
                         
                        inter.mensagem("Adicione informações do veículo do cliente!");
                        Veiculo Novoveiculo = InVeiculo.receberVeiculo(clientes, novoCliente, inter);                      
                        
                         if(Novoveiculo != null) {
                            inter.mensagem("Cadastro:\n" + novoCliente.toString() + "\nFinalizado com sucesso!");
                            clientes.add(novoCliente);
                         }
                         else {
                            /*limpar a variavel novo cliente*/
                            inter.mensagem("Erro ao cadastrar: Placa já existente no sistema.");
                         }                   
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                        cpf = inter.receberString("Digite o CPF");
                        if(opCliente.relatorioCliente(clientes, cpf) == null){
                            inter.mensagem("\nCliente não encontrado!\n");
                        } else {
                            inter.mensagem(opCliente.relatorioCliente(clientes, cpf));
                        } 
                    break;   
                    case 3:
                        /*excluir cliente*/
                        cpf = inter.receberString("Digite o CPF");
                        
                        Cliente clieteEx = opCliente.verificarCliente(clientes, cpf);
                        if(clieteEx == null) {
                            inter.mensagem("\nErro: Cliente nao encontrado!\n");
                            break;
                        }
                        if(opCliente.excluirCliente(clientes, clieteEx, tickets) == true) {
                            clieteEx = null;
                            inter.mensagem("\nCliente excluído com sucesso!\n");
                        }else {
                            inter.mensagem("\nErro: Não é possivel excluir o cliente, pois existe um Ticket em seu nome!\n");
                            break;
                        }
                    break;    
                    case 4:
                        /*editar cliente*/
                        String novoNome, novoTelefone;
                        cpf = inter.receberString("Digite o CPF:");
                        if(opCliente.verificarCliente(clientes, cpf) == null) {
                           inter.mensagem("\nErro: cliente não econtrado!\n");
                           break; 
                        }                                                                        
                        novoNome = inter.receberString("Digite o novo nome:");                     
                        novoTelefone = inter.receberString("Digite o novo telefone:");
                        opCliente.editarCliente(opCliente.verificarCliente(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veículos do cliente*/
                        Cliente operador;
                        cpf = inter.receberString("Digite o CPF do cliente que deseja gerenciar os veículos");
                        operador = opCliente.verificarCliente(clientes, cpf);
                        if(operador == null) {
                           inter.mensagem("\nErro: Cliente não econtrado!\n");
                            break; 
                        }

                        inter.mensagem("Os veículos do cliente " + operador.getNome() + " são:\n");
                        inter.mensagem(opCliente.mostraVeiculos(clientes, cpf));  
                                               
                        do{
                            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                            de linhas das Classes da interface */
                            opcao3 = inter.imprimeVeiculo(); 
                            switch(opcao3){
                                case 1: /*Adicionar um veículo*/
                                    Veiculo veiculoAdicional = InVeiculo.receberVeiculo(clientes, operador, inter);
                                    if(veiculoAdicional != null) {
                                        operador.addVeiculo(veiculoAdicional);
                                        inter.mensagem("Veículo cadastrado com sucesso!!");
                                    }
                                    else {
                                        inter.mensagem("Erro: Placa já registrada no sistema!");
                                    }
                                break;
                                case 2: /*Remover um veiculo*/
                                    String placa;
                                    placa = inter.receberString("Digite a placa:");
                                    if(opCliente.apagaVeiculo(clientes, placa, tickets) == true) {
                                        inter.mensagem("Veículo excluído com sucesso!");
                                    } 
                                break;
                                case 3: /*Editar um veiculo*/
                                    String cor, descricao;
                                    placa = inter.receberString("Digite a placa:");
                                    if(opCliente.verificarVeiculo(clientes, placa) != null) {                                   
                                        Veiculo auxVeiculo = opCliente.verificarVeiculo(clientes, placa);

                                        cor = inter.receberString("Digite a nova cor");
                                        descricao = inter.receberString("Digite a nova descrição");
                                        Cor ediColor = new Cor(cor, descricao);
                                        auxVeiculo.setColor(ediColor);

                                        inter.mensagem("Veículo editado com sucesso!");
                                    } else {
                                        inter.mensagem("\nVeículo não encontrado!\n");
                                    }
                                break;
                                case 4:
                                break;
                                default:
                                    inter.mensagem("Insira uma opção válida!");     
                                break;
                            }
                        }while(opcao3 != 4);
                    break;    
                    case 6: 
                        /*listar todos os cadastros de cliente*/
                        List<String> listaC = opCliente.relatorioCliente(clientes);
                        for(String c : listaC) {
                            inter.mensagem(c);
                        }
                    break;
                    case 7:
                    break;
                    default:
                        inter.mensagem("Insira uma opção válida!");   
                    break;
                }             
        }while(opcao2 != 7);
        
    }
}
