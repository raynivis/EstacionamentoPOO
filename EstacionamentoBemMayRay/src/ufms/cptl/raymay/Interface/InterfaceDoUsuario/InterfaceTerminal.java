/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuGeral;
import static ufms.cptl.raymay.InterfaceOpcoes.MostraMensagem.mostrarInterMensagem;

/**
 *
 * @author nivis
 */
public class InterfaceTerminal implements UserInterface{
    private final Scanner scanner = new Scanner(System.in);
    
    /* Métodos de impressão de menus que irão retornar um valor que será atribuido a uma variável do mesmo tipo (int)
    que permitirá a escolha da opcao */
    /* Os métodos dessa Classe também são criados em UserInterface para auxiliar a troca de interface na main */
     @Override
    public void imprimirMensagem(String mensagem){
        mostrarInterMensagem("\n" + mensagem + "\n");
    }
    
    /* Abaixo, métodos que recebem String, verificam Strings inteiros e double e data. */
     @Override
    public String receberString(String mensagem) {
        mostrarInterMensagem(mensagem);
        String valor = scanner.nextLine();
        return valor;
    }
    
    @Override
    public String receberStringFormat(String mensagem, String formatacao, String tipo) {
        while(true) {
            mostrarInterMensagem(mensagem);
            String palavra = scanner.nextLine();
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
        imprimirMensagem("Exception Erro: " + mensagem);
    }
    
    @Override
    public int receberInteiro(String mensagem) {
        while (true) {
            mostrarInterMensagem(mensagem);
            String inteiro = scanner.nextLine();
            try {
                if(inteiro != null) {
                    return Integer.parseInt(inteiro); 
                }
            } catch (NumberFormatException e) {
                mostrarInterMensagem("O valor inserido não é um número inteiro! Tente novamente.");
            }    
        }
    }
    
    /* Erro que pode ocorrer no terminal, inserir uma letra ou caractere que não seja um número, tratado! */
    public int lerOpMenu() {
        while (true) {
            String op = scanner.nextLine();
            try {
                return Integer.parseInt(op); 
            } catch (NumberFormatException e) {
                imprimirException("Insira um número para selecionar uma opção do menu!!");
            }
        }
    }
    
    @Override
    public double receberDouble(String mensagem) {
        while (true) {
            mostrarInterMensagem(mensagem);
            String doublee = scanner.nextLine();
            try {
                return Double.parseDouble(doublee); 
            } catch (NumberFormatException e) {
                mostrarInterMensagem("O valor inserido não é um número válido! Tente novamente.");
            }    
        }
    }
    
    @Override
    public LocalDateTime receberData(String mensagem, String horarioAux) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        while (true) {
            mostrarInterMensagem(mensagem);
            String dataInput = scanner.nextLine();
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
     
    /* Método genérico para auxiliar no manuseamento da interface terminal e gráfica, com a utilização do
    método genérico, consigo selecionar o menu que quero que apareça, tornando o código mais limpo! */
    @Override
    public <T extends Enum<T> & InterMenuGeral> int imprimirMenu(Class<T> escolhaMenu, String menuSelecao) {
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
            out.println(menuSelecao);

            T[] menu = escolhaMenu.getEnumConstants();
            for (T constant : menu) {
                out.println(constant.getValorOpcao() + " - " + constant.getDesc());
            }
            
            return lerOpMenu();
            
        } catch (Exception e) {
            return -1;
        }
    }    
  
}
  
    
