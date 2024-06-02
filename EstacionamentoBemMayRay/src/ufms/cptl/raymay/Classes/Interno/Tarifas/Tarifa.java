/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tarifas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;

/**
 *
 * @author ra
 */
public abstract class Tarifa {
    
   /* Atributos característicos da tarifa */ 
    protected final DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
    protected LocalDateTime inicio;
    protected List<DiaSemana> diasSemana;
    protected List<TipoVeiculo> tarifaVeiculos;

    public Tarifa(LocalDateTime inicio, List<DiaSemana> diasSemana, List<TipoVeiculo> tarifaVeiculos) {
        this.inicio = inicio;
        this.diasSemana = diasSemana;
        this.tarifaVeiculos = tarifaVeiculos;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public List<TipoVeiculo> getTarifaVeiculos() {
        return tarifaVeiculos;
    }

    public void setTarifaVeiculos(List<TipoVeiculo> tarifaVeiculos) {
        this.tarifaVeiculos = tarifaVeiculos;
    }
    
}
