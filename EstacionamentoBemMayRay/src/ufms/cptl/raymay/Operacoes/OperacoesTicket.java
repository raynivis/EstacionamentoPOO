/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import ufms.cptl.raymay.Externo.Automovel.Modelo;
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
    
    
    public boolean retirar(List<Ticket> tickets, List<Vaga> vagas, int numero, String rua){
        for(Ticket t : tickets) {
            if(t.getNumeroVaga() == numero && t.getRuaVaga().equals(rua)) {
                t.setStatus(Ticket.Operando.DESATIVO);
                t.setFim(LocalDateTime.now());
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
    
    
    
    public void cadastrosGerais(List<Cliente> clientes, List<Vaga> vagas){
        for(Cliente c : clientes){           
            System.out.println(c.toString());    
        }
        for(Vaga v : vagas){           
            System.out.println(v.toString());    
        }
    }
    
    
    public Tarifa.DiaSemana semanaToEnum(LocalDateTime data){
        DayOfWeek diaS = data.getDayOfWeek();
        Tarifa.DiaSemana tipo = null;
        
        switch(diaS){
            case SUNDAY:
                tipo = Tarifa.DiaSemana.DOMINGO;
            break; 
            case MONDAY:
                tipo = Tarifa.DiaSemana.SEGUNDA;
            break;
            case TUESDAY:
                tipo = Tarifa.DiaSemana.TERCA;
            break;
            case WEDNESDAY:
                tipo = Tarifa.DiaSemana.QUARTA;
            break;
            case THURSDAY:
                tipo = Tarifa.DiaSemana.QUINTA;
            break;
            case FRIDAY:
                tipo = Tarifa.DiaSemana.SEXTA;
            break;
            case SATURDAY:
                tipo = Tarifa.DiaSemana.SABADO;
            break;           
        }  
        return tipo;
    }
    
    
    
    public void totalFaturadoTicket(Ticket ticket, List<Vaga> vagas, List<Tarifa> tarifas){
        double total;
        Modelo.Tipo ticketV = null; 
        LocalDateTime diaS; 
        
        for(Vaga v : vagas){
            if(ticket.getRuaVaga().equals(v.getRua()) && ticket.getNumeroVaga() == v.getNumero()){
                ticketV = v.getTipo();
            }
        }
        
        diaS = ticket.getInicio();
        
        Tarifa tarifa  = ticket.getTarifaAtual();
        
        total = tarifa.getValorPrimeira(ticketV, semanaToEnum(diaS));
        
        diaS = diaS.plusHours(1);
        
        while(diaS.isEqual(ticket.getFim()) != true && diaS.isAfter(ticket.getFim()) != true){
            for(Tarifa t : tarifas) {
                if( t.getInicio().isAfter(diaS) && t.getInicio().isBefore(diaS.plusHours(1))) {
                    /*se foi iniciada entre diaS e diaS + 1*/
                    tarifa = t;
                }
            }
            total = total + tarifa.getValorHoraSeguinte(ticketV, semanaToEnum(diaS));
            diaS = diaS.plusHours(1);
        }
        
    }
    
}
