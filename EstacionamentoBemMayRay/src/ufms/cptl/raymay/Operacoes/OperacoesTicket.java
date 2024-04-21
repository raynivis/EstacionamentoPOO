/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.Operando;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.OperacaoMostraMensagem.operacaoMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;

/**
 *
 * @author ra
 */
public class OperacoesTicket {
    
    public boolean retirar(List<Ticket> tickets, List<Vaga> vagas, int numero, String rua){
        for(Ticket t : tickets) {
            if(t.getNumeroVaga() == numero && t.getRuaVaga().equals(rua) && t.getStatus() == Operando.ATIVO) {
                t.setStatus(Operando.DESATIVO);
                t.setFim(LocalDateTime.now());
                for(Vaga v : vagas) {
                    if(v.getNumero() == (t.getNumeroVaga()) && v.getRua().equals(t.getRuaVaga())){
                        v.setStatus(VagaStatus.DISPONIVEL);
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
    
      
    public DiaSemana semanaToEnum(LocalDateTime data){
        DayOfWeek diaS = data.getDayOfWeek();
        DiaSemana tipo = null;
        
        switch(diaS){
            case SUNDAY:
                tipo = DiaSemana.DOMINGO;
            break; 
            case MONDAY:
                tipo = DiaSemana.SEGUNDA;
            break;
            case TUESDAY:
                tipo = DiaSemana.TERCA;
            break;
            case WEDNESDAY:
                tipo = DiaSemana.QUARTA;
            break;
            case THURSDAY:
                tipo = DiaSemana.QUINTA;
            break;
            case FRIDAY:
                tipo = DiaSemana.SEXTA;
            break;
            case SATURDAY:
                tipo = DiaSemana.SABADO;
            break;           
        }  
        return tipo;
    }
    
    public Tarifa buscarTarifa(List<Tarifa> tarifas, String inicio, List<DiaSemana> dias, List<TipoVeiculo> veiculos) {
        for(Tarifa t : tarifas) {
            if(t.getInicio().format(t.getDataBonitinha()).equals(inicio) 
            && t.getDiasSemana().equals(dias) && t.getTarifaVeiculos().equals(veiculos)) {
                return t;
            }            
        }
        return null;
    }
    
    public Ticket buscarTicket(List<Ticket> tickets, int codigo) {
        for(Ticket t : tickets) {
            if(t.getCodigo() == codigo) {
                return t;
            }
        }
        return null;
    }
      
    public Tarifa tarifaProxima(List<Tarifa> tarifas, LocalDateTime inicio, Veiculo veiculo) {
        Tarifa tarifaPerto = null;
        for(Tarifa t : tarifas) {
            if(t.getInicio().isBefore(inicio) && t.getDiasSemana().contains(semanaToEnum(LocalDateTime.now())) )
                if(t.getTarifaVeiculos().contains(veiculo.getModel().getTipoVeiculo())) {               	
                    if(tarifaPerto == null || Duration.between(t.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() )
                        tarifaPerto = t;               
                }
	}
        return tarifaPerto;
    }
    
    
    public double totalFaturadoTicket(Ticket ticket){
        double total;
        
        LocalDateTime diaS = ticket.getInicio();
        
        Tarifa tarifa  = ticket.getTarifaAtual();
        
        total = tarifa.getValorPrimeiraHora();
        
        diaS = diaS.plusHours(1);
        
        while(diaS.isEqual(ticket.getFim()) != true && diaS.isAfter(ticket.getFim()) != true){
            total = total + tarifa.getValorHoraSubsequente();
            diaS = diaS.plusHours(1);
        }
        return total;
    }
    
    public void relatorioTarifa(List<Tarifa> tarifas) {
        for(Tarifa t : tarifas) {
            System.out.println(t.toString());
            operacaoMensagem("Dia/s da Semana:");
            for(DiaSemana ds : t.getDiasSemana()){
                System.out.print(ds.toString() + " ");
            }
            operacaoMensagem("\nTipo/s de Veiculo:");
            for(TipoVeiculo tv : t.getTarifaVeiculos()){
                System.out.print(tv.toString() + " ");
            }
            operacaoMensagem("\n///////////////////////////////////////////////////");
        }
    }
    
    public void ListarTicketAtivo(List<Ticket> tickets) {
        for(Ticket t : tickets) {
            if(t.getStatus() == Operando.ATIVO){
                operacaoMensagem(t.toString());
                operacaoMensagem("///////////////////////////////////////////////////");
            }               
        }
    }
      
    public double FaturadoPeriodo(List<Ticket> tickets, LocalDateTime inicio, LocalDateTime fim){
        double soma = 0;
        for(Ticket t : tickets) {
            if(t.getFim().isAfter(inicio) && t.getFim().isBefore(fim)) {
                soma = soma + totalFaturadoTicket(t);
            }
        }
        return soma;
    }
    
}
