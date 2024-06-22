/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;

/**
 *
 * @author maymi
 */
public class OperacoesTarifa {
    
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /*Métodos para facilitar a busca de tarifa na interface, a tarifa é identificada pela data de inicio, os dias da semana dela e o tipo/s
    de veiculo/s que a tarifa atende, o método retorna a tarifa se encontra-la, se não, retorna um ponteiro nulo*/
    public TarifaHorista buscarHorista(List<Tarifa> tarifas, String inicio, List<DiaSemana> dias) {
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaHorista) {
                TarifaHorista tH = (TarifaHorista) t;
                if(tH.getInicio().format(dataBonitinha).equals(inicio) 
                && tH.getDiasSemana().equals(dias)) {
                    return tH;
                } 
            }
        }
        return null;
    }
    public TarifaMensalista buscarMensalista(List<Tarifa> tarifas, String inicio) {
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaMensalista) {
                if(t.getInicio().format(dataBonitinha).equals(inicio)) {
                    return (TarifaMensalista) t;
                }
            }
        }
        return null;
    }
    
    /*Método para faciltar a identificação de LocalDateTime.DayOfWeek para o Enum criado, pela data passada por paramêtro, ele retorna
    o dia da semana pelo o Enum criado, para assim identificar os preços das tarifas*/ 
    public DiaSemana identificarDiaSemanaToEnum(LocalDateTime data){
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
    /*Método que busca a tarifa Mensalista mais proxima a partir dos atributos da tarifa mensalista mais proxima da
    data do ticket*/ 
    public TarifaMensalista buscarMensalistaMaisProxima(List<Tarifa> tarifas, LocalDateTime inicio) { 
        TarifaMensalista tarifaPerto = null;       
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaMensalista && t.getInicio().isBefore(inicio)) {               	
                if(tarifaPerto == null || Duration.between(t.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() ) {
                     tarifaPerto = (TarifaMensalista)t;  
                }  
            }    
        }    
        return tarifaPerto;
    }
    /*Método para achar a tarifa que o ticket vai usar, na lista de tarifas ele busca pelas tarifas que começaram antes da data passada por 
    referencia, além de ver se ela contem o "dia da Semana" da data, se ela atender os requisitos, ela verifica se o Tipo de Veiculo é compativel
    com essa tarifa.
    Após isso, se for a primeira vez que ele entrar na condição da "linha 117" ele já vai receber a tarifa
    Se mais de uma tarifa entrar nessa condição ele vai comparar qual a tarifa mais perto da data passada (comparando os segundos)
    por referencia.
    A melhor tarifa é escolhida e retornada, se não achar nenhum tarifa nas condições retorna um ponteiro null*/
    public TarifaHorista buscarHoristaMaisProxima(List<Tarifa> tarifas, LocalDateTime inicio) { 
        TarifaHorista tarifaPerto = null; 
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaHorista) {
                TarifaHorista tH = (TarifaHorista) t;
                if(tH.getInicio().isBefore(inicio) && tH.getDiasSemana().contains(identificarDiaSemanaToEnum(LocalDateTime.now())) ) {                      	
                    if(tarifaPerto == null || Duration.between(tH.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() ) {
                         tarifaPerto = tH;  
                    }
                }
            }
        }
        return tarifaPerto;
    }
    
    /*Método que retorna uma lista com as tarifas cadastradas no sistema para ter uma visualização*/
    public List<String> listarCadastradas(List<Tarifa> tarifas) { /* ex relatorioTarifa */
        List<String> lista = new ArrayList<>(); 
        String tarife;
        for(Tarifa t : tarifas) {
            tarife = "";
            if(t instanceof TarifaHorista) {
                TarifaHorista tH = (TarifaHorista) t;
                tarife = tarife + tH.toString();
                tarife = tarife + "\nDia/s da Semana:";
                for(DiaSemana ds : tH.getDiasSemana()){
                    tarife = tarife + ds.toString() + " ";
                }
            }
            else {
                TarifaMensalista tM = (TarifaMensalista) t;
                tarife = tarife + tM.toString();              
            }
            lista.add(tarife); 
        }
        return lista;
    }
    
    /* O método recebe a tarifa a ser procurada e a lista de tickets, faz a procura dessa tarifa nos tickets
    da lista e retorna true se encontrar, se não, retorna false */
    public boolean procurar(Tarifa tarifa, List<Ticket> tickets) {
        for(Ticket t : tickets) {
            if(t instanceof TicketHorista) {
                TicketHorista tH =  (TicketHorista) t;
                if(tH.getTarifaTicketH().equals(tarifa)) {
                    return true;
                }
            } else {
                TicketMensalista tM =  (TicketMensalista) t;
                if(tM.getTarifaTicketM().equals(tarifa)) {
                    return true;
                }
            }         
        }
        return false;
    }
}
