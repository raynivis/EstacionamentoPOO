/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tarifas;
import java.time.LocalDateTime;

/**
 *
 * @author nivis
 */
public class TarifaMensalista extends Tarifa{
    private double valorUnico;

    public TarifaMensalista(double valorUnico, LocalDateTime inicio) {
        super(inicio);
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
