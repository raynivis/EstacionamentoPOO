/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceEnumOpcao;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author maymi
 */
public class OpcaoVeiculo {
    /**
    * @charset UTF-8 -> comando para imprimir simbolos especiais no terminal
    */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public enum InterVeiculo{
        CADASTRAR (1, "Cadastrar novo veÍculo"),
        EXCLUIR (2, "Excluir veículo"),
        EDITAR(3, "Editar veículo"),
        SAIR(4, "Voltar");
        
        public int valorOpcao;
        public String desc;

        private InterVeiculo(int valorOpcao, String desc) {
            this.valorOpcao = valorOpcao;
            this.desc = desc;
        }

        public int getValorOpcao() {
            return valorOpcao;
        }

        public void setValorOpcao(int valorOpcao) {
            this.valorOpcao = valorOpcao;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public static void imprimeVeiculo(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoVeiculo.InterVeiculo op : OpcaoVeiculo.InterVeiculo.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }  
}
