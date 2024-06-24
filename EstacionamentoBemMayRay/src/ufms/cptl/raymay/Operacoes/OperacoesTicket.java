/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
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
               if(tM.getVeiculoTicket().equals(veiculoT) && tM.getVagaTicket().isIndisponivel()) {                 
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
                    if(t.getVeiculoTicket().equals(v) && t.getVagaTicket().isOcupada()) {
                        return t;
                    }
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
            if(t instanceof TicketHorista && t.getFim() != null && t.getFim().isAfter(inicio)) {
                if(t.getFim().isBefore(fim)){
                    TicketHorista tH = (TicketHorista) t;
                    soma = soma + tH.getFaturado();
                }                         
            }
            
            if(t instanceof TicketMensalista &&  t.getInicio().isAfter(inicio)){
                TicketMensalista tM = (TicketMensalista) t;
                soma = soma + tM.getFaturado();
            }    
        }
        return soma;
    }
    
    public List<String> calcularTotalFaturadoVeiculo(List<Cliente> clientes){
        List<String> textos = new ArrayList<>();
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
        
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                textos.add("O veiculo com a placa " + v.getPlaca() + " contribuiu com " + dinheiro.format(v.getContribuido()) +
               " ao Estacionamento!" );
            }
        }
      
        return textos;
    }
    
}
