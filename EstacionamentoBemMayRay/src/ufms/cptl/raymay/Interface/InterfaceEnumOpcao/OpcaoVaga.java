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
public class OpcaoVaga {
    
    /**
    * @charset UTF-8 -> comando para imprimir simbolos especiais no terminal
    */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public enum InterVaga{
        CADASTRAR_VAGA(1, "Cadastrar"),
        CONSULTAR_NUM(2, "Consultar por número"),
        EXCLUIR(3, "Excluir"),
        EDITAR(4, "Editar"),
        ALTERAR_DISP(5, "Alterar disponibilidade"),
        VOLTAR(6, "Voltar");
        
        public int valorOpcao;
        public String desc;

        private InterVaga(int valorOpcao, String desc) {
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
    public static void imprimeVaga(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (OpcaoVaga.InterVaga op : OpcaoVaga.InterVaga.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }  
}
