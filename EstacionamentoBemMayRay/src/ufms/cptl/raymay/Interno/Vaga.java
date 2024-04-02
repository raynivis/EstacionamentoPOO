/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interno;

/**
 *
 * @author ra
 */
public class Vaga {
    enum VagaStatus {
        DISPONIVEL, OCUPADA, INDISPONIVEL
    }
    
    private int numero;
    private String rua;
    private VagaStatus status;
    /*Adicionar o tipo de veiculo na vaga*/

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public VagaStatus getStatus() {
        return status;
    }

    public void setStatus(VagaStatus status) {
        this.status = status;
    }
    

}
