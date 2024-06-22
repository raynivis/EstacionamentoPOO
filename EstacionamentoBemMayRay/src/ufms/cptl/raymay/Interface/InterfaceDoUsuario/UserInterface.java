/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;

import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuGeral;



/**
 *
 * @author maymi
 */
public interface UserInterface{
    /* interface UserInterface que possui os métodos utilizados nas classes implements InterfaceTerminal e InterfaceGrafica
    Essa interface auxilia na chamada do tipo de interclasse na main */
        public void imprimirMensagem(String mensagem);
        
        /* Método que recebe uma string e, dependendo da interface escolhida posteriormente, imprime de maneiras diferentes
        a mesma informação/mensagem */
        public String receberString(String mensagem);
              
        public <T extends Enum<T> & InterMenuGeral> int imprimirMenu(Class<T> escolhaMenu, String menu);
        
        
}    
