/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;



/**
 *
 * @author ra
 */
public class OperacoesTicket {
    /* Essa Classe possui métodos que serão realizados com o ticket e as tarifas,
    de acordo com a opção escolhida no Menu */
    
    OperacoesCliente opClie = new OperacoesCliente();
    DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    
    /* O método percorre a lista de tickets a procura do ticket horista que possui a placa inserida e que
    esteja ATIVO (pois pode conter mais de um ticket com a mesma placa, porém so haverá um ATIVO),
    caso encontre o ativo, faz a retirada do veículo do estacionamento, torna a vaga disponível
    e deixa o ticket DESATIVO */
    /*No caso do ticket mensalista ele verifica se o ticket mensalista está ativo e se o veiculo passado por referencia 
    pertence a ele, se ele satisfazer as duas condições, retorna true*/
    public boolean retirar(List<Ticket> tickets, Veiculo veiculoEstacio){             
        for(Ticket t : tickets) {
            if(t instanceof TicketHorista) {
                TicketHorista tH = (TicketHorista) t;
                if(tH.getVeiculoTicket().equals(veiculoEstacio) && tH.getStatus() == Operando.ATIVO) {
                    tH.encerrar();
                    return true;
                }
            }
            else{ /*Sem esse "t instanceof TicketMensalista" o IDE estava reclamando*/
                if(t instanceof TicketMensalista && t.getStatus() == Operando.ATIVO && t.getVeiculoTicket().equals(veiculoEstacio)){
                    t.getVagaTicket().indisponibilizar();
                    return true;
                }         
            }
        }
        return false;    
    }

    /*Método para verificar se a vaga esta liberada para uso nos tickets mensalistas, ele é iniciado sempre após entrar em interfaces de
    estascionamento. Lembrando que ao criar um ticket mensalista ele ja define a data final do ticket*/
    public void verificarMensalista30dias(List<Ticket> tickets) {
        for(Ticket t : tickets){
            if(t instanceof TicketMensalista && t.getStatus().equals(Operando.ATIVO)){
               TicketMensalista tM = (TicketMensalista) t; 
               if(tM.getFim().isBefore(LocalDateTime.now()) || tM.getFim().isEqual(LocalDateTime.now())){
                   tM.encerrar();
               }
            }
        }
    }
    
