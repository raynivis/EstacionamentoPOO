/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.EnumOpcao;

/**
 *
 * @author maymi
 */
public enum InterMenuCadastroGeral implements InterMenuGeral{
        TICKET_TARIFA(1, "Teste de ticket com tarifa"),
        VEICULO(2, "Consultar veículo"),
        TARIFA(3, "Consultar tarifa"),
        TICKET(4, "Consultar ticket"),
        TICKETATIVO(5, "Listar tickets ativos"),
        VAGA(6, "Listar vagas cadastradas"),
        VOLTAR(7, "Voltar");
        
        /* Como esse enum trabalha com a impressão de menus, os atributos são private -> final <-  pois
        não serão alterados, caso fossem isso traria confusão ao usuário e perderia a característica
        imutável dos enums */
        private final int valorOpcao;
        private final String desc;

        private InterMenuCadastroGeral(int valorOpcao, String desc) {
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
