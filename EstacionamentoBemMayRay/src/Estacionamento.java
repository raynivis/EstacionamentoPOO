/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceInicial;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.Teste.ListasProntas;

/**
 *
 * @author ra
 */
public class Estacionamento {
    public static void main(String[] args){
        /*Listas do sistema*/
        ListasProntas listaPrnts = new ListasProntas();
        List<Cliente> clientes = new ArrayList<>();
        List<Vaga> vagas = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        List<Tarifa> tarifas = new ArrayList<>();
        InterfaceInicial userinterface =  new InterfaceInicial();
        
        
        /*opções para as listas prontas para teste*/
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja inserir clientes e vagas prontos nas listas para testes?", "Selecione", JOptionPane.YES_NO_OPTION);


        if(opcao == JOptionPane.YES_OPTION){
            listaPrnts.gerarListas(clientes, vagas);
        }

        /*primeira interface do programa Estaciona Bem!!*/
        String[] opInterface = {"Terminal", "Grafica"};
        int escolha = JOptionPane.showOptionDialog(
            null,
            "Escolha um tipo:",
            "Interface",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opInterface,
            opInterface[0]);

        // Verificando a escolha do usuário
        if (escolha != JOptionPane.CLOSED_OPTION) {
            switch(escolha) {
                case 0:
                    InterfaceTerminal userinterfaceT = new InterfaceTerminal();
                    userinterface.realizarOpcoesIniciais(clientes, vagas, tickets, tarifas, userinterfaceT);
                    break;
                case 1:
                    InterfaceGrafica userinterfaceG = new InterfaceGrafica();
                    userinterface.realizarOpcoesIniciais(clientes, vagas, tickets, tarifas, userinterfaceG);
                    break;     
            }
        }
   }
}
