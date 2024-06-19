/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tickets;

import java.time.LocalDateTime;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;

/**
 *
 * @author nivis
 */
public class TicketMensalista extends Ticket {
    private TarifaMensalista tarifaTicketM;

    public TicketMensalista(TarifaMensalista tarifaTicketM, Veiculo veiculoTicket, Vaga vagaTicket) {
        super(veiculoTicket, vagaTicket);
        this.tarifaTicketM = tarifaTicketM;
        this.fim = LocalDateTime.now().plusDays(30);
        faturar();
    }

    public TarifaMensalista getTarifaTicketM() {
        return tarifaTicketM;
    }
    
    /*Encerrar o ticket*/
    @Override
    public void encerrar() {
        this.status = Operando.DESATIVO;       
        this.vagaTicket.disponibilizar();
    }
    
     /*Método para calcular quanto o ticket faturou, pela tarifa escolhida pelo ticket, ele ja adiciona o preço unico, logo
    em seguida verifica o tipo de veiculo para calcular sua porcetagem e retorna o total faturado do ticket*/
    @Override
    public void faturar(){
        double total;
        
        TarifaMensalista tarifa  = tarifaTicketM;
        
        total = tarifa.getValorUnico();
             
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
        return   "Tarifa: Mensalista" + "Codigo: " + codigo + "\nStatus: " + status + "\nInicio do ticket: " + inicio.format(dataBonitinha) +
                "\nData da Tarifa: " + tarifaTicketM.getInicio().format(dataBonitinha) + "\nPlaca do Veiculo: " 
                + veiculoTicket.getPlaca() + "\nVaga: " + vagaTicket.getNumero() + " " + vagaTicket.getRua();
    }
}
