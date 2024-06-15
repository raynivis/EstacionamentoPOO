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

    public TicketMensalista(Tarifa tarifaTicket, Veiculo veiculoTicket, Vaga vagaTicket) {
        super(tarifaTicket, veiculoTicket, vagaTicket);
        this.fim = LocalDateTime.now().plusDays(30);
        faturar();
    }
    
    /*Encerrar o ticket*/
    @Override
    public void encerrar() {
        this.status = Operando.DESATIVO;       
        this.vagaTicket.setStatus(VagaStatus.DISPONIVEL);
    }
    
     /*Método para calcular quanto o ticket faturou, pela tarifa escolhida pelo ticket, ele ja adiciona o preço unico, logo
    em seguida verifica o tipo de veiculo para calcular sua porcetagem e retorna o total faturado do ticket*/
    @Override
    public void faturar(){
        double total;
        
        TarifaMensalista tarifa  = (TarifaMensalista) tarifaTicket;
        
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
                "\nData da Tarifa: " + tarifaTicket.getInicio().format(dataBonitinha) + "\nPlaca do Veiculo: " 
                + veiculoTicket.getPlaca() + "\nVaga: " + vagaTicket.getNumero() + " " + vagaTicket.getRua();
    }
}
