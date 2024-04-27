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
import static ufms.cptl.raymay.Operacoes.OperacaoMostraMensagem.operacaoMensagem;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;

/**
 *
 * @author ra
 */
public class OperacoesTicket {
    OperacoesCliente opClie = new OperacoesCliente();
    Vaga vag; /* Variavel do tipo Vaga utilizada em retirar, faz a comparação entre a vaga inserida*/
    
    /*SERIA BOM MUDAR ESSA FUNÇÃO, ELA LE SO A PLACA A IDENTIFICA O TICKET*/
    public boolean retirar(List<Ticket> tickets, List<Vaga> vagas, int numero, String rua){
        vag.setNumero(numero);
        vag.setRua(rua);
        
        for(Ticket t : tickets) {
            if(t.getVagaTicket().equals(vag) && t.getStatus() == Operando.ATIVO) {
                t.setStatus(Operando.DESATIVO);
                t.setFim(LocalDateTime.now());
                for(Vaga v : vagas) {
                    if(v.equals(vag)){
                        v.setStatus(VagaStatus.DISPONIVEL);
                        return true;
                    }
                }
            }
        }
        return false;    
    }

    /* Método que verifica se o veículo inserido possui algum ticket ATIVO ou DESATIVO atrelado a ele. 
    Primeiro faz a verificação se a placa já foi cadastrada com o método verificaTicketVeiculo, que retorna 
    o veículo com a placa inserida. Após isso, é comparado os veículos dentro da lista de tickets com o 
    veículo da placa inserida */
    public Ticket verificaTicketVeiculo(List<Cliente> clientes, String placa, List<Ticket> tickets) {
        if(opClie.verificarVeiculo(clientes, placa) != null) {
            Veiculo v = opClie.verificarVeiculo(clientes, placa);
        
            for(Ticket t : tickets) {
                if(t.getVeiculoTicket().equals(v));
                return t;
            }
        }
       return null;
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
        
        Tarifa tarifa  = ticket.getTarifaTicket();
        
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
