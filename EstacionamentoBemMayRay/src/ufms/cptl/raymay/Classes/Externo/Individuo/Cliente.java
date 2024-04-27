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
    private String nome;
    private String cpf;
    private String telefone;
    private List<Veiculo> veiculos;
    

    /*PARA CADASTRAR O CLIENTE É NECESSARIO CADASTRAR PELO MENOS UM VEICULO NA LISTA DE CARROS
    ESTES CONSTRUTOR É INSTACIADO NO CADASTRO DE QUALQUER CLIENTE*/
    public Cliente(String nome, String cpf, String telefone, List<Veiculo> veiculos) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.veiculos = veiculos;
        
    }
    
    /*ADICIONAR UM NOVO VEICULO NA LISTA DE VEICULOS DO CLIENTE*/
    public void setVeiculoNaLista(Veiculo novoVeiculo)
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

    @Override /*Java faz isso de precaucao?*/
    public String toString() {
        return "Cliente: " + nome + "\nCPF: " + cpf + "\nTelefone: " + telefone;
    } 
    
    
}
