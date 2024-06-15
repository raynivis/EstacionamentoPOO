/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tickets;

import java.time.LocalDateTime;
import java.time.LocalTime;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;

/**
 *
 * @author nivis
 */
public class TicketHorista extends Ticket{
    
    public TicketHorista(Tarifa tarifaTicket, Veiculo veiculoTicket, Vaga vagaTicket) {
        super(tarifaTicket, veiculoTicket, vagaTicket);
    }
    
    /*Encerrar o ticket*/
    @Override
    public void encerrar() {   
        this.status = Operando.DESATIVO;
        this.fim = LocalDateTime.now();
        this.vagaTicket.setStatus(VagaStatus.DISPONIVEL);
        faturar();
    }

    /*Método para calcular quanto o ticket faturou, pela tarifa escolhida pelo ticket, ele ja adiciona o preço da primeira hora, logo
    em seguida ele entra em um laço adicionando 1 hora para ir adicionando "o custo" e "o tempo que o veiculo ficou estacionado"
    o laço para quando der a data de fim ou passar dela. Se a data de fim for em outro dia ele adiciona o dobro da primeira hora 
    a cada hora subsquente. Logo depois, verifica o tipo de veiculo para calcular sua porcetagem e
    retorna o total faturado do ticket*/
    @Override
     public void faturar(){
        double total;
        
        /*caso o ticket ainda estiver aberto*/
        if(fim == null) {
            total = -1;
            faturado = total;     
        }
        
        LocalDateTime diaS = inicio;
        
        TarifaHorista tarifa  = (TarifaHorista) tarifaTicket;
        
        total = tarifa.getValorPrimeiraHora();
        
        boolean multa = false;
        LocalDateTime dataMulta = LocalDateTime.of(diaS.plusDays(1).toLocalDate(), LocalTime.of(0, 0, 0));
        diaS = diaS.plusHours(1);
        
        while(diaS.isEqual(fim) != true && diaS.isAfter(fim) != true){           
            if(!multa)
                total += tarifa.getValorHoraSubsequente();         
            
            diaS = diaS.plusHours(1);
            if((diaS.isAfter(dataMulta) || diaS.isEqual(dataMulta) ) && ( fim.isAfter(dataMulta) || fim.isEqual(dataMulta)) )
                multa = true;
            
            if(multa)
                total += tarifa.getValorPrimeiraHora()*2;     
        }
        
        if(veiculoTicket.getModel().getTipoVeiculo().equals(TipoVeiculo.MOTO)) {
            total /= 2;
        }
        if(veiculoTicket.getModel().getTipoVeiculo().equals(TipoVeiculo.ONIBUS)) {
            total += total/2;
        }
        
        faturado = total;
    }
     
    @Override
    public String toString() {    
        return   "Tarifa: Horista" + "Codigo: " + codigo + "\nStatus: " + status + "\nInicio do ticket: " + inicio.format(dataBonitinha) +
                "\nData da Tarifa: " + tarifaTicket.getInicio().format(dataBonitinha) + "\nPlaca do Veiculo: " 
                + veiculoTicket.getPlaca() + "\nVaga: " + vagaTicket.getNumero() + " " + vagaTicket.getRua();
    }
}
