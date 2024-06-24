/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceInicial;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.CarregamentoArquivos.GerenciamentoArquivo;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException;

/**
 *
 * @author ra
 */
public class Estacionamento {
    public static void main(String[] args) throws InterfaceException.TicketException{
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Vaga> vagas = new ArrayList<>();
        ArrayList<Ticket> tickets  = new ArrayList<>();
        ArrayList<Tarifa> tarifas = new ArrayList<>();
        
        InterfaceInicial userinterface =  new InterfaceInicial();
            

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
                    
                    /*Carregar arquivo de dados do sistema // Tive q repetir o codigo por causa da mensagem*/
                    try {
                        ObjectInputStream arq = new ObjectInputStream(new FileInputStream("dados.bin"));

                        clientes = (ArrayList<Cliente>) arq.readObject();         
                        vagas = (ArrayList<Vaga>) arq.readObject();
                        tickets = (ArrayList<Ticket>) arq.readObject();
                        tarifas = (ArrayList<Tarifa>) arq.readObject();                     
                        userinterfaceT.imprimirMensagem("Arquivo de Banco de dados carregado!");
                        arq.close();           
                    } catch (IOException | ClassNotFoundException ex) {
                        userinterfaceT.imprimirMensagem("Ocorreu um erro ao carregar os Dados do Banco");                    
                    }        
                    /*ListasProntas.gerarListas(clientes, vagas); /*tirar*/
                    userinterface.realizarOpcoesIniciais(clientes, vagas, tickets, tarifas, userinterfaceT);
                    GerenciamentoArquivo.salvarDados(clientes, vagas, tickets, tarifas,userinterfaceT);
                    break;
                case 1:
                    InterfaceGrafica userinterfaceG = new InterfaceGrafica();
                    
                    /*Carregar arquivo de dados do sistema // Tive q repetir o codigo por causa da mensagem*/
                    try {
                        ObjectInputStream arq = new ObjectInputStream(new FileInputStream("dados.bin"));

                        clientes = (ArrayList<Cliente>) arq.readObject();         
                        vagas = (ArrayList<Vaga>) arq.readObject();
                        tickets = (ArrayList<Ticket>) arq.readObject();
                        tarifas = (ArrayList<Tarifa>) arq.readObject();

                        userinterfaceG.imprimirMensagem("Carregou");
                        arq.close();           
                    } catch (IOException | ClassNotFoundException ex) {
                        userinterfaceG.imprimirMensagem("Ocorreu um erro ao carregar os Dados do Banco");                    
                    }                
                    userinterface.realizarOpcoesIniciais(clientes, vagas, tickets, tarifas, userinterfaceG);
                    GerenciamentoArquivo.salvarDados(clientes, vagas, tickets, tarifas, userinterfaceG);
                    break;     
            }
        }
        
   }
}
