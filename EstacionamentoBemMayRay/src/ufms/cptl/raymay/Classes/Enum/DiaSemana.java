/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra
 */
public enum DiaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;
    
    public static List<DiaSemana> criarLista(String texto){
        List<DiaSemana> listaSemana = new ArrayList<>();
        String[] partes = texto.split(" ");
        for(int i = 0; i<partes.length; i++){
            try {
                listaSemana.add(DiaSemana.valueOf(partes[i]));
            } catch (IllegalArgumentException e) {
            }
        }
        return listaSemana;
    }
}
