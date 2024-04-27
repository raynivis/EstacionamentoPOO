/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ufms.cptl.raymay.Enum.Operando;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;


/**
 *
 * @author ra
 */
public class Ticket {

    private DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
    private static int proxCodigo = 1;
    private final int codigo; /* código de identificação para cada ticket, começa em 1 */
    private Operando status;
    private LocalDateTime inicio;
    private LocalDateTime fim;  
    private Tarifa tarifaTicket;
    private Veiculo veiculoTicket;
    private Vaga vagaTicket;

    public Ticket(Tarifa tarifaTicket, Veiculo veiculoTicket, Vaga vagaTicket) {
        this.codigo = proxCodigo;
        this.tarifaTicket = tarifaTicket;
        this.veiculoTicket = veiculoTicket;
        this.vagaTicket = vagaTicket;
        proxCodigo++;
    }

    public static int getProxCodigo() {
        return proxCodigo;
    }

    public static void setProxCodigo(int proxCodigo) {
        Ticket.proxCodigo = proxCodigo;
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

    @Override
    public String toString() {
        return  "Codigo: " + codigo + "\nStatus: " + status + "\nInicio do ticket: " + inicio.format(dataBonitinha) +
                "\nData da Tarifa: " + tarifaTicket.getInicio().format(dataBonitinha) + "\nPlaca do Ticket: " 
                + veiculoTicket.getPlaca() + "\nVaga: " + vagaTicket.getNumero() + " " + vagaTicket.getRua();
    }
    
    

}
