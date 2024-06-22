/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Externo.Automovel;

import java.io.Serializable;

/**
 *
 * @author ra
 */
public class Veiculo implements Serializable{
    
    /* Atributos característicos do veículo */
    private String placa; /* A placa funciona como a chave primária do veículo */
    private Modelo model;
    private Cor color;
    private double contribuido;

   public Veiculo(String placa, Modelo model, Cor color) {
        this.placa = placa;
        this.model = model;
        this.color = color;
        this.contribuido = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public Modelo getModel() {
        return model;
    }

    public Cor getColor() {
        return color;
    }

    public void setColor(Cor color) {
        this.color = color;
    }

    public double getContribuido() {
        return contribuido;
    }
    public void adicionarContribuicao(double contribuicao){
        contribuido += contribuicao;
    }
    
    @Override
    public String toString() {
        return "   Placa: " + placa + "\n   Marca: " + model.getMarca() +  "\n   Modelo: "+ model.getModelo() +
        "\n   Tipo de Veiculo: " + model.getTipoVeiculo() +"\n   Cor: " +color.getCor()+ "\n   Descrição: " + color.getDescricao()
                + "\n";
    }
    
        
}
