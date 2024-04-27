/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.InterfaceInicial;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Teste.ListasProntas;

/**
 *
 * @author ra
 */
public class Estacionamento {
    public static void main(String[] args){
        ListasProntas listaPrnts = new ListasProntas();
        InterfaceInicial inter = new InterfaceInicial();
        List<Cliente> clientes = new ArrayList<>();
        List<Vaga> vagas = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        List<Tarifa> tarifas = new ArrayList<>();
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja inserir clientes e vagas prontos nas listas para testes?", "Selecione", JOptionPane.YES_NO_OPTION);
        switch(opcao) {
            case JOptionPane.YES_OPTION:
                listaPrnts.GerarListas(clientes, vagas);
            break;
            default:
            break;
        }
        
        inter.primeirasOpcoes(clientes, vagas, tickets, tarifas);   
        
   }
}
