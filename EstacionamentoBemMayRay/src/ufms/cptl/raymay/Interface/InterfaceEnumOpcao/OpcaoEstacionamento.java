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
public class OpcaoEstacionamento {
    
    /**
    * @charset UTF-8 -> comando para imprimir simbolos especiais no terminal
    */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public enum InterEstacionamento{
        ESTACIONAR(1, "Estacionar"),
        RETIRAR(2, "Retirar"),
        LISTAR_VAGAS_DISP(3, "Listar todas as vagas disponíveis"),
        GERENCIAR_TARIFAS(4, "Gerenciar tarifas"),
        VOLTAR(5, "Voltar");
        
        public int valorOpcao;
        public String desc;

        private InterEstacionamento(int valorOpcao, String desc) {
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
    
    public static void imprimeEstacionamento(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoEstacionamento.InterEstacionamento op : OpcaoEstacionamento.InterEstacionamento.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }  
}
