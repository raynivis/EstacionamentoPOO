/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Operacoes;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import static ufms.cptl.raymay.Operacoes.OperacaoMostraMensagem.operacaoMensagem;
import ufms.cptl.raymay.Classes.Interno.Vaga;
/**
 *
 * @author maymi
 */
public class OperacoesVagas {
    
    /* O método verifica o número e a rua da vaga a ser cadastrada, identifica se a chave composta
    (número e rua) já existe na lista de vagas (nesse caso, retorna false), se não existe, a vaga é
    cadastrada e adicionada a lista de vagas */
    public boolean cadastrarVaga(List<Vaga> vagas, Vaga novaVaga, String rua, int numero) {
        for (Vaga v : vagas) {
            if (v.getRua().equals(rua) && v.getNumero() == numero) {
                return false;
            }
        }
       vagas.add(novaVaga);
       return true;
    }

    
    /* O método recebe o número e a rua da vaga a ser consultada e a lista de vagas para realizar 
    a procura, caso seja encontrada, retorna a própria vaga (objeto), se não, retorna null */
    public Vaga consultarVaga(List<Vaga> vagas, int numero, String rua) {
        for(Vaga v : vagas) {
            if(v.getNumero() == numero && v.getRua().equals(rua)) {              
                return v;
            }
        }
        return null;
    }
    
    
     /* O método realiza a procura pela vaga, se for encontrada percorre a lista de tickets para verificar
    se existe algum ticket cadastrado (ATIVO OU DESATIVO), caso possua, a vaga não é excluída,
    se não, ela é excluída*/
    public String excluirVaga(List<Vaga> vagas, List<Ticket> tickets, String rua, int numero) {
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                for(Ticket t: tickets) {
                    if(t.getVagaTicket().equals(v)) {
                        /* 1 condição que justifica não ser possível a exclusão da vaga -> possui ticket */                    
                        return "\nA vaga não pode ser excluída pois ela possui um ticket cadastrado!\n";
                    }
                }
                vagas.remove(v);
                return null;
            }  
        }
        /* 2 condição que justifica não ser possível a exclusão da vaga -> vaga inexistente */
        return "\nVaga inexistente!\n";
    }
    
    
    /* O método recebe a lista de vagas, a rua e o número da vaga existente e seu novo número e rua,
    quando encontra a vaga na lista, substitui os atributos pelos novos inseridos e retorna true,
    caso não encontre a vaga retorna false */
    public boolean editarVaga(List<Vaga> vagas, String rua, int numero, String novaRua, int novoNumero) {
        if(consultarVaga(vagas, novoNumero,novaRua) != null) {
            return false;
        }
        for(Vaga v : vagas) {
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                v.setRua(novaRua);
                v.setNumero(novoNumero);
                return true;
            }
        }
        return false;
    }
    
    
    /* O método procura a vaga inserida (rua e número), se encontrar verifica se está OCUPADA, ou seja
    possui um ticket ATIVO, se estiver não é possível a modificação da disponibilidade, se não, 
    sua disponibilidade é alterada */
    public boolean alterarDispinibilidade(List<Vaga> vagas, String rua, int numero, VagaStatus novoStatus) {
        for(Vaga v : vagas){
            if(v.getRua().equals(rua) && v.getNumero() == numero) {
                if(v.getStatus() != VagaStatus.OCUPADA){                   
                    v.setStatus(novoStatus);
                    return true;
                }
                operacaoMensagem("A disponibilidade da vaga não pode ser alterada pois está OCUPADA!");
                return false;
            }          
        }
        operacaoMensagem("Vaga não existente!");
        return false;
    }
    
    
    /* O método lista todas as vagas disponíveis na lista de vagas */
    public List<String> listarVagasDisponiveis(List<Vaga> vagas) {
        List<String> lista = new ArrayList<>();   
        for(Vaga v : vagas) {         
            if(v.getStatus() == VagaStatus.DISPONIVEL) {
                String vaga;
                vaga = "\n" + v.toString() + "\n";
                vaga = vaga + "\n" + "///////////////////////////////////////////////////";  
                lista.add(vaga);
            }           
        }
        return lista;
    } 
    
    
    /* O método lista TODAS as vagas cadastradas no sistema, presentes na lista de vagas */
    public List<String> listarVagasCadastradas(List<Vaga> vagas) {
        List<String> lista = new ArrayList<>();      
        for(Vaga v : vagas) { 
            String vaga;
            vaga = "\n" + v.toString() + "\n";
            vaga = vaga + "\n" + "///////////////////////////////////////////////////";  
            lista.add(vaga);
        }
        return lista; 
    }    
}
