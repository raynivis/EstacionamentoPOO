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
    
    
    /*Procura um Cliente na lista de Clientes e mostra na tela a suas informaçoes */
    /*retorna verdadeiro se conseguir encontrar o cliente e falso se o cpf do cliente não 
    estar cadastrado*/
    public Cliente verificarCliente(List<Cliente> clientes, String documento){
        for(Cliente i : clientes) {
            if (i.getCpf().equals(documento)) {
                return i;
            }
        }
        return null;
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
    /*retorna vazio pois nao eh necessario nenhuma verificacao*/
    public void editarCliente(Cliente editarC, String nomeNovo, String telefoneNovo) {                                                        
        editarC.setNome(nomeNovo);                                                         
        editarC.setTelefone(telefoneNovo);                                                                                           
    }
    
    /*Gerenciar veiculos do cliente a partir do documento*/
    /*Operacao de gerenciar os veiculos do Cliente, nele voce pode adicionar ou excluir veiculo*/
    public Veiculo verificarVeiculo(List<Cliente> clientes, String placa){
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                    if(v.getPlaca().equals(placa)) {                                               
                            return v;
                    }
            }
        }
        return null;
    }
    
    public boolean apagaVeiculo(List<Cliente> clientes, String placa){
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                    if(v.getPlaca().equals(placa)) {                                               
                            c.getVeiculos().remove(v);
                            return true;
                    }
            }
        }
        return false;
    }
    
    
    public void relatorioCliente(List<Cliente> clientes) {
        for(Cliente c : clientes) {
            System.out.println(c.toString());
            System.out.println("Veiculos:");
            for(Veiculo v : c.getVeiculos()){
                System.out.println("        Placa: " + v.getPlaca());
            }
            System.out.println("///////////////////////////////////////////////////");
        }
    }
    public Cliente relatorioCliente(List<Cliente> clientes, String documento) {
        for(Cliente c : clientes) {
            if(c.getCpf().equals(documento)) {
                System.out.println("///////////////////////////////////////////////////");
                System.out.println(c.toString());
                System.out.println("Veiculos:");
                for(Veiculo v : c.getVeiculos()){
                    System.out.println("        Placa: " + v.getPlaca());
                }
                System.out.println("///////////////////////////////////////////////////");
                return c;
            }
        }
        return null;
    }
}    
         
