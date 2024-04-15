/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interno;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import ufms.cptl.raymay.Externo.Automovel.Modelo;

/**
 *
 * @author ra
 */
public class Tarifa {
    
    public enum DiaSemana {
        SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO
    
    }
    private LocalDateTime inicio;
    
    
    private List<Double> precoVeiculo;
    private List<Double> precoPrimeiraHora;
    private List<Double> precoDias;

    public Tarifa(LocalDateTime inicio, List<Double> precoVeiculo, List<Double> precoPrimeiraHora, List<Double> precoDias) {
        this.inicio = inicio;
        this.precoVeiculo = precoVeiculo;
        this.precoPrimeiraHora = precoPrimeiraHora;
        this.precoDias = precoDias;
    }
    
    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    /*A partir desse metodo ele retorna o valor necessario para o calculo da primeira hora do veiculo
    baseado no seu tipo e no dia da semana da tarifa*/
    public double getValorPrimeira(Modelo.Tipo tipoV, DiaSemana dia) {
        double precoPrimeiraHora = 0;
        double precoTipoVeiculo = 0;
        
        if(tipoV == Modelo.Tipo.MOTOCICLETA) {
           precoTipoVeiculo = this.precoVeiculo.get(0);  
           if(dia == DiaSemana.DOMINGO) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(0);
           }
           if(dia == DiaSemana.SEGUNDA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(1);
           }
           if(dia == DiaSemana.TERCA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(2);
           }
           if(dia == DiaSemana.QUARTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(3);
           }
           if(dia == DiaSemana.QUINTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(4);
           }
           if(dia == DiaSemana.SEXTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(5);
           }
          if(dia == DiaSemana.SABADO) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(6);
           }         
        }
        if(tipoV == Modelo.Tipo.MEDIOPORTE) {
           precoTipoVeiculo = this.precoVeiculo.get(1);  
           if(dia == DiaSemana.DOMINGO) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(0);
           }
           if(dia == DiaSemana.SEGUNDA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(1);
           }
           if(dia == DiaSemana.TERCA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(2);
           }
           if(dia == DiaSemana.QUARTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(3);
           }
           if(dia == DiaSemana.QUINTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(4);
           }
           if(dia == DiaSemana.SEXTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(5);
           }
          if(dia == DiaSemana.SABADO) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(6);
           }         
        }
        
        if(tipoV == Modelo.Tipo.GRANDEPORTE) {
           precoTipoVeiculo = this.precoVeiculo.get(2);  
           if(dia == DiaSemana.DOMINGO) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(0);
           }
           if(dia == DiaSemana.SEGUNDA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(1);
           }
           if(dia == DiaSemana.TERCA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(2);
           }
           if(dia == DiaSemana.QUARTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(3);
           }
           if(dia == DiaSemana.QUINTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(4);
           }
           if(dia == DiaSemana.SEXTA) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(5);
           }
          if(dia == DiaSemana.SABADO) {
               precoPrimeiraHora = this.precoPrimeiraHora.get(6);
           }         
        }  
      return precoPrimeiraHora + precoTipoVeiculo;
    }

    public double getValorHoraSeguinte(Modelo.Tipo tipoV, DiaSemana dia) {
        double precoHoras = 0;
        double precoTipoVeiculo = 0;
        
        if(tipoV == Modelo.Tipo.MOTOCICLETA) {
           precoTipoVeiculo = this.precoVeiculo.get(0);  
           if(dia == DiaSemana.DOMINGO) {
               precoHoras = this.precoDias.get(0);
           }
           if(dia == DiaSemana.SEGUNDA) {
               precoHoras = this.precoDias.get(1);
           }
           if(dia == DiaSemana.TERCA) {
               precoHoras = this.precoDias.get(2);
           }
           if(dia == DiaSemana.QUARTA) {
               precoHoras = this.precoDias.get(3);
           }
           if(dia == DiaSemana.QUINTA) {
               precoHoras = this.precoDias.get(4);
           }
           if(dia == DiaSemana.SEXTA) {
               precoHoras = this.precoDias.get(5);
           }
          if(dia == DiaSemana.SABADO) {
               precoHoras = this.precoDias.get(6);
           }         
        }
        if(tipoV == Modelo.Tipo.MEDIOPORTE) {
           precoTipoVeiculo = this.precoVeiculo.get(1);  
           if(dia == DiaSemana.DOMINGO) {
               precoHoras = this.precoDias.get(0);
           }
           if(dia == DiaSemana.SEGUNDA) {
               precoHoras = this.precoDias.get(1);
           }
           if(dia == DiaSemana.TERCA) {
               precoHoras = this.precoDias.get(2);
           }
           if(dia == DiaSemana.QUARTA) {
               precoHoras = this.precoDias.get(3);
           }
           if(dia == DiaSemana.QUINTA) {
               precoHoras = this.precoDias.get(4);
           }
           if(dia == DiaSemana.SEXTA) {
               precoHoras = this.precoDias.get(5);
           }
          if(dia == DiaSemana.SABADO) {
               precoHoras = this.precoDias.get(6);
           }         
        }
        
        if(tipoV == Modelo.Tipo.GRANDEPORTE) {
           precoTipoVeiculo = this.precoVeiculo.get(2);  
           if(dia == DiaSemana.DOMINGO) {
               precoHoras = this.precoDias.get(0);
           }
           if(dia == DiaSemana.SEGUNDA) {
               precoHoras = this.precoDias.get(1);
           }
           if(dia == DiaSemana.TERCA) {
               precoHoras = this.precoDias.get(2);
           }
           if(dia == DiaSemana.QUARTA) {
               precoHoras = this.precoDias.get(3);
           }
           if(dia == DiaSemana.QUINTA) {
               precoHoras = this.precoDias.get(4);
           }
           if(dia == DiaSemana.SEXTA) {
               precoHoras = this.precoDias.get(5);
           }
          if(dia == DiaSemana.SABADO) {
               precoHoras = this.precoDias.get(6);
           }         
        }  
      return precoHoras + precoTipoVeiculo;
    }

    public List<Double> getPrecoVeiculo() {
        return precoVeiculo;
    }

    public void setPrecoVeiculo(List<Double> precoVeiculo) {
        this.precoVeiculo = precoVeiculo;
    }

    public List<Double> precoPrimeiraHora() {
        return precoPrimeiraHora;
    }

    public void setprecoPrimeiraHora(List<Double> precoPrimeiraHora) {
        this.precoPrimeiraHora = precoPrimeiraHora;
    }

    public List<Double> getPrecoDias() {
        return precoDias;
    }

    public void setPrecoDias(List<Double> precoDias) {
        this.precoDias = precoDias;
    }

    
    
   
     
}
