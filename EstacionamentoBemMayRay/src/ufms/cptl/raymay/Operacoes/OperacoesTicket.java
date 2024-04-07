/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;

/**
 *
 * @author ra
 */
public class OperacoesTicket {
    
    /*metodo para estacionar o veiculo, consequentenmente criar um ticket, eh necessario ver se a vaga
    ja esta ocupada*/
    /*retorna verdade caso conseguir iniciar o ticket e falso se não conseguir pela vaga estar preenchida*/
    public boolean estacionar(List<Ticket> tickets, List<Vaga> vagas, Ticket novoTicket){
       
        for(Vaga v : vagas)
       {
           if(v.getNumero() == (novoTicket.getNumeroVaga()) && v.getRua().equals(novoTicket.getRuaVaga())) {
               if(v.getStatus() == Vaga.VagaStatus.OCUPADA) {
                    return false;
               }
               break;
           }
       }
              
       tickets.add(novoTicket);
       for(Vaga v : vagas)
       {
           if(v.getNumero() == (novoTicket.getNumeroVaga()) && v.getRua().equals(novoTicket.getRuaVaga())) {
               v.setStatus(Vaga.VagaStatus.OCUPADA);
               return true;
           }
       }
       return false;   
    }
    
    public boolean retirar(List<Ticket> tickets, List<Vaga> vagas, int codigoTicket){
        for(Ticket t : tickets) {
            if(t.getCodigo() == codigoTicket) {
                t.setStatus("Desativado");
                for(Vaga v : vagas) {
                    if(v.getNumero() == (t.getNumeroVaga()) && v.getRua().equals(t.getRuaVaga())){
                        v.setStatus(Vaga.VagaStatus.DISPONIVEL);
                        return true;
                    }
                }
            }
        }
        return false;    
    }
    
    public void gerenciarTarifa(Tarifa Novatarifa){
        /*Não sei como vai funcionar*/
        
        
    }
    
    public void cadastrosGerais(List<Cliente> clientes, List<Vaga> vagas){
        for(Cliente c : clientes){           
            System.out.println(c.toString());    
        }
        for(Vaga v : vagas){           
            System.out.println(v.toString());    
        }
    }
    /*      
    public void totalFaturado();
    {
        
    }*/
    
}
