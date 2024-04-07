/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interno;

import java.util.Date;

/**
 *
 * @author ra
 */
public class Ticket {
 
    private int codigo;
    private String status;
    private Date inicio;
    private Date fim;
    private int numeroVaga;
    private String ruaVaga;
    private String placaVeiOcupado;
    private Tarifa tarifaAtual;    
    
    public Ticket(int codigo, int numeroVaga, String ruaVaga, String placaVeiOcupado, Tarifa tarifaAtual) {
        this.codigo = codigo;
        this.numeroVaga = numeroVaga;
        this.ruaVaga = ruaVaga;
        this.placaVeiOcupado = placaVeiOcupado;
        this.tarifaAtual = tarifaAtual;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(int numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public String getRuaVaga() {
        return ruaVaga;
    }

    public void setRuaVaga(String ruaVaga) {
        this.ruaVaga = ruaVaga;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public String getPlacaVeiOcupado() {
        return placaVeiOcupado;
    }

    public void setPlacaVeiOcupado(String placaVeiOcupado) {
        this.placaVeiOcupado = placaVeiOcupado;
    }

    public Tarifa getTarifaAtual() {
        return tarifaAtual;
    }

    public void setTarifaAtual(Tarifa tarifaAtual) {
        this.tarifaAtual = tarifaAtual;
    }

    
}
