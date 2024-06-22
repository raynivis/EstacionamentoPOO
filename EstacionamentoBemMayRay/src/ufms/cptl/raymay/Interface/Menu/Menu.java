/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Menu;

import ufms.cptl.raymay.Interface.EnumOpcao.InterMenuGeral;

/**
 *
 * @author maymi
 */
public interface Menu {
    public <T extends Enum<T> & InterMenuGeral> int adaptarInterfaceMenu(Class<T> escolhaMenu, String menu);
}
