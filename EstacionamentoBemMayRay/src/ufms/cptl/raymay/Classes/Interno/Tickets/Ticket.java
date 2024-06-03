/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tickets;

import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;


/**
 *
 * @author ra
 */
public abstract class Ticket {

    /* Os códigos auxiliam na melhor organização e identificação do ticket na lista de tickets */
    protected  final DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
    protected static int proxCodigo = 1; /* Incrementa o código para o proximo ticket a ser cadastrado */
    protected final int codigo; /* código de identificação para cada ticket, começa em 1 */
    
    /* Atributo que identifica os tickets ATIVOS e DESATIVOS, todos ficarão salvos na lista*/
    protected Operando status; 
    protected LocalDateTime inicio;
    protected LocalDateTime fim;  
    protected Tarifa tarifaTicket;
    protected Veiculo veiculoTicket;
    protected Vaga vagaTicket;

    public Ticket(Tarifa tarifaTicket, Veiculo veiculoTicket, Vaga vagaTicket) {
        this.inicio = LocalDateTime.now();
        this.status = Operando.ATIVO;
        this.codigo = proxCodigo;
        this.tarifaTicket = tarifaTicket;
        this.veiculoTicket = veiculoTicket;
        this.vagaTicket = vagaTicket;
        this.vagaTicket.setStatus(VagaStatus.OCUPADA);
        proxCodigo++;
    }

    public static int getProxCodigo() {
        return proxCodigo;
    }

    public Operando getStatus() {
        return status;
    }

    public void setStatus(Operando status) {
        this.status = status;
    }


    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Tarifa getTarifaTicket() {
        return tarifaTicket;
    }

    public void setTarifaTicket(Tarifa tarifaTicket) {
        this.tarifaTicket = tarifaTicket;
    }

    public Veiculo getVeiculoTicket() {
        return veiculoTicket;
    }

    public void setVeiculoTicket(Veiculo veiculoTicket) {
        this.veiculoTicket = veiculoTicket;
    }

    public Vaga getVagaTicket() {
        return vagaTicket;
    }

    public void setVagaTicket(Vaga vagaTicket) {
        this.vagaTicket = vagaTicket;
    }

    public int getCodigo() {
        return codigo;
    }
    /*Metodo polimorfico para o calculado do total faturado*/
    public abstract double totalFaturadoTicket();
    
    @Override
    public String toString() {
        String tarifa; 
        if(tarifaTicket instanceof TarifaMensalista){
            tarifa = "\nTarifa: Mensalista";
        }
        else {
            tarifa = "\nTarifa: Horista";
        }
             
        return  "Codigo: " + codigo + "\nStatus: " + status + "\nInicio do ticket: " + inicio.format(dataBonitinha) + tarifa +
                "\nData da Tarifa: " + tarifaTicket.getInicio().format(dataBonitinha) + "\nPlaca do Veiculo: " 
                + veiculoTicket.getPlaca() + "\nVaga: " + vagaTicket.getNumero() + " " + vagaTicket.getRua();
    }
    
    

}
