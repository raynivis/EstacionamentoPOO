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
public class TarifaMensalista extends Tarifa{
    private double valorUnico;

    public TarifaMensalista(double valorUnico, LocalDateTime inicio, List<DiaSemana> diasSemana, List<TipoVeiculo> tarifaVeiculos) {
        super(inicio, diasSemana, tarifaVeiculos);
        this.valorUnico = valorUnico;
    }

    public double getValorUnico() {
        return valorUnico;
    }

    public void setValorUnico(double valorUnico) {
        this.valorUnico = valorUnico;
    }
       
    @Override
    public String toString() {
        return  "Tarifa: Mensalista \nInicio: " + inicio.format(dataBonitinha) + "\nValor: " + valorUnico;
    }
   
}
