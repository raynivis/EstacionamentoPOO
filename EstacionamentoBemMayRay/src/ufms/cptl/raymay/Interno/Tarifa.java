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
public class Tarifa {
    enum DiaSemana {
        SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO
    
    }
    private Date inicio;
    private double valorPrimeira;
    private double valorHoraSeguinte;
    private DiaSemana diasemanas[];
    /*Adicionar outro enum para preços do tipo de veiculo*/
    

    public Tarifa(Date inicio, double valorPrimeira, double valorHoraSeguinte, DiaSemana[] diasemanas) {
        this.inicio = inicio;
        this.valorPrimeira = valorPrimeira;
        this.valorHoraSeguinte = valorHoraSeguinte;
        this.diasemanas = diasemanas;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public double getValorPrimeira() {
        return valorPrimeira;
    }

    public void setValorPrimeira(double valorPrimeira) {
        this.valorPrimeira = valorPrimeira;
    }

    public double getValorHoraSeguinte() {
        return valorHoraSeguinte;
    }

    public void setValorHoraSeguinte(double valorHoraSeguinte) {
        this.valorHoraSeguinte = valorHoraSeguinte;
    }
    
    
    
}
