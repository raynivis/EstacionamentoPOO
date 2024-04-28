/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Externo.Automovel;

import ufms.cptl.raymay.Enum.TipoVeiculo;

/**
 *
 * @author ra
 */
public class Modelo {
    /* Enumeração do tipo de veiculo, consideramos a melhor opção pra identificar
    do veículo */
   
    
    /* Marca do veículo a considerar pelo veículo */
    private String marca;
    /* especificação do modelo do carro */
    private String modelo;
    /* especificação do tipo de automovel pelo porte de enum */
    private TipoVeiculo tipoVeiculo;
    

    public Modelo(String marca, String modelo, TipoVeiculo tipoVeiculo) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipoVeiculo = tipoVeiculo;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
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
