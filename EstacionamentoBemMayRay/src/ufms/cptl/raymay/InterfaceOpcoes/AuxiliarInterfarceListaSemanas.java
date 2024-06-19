/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.util.List;

import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;

/**
 *
 * @author ra
 */
public class AuxiliarInterfarceListaSemanas {
    
    /*Método para identificação e cadastro de listas de Dias da Semana e Listas de Veiculos, para não ficar repetindo codigo.*/
    public void OperacaoListaDiasSemanas(List<DiaSemana> diaSmns, UserInterface inter){
        String dias;
        if(inter instanceof InterfaceTerminal){
            inter = (InterfaceTerminal) inter;
            inter.mensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
            dias = inter.receberString("Caso seja para todos os dias da semana, digite: Todos");
         }
         else {
            inter = (InterfaceGrafica) inter;
            inter.mensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
            dias = inter.receberString("Caso seja para todos os dias da semana, digite: Todos");
        }       
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
            if(dias.contains("DOMINGO")) 
                diaSmns.add(DiaSemana.DOMINGO);
            if(dias.contains("SEGUNDA"))
                diaSmns.add(DiaSemana.SEGUNDA); 
            if(dias.contains("TERCA"))
                diaSmns.add(DiaSemana.TERCA); 
            if(dias.contains("QUARTA"))
                diaSmns.add(DiaSemana.QUARTA); 
            if(dias.contains("QUINTA"))
                diaSmns.add(DiaSemana.QUINTA); 
            if(dias.contains("SEXTA"))
                diaSmns.add(DiaSemana.SEXTA); 
            if(dias.contains("SABADO"))
                diaSmns.add(DiaSemana.SABADO); 
        }       
    }
}
