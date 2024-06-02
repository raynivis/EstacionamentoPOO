/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Terminal;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoCadastroGeral;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoCliente;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoEstacionamento;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoInicial;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoTarifa;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoVaga;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoVeiculo;

/**
 *
 * @author maymi
 */
public class ItensMenu {
    /* Esta Classe cria métodos de impressão (que percorrem o laço for do primeiro item do Enum até o ultimo)
    para os enums criados no package InterfaceEnumOpcao, reduzindo a quantidade de linhas
    das Classes do package da interface e auxiliando no melhor entendimento do código,
    quando for relido/corrigido ou para realizar alterações */
    
    /* Todos os métodos da classe permitem a impressão com caracteres especiais na interface!!! */
    
    public void imprimeInicio(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoInicial.InterInicial op : OpcaoInicial.InterInicial.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }  
    
    public void imprimeCliente(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoCliente.InterCliente op : OpcaoCliente.InterCliente.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }   
    }
    
    public void imprimeVeiculo(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoVeiculo.InterVeiculo op : OpcaoVeiculo.InterVeiculo.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }
    
     public void imprimeVaga(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoVaga.InterVaga op : OpcaoVaga.InterVaga.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }
     
    public void imprimeEstacionamento(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoEstacionamento.InterEstacionamento op : OpcaoEstacionamento.InterEstacionamento.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }   
    }
    
    public void imprimeTarifa(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoTarifa.InterTarifa op : OpcaoTarifa.InterTarifa.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    } 
    
    public void imprimeCadastroGeral(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoCadastroGeral.InterCadastroGeral op : OpcaoCadastroGeral.InterCadastroGeral.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }
}
        
