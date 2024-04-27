/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;

/**
 *
 * @author ra
 */
public class AuxiliarInterfarceListaTipoSemanas {
    Scanner scanner = new Scanner(System.in);
    
    public void OperacaoListaTVDS(List<DiaSemana> diaSmns, List<TipoVeiculo> listaTps){
        interMensagem("Digite o/s dia/s da semana dessa tarifa (Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado)");
        interMensagem("Caso seja para todos os dias da semana, digite: Todos");
        
        String dias = scanner.nextLine();
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
       
        
        interMensagem("Digite o/s tipo/s de veículo/s dessa tarifa (MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
        interMensagem("Caso seja para todos os tipos de veículo, digite: Todos");
        String tipos = scanner.nextLine();
        tipos = tipos.toUpperCase();
                    
        if(tipos.contains("MOTOCICLETA") || tipos.contains("TODOS"))
            listaTps.add(TipoVeiculo.MOTOCICLETA);
        if(tipos.contains("MEDIOPORTE") || tipos.contains("TODOS"))
            listaTps.add(TipoVeiculo.MEDIOPORTE); 
        if(tipos.contains("GRANDEPORTE") || tipos.contains("TODOS"))
            listaTps.add(TipoVeiculo.GRANDEPORTE); 
    }
}
