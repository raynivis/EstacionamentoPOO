/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;
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
    public <T extends Enum<T> & InterMenuGeral> int imprimirMenu(Class<T> escolhaMenu, String menuSelecao) {
        T[] menu = escolhaMenu.getEnumConstants();
         String[] opDesc = new String[menu.length];
    
        for (int i = 0; i < menu.length; i++) {
            T constant = menu[i];
            opDesc[i] = constant.getValorOpcao() + ". " + constant.getDesc();
        }
        String escolha = (String) JOptionPane.showInputDialog(
                   null,
                   "Escolha uma opção:",
                   menuSelecao,
                   JOptionPane.INFORMATION_MESSAGE,
                   null,
                   opDesc,
                   opDesc[0]);

        // Converte a escolha para o enum correspondente e obtém o valor da opção
        if (escolha != null) {
            for (T opcao : menu) {
                if (escolha.equals(opcao.getValorOpcao() + ". " + opcao.getDesc())) {
                    return opcao.getValorOpcao();
                }
            }    
        }
        /* Opcao inválida, tera um break na interface */
        return -1;
    }
    
}  

