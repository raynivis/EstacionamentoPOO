/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.TipoVeiculo;

/**
 *
 * @author ra
 */
public class Tarifa {
    
   /* Atributos característicos da tarifa */ 
    private DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
    private LocalDateTime inicio;
    private double valorPrimeiraHora;
    private double valorHoraSubsequente;
    private List<DiaSemana> diasSemana;
    private List<TipoVeiculo> tarifaVeiculos;

    public Tarifa(LocalDateTime inicio, double valorPrimeiraHora, double valorHoraSubsequente, List<DiaSemana> diasSemana, List<TipoVeiculo> tarifaVeiculos) {
        this.inicio = inicio;
        this.valorPrimeiraHora = valorPrimeiraHora;
        this.valorHoraSubsequente = valorHoraSubsequente;
        this.diasSemana = diasSemana;
        this.tarifaVeiculos = tarifaVeiculos;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public double getValorPrimeiraHora() {
        return valorPrimeiraHora;
    }

    public void setValorPrimeiraHora(double valorPrimeiraHora) {
        this.valorPrimeiraHora = valorPrimeiraHora;
    }

    public double getValorHoraSubsequente() {
        return valorHoraSubsequente;
    }

    public void setValorHoraSubsequente(double valorHoraSubsequente) {
        this.valorHoraSubsequente = valorHoraSubsequente;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }
    
    public void setDiaSemanaNaLista(DiaSemana dia) {
        this.diasSemana.add(dia);
    }

    public List<TipoVeiculo> getTarifaVeiculos() {
        return tarifaVeiculos;
    }

    public void setTarifaVeiculos(List<TipoVeiculo> tarifaVeiculos) {
        this.tarifaVeiculos = tarifaVeiculos;
    }
    
   public void setTipoVeiculoNaLista(TipoVeiculo tVeiculo) {
        this.tarifaVeiculos.add(tVeiculo);
    }

    @Override
    public String toString() {
        return  "inicio: " + inicio.format(dataBonitinha) + "\nvalorPrimeiraHora: " + valorPrimeiraHora + "\nvalorHoraSubsequente: " + valorHoraSubsequente;
    }
   
   
      
}
