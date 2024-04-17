/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;
import java.util.List;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
import ufms.cptl.raymay.Interno.Vaga;
/**
 *
 * @author maymi
 */
public class OperacoesVagas {
    /*retorna true se a vaga foi cadastrada e false caso não já foi cadastrada ou não 
    rpeenche os requisitos*/
    public boolean cadastrarVaga(List<Vaga> vagas, Vaga novaVaga, String rua, int numero) {
        for (Vaga v : vagas) {
            if (v.getRua().equals(rua) && v.getNumero() == numero) {
                return false;
            }
        }
        vagas.add(novaVaga);
       return true;
    }

    /*Retorna a vaga*/
    public Vaga consultarVaga(List<Vaga> vagas, int numero, String rua) {
        for(Vaga v : vagas) {
            if(v.getNumero() == numero && v.getRua().equals(rua)) {              
                return v;
            }
        }
        return null;
    }
     /* retorna true se foi excluida e false se nao foi ou nao existe */
    public boolean excluirVaga(List<Vaga> vagas, String rua, int numero) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                if(v.getStatus() != VagaStatus.OCUPADA) {
                    vagas.remove(v);
                    /*interface para mostrar que foi removido*/
                    return true;
                }
                System.out.println("A vaga nao pode ser excluida pois esta sendo usada (OCUPADA)!");
                return false;
            }  
        }
        /*interface para mostra que não foi encontrado a vaga*/
        System.out.println("Vaga nao existente!");
        return false;
    }
    public boolean editarVaga(List<Vaga> vagas, String rua, int numero, String novaRua, int novoNumero, TipoVeiculo novoTipo) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                v.setRua(novaRua);
                v.setNumero(novoNumero);
                v.setTipo(novoTipo);
                return true;
            }
        }
        return false;
    }
    /* se o ticket referente a vaga não tiver voltado ao sistema, para ser descartado, 
    não há possibilidade de alterar a disponibilidade da vaga*/ 
    public boolean alterarDispinibilidade(List<Vaga> vagas, String rua, int numero, VagaStatus novoStatus) {
        for(Vaga v : vagas){
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                if(v.getStatus() != VagaStatus.OCUPADA){                   
                    v.setStatus(novoStatus);
                    return true;
                }
                System.out.println("A disponibilidade da vaga nao pode ser alterada pois o Ticket nao foi devolvido (OCUPADA)");
                return false;
            }          
        }
        System.out.println("Vaga nao existente!");
        return false;
    }
    public void listarVagasDisponiveis(List<Vaga> vagas) {
        for(Vaga v : vagas) {
            if(v.getStatus() == VagaStatus.DISPONIVEL) {
                System.out.println(v.toString());
            }
        }
    } 
}
