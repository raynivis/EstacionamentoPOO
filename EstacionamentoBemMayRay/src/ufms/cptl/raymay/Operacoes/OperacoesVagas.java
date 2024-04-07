/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;
import java.util.List;
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
    public Vaga consultarVaga(List<Vaga> vagas, String rua, int numero) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                System.out.println(v.toString());
                return v;
            }
        }
        return null;
    }
     /* retorna true se foi excluida e false se nao foi ou nao existe */
    public boolean excluirVaga(List<Vaga> vagas, String rua, int numero) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                vagas.remove(v);
                /*interface para mostrar que foi removido*/
                return true;
            }
        }
        /*interface para mostra que não foi encontrado a vaga*/
        return false;
    }
    public boolean editarVaga(List<Vaga> vagas, String rua, int numero, String novaRua, int novoNumero) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                v.setRua(novaRua);
                v.setNumero(novoNumero);
                return true;
            }
        }
        return false;
    }
    /* se o ticket referente a vaga não tiver voltado ao sistema, para ser descartado, 
    não há possibilidade de alterar a disponibilidade da vaga*/ 
    public boolean alterarDispinibilidade(List<Vaga> vagas, String rua, int numero, Vaga.VagaStatus novoStatus) {
            for(Vaga v : vagas){               
                if(v.getRua().equals(rua) && v.getNumero() == numero) {
                    if(v.getStatus() != Vaga.VagaStatus.OCUPADA ){                   
                        v.setStatus(novoStatus);
                        return true;
                    }
                    return false;
                }
            }
            return false;
    }
    public void listarVagasDisponiveis(List<Vaga> vagas) {
        for(Vaga v : vagas) {
            if(v.getStatus() == Vaga.VagaStatus.DISPONIVEL) {
                System.out.println(v.toString());
            }
        }
    } 
}
