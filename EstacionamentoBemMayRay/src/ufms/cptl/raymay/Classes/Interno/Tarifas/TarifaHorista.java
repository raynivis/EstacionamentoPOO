/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tarifas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
/**
 *
 * @author nivis
 */
public class TarifaHorista extends Tarifa implements Serializable{
    private List<DiaSemana> diasSemana;
    private double valorPrimeiraHora;
    private double valorHoraSubsequente;

    public TarifaHorista(double valorPrimeiraHora, double valorHoraSubsequente, LocalDateTime inicio, List<DiaSemana> diasSemana) {
        super(inicio);
        this.diasSemana = diasSemana;
        this.valorPrimeiraHora = valorPrimeiraHora;
        this.valorHoraSubsequente = valorHoraSubsequente;
    }
    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
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
       
    @Override
    public String toString() {
        DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
        return  "Tarifa: Horista \nInicio: " + inicio.format(dataBonitinha) + "\nvalorPrimeiraHora: " + valorPrimeiraHora + "\nvalorHoraSubsequente: " + valorHoraSubsequente;
    }
   
}
