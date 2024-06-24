/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.CarregamentoArquivos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;

/**
 *
 * @author nivis
 */
public class GerenciamentoArquivo {
    /*metodo de procedimento para facililar a operacao de salvar os dados, ele está salvando em binario!*/
    public static void salvarDados(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter){
        try{
           ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("dados.bin"));                  
           arq.writeObject(clientes);
           arq.writeObject(vagas);
           arq.writeObject(tickets);
           arq.writeObject(tarifas);
           arq.close();
           inter.imprimirMensagem("Dados Salvos com sucesso!");
        } catch(IOException ex){
            inter.imprimirMensagem("Ocorreu um erro ao salvar os Dados no Banco");
            ex.printStackTrace();
        }
    }
  
}
