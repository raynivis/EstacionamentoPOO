/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.Terminal.TerminalInterfaceInicial;
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
        /*Listas do sistema*/
        ListasProntas listaPrnts = new ListasProntas();
        TerminalInterfaceInicial inter = new TerminalInterfaceInicial();
        List<Cliente> clientes = new ArrayList<>();
        List<Vaga> vagas = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        List<Tarifa> tarifas = new ArrayList<>();
        
        /*opções para as listas prontas para teste*/
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja inserir clientes e vagas prontos nas listas para testes?", "Selecione", JOptionPane.YES_NO_OPTION);


        if(opcao == JOptionPane.YES_OPTION){
            listaPrnts.GerarListas(clientes, vagas);
        }

        /*primeira interace do programa Estaciona Bem!!*/
        inter.primeirasOpcoes(clientes, vagas, tickets, tarifas);   
        
   }
}
