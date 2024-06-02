/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.Terminal.InterfaceInicial;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Interface.Grafica.InterfaceInicialG;
import ufms.cptl.raymay.Interface.Userinterface.UserInterface;
import ufms.cptl.raymay.Teste.ListasProntas;

/**
 *
 * @author ra
 */
public class Estacionamento {
    public static void main(String[] args){
        /*Listas do sistema*/
        ListasProntas listaPrnts = new ListasProntas();
        UserInterface userinterface;
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
        String[] tipo = {"Terminal", "Grafica"};
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha a interface: ",
                "Tipos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                tipo,
                tipo[0]
        ); 
        
        if (escolha == 0) {
            userinterface = new InterfaceInicial();
        } else if (escolha == 1) {
            userinterface = new InterfaceInicialG();
        } else {
            JOptionPane.showMessageDialog(null, "Erro!", "Nenhuma interface foi selecionada!", JOptionPane.ERROR_MESSAGE);
        }
        
   }
}