    /*Metodo para verificar se o existe um ticket mensalista para o veiculo ser estacionado na Vaga destinada do ticket
    ele olha para todos os tickets que possuem a estancia de Ticket mensalista e está ativo e retorna verdade se o veiculo 
    estiver vinculado a o Ticket Mensalista*/
    /*retorna true se o veiculo estiver vinculado ao um ticket mensalista e falso se não estiver*/
    public boolean verificarMensalistaParaVeiculo(List<Ticket> tickets, Veiculo veiculoT) {
        for(Ticket t : tickets){
            if(t instanceof TicketMensalista && t.getStatus().equals(Operando.ATIVO)){
               TicketMensalista tM = (TicketMensalista) t; 
               if(tM.getVeiculoTicket().equals(veiculoT) && tM.getVagaTicket().getStatus() == VagaStatus.INDISPONIVEL) {                 
                   tM.getVagaTicket().ocupar();
                   return true;
               }
            }
        }
        return false;
    }
    
    
    /* Método que verifica se o veículo possui algum ticket SENDO UTILIZADO atrelado a ele, primeiro verifica se o ticket é
    horista ou mensalista (necessário pois, mesmo após a retirada do veículo mensalista da vaga, o ticket pode continuar
    ATIVO!), se for horista verifica se está ativo e retorna o ticket. Caso não seja horista, verifica se é mensalista
    e se a vaga atrelada a esse ticket está ocupada, se estiver, significa que está sendo utilizado e retorna o ticket */
    public Ticket verificarUtilizacaoParaVeiculo(List<Cliente> clientes, String placa, List<Ticket> tickets) {
        if(opClie.buscarVeiculo(clientes, placa) != null) {
            Veiculo v = opClie.buscarVeiculo(clientes, placa);
            for(Ticket t : tickets) {
                if(t instanceof TicketHorista) {
                    if(t.getStatus() == Operando.ATIVO) {
                    if(t.getVeiculoTicket().equals(v))
                        return t;
                    }
                }
                else if (t instanceof TicketMensalista) {
                    if(t.getVeiculoTicket().equals(v) && t.getVagaTicket().getStatus() == VagaStatus.OCUPADA) {
                        return t;
                    }
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
     
    /*Métodos para facilitar a busca de tarifa na interface, a tarifa é identificada pela data de inicio, os dias da semana dela e o tipo/s
    de veiculo/s que a tarifa atende, o método retorna a tarifa se encontra-la, se não, retorna um ponteiro nulo*/
    public TarifaHorista buscarTarifaHorista(List<Tarifa> tarifas, String inicio, List<DiaSemana> dias) {
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
    public TarifaMensalista buscarTarifaMensalista(List<Tarifa> tarifas, String inicio) {
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaMensalista) {
                if(t.getInicio().format(dataBonitinha).equals(inicio)) {
                    return (TarifaMensalista) t;
                }
            }
        }
        return null;
    }
    
    
    /* O método recebe o código do ticket e a lista de tickets e percorre a lista de tickets fazendo
    a procura do código, se encontrar retorna o ticket, se não, retorna null*/
    public Ticket buscar(List<Ticket> tickets, int codigo) {
        for(Ticket t : tickets) {
            if(t.getCodigo() == codigo) {
                return t;
            }
        }
        return null;
    }
    
    
    /*Método para achar a tarifa que o ticket vai usar, na lista de tarifas ele busca pelas tarifas que começaram antes da data passada por 
    referencia, além de ver se ela contem o "dia da Semana" da data, se ela atender os requisitos, ela verifica se o Tipo de Veiculo é compativel
    com essa tarifa.
    Após isso, se for a primeira vez que ele entrar na condição da "linha 139" ele já vai receber a tarifa
    Se mais de uma tarifa entrar nessa condição ele vai comparar qual a tarifa mais perto da data passada (comparando os segundos)
    por referencia.
    A melhor tarifa é escolhida e retornada, se não achar nenhum tarifa nas condições retorna um ponteiro null*/
    public Tarifa buscarTarifaProxima(List<Tarifa> tarifas, LocalDateTime inicio, String tipoTi) { 
        Tarifa tarifaPerto = null;
        if(tipoTi.equalsIgnoreCase("HORISTA")){
            for(Tarifa t : tarifas) {
                TarifaHorista tH = (TarifaHorista) t;
                if(t instanceof TarifaHorista && tH.getInicio().isBefore(inicio) && tH.getDiasSemana().contains(identificarDiaSemanaToEnum(LocalDateTime.now())) ) {                      	
                    if(tarifaPerto == null || Duration.between(tH.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() ) {
                         tarifaPerto = tH;  
                    }
                }
            }
        }
        else {
            for(Tarifa t : tarifas) {
                if(t instanceof TarifaMensalista && t.getInicio().isBefore(inicio)) {               	
                    if(tarifaPerto == null || Duration.between(t.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() ) {
                         tarifaPerto = t;  
                    }  
                }    
            }
        }
        
        return tarifaPerto;
    }
    
    
    /*Método que retorna uma lista com as tarifas cadastradas no sistema para ter uma visualização*/
    public List<String> listarTarifasCadastradas(List<Tarifa> tarifas) { /* ex relatorioTarifa */
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
    public boolean procurarTarifa(Tarifa tarifa, List<Ticket> tickets) {
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
    
    
    /* O método retona uma lista com todos os tickets ATIVOS no momento */
    public List<String> listarAtivos(List<Ticket> tickets) {
        List<String> lista = new ArrayList<>();      
        for(Ticket t : tickets) {
            if(t.getStatus() == Operando.ATIVO){
                String ticke = t.toString();
                lista.add(ticke);
            }               
        }
        return lista;
    }
      
    
    /*Método para ver quantos tickets faturou pelo periodo passado por referencia, ele usa o metodo de total faturado do ticket
    retorna o total faturado no periodo*/
    public double calcularTotalFaturadoPeriodo(List<Ticket> tickets, LocalDateTime inicio, LocalDateTime fim){
        double soma = 0;
        for(Ticket t : tickets) {
            if(t.getFim().isAfter(inicio)) {
                if(t instanceof TicketHorista && t.getFim().isBefore(fim)){
                    TicketHorista tH = (TicketHorista) t;
                    soma = soma + tH.getFaturado();
                }
                
                if (t instanceof TicketMensalista){
                    TicketMensalista tM = (TicketMensalista) t;
                    soma = soma + tM.getFaturado();
                }                
            }
        }
        return soma;
    }
    
}
