/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Menu;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuGeral;

/**
 *
 * @author maymi
 */
public class MenuTerminal implements Menu{
    private final Scanner scanner = new Scanner(System.in);
    
    @Override
    public <T extends Enum<T> & InterMenuGeral> int adaptarInterfaceMenu(Class<T> escolhaMenu, String menuSelecao) {
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