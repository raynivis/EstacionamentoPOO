/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.EnumOpcao;

/**
 *
 * @author maymi
 */
public interface InterMenuGeral {
    /*Interface InterMenuGeral para que os outros enums implements a partir dessa
    assim consigo utilizar nos métodos genéricos da interface a chamada do enum correto */
    int getValorOpcao();
    String getDesc();
}
