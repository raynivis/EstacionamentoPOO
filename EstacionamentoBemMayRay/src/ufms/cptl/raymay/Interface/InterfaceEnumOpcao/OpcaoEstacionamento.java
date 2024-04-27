/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceEnumOpcao;
/**
 *
 * @author maymi
 */
public class OpcaoEstacionamento {
    
    public enum InterEstacionamento{
        ESTACIONAR(1, "Estacionar"),
        RETIRAR(2, "Retirar"),
        LISTAR_VAGAS_DISP(3, "Listar todas as vagas disponíveis"),
        GERENCIAR_TARIFAS(4, "Gerenciar tarifas"),
        VOLTAR(5, "Voltar");
        
        /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        public int valorOpcao;
        public String desc;
        
        private InterEstacionamento(int valorOpcao, String desc) {
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
