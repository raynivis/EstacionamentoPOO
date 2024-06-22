/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno;

import java.io.Serializable;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
/**
 *
 * @author ra
 */
public class Vaga implements Serializable{

    /* A vaga contém uma chave composta => numero e rua */
    private int numero;
    private String rua;
    private VagaStatus status; /* OCUPADA, DISPONIVEL, INDISPONIVEL */
    
    /* A vaga possuirá somente UM tipo de veículo e somente esse tipo pode ser estacionado nela */
    public TipoVeiculo tipo; 


    public Vaga(int numero, String rua, TipoVeiculo tipo) {
        this.numero = numero;
        this.rua = rua;
        this.status = VagaStatus.DISPONIVEL; /* Vaga acabou de ser cadastrada, portando está disponível até
        algum estacionamento de veículo ou até que se torne indisponível */
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
    /*metodos para verifciar a disponibilidade da vaga*/
    public boolean isDisponivel(){
        if(this.status == VagaStatus.DISPONIVEL)
            return true;
        return false;     
    }
    public boolean isOcupada(){
        if(this.status == VagaStatus.OCUPADA)
            return true;
        return false;     
    }
    public boolean isIndisponivel(){
        if(this.status == VagaStatus.INDISPONIVEL)
            return true;
        return false;     
    }
   
    /*Alterar o status da vaga por meio desses 3 metodos*/
    public void disponibilizar() {
        this.status = VagaStatus.DISPONIVEL;
    }
    
    public void ocupar() {
        this.status = VagaStatus.OCUPADA;
    }
    public void indisponibilizar() {
        this.status = VagaStatus.INDISPONIVEL;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }
}
