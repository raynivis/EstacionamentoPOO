package ufms.cptl.raymay.Operacoes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;
import ufms.cptl.raymay.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import java.util.Scanner;
import ufms.cptl.raymay.Externo.Automovel.Cor;

/**
 *
 * @author ra
 */

public class OperacoesCliente { /*Criamos essa classe para deixa o código do estacionamento mais limpo*/
    
    /*Scanner para pegar itens */
    Scanner scanner = new Scanner(System.in);
       
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
                    /*Escrever que para editar veiculos precisa ir na opcao de gerenciar veiculo*/                                      
                    String nomeNovo = scanner.nextLine();
                    i.setNome(nomeNovo);
                       
                    /*mensagem de interface para digitar o novo telefone*/
                    String telefoneNovo = scanner.nextLine();
                    i.setTelefone(telefoneNovo);                                                                 
                    }
                    scanner.close();
                    break;    
                }
             return true;
            } else {
            return false;
            }
    }
    
    /*Gerenciar veiculos do cliente a partir do documento*/
    /*Operacao de gerenciar os veiculos do Cliente, nele voce pode adicionar ou excluir veiculo*/
    /*Ajuda*/
    public boolean gerenciarVeiculo(List<Cliente> clientes, String documento){
        boolean pertence = consultarCliente(clientes, documento);

            if(pertence == true){ 
               /*se mudarmos a interface vai ter que mudar isso*/
                int operacaoVeiculo = scanner.nextInt();
                    
                scanner.nextLine(); /*Para evitar problema de puxar o inteiro na proxima 
                leitura*/
                
                for(Cliente i : clientes) {
                     if (i.getCpf().equals(documento)) {
                         System.out.println("1 - Adicionar Veiculo");
                         System.out.println("2 - Excluir Veiculo");                         
                        switch(operacaoVeiculo){
                            case 1: /*para adicionar um veiculo*/
                                /*mensagem de interface para digitar um novo veiculo*/
                                System.out.println("Digite a Cor, Descricao, Marca, Modelo, ");
                                System.out.println("Tipo do Veiculo(MOTOCICLETA, MEDIOPORTE, GRANDEPORTE) e a Placa");
                                String cor = scanner.nextLine();
                                String descricao = scanner.nextLine();
                                Cor color = new Cor(cor, descricao);

                                String marca = scanner.nextLine();
                                String modelo = scanner.nextLine();
                                String tipoV = scanner.nextLine();
                                Modelo.Tipo type = Modelo.Tipo.valueOf(tipoV.toUpperCase());
                                Modelo model = new Modelo(marca, modelo, type);

                                String placa = scanner.nextLine();    
                                
                                i.setVeiculoNaLista(placa, model, color);
                            break;
                            case 2: /*para excluir um veiculo*/
                                /*mensagem de interface para excluir um veiculo*/
                                System.out.println("Digite a placa para excluir:");
                                 String placaExcluir = scanner.nextLine(); 
                                 
                                for(Veiculo item : i.getVeiculos())
                                {
                                    if(item.getPlaca().equals(placaExcluir)){
                                        i.getVeiculos().remove(item);                                       
                                        break;
                                    }
                                }     
                            break;
                            default: /*qualquer digito para voltar*/
                            break;  
                            }
                        scanner.close();
                     }
                }
                    return true;
                }else{
                     return false;   
                }
    }           
    
    public void relatorioCliente(List<Cliente> clientes) {
        for (int i = 0; i <= clientes.size(); i++) {
            System.out.println(clientes.get(i).toString()); 
        }
    }
}    
         
