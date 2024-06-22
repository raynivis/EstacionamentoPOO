/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;

import java.util.Scanner;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuCadastroGeral;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuCliente;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuEstacionamento;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuInicial;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuTarifa;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuVaga;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuVeiculo;
import ufms.cptl.raymay.Interface.Menu.MenuTerminal;
import static ufms.cptl.raymay.InterfaceOpcoes.MostraMensagem.mostrarInterMensagem;

/**
 *
 * @author nivis
 */
public class InterfaceTerminal implements UserInterface{
    MenuTerminal terminalMenu = new MenuTerminal();
    private final Scanner scanner = new Scanner(System.in);
    
    /* Métodos de impressão de menus que irão retornar um valor que será atribuido a uma variável do mesmo tipo (int)
    que permitirá a escolha da opcao */
    /* Os métodos dessa Classe também são criados em UserInterface para auxiliar a troca de interface na main */
     @Override
    public void imprimirMensagem(String mensagem){
        mostrarInterMensagem(mensagem);
    }
    
     @Override
     public String receberString(String mensagem){
        mostrarInterMensagem(mensagem);
        String valor = scanner.nextLine();
        return valor;
    }
  
    @Override
    public int imprimirInicio(){     
        if(terminalMenu.adaptarInterfaceMenu(InterMenuInicial.class, receberString("Menu Inicial")) == 0) {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;  
        } else
            return -1;   
    } 
    
    @Override
    public int imprimirCadastroGeral(){    
        int opcao3 = terminalMenu.adaptarInterfaceMenu(InterMenuCadastroGeral.class, receberString("Menu Cadastro Geral"));
        return opcao3;           
    }  
    
    @Override 
    public int imprimirCliente() {
        int opcao2 = terminalMenu.adaptarInterfaceMenu(InterMenuCliente.class, receberString("Menu Cliente"));
        return opcao2;
    }
    
    @Override
    public int imprimirVeiculo() {
        int opcao3 = terminalMenu.adaptarInterfaceMenu(InterMenuVeiculo.class, receberString("Menu gerenciamento de Veículo"));
        return opcao3;
    }
  
    @Override 
    public int imprimirVaga() {
        int opcao2 = terminalMenu.adaptarInterfaceMenu(InterMenuVaga.class, receberString("Menu Vaga"));
        return opcao2;
    }
    
     @Override  
     public int imprimirEstacionamento(){
        int opcao2 = terminalMenu.adaptarInterfaceMenu(InterMenuEstacionamento.class, receberString("Menu Estacionamento"));
        return opcao2;
    }
    
    @Override
    public int imprimirTarifa(){
        int opcao3 = terminalMenu.adaptarInterfaceMenu(InterMenuTarifa.class, receberString("Menu Tarifa"));
        return opcao3;
    }
}
 
  
    
