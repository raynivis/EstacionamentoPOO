/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.EnumOpcao;
/**
 *
 * @author maymi
 */
public enum InterMenuInicial implements InterMenuGeral{
        GERENCIAR_CLIENTES(1, "Gerenciar clientes"),
        GERENCIAR_VAGAS(2, "Gerenciar vagas"),
        GERENCIAR_ESTACIONAMENTO(3, "Gerenciar estacionamento"),
        CADASTROS_GERAIS(4, "Cadastros gerais"),
        TOTAL_FATURADO(5, "Consultar total faturado em um período"),
        SAIR(6, "Sair do programa");
        
        /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        public int valorOpcao;
        public String desc;

        private InterMenuInicial(int valorOpcao, String desc) {
            this.valorOpcao = valorOpcao;
            this.desc = desc;
        }
        /* Como se trata de um enum e menu, não será necessário métodos setters na Classe, pois os atributos
        não serão alterados ou definidos posteriormente e já foram inicializados no construtor */
        @Override
        public int getValorOpcao() {
            return valorOpcao;
        }

        @Override
        public String getDesc() {
            return desc;
        }  
    }
