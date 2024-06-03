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
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoCliente;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoInicial;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoVaga;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoVeiculo;
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
    
    
    
    
    
    public int imprimeVaga() {
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoVaga.InterVaga op : OpcaoVaga.InterVaga.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
        int opcao2 = scanner.nextByte();
        scanner.nextLine();
        return opcao2;
    }
    
    
    public int imprimeCliente() {
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoCliente.InterCliente op : OpcaoCliente.InterCliente.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        } 
        int opcao2 = scanner.nextByte();
        scanner.nextLine();
        return opcao2;
    }
    
    public int imprimeVeiculo() {
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoVeiculo.InterVeiculo op : OpcaoVeiculo.InterVeiculo.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
        int opcao3 = scanner.nextByte();
        scanner.nextLine();
        return opcao3;
    }
}   
  
    
