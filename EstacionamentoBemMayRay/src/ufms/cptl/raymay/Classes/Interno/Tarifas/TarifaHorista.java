/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tarifas;

import java.time.LocalDateTime;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;

/**
 *
 * @author nivis
 */
public class TarifaHorista extends Tarifa{
    private double valorPrimeiraHora;
    private double valorHoraSubsequente;

    public TarifaHorista(double valorPrimeiraHora, double valorHoraSubsequente, LocalDateTime inicio, List<DiaSemana> diasSemana, List<TipoVeiculo> tarifaVeiculos) {
        super(inicio, diasSemana, tarifaVeiculos);
        this.valorPrimeiraHora = valorPrimeiraHora;
        this.valorHoraSubsequente = valorHoraSubsequente;
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
        return  "Tarifa: Horista \nInicio: " + inicio.format(dataBonitinha) + "\nvalorPrimeiraHora: " + valorPrimeiraHora + "\nvalorHoraSubsequente: " + valorHoraSubsequente;
    }
   
}
