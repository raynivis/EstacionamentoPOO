/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interno;

import ufms.cptl.raymay.Externo.Automovel.Modelo;

/**
 *
 * @author ra
 */
public class Vaga {

    public enum VagaStatus {
        DISPONIVEL, OCUPADA, INDISPONIVEL
    }
    
    private int numero;
    private String rua;
    private VagaStatus status;
    public Modelo.Tipo tipo;

    public Vaga(int numero, String rua, VagaStatus status, Modelo.Tipo tipo) {
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

    public Modelo.Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Modelo.Tipo tipo) {
        this.tipo = tipo;
    }

}
