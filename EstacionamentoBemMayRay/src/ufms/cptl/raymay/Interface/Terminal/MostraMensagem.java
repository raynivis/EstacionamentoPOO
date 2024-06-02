/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Terminal;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author maymi
 */
public class MostraMensagem {
    /* Método estático que mostra uma mensagem de texto na interface, será utilizada para casos 
    específicos e mensagens indicadoras de operações e finalizadoras dentro da própria interface */
    public static void interMensagem(String mensagem){
        try {
            /* Impressão com caracteres especiais no formato UTF-8 */
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            out.println(mensagem);
        } catch (UnsupportedEncodingException e) {
            /* Tratamento de excessão */
        }
    }
   
}
