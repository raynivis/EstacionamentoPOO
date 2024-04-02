/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Externo.Automovel;

/**
 *
 * @author ra
 */
public class Modelo {
    /*Enumeração do tipo de veiculo, consideramos a melhor opção pra identificar
    do veículo*/
    public enum Tipo {
        MOTOCICLETA, MEDIOPORTE, GRANDEPORTE    
    }
    
    /*Marca do veículo a considerar pelo veículo*/
    private String marca;
    /*especificação do modelo do carro*/
    private String modelo;
    /*especificação do tipo de automovel pelo porte de enum*/
    private Tipo tipoVeiculo;
    

    public Modelo(String marca, String modelo, Tipo tipoVeiculo) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipoVeiculo = tipoVeiculo;
    }

    public Tipo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(Tipo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
     public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
}
