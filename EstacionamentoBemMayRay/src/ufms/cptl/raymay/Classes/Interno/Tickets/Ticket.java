/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Interno.Tickets;

import java.io.Serializable;
import java.time.LocalDateTime;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Interno.Vaga;


/**
 *
 * @author ra
 */
public abstract class Ticket implements Serializable{

    /* Os códigos auxiliam na melhor organização e identificação do ticket na lista de tickets */
    protected static int proxCodigo = 1; /* Incrementa o código para o proximo ticket a ser cadastrado */
    protected final int codigo; /* código de identificação para cada ticket, começa em 1 */
    
    /* Atributo que identifica os tickets ATIVOS e DESATIVOS, todos ficarão salvos na lista*/
    protected Operando status; 
    protected LocalDateTime inicio;
    protected LocalDateTime fim;  
    protected Veiculo veiculoTicket;
    protected Vaga vagaTicket;
    protected double faturado; 

    public Ticket(Veiculo veiculoTicket, Vaga vagaTicket) {
        this.faturado = 0;
        this.inicio = LocalDateTime.now();
        this.status = Operando.ATIVO;
        this.codigo = proxCodigo;
        this.veiculoTicket = veiculoTicket;
        this.vagaTicket = vagaTicket;
        this.vagaTicket.ocupar();
        proxCodigo++;
    }

    public Operando getStatus() {
        return status;
    }

    public void setStatus(Operando status) {
        this.status = status;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Veiculo getVeiculoTicket() {
        return veiculoTicket;
    }

    public Vaga getVagaTicket() {
        return vagaTicket;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getFaturado() {
        return faturado;
    } 
    
    /*Metodo polimorfico para encerrar o ticket*/
    public abstract void encerrar();  
    
    /*Metodo polimorfico para o calculado do total faturado*/
    public abstract void faturar();  
}
