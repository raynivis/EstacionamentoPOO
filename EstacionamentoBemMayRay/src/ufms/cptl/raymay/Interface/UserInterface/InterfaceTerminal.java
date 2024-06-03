/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.UserInterface;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoCadastroGeral;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoInicial;
import static ufms.cptl.raymay.InterfaceOp.MostraMensagem.interMensagem;

/**
 *
 * @author nivis
 */
public class InterfaceTerminal implements UserInterface{
    private Scanner scanner;
    
    public InterfaceTerminal() {
        scanner = new Scanner(System.in);
    }
    
    
    public void mensagem(String mensagem){
        interMensagem(mensagem);
    }
  
    @Override
    public int imprimeInicio(){        
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoInicial.InterInicial op : OpcaoInicial.InterInicial.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
            }           
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        
    }
    @Override
    public int imprimeCadastroGeral(){     
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoCadastroGeral.InterCadastroGeral op : OpcaoCadastroGeral.InterCadastroGeral.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
        int opcao3 = scanner.nextInt();
        scanner.nextLine();
        return opcao3;           
    }  
    
    public String receberString(String mensagem){
        interMensagem(mensagem);
        String valor = scanner.nextLine();
        scanner.nextLine();
        return valor;
    }
    
}   
  
    
