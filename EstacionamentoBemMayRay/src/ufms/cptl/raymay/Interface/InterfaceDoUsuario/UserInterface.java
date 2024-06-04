/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.InterfaceDoUsuario;



/**
 *
 * @author maymi
 */
public interface UserInterface{
    /* interface UserInterface que possui os métodos utilizados nas classes implements InterfaceTerminal e InterfaceGrafica
    Essa interface auxilia na chamada do tipo de interclasse na main */
        public void mensagem(String mensagem);
        
        /* Método que recebe uma string e, dependendo da interface escolhida posteriormente, imprime de maneiras diferentes
        a mesma informação/mensagem */
        public String receberString(String mensagem);
        
        
        /* Os métodos abaixo pertencem às Classes InterfaceGrafica e InterfaceTerminal, eles retornam uma opção e
        tal valor será atribuído a uma váriavel do tipo retornado (int) nas Classes do Package InterfaceOpcoes.
        A criação das classes do Package InterfaceDoUsuario auxiliam para deixar o código mais limpo e para a
        transição de interfaces, o mesmo método que possui funções diferentes dentro dele dependendo da interface
        selecionada */
        public int imprimeInicio();
          
        public int imprimeCadastroGeral();
        
        public int imprimeCliente();
        
        public int imprimeVeiculo();
        
        public int imprimeVaga();
                  
        public int imprimeEstacionamento();
        
        public int imprimeTarifa();
}    
