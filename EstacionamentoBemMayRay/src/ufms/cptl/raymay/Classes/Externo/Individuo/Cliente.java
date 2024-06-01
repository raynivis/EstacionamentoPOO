/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Externo.Individuo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import java.util.List;
/**
 *
 * @author ra
 */
public class Cliente {
    
    /* Atributos característicos do cliente, possui também a lista de veículos pertencente ao cliente */
    private String nome;
    private String cpf; /* o CPF funciona como a chave primaria do cliente */
    private String telefone;
    private List<Veiculo> veiculos;
    

    
    /* Para cadastrar o cliente é necessário cadastrar pelo menos um veículo na lista de veículos.
    Este construtor é instanciado no cadastro de qualquer cliente */
    public Cliente(String nome, String cpf, String telefone, List<Veiculo> veiculos) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.veiculos = veiculos;
        
    }
     
    /* Método para adicionar um novo veículo na lista de veículos do cliente */
    public void addVeiculo(Veiculo novoVeiculo)
    {
        this.veiculos.add(novoVeiculo);
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
    
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "   Cliente: " + nome + "\n   CPF: " + cpf + "\n   Telefone: " + telefone;
    } 
    
    
}
