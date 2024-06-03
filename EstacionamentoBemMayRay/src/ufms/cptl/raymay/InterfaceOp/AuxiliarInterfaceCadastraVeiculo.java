/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOp;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceGrafica;
import ufms.cptl.raymay.Interface.UserInterface.InterfaceTerminal;
import ufms.cptl.raymay.Interface.UserInterface.UserInterface;

/**
 *
 * @author maymi
 */
public class AuxiliarInterfaceCadastraVeiculo {
    Scanner scanner = new Scanner(System.in);
    
    /*método para facilitar o cadastro de um veiculo na interface, para assim não ficar repetindo codigo atoa.*/
    public Veiculo receberVeiculo(List<Cliente> clientes, Cliente cliente, UserInterface inter, int face) {
        /*mensagem de interface para digitar um novo veiculo*/
        String placa;
        if(face == 0) {
            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
            placa = interfaces.receberString("Digite a placa");
        }else {
            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
            placa = interfaces.receberString("Digite a placa");
        }
        
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                if(v.getPlaca().equals(placa)) {
                    /*Placa ja existente*/                           
                    return null;
                }
            }
        }
        String tipoV, cor, descricao, marca, modelo;
        TipoVeiculo type;
        if(face == 0) {
            InterfaceTerminal interfaces = (InterfaceTerminal) inter;
            tipoV = interfaces.receberString("Digite o tipo do veículo(MOTO, CARRO ou ONIBUS)");
            type = TipoVeiculo.valueOf(tipoV.toUpperCase());
            
            cor = interfaces.receberString("Digite a cor");
            
            descricao = interfaces.receberString("Digite a descrição");
            
            marca = interfaces.receberString("Digite a marca");
            
            modelo = interfaces.receberString("Digite o modelo");
        }else {
            InterfaceGrafica interfaces = (InterfaceGrafica) inter;
            tipoV = interfaces.receberString("Digite o tipo do veículo(MOTO, CARRO ou ONIBUS)");
            type = TipoVeiculo.valueOf(tipoV.toUpperCase());
            
            cor = interfaces.receberString("Digite a cor");
            
            descricao = interfaces.receberString("Digite a descrição");
            
            marca = interfaces.receberString("Digite a marca");
            
            modelo = interfaces.receberString("Digite o modelo");
        }
        
        Cor color = new Cor(cor, descricao);        

        Modelo model = new Modelo(marca, modelo, type);

                                          
        Veiculo novoV = new Veiculo(placa, model, color);
        return novoV;
    }
}
