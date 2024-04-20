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
public class OpcaoCliente {
    
    /**
    * @charset UTF-8 -> comando para imprimir simbolos especiais no terminal
    */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public enum InterCliente{
        CADASTRAR_CLIENTE(1, "Cadastrar"),
        CONSULTA_DOC(2, "Consultar por documento"),
        EXCLUIR(3, "Excluir"),
        EDITAR(4, "Editar"),
        GERENCIAR_VEICULOS(5, "Gerenciar veículos"),
        LISTAR_CADASTROS(6, "Listar todos os cadastros"),
        VOLTAR(7, "Voltar");
        
        public int valorOpcao;
        public String desc;

        private InterCliente(int valorOpcao, String desc) {
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
    
    public static void imprimeCliente(){
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
            for (InterCliente op : InterCliente.values()) {
                out.println(op.getValorOpcao() + " - " + op.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
        }
    }    
}
