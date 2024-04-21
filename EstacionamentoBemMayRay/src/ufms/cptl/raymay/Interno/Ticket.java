/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interno;

import java.time.LocalDateTime;
import ufms.cptl.raymay.Enum.Operando;


/**
 *
 * @author ra
 */
public class Ticket {

    private static int proxCodigo = 1;
    private final int codigo;
    private Operando status;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private int numeroVaga;
    private String ruaVaga;
    private String placaVeiOcupado;
    private Tarifa tarifaAtual;    
    
    public Ticket(int numeroVaga, String ruaVaga, String placaVeiOcupado, Tarifa tarifaAtual) {   
        this.numeroVaga = numeroVaga;
        this.ruaVaga = ruaVaga;
        this.placaVeiOcupado = placaVeiOcupado;
        this.tarifaAtual = tarifaAtual;
        this.codigo = proxCodigo;
        proxCodigo++;
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

    public Operando getStatus() {
        return status;
    }

    public void setStatus(Operando status) {
        this.status = status;
    }


    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
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

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\nInicio do Ticket: " + inicio.format(tarifaAtual.getDataBonitinha()) + 
        "\nNumero da Vaga: " + numeroVaga + "\nRua da Vaga: " + ruaVaga + "\nPlaca do Veículo Ocupado: " + placaVeiOcupado + 
        "\nData da Tarifa do Ticket: " + tarifaAtual.getInicio().format(tarifaAtual.getDataBonitinha());
    }

    
    
}
