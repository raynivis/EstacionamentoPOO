/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;
import java.util.List;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;
import static ufms.cptl.raymay.Interface.OperacaoMostraMensagem.operacaoMensagem;
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
     /* Retorna true se foi excluída e false se não foi ou não existe */
    public boolean excluirVaga(List<Vaga> vagas, String rua, int numero) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                if(v.getStatus() != VagaStatus.OCUPADA) {
                    vagas.remove(v);
                    /*interface para mostrar que foi removida*/
                    return true;
                }
                /* 1 condição que justifica não ser possível a exclusão da vaga -> Estar sendo utilizada */
                operacaoMensagem("A vaga não pode ser excluída pois está sendo usada (OCUPADA)!");
                return false;
            }  
        }
        /* 2 condição que justifica não ser possível a exclusão da vaga -> Vaga inexistente */
        operacaoMensagem("Vaga não existente!");
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
                operacaoMensagem("A disponibilidade da vaga não pode ser alterada pois o Ticket não foi devolvido (OCUPADA)!");
                return false;
            }          
        }
        operacaoMensagem("Vaga não existente!");
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
