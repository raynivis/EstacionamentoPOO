/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoCadastroGeral;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoCliente;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoEstacionamento;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoInicial;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoTarifa;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoVaga;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoVeiculo;

/**
 *
 * @author maymi
 */
public class ItensMenu {
    
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
    
   
}
        
