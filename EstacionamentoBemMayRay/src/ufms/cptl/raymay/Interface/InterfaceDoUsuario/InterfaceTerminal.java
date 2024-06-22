/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
    
     @Override
     public String receberString(String mensagem){
        mostrarInterMensagem(mensagem);
        String valor = scanner.nextLine();
        return valor;
    }
     
    @Override
    public <T extends Enum<T> & InterMenuGeral> int imprimirMenu(Class<T> escolhaMenu, String menuSelecao) {
        try {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
            out.println(menuSelecao);

            T[] menu = escolhaMenu.getEnumConstants();
            for (T constant : menu) {
                out.println(constant.getValorOpcao() + " - " + constant.getDesc());
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
            
        } catch (Exception e) {
            return -1;
        }
    }    
  
}
  
    
