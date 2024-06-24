/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.util.List;

import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ErroDigitacaoException;

/**
 *
 * @author ra
 */
public class AuxiliarInterfarceListaSemanas {
    InterfaceException ex = new InterfaceException();
    /*Método para identificação e cadastro de listas de Dias da Semana e Listas de Veiculos, para não ficar repetindo codigo.*/
    public int GerenciarListaDiasSemanas(List<DiaSemana> diaSmns, UserInterface inter){
        String dias;
        
        while(true) {
            try {
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
                    return 1;
                } else {     
                    List<DiaSemana> diaSCadas = DiaSemana.criarLista(dias);
                    if(diaSCadas == null){
                        throw ex.new ErroDigitacaoException("Digite todos OU os dias da semana correspondentes!");                          
                    }
                    diaSmns.addAll(diaSCadas);
                    return 1;
                }                                     
            } catch (ErroDigitacaoException e) {
                inter.imprimirException(e.getMessage());               
            }
        }
    }
}
