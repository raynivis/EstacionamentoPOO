/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.UserInterface;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoCadastroGeral;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoEstacionamento;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoInicial;
import ufms.cptl.raymay.Interface.EnumOpcao.OpcaoTarifa;

/**
 *
 * @author nivis
 */
public class InterfaceGrafica implements UserInterface{
    
    
     public void mensagem(String mensagem){
         JOptionPane.showMessageDialog(null, mensagem);
     }
    
    @Override
    public int imprimeInicio(){       
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
        return -1;   
    }
    
     public int imprimeCadastroGeral(){       
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
    public String receberString(String mensagem){
        String valor = JOptionPane.showInputDialog(null, mensagem, JOptionPane.PLAIN_MESSAGE);                                                  
        return valor;  
    } 
    
    public int imprimeEstacionamento(){
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
            /* Opcao inválida*/
            return -1;
    }
    
     public int imprimeTarifa(){
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

