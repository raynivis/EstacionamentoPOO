/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuCadastroGeral;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuCliente;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuEstacionamento;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuInicial;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuTarifa;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuVaga;
import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuVeiculo;
import ufms.cptl.raymay.Interface.Menu.MenuGrafica;

/**
 *
 * @author nivis
 */
public class InterfaceGrafica implements UserInterface{
    MenuGrafica graficaMenu = new MenuGrafica();
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
    public int imprimirInicio(){     
        opcao = graficaMenu.adaptarInterfaceMenu(InterMenuInicial.class, "Menu Inicial");
        return opcao;
    }
    
    @Override
    public int imprimirCadastroGeral(){       
      opcao = graficaMenu.adaptarInterfaceMenu(InterMenuCadastroGeral.class, "Menu Cadastro Geral");
        return opcao;
    }  
    
    @Override 
    public int imprimirCliente() {
       opcao = graficaMenu.adaptarInterfaceMenu(InterMenuCliente.class, "Menu Cliente");
        return opcao;
    }
    
    @Override
    public int imprimirVeiculo(){
        opcao = graficaMenu.adaptarInterfaceMenu(InterMenuVeiculo.class, "Menu gerenciamento de Veículo");
        return opcao;
    }
   
    @Override
    public int imprimirVaga() {
        opcao = graficaMenu.adaptarInterfaceMenu(InterMenuVaga.class, "Menu Vaga");
        return opcao;
    }
    
    @Override   
    public int imprimirEstacionamento(){
       opcao = graficaMenu.adaptarInterfaceMenu(InterMenuEstacionamento.class, "Menu Estacionamento");
        return opcao;
    }
    
    @Override
    public int imprimirTarifa(){
        opcao = graficaMenu.adaptarInterfaceMenu(InterMenuTarifa.class, "Menu Tarifa");
        return opcao;
    }    
}  

