/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ufms.cptl.raymay.Enum.DiaSemana;
import ufms.cptl.raymay.Enum.Operando;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Operacoes.OperacaoMostraMensagem.operacaoMensagem;
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
    
    
    /* O método percorre a lista de tickets a procura do ticket que possui a placa inserida e que
    esteja ATIVO (pois pode conter mais de um ticket com a mesma placa, porém so haverá um ATIVO),
    caso encontre o ativo, faz a retirada do veículo do estacionamento, torna a vaga disponível
    e deixa o ticket DESATIVO */
    public boolean retirar(List<Ticket> tickets, Veiculo veiculoEstacio){             
        for(Ticket t : tickets) {
            if(t instanceof TicketHorista) {
                TicketHorista tH = (TicketHorista) t;
                if(tH.getVeiculoTicket().equals(veiculoEstacio) && tH.getStatus() == Operando.ATIVO) {
                    tH.setStatus(Operando.DESATIVO);
                    tH.setFim(LocalDateTime.now());
                    tH.getVagaTicket().setStatus(VagaStatus.DISPONIVEL);
                    return true;
                }
            }
        }
        return false;    
    }
    /*Método para verificar se a vaga esta liberada para uso nos tickets mensalistas, ele é iniciado sempre após entrar em interfaces de
    estascionamento*/
    public void verificarTicketsMensalista(List<Ticket> tickets) {
        for(Ticket t : tickets){
            if(t instanceof TicketMensalista && t.getStatus().equals(Operando.ATIVO)){
               TicketMensalista tM = (TicketMensalista) t; 
               if(tM.getFim().isBefore(LocalDateTime.now()) || tM.getFim().isEqual(LocalDateTime.now())){
                   tM.setStatus(Operando.DESATIVO);
                   tM.getVagaTicket().setStatus(VagaStatus.DISPONIVEL);
               }
            }
        }
    }
    
    
    /* Método que verifica se o veículo inserido possui algum ticket ATIVO atrelado a ele. 
    Primeiro faz a verificação se a placa já foi cadastrada no sistema, com o método verificarVeiculo,
    que retorna o veículo com a placa respectiva. Após isso, passa pela lista de tickets, se estiver ativo
    verifica se a respectiva placa está contida nesse ticket. Se encontrar, retorna o ticket, se não,
    retorna null */
    public Ticket verificaTicketVeiculo(List<Cliente> clientes, String placa, List<Ticket> tickets) {
        if(opClie.verificarVeiculo(clientes, placa) != null) {
            Veiculo v = opClie.verificarVeiculo(clientes, placa);
            for(Ticket t : tickets) {
                if(t.getStatus() == Operando.ATIVO) {
                    if(t.getVeiculoTicket().equals(v))
                        return t;
                }
            }
        }
       return null;
    }    
    
    
    /*Método para faciltar a identificação de LocalDateTime.DayOfWeek para o Enum criado, pela data passada por paramêtro, ele retorna
    o dia da semana pelo o Enum criado, para assim identificar os preços das tarifas*/ 
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
    
    
    /*Métodos para facilitar a busca de tarifa na interface, a tarifa é identificada pela data de inicio, os dias da semana dela e o tipo/s
    de veiculo/s que a tarifa atende, o método retorna a tarifa se encontra-la, se não, retorna um ponteiro nulo*/
    public TarifaHorista buscarTarifaHorista(List<Tarifa> tarifas, String inicio, List<DiaSemana> dias, List<TipoVeiculo> veiculos) {
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaHorista) {
                if(t.getInicio().format(dataBonitinha).equals(inicio) 
                && t.getDiasSemana().equals(dias) && t.getTarifaVeiculos().equals(veiculos)) {
                    return (TarifaHorista) t;
                } 
            }
        }
        return null;
    }
    public TarifaMensalista buscarTarifaMensalista(List<Tarifa> tarifas, String inicio, List<DiaSemana> dias, List<TipoVeiculo> veiculos) {
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaMensalista) {
                if(t.getInicio().format(dataBonitinha).equals(inicio) 
                && t.getDiasSemana().equals(dias) && t.getTarifaVeiculos().equals(veiculos)) {
                    return (TarifaMensalista) t;
                }
            }
        }
        return null;
    }
    
    
    /* O método recebe o código do ticket e a lista de tickets e percorre a lista de tickets fazendo
    a procura do código, se encontrar retorna o ticket, se não, retorna null*/
    public Ticket buscarTicket(List<Ticket> tickets, int codigo) {
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
    public Tarifa tarifaProxima(List<Tarifa> tarifas, LocalDateTime inicio, Veiculo veiculo, String tipoTi) {
        Tarifa tarifaPerto = null;
        if(tipoTi.equalsIgnoreCase("HORISTA")){
            for(Tarifa t : tarifas) {
            if(t instanceof TarifaHorista && t.getInicio().isBefore(inicio) && t.getDiasSemana().contains(semanaToEnum(LocalDateTime.now())) )
                if(t.getTarifaVeiculos().contains(veiculo.getModel().getTipoVeiculo())) {               	
                    if(tarifaPerto == null || Duration.between(t.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() ) {
                         tarifaPerto = t;  
                    }                               
                }
            }
        }
        else {
            for(Tarifa t : tarifas) {
            if(t instanceof TarifaMensalista && t.getInicio().isBefore(inicio) && t.getDiasSemana().contains(semanaToEnum(LocalDateTime.now())) )
                if(t.getTarifaVeiculos().contains(veiculo.getModel().getTipoVeiculo())) {               	
                    if(tarifaPerto == null || Duration.between(t.getInicio(), inicio).getSeconds() <= Duration.between(tarifaPerto.getInicio(), inicio).getSeconds() ) {
                         tarifaPerto = t;  
                    }                               
                }
            }
        }
        
        return tarifaPerto;
    }
    
    
    /*Método para printar as tarifas cadastrada no sistema para ter uma visualização*/
    public void relatorioTarifa(List<Tarifa> tarifas) {
        for(Tarifa t : tarifas) {
            if(t instanceof TarifaHorista) {
                TarifaHorista tH = (TarifaHorista) t;
                System.out.println(tH.toString());
            }
            else {
                TarifaMensalista tM = (TarifaMensalista) t;
                System.out.println(tM.toString());
            }
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
    
    
    /* O método recebe a tarifa a ser procurada e a lsita de tickets, faz a procura dessa tarifa nos tickets
    da lista e retorna true se encontrar, se não, retorna false */
    public boolean procuraTarifaEmTicket(Tarifa tarifa, List<Ticket> tickets) {
        for(Ticket t : tickets) {
            if(t.getTarifaTicket().equals(tarifa)) {
                return true;
            }
        }
        return false;
    }
    
    
    /* O método lsita todos os tickets ATIVOS no momento */
    public void ListarTicketAtivo(List<Ticket> tickets) {
        for(Ticket t : tickets) {
            if(t.getStatus() == Operando.ATIVO){
                operacaoMensagem(t.toString());
                operacaoMensagem("///////////////////////////////////////////////////");
            }               
        }
    }
      
    
    /*Método para ver quantos tickets faturou pelo periodo passado por referencia, ele usa o metodo de total faturado do ticket
    retorna o total faturado no periodo*/
    public double FaturadoPeriodo(List<Ticket> tickets, LocalDateTime inicio, LocalDateTime fim){
        double soma = 0;
        for(Ticket t : tickets) {
            if(t.getFim().isAfter(inicio)) {
                if(t instanceof TicketHorista && t.getFim().isBefore(fim)){
                    TicketHorista tH = (TicketHorista) t;
                    soma = soma + tH.totalFaturadoTicket();
                }
                
                if (t instanceof TicketMensalista){
                    TicketMensalista tM = (TicketMensalista) t;
                    soma = soma + tM.totalFaturadoTicket();
                }                
            }
        }
        return soma;
    }
    
}
