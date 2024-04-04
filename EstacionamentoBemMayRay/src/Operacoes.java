/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;
import ufms.cptl.raymay.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import java.util.Scanner;
import java.util.Collections;

/**
 *
 * @author ra
 */
public class Operacoes { /*Criamos essa classe para deixa o código do estacionamento mais limpo*/
    
    /*Scanner para pegar itens */
    Scanner scannner = new Scanner(System.in);
    
    /*Método para cadastrar um cliente na lista de cliente do Estacionamento Bem*/
    /*retorna verdadeiro se conseguir cadastrar e falso se ele achar um cpf ja cadastrado*/
    public boolean cadastrarCliente(List<Cliente> clientes, Cliente novo)
    {
        String cpfnovo = novo.getCpf();
        
        for(Cliente i : clientes) {
            if (i.getCpf().equals(cpfnovo)) {
                return false;
            }
        }
        clientes.add(novo);
        return true;
    }
    
    /*Procura um Cliente na lista de Clientes e mostra na tela a suas informaçoes */
    /*retorna verdadeiro se conseguir encontrar o cliente e falso se o cpf do cliente não 
    estar cadastrado*/
    public boolean consultarCliente(List<Cliente> clientes, String documento){
        for(Cliente i : clientes) {
            if (i.getCpf().equals(documento)) {
                System.out.println(i.toString());
                return true;
            }
        }
        return false;
    }
    
    /*Excluir o Cliente da lista de Clientes pelo CPF // talvez fazer de outras formas
    vai depender do professor*/
    /* retorna verdadeiro se o cliente for excluido e falso se o cliente não estiver com cpf
    cadastrado*/
    public boolean excluirCliente(List<Cliente> clientes, String documento){
        for(Cliente i : clientes) {
            if (i.getCpf().equals(documento)) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /*Editar dados dos clientes (não é recomendavel mudar o cpf, pelo menos em alguns sistemas) e 
    não devemos gerenciar os veiculos do cliente, pois tem outro metodo para isso*/
    /*retorna verdadeira se achar o cliente e retorna falso se não achar o cliente*/
    public boolean editarCliente(List<Cliente> clientes, String documento) {
        boolean pertence = consultarCliente(clientes, documento);
        
        if(pertence == true){
             for(Cliente i : clientes) {
                if (i.getCpf().equals(documento)) {                   
                    /*mensagem de interface para seleciona uma opcao para mudar de 1 a 2
                    Escrever que para editar veiculos precisa ir na opcao de gerenciar veiculo*/
                    int operacaoEditar = scannner.nextInt();
                    
                    scannner.nextLine(); /*Para evitar problema de puxar o inteiro na proxima 
                    leitura*/
                    
                    switch(operacaoEditar){
                        case 1: /*para mudar o nome*/
                            /*mensagem de interface para digitar o novo nome*/
                            String nomeNovo = scannner.nextLine();
                            i.setNome(nomeNovo);
                         break;
                        case 2: /*para mudar o telefone*/
                            /*mensagem de interface para digitar o novo telefone*/
                            String telefoneNovo = scannner.nextLine();
                            i.setTelefone(telefoneNovo);
                         break;
                        default: /*qualquer digito para voltar*/
                        break;                                               
                    }
                    break;    
                }
            }
            return true;
        } else {
            return false;
        }  
    }
    
    /*Gerenciar veiculos do cliente a partir do documento*/
    /*Retornar verdadeiro e falso*/
    
    public boolean gerenciarVeiculo(List<Cliente> clientes, String documento){
        boolean pertence = consultarCliente(clientes, documento);

            if(pertence == true){
                /*mensagem de interface para digitar a placa do carro a ser modificado*/
                
                for(Cliente i : clientes) {
                     if (i.getCpf().equals(documento)) {  
                         
                         String placaVeiculo = scannner.nextLine();
                        
                         for(Veiculo j :  i.getVeiculos()) {
                              if(j.getPlaca().equals(placaVeiculo)) {                                
                                 /*Criar uma função para gerenciar veiculos*/ 
                              }
                         break;
                        }
                     }
                    break;
                }
                return true;
            } else {
                return false;
            }       
    } 
    
    public void relatorioCliente(List<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).toString()); 
         }      
    }
         
    
    
    
         
    
    
            
}
