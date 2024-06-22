/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.EnumOpcao;
/**
 *
 * @author maymi
 */
public enum InterMenuVeiculo implements InterMenuGeral{
        CADASTRAR (1, "Cadastrar novo veículo"),
        EXCLUIR (2, "Excluir veículo"),
        EDITAR(3, "Editar veículo"),
        SAIR(4, "Voltar");
        
        /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        public int valorOpcao;
        public String desc;

        private InterMenuVeiculo(int valorOpcao, String desc) {
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
