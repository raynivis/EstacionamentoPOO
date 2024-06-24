/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

/**
 *
 * @author maymi
 */
public class InterfaceException {
    
    /* Caso de exceção para listas de objetos vazias */
    public class ListaVaziaException extends Exception{
        public ListaVaziaException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
    
    /* Caso de exceção para erros de digitação ("agora", "mensalista", "horista" ... ) */
    public class ErroDigitacaoException extends Exception{
        public ErroDigitacaoException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
    
    /* Caso de exceção para cliente */
    public class ClienteException extends Exception{
        public ClienteException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
    
    /* Caso de exceção para placa do veículo */
    public class VeiculoException extends Exception{
        public VeiculoException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
    
    /* Caso de exceção para ticket */
    public class TicketException extends Exception{
        public TicketException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
    
    /* Caso de exceção para tarifa */
    public class TarifaException extends Exception{
        public TarifaException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
    
    /* Caso de exceção para vaga */
    public class VagaException extends Exception{
        public VagaException(String mensagem) {
            super(mensagem);
        }  
    }  
    /////////////
}
