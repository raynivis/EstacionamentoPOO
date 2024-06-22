/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tarifas;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 *
 * @author ra
 */
public abstract class Tarifa implements Serializable{
    
   /* Atributos característicos da tarifa */ 
    protected LocalDateTime inicio;
  

    public Tarifa(LocalDateTime inicio) {
        this.inicio = inicio;        
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }
}
