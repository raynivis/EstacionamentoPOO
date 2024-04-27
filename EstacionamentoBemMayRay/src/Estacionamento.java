/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Interface.InterfaceInicial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra
 */
public class Estacionamento {
    public static void main(String[] args){
        InterfaceInicial inter = new InterfaceInicial();
        List<Cliente> clientes = new ArrayList<>();
        List<Vaga> vagas = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        List<Tarifa> tarifas = new ArrayList<>();
        
        
        inter.primeirasOpcoes(clientes, vagas, tickets, tarifas);   
        
   }
}
