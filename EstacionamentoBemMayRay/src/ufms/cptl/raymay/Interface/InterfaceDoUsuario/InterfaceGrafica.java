/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuGeral;
/**
 *
 * @author nivis
 */
public class InterfaceGrafica implements UserInterface{
    public int opcao;
    /* Métodos de impressão de menus que irão retornar um valor que será atribuido a uma variável do mesmo tipo (int)
    que permitirá a escolha da opcao */
    /* Os métodos dessa Classe também são criados em UserInterface para auxiliar a troca de interface na main */
    @Override
    public void imprimirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
    @Override
    public String receberString(String mensagem){
       String valor = JOptionPane.showInputDialog(null, mensagem);                                                  
       return valor;  
    }
    
    @Override
    public String receberStringFormat(String mensagem, String formatacao, String tipo) {
        while(true) {
            String palavra = JOptionPane.showInputDialog(null, mensagem);
            /* esse comando serve para verificar se a string contem somente letras, acentos ou em outro idioma como "ç" */
            if (palavra.matches(formatacao)) {
                return palavra;
            } else {
                imprimirException("Insira um/uma " + tipo + " com a formatação/caracteres válidos!");
            }  
        }   
    }
    
    @Override
    public void imprimirException(String mensagem) {
        JOptionPane.showMessageDialog(null, "Erro: " + mensagem, "Exception", JOptionPane.ERROR_MESSAGE); 
    }
    
    @Override
    public int receberInteiro(String valor) {
        while (true) {
            String inteiro = JOptionPane.showInputDialog(null, valor);
            try {
                return Integer.parseInt(inteiro); 
            } catch (NumberFormatException e) {
                imprimirMensagem("O valor inserido não é um número inteiro! Tente novamente.");
            }    
        }
    }
    
    @Override
    public double receberDouble(String mensagem) {
        while (true) {
            String doublee = JOptionPane.showInputDialog(null, mensagem);
            try {
                return Double.parseDouble(doublee); 
            } catch (NumberFormatException e) {
                imprimirMensagem("O valor inserido não é um número válido! Tente novamente.");
            }    
        }
    }
    
    @Override
    public LocalDateTime receberData(String mensagem, String horarioAux) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        while (true) {
            String dataInput = JOptionPane.showInputDialog(null, mensagem);
            
            if(dataInput != null && dataInput.equalsIgnoreCase("agora"))
                return LocalDateTime.now();
            
            String dataTexto = dataInput + horarioAux;            
            try {
                LocalDateTime dataHora = LocalDateTime.parse(dataTexto, formatoData);
                return dataHora;
            } catch (DateTimeParseException e) {
                imprimirMensagem("A data que você digitou não condiz com o formato! Tente novamente.");
            }
        }
    }
    
    @Override
    public <T extends Enum<T> & InterMenuGeral> int imprimirMenu(Class<T> escolhaMenu, String menuSelecao) {
        T[] menu = escolhaMenu.getEnumConstants();
        String[] opDesc = new String[menu.length];
        StringBuilder ops = new StringBuilder();

        // Construir as descrições das opções
        for (int i = 0; i < menu.length; i++) {
            T constant = menu[i];
            ops.append(constant.getValorOpcao()).append(". ").append(constant.getDesc()).append("\n");
            opDesc[i] = String.valueOf(constant.getValorOpcao());
        }

        int escolha = JOptionPane.showOptionDialog(
            null,
            ops.toString(),
            menuSelecao,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opDesc,
            opDesc[0]
        );

        if (escolha >= 0 && escolha < menu.length) {
            return menu[escolha].getValorOpcao();
        }
         else
            return menu[menu.length-1].getValorOpcao();
    }
    
}  

