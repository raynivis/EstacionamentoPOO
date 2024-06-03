/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.EnumOpcao;
/**
 *
 * @author maymi
 */
public class OpcaoVaga {

    public enum InterVaga{
        CADASTRAR_VAGA(1, "Cadastrar"),
        CONSULTAR_NUM(2, "Consultar por número"),
        EXCLUIR(3, "Excluir"),
        EDITAR(4, "Editar"),
        ALTERAR_DISP(5, "Alterar disponibilidade"),
        VOLTAR(6, "Voltar");
        
        /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        public int valorOpcao;
        public String desc;

        private InterVaga(int valorOpcao, String desc) {
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