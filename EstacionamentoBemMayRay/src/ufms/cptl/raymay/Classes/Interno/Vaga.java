/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno;

import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
/**
 *
 * @author ra
 */
public class Vaga {

    /* A vaga contém uma chave composta => numero e rua */
    private int numero;
    private String rua;
    private VagaStatus status; /* OCUPADA, DISPONIVEL, INDISPONIVEL */
    
    /* A vaga possuirá somente UM tipo de veículo e somente esse tipo pode ser estacionado nela */
    public TipoVeiculo tipo; 

    public Vaga(int numero, String rua, VagaStatus status, TipoVeiculo tipo) {
        this.numero = numero;
        this.rua = rua;
        this.status = status;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return ("Vaga:\n rua: " + rua + "\n numero: " + numero + "\n status: " + status + "\n tipo: " + tipo);
    }  
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public VagaStatus getStatus() {
        return status;
    }

    public void setStatus(VagaStatus status) {
        this.status = status;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

}