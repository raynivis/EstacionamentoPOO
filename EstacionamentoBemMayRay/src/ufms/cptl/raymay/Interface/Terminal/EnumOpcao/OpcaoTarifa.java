/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Terminal.EnumOpcao;
/**
 *
 * @author maymi
 */
public class OpcaoTarifa {

    public enum InterTarifa{
        CADASTRAR (1, "Cadastrar tarifa"),
        EXCLUIR (2, "Excluir tarifa"),
        EDITAR(3, "Editar tarifa"),
        CONSULTAR(4, "Imprimir as tarifas cadastradas"),
        SAIR(5, "Voltar");
        
       /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        public int valorOpcao;
        public String desc;

        private InterTarifa(int valorOpcao, String desc) {
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
