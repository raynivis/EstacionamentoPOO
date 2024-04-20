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
public class OpcaoTarifa {
    /**
    * @charset UTF-8 -> comando para imprimir simbolos especiais no terminal
    */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public enum InterTarifa{
        CADASTRAR (1, "Cadastrar tarifa"),
        EXCLUIR (2, "Excluir tarifa"),
        EDITAR(3, "Editar tarifa"),
        SAIR(4, "Voltar");
        
        public int valorOpcao;
        public String desc;

        private InterTarifa(int valorOpcao, String desc) {
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
    public static void imprimeTarifa(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoTarifa.InterTarifa op : OpcaoTarifa.InterTarifa.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }  
}
