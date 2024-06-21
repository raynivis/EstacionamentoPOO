/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.util.List;

import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;

/**
 *
 * @author ra
 */
public class AuxiliarInterfarceListaSemanas {
    
    /*Método para identificação e cadastro de listas de Dias da Semana e Listas de Veiculos, para não ficar repetindo codigo.*/
    public void GerenciarListaDiasSemanas(List<DiaSemana> diaSmns, UserInterface inter){
        String dias;
        inter.imprimirMensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terca, Quarta, Quinta, Sexta, Sábado)");
        dias = inter.receberString("Caso seja para todos os dias da semana, digite: Todos");
        dias = dias.toUpperCase();
        
        if(dias.contains("TODOS")){
            diaSmns.add(DiaSemana.DOMINGO);
            diaSmns.add(DiaSemana.SEGUNDA); 
            diaSmns.add(DiaSemana.TERCA);
            diaSmns.add(DiaSemana.QUARTA);
            diaSmns.add(DiaSemana.QUINTA); 
            diaSmns.add(DiaSemana.SEXTA);
            diaSmns.add(DiaSemana.SABADO);   
        } else {           
            diaSmns.addAll(DiaSemana.criarLista(dias));
        }       
    }
}
