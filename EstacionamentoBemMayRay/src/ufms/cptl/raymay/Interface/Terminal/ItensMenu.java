/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Terminal;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoCadastroGeral;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoCliente;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoEstacionamento;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoInicial;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoTarifa;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoVaga;
import ufms.cptl.raymay.Interface.Terminal.EnumOpcao.OpcaoVeiculo;

/**
 *
 * @author maymi
 */
public class ItensMenu {
    /* Esta Classe cria métodos de impressão (que percorrem o laço for do primeiro item do Enum até o ultimo)
    para os enums criados no package InterfaceEnumOpcao, reduzindo a quantidade de linhas
    das Classes do package da interface e auxiliando no melhor entendimento do código,
    quando for relido/corrigido ou para realizar alterações */
    
    /* Todos os métodos da classe permitem a impressão com caracteres especiais na interface!!! */
    
    /*num = 0 para interface terminal e num = 1 para interface grafica */
    public int imprimeInicio(int num){ 
        if(num == 0) {
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoInicial.InterInicial op : OpcaoInicial.InterInicial.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            }
            return 0;
        } else {
            OpcaoInicial.InterInicial[] op = OpcaoInicial.InterInicial.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Inicial",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoInicial.InterInicial opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    }  
    
    public int imprimeCliente(int num){
        if(num == 0) {
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoCliente.InterCliente op : OpcaoCliente.InterCliente.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            } 
            return 0;
        } else {
            OpcaoCliente.InterCliente[] op = OpcaoCliente.InterCliente.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Cliente",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoCliente.InterCliente opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    }
    
    public int imprimeVeiculo(int num){
        if(num == 0) {
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoVeiculo.InterVeiculo op : OpcaoVeiculo.InterVeiculo.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            }
            return 0;
        } else {
            OpcaoVeiculo.InterVeiculo[] op = OpcaoVeiculo.InterVeiculo.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Veiculo",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoVeiculo.InterVeiculo opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    }
    
     public int imprimeVaga(int num){
        if(num == 0) { 
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoVaga.InterVaga op : OpcaoVaga.InterVaga.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            }
            return 0;
        } else {
            OpcaoVaga.InterVaga[] op = OpcaoVaga.InterVaga.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Vaga",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoVaga.InterVaga opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    }
     
    public int imprimeEstacionamento(int num){
        if(num == 0) {
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoEstacionamento.InterEstacionamento op : OpcaoEstacionamento.InterEstacionamento.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            }  
            return 0;
        } else {
            OpcaoEstacionamento.InterEstacionamento[] op = OpcaoEstacionamento.InterEstacionamento.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Estacionamento",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoEstacionamento.InterEstacionamento opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    }
    
    public int imprimeTarifa(int num){
        if(num == 0) {
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoTarifa.InterTarifa op : OpcaoTarifa.InterTarifa.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            }
            return 0;
        } else {
            OpcaoTarifa.InterTarifa[] op = OpcaoTarifa.InterTarifa.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Tarifa",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoTarifa.InterTarifa opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    } 
    
    public int imprimeCadastroGeral(int num){
        if(num == 0) {
            try {
                PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.toString());
                for (OpcaoCadastroGeral.InterCadastroGeral op : OpcaoCadastroGeral.InterCadastroGeral.values()) {
                    out.println(op.getValorOpcao() + " - " + op.getDesc());
                }
            } catch (UnsupportedEncodingException e) {
            }
            return 0;
        } else {
            OpcaoCadastroGeral.InterCadastroGeral[] op = OpcaoCadastroGeral.InterCadastroGeral.values();
            String[] opDesc = new String[op.length];
            for (int i = 0; i < op.length; i++) {
                opDesc[i] = op[i].getValorOpcao() + ". " + op[i].getDesc();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Cadastro Geral",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opDesc,
                    opDesc[0]);

            // Converte a escolha para o enum correspondente e obtém o valor da opção
            if (escolha != null) {
                for (OpcaoCadastroGeral.InterCadastroGeral opcao : op) {
                    if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                        return opcao.getValorOpcao();
                    }
                }    
            }
            /* Opcao inválida, tera um break na interface */
            return -1;
        }
    }
}
        
