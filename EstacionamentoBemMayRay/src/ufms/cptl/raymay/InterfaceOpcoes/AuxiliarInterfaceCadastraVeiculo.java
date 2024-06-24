/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.util.List;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.InterfaceOpcoes.InterfaceException.ErroDigitacaoException;

/**
 *
 * @author maymi
 */
public class AuxiliarInterfaceCadastraVeiculo {
    InterfaceException ex = new InterfaceException();
    /*método para facilitar o cadastro de um veiculo na interface, para assim não ficar repetindo codigo atoa.*/
    public Veiculo cadastrarVeiculo(List<Cliente> clientes, Cliente cliente, UserInterface inter) {
        try {
            /*mensagem de interface para digitar um novo veiculo*/
            String placa;
            placa = inter.receberString("Digite a placa");

            for(Cliente c : clientes) {
                for(Veiculo v : c.getVeiculos() ) {
                    if(v.getPlaca().equals(placa)) {
                        /*Placa ja existente*/  
                        throw ex.new ErroDigitacaoException("Placa já existente no sistema!!!");
                    }
                }
            }
            /* atribuindo as características do veículo */
            String tipoV, cor, descricao, marca, modelo;
            TipoVeiculo type;

            tipoV = inter.receberString("Digite o tipo do veículo(MOTO, CARRO ou ONIBUS)");
            if(!tipoV.equalsIgnoreCase("MOTO") || !tipoV.equalsIgnoreCase("CARRO") || !tipoV.equalsIgnoreCase("ONIBUS")) {
                throw ex.new ErroDigitacaoException("São válidas somente as palavras moto, carro ou onibus!");
            } 
            type = TipoVeiculo.valueOf(tipoV.toUpperCase());


            tipoV = inter.receberString("Digite o tipo do veículo(MOTO, CARRO ou ONIBUS)");
            type = TipoVeiculo.valueOf(tipoV.toUpperCase());

            cor = inter.receberStringFormat("Digite a cor", "^[\\p{L}]+$", "cor");

            descricao = inter.receberString("Digite a descrição");

            marca = inter.receberString("Digite a marca");

            modelo = inter.receberString("Digite o modelo");

            Cor color = new Cor(cor, descricao);        

            Modelo model = new Modelo(marca, modelo, type);

            Veiculo novoV = new Veiculo(placa, model, color);
            return novoV;
        } catch (ErroDigitacaoException e){
            inter.imprimirException(e.getMessage());
        }
        return null;
    }
}
