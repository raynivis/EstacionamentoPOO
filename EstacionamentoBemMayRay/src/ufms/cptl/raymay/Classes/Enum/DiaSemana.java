/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Enum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra
 */
public enum DiaSemana implements Serializable{
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;
    
    /*método para facilitar o insercao de dias da semana na tarifa*/
    public static List<DiaSemana> criarLista(String texto){
        List<DiaSemana> listaSemana = new ArrayList<>();
        String[] partes = texto.split(" ");
        for(int i = 0; i<partes.length; i++){
            try {
                listaSemana.add(DiaSemana.valueOf(partes[i]));
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return listaSemana;
    }
}
