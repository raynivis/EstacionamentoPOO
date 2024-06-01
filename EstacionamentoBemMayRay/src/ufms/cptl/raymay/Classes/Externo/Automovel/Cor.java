/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Classes.Externo.Automovel;

/**
 *
 * @author ra
 */
public class Cor {
    private String cor;
    private String descricao;

    
    public Cor(String cor) {
        this.cor = cor;
    }

    public Cor(String cor, String descricao) {
        this.cor = cor;
        this.descricao = descricao;
    }
    
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
}
