/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author maymi
 */
public class OperacaoMostraMensagem {
    /* Método estático que mostra uma mensagem de texto na interface, será utilizada para casos 
    específicos dentro das operações, no package de operações, e da interface */
    public static void operacaoMensagem(String mensagem){
        try {
            /* Impressão com caracteres especiais no formato UTF-8 */
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            out.println(mensagem);
        } catch (UnsupportedEncodingException e) {
            /* Tratamento de excessão */
        }
    }
   
}
