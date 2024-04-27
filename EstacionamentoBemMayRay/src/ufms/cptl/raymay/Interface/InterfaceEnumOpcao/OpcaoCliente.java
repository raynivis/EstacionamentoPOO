/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceEnumOpcao;
/**
 *
 * @author maymi
 */
public class OpcaoCliente {

    public enum InterCliente{
        CADASTRAR_CLIENTE(1, "Cadastrar"),
        CONSULTA_DOC(2, "Consultar por documento"),
        EXCLUIR(3, "Excluir"),
        EDITAR(4, "Editar"),
        GERENCIAR_VEICULOS(5, "Gerenciar veículos"),
        LISTAR_CADASTROS(6, "Listar todos os cadastros"),
        VOLTAR(7, "Voltar");
        
       /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        private final int valorOpcao;
        private final String desc;

        private InterCliente(int valorOpcao, String desc) {
            this.valorOpcao = valorOpcao;
            this.desc = desc;
        }
        /* Como se trata de um enum e menu, não será necessário métodos setters na Classe, pois os atributos
        não serão alterados ou definidos posteriormente e já foram inicializados no construtor */
        public int getValorOpcao() {
            return valorOpcao;
        }

        public String getDesc() {
            return desc;
        }   
    }    
}
