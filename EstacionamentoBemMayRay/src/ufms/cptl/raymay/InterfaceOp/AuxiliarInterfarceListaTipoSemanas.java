/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceGrafica;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceTerminal;
import ufms.cptl.raymay.Interface.UserInterface.UserInterface;
import static ufms.cptl.raymay.InterfaceOp.MostraMensagem.interMensagem;

/**
 *
 * @author ra
 */
public class AuxiliarInterfarceListaTipoSemanas {
    Scanner scanner = new Scanner(System.in);
    
    /*Método para identificação e cadastro de listas de Dias da Semana e Listas de Veiculos, para não ficar repetindo codigo.*/
    public void OperacaoListaTVDS(List<DiaSemana> diaSmns, List<TipoVeiculo> listaTps, UserInterface inter, int face){
        String dias;
        if(face == 0){
            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
            interfaces.mensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
            dias = interfaces.receberString("Caso seja para todos os dias da semana, digite: Todos");
         }
         else {
            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
            interfaces.mensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
            dias = interfaces.receberString("Caso seja para todos os dias da semana, digite: Todos");
        }       
        dias = dias.toUpperCase();
        
        if(dias.contains("DOMINGO") || dias.contains("TODOS")) 
            diaSmns.add(DiaSemana.DOMINGO);
        if(dias.contains("SEGUNDA") || dias.contains("TODOS"))
            diaSmns.add(DiaSemana.SEGUNDA); 
        if(dias.contains("TERCA") || dias.contains("TODOS"))
            diaSmns.add(DiaSemana.TERCA); 
        if(dias.contains("QUARTA") || dias.contains("TODOS"))
            diaSmns.add(DiaSemana.QUARTA); 
        if(dias.contains("QUINTA") || dias.contains("TODOS"))
            diaSmns.add(DiaSemana.QUINTA); 
        if(dias.contains("SEXTA") || dias.contains("TODOS"))
            diaSmns.add(DiaSemana.SEXTA); 
        if(dias.contains("SABADO") || dias.contains("TODOS"))
            diaSmns.add(DiaSemana.SABADO);      
        
        String tipos;
        if(face == 0){
            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
            interfaces.mensagem("Digite o/s tipo/s de veículo/s dessa tarifa (MOTO, CARRO, ONIBUS):");
            tipos = interfaces.receberString("Caso seja para todos os tipos de veículo, digite: Todos");
         }
         else {
            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
            interfaces.mensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
            tipos = interfaces.receberString("Caso seja para todos os dias da semana, digite: Todos");
        }       
        tipos = tipos.toUpperCase();
                    
        if(tipos.contains("MOTO") || tipos.contains("TODOS"))
            listaTps.add(TipoVeiculo.MOTO);
        if(tipos.contains("CARRO") || tipos.contains("TODOS"))
            listaTps.add(TipoVeiculo.CARRO); 
        if(tipos.contains("ONIBUS") || tipos.contains("TODOS"))
            listaTps.add(TipoVeiculo.ONIBUS); 

    }
}
