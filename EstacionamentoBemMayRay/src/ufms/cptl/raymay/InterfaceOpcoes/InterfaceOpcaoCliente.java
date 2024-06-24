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
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuCliente;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuVeiculo;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ClienteException;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.VeiculoException;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
/**
 *
 * @author maymi
 */
public class InterfaceOpcaoCliente{
    OperacoesCliente opCliente = new OperacoesCliente();
    OperacoesTicket opTicket = new OperacoesTicket();
    InterfaceException ex = new InterfaceException();
    AuxiliarInterfaceCadastraVeiculo InVeiculo = new AuxiliarInterfaceCadastraVeiculo();
    
    int opcao2;
    int opcao3;
    
    String nome;
    String cpf;
    /* Método geral das opções do cliente que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao cliente */
    public void realizarOpcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) { 
        do{
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */
            opcao2 = inter.imprimirMenu(InterMenuCliente.class, "Menu Cliente");
            try {
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        nome = inter.receberStringFormat("Digite o nome", "^[\\p{L}]+$", "nome");
                        cpf = inter.receberStringFormat("Digite o CPF a ser cadastrado (xxx.xxx.xxx-xx)", "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d", "CPF");

                        if(opCliente.buscar(clientes, cpf) != null) {
                            throw ex.new ClienteException("Cliente já existente");
                        }

                        String telefone = inter.receberStringFormat("Digite o telefone (xx xxxxx-xxxx)", "\\d\\d \\d\\d\\d\\d\\d-\\d\\d\\d\\d", "telefone");

                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);

                        inter.imprimirMensagem("Adicione informações do veículo do cliente!");
                        Veiculo Novoveiculo = InVeiculo.cadastrarVeiculo(clientes, novoCliente, inter);                      

                         if(Novoveiculo != null) {
                            novoCliente.addVeiculo(Novoveiculo);
                            inter.imprimirMensagem("Cadastro:\n" + novoCliente.toString() + "\nFinalizado com sucesso!");
                            clientes.add(novoCliente);
                         }   
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                        cpf = inter.receberString("Digite o CPF a ser consultado (xxx.xxx.xxx-xx)");
                        if(opCliente.buscarDocumentoParaRelatorio(clientes, cpf) == null){
                            throw ex.new ClienteException("Cliente não encontrado! (lembre-se de inserir no formato indicado)");
                        } else {
                            inter.imprimirMensagem(opCliente.buscarDocumentoParaRelatorio(clientes, cpf));
                        } 
                    break;   
                    case 3:
                        /*excluir cliente*/
                        cpf = inter.receberString("Digite o CPF a ser excluído (xxx.xxx.xxx-xx)");

                        Cliente clieteEx = opCliente.buscar(clientes, cpf);
                        if(clieteEx == null) {
                            throw ex.new ClienteException("Cliente não encontrado! (lembre-se de inserir no formato indicado)");
                        }
                        if(opCliente.excluir(clientes, clieteEx, tickets) == true) {
                            clieteEx = null;
                            inter.imprimirMensagem("\nCliente excluído com sucesso!\n");
                        }else {
                            throw ex.new ClienteException("Não é possivel excluir o cliente, pois existe um Ticket em seu nome!");
                        }   
                    break;    
                    case 4:
                        /*editar cliente*/
                        String novoNome, novoTelefone;
                        cpf = inter.receberString("Digite o CPF para editar o cadastro (xxx.xxx.xxx-xx)");
                        if(opCliente.buscar(clientes, cpf) == null) {
                           throw ex.new ClienteException("Cliente não encontrado (lembre-se de inserir no formato indicado)");
                        }                                                                        
                        novoNome = inter.receberStringFormat("Digite o novo nome:", "^[\\p{L}]+$", "nome");                     
                        novoTelefone = inter.receberStringFormat("Digite o novo telefone (xx xxxxx-xxxx)", "\\d\\d \\d\\d\\d\\d\\d-\\d\\d\\d\\d", "telefone");
                        opCliente.editar(opCliente.buscar(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veículos do cliente*/
                        Cliente operador;
                        cpf = inter.receberString("Digite o CPF do cliente que deseja gerenciar os veículos (xxx.xxx.xxx-xx)");
                        operador = opCliente.buscar(clientes, cpf);
                        if(operador == null) {
                           throw ex.new ClienteException("Cliente não encontrado! (lembre-se de inserir no formato indicado)"); 
                        }

                        inter.imprimirMensagem("Os veículos do cliente " + operador.getNome() + " são:\n");
                        inter.imprimirMensagem(opCliente.listarVeiculos(clientes, cpf));  
                                               
                        opcoesVeiculo(operador, clientes, tickets, inter);
                    break;    
                    case 6: 
                        /*listar todos os cadastros de cliente*/
                        List<String> listaC = opCliente.listarCadastros(clientes);
                        if(listaC == null) {
                            throw ex.new ClienteException("Nenhum cadastro de cliente encontrado!");
                        }
                        for(String c : listaC) {
                            inter.imprimirMensagem(c);
                        }
                    break;
                    case 7:
                    break;
                    default:
                        inter.imprimirException("Insira uma opção válida!");   
                    break;
                } 
            } catch (ClienteException e) {
                inter.imprimirException(e.getMessage());
            }     
        }while(opcao2 != 7);       
    }
    
    private void opcoesVeiculo(Cliente operador, List<Cliente> clientes, List<Ticket> tickets, UserInterface inter) { 
        do{
            /* Utiliza o método criado na classe InterfaceTerminal ou na Classe InterfaceGrafica, dependendo
            da escolha do usuário */
            opcao3 = inter.imprimirMenu(InterMenuVeiculo.class, "Menu gerenciamento de Veículo");
            try{
                switch(opcao3){
                    case 1: /*Adicionar um veículo*/
                        Veiculo veiculoAdicional = InVeiculo.cadastrarVeiculo(clientes, operador, inter);
                        if(veiculoAdicional != null) {
                            operador.addVeiculo(veiculoAdicional);
                            inter.imprimirMensagem("Veículo cadastrado com sucesso!!");
                        }
                        else {
                            throw ex.new VeiculoException("Placa já registrada no sistema!");
                        }
                    break;
                    case 2: /*Remover um veiculo*/
                        String placa = inter.receberString("Digite a placa:");
                        if(opCliente.apagarVeiculo(clientes, operador.getCpf(), placa, tickets) == true) {
                            inter.imprimirMensagem("Veículo excluído com sucesso!");
                        } else if(opTicket.verificarUtilizacaoParaVeiculo(clientes, placa, tickets) != null) {
                            throw ex.new VeiculoException("O veículo não pode ser excluído pois possui um ticket ATIVO (está estacionado)!");
                        } else {
                            throw ex.new VeiculoException("Veículo do cliente não encontrado!");
                        }
                    break;
                    case 3: /*Editar um veiculo*/
                        String cor, descricao;
                        placa = inter.receberString("Digite a placa:");
                        if(opCliente.buscarVeiculo(clientes, placa) != null) {                                   
                            Veiculo auxVeiculo = opCliente.buscarVeiculo(clientes, placa);

                            cor = inter.receberStringFormat("Digite a nova cor", "^[\\p{L}]+$", "cor");
                            descricao = inter.receberString("Digite a nova descrição");
                            Cor ediColor = new Cor(cor, descricao);
                            auxVeiculo.setColor(ediColor);

                            inter.imprimirMensagem("Veículo editado com sucesso!");
                        } else {
                            throw ex.new VeiculoException("Veículo não encontrado!");
                        }
                    break;
                    case 4:
                    break;
                    default:
                        inter.imprimirException("Insira uma opção válida!");      
                    break;
                }
            } catch (VeiculoException e) {
                inter.imprimirException(e.getMessage());
            }    
        }while(opcao3 != 4);  
    }
}
