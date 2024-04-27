/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import static ufms.cptl.raymay.Interface.MostraMensagem.interMensagem;

/**
 *
 * @author maymi
 */
public class AuxiliarInterfaceCadastraVeiculo {
    Scanner scanner = new Scanner(System.in);
    
    public Veiculo receberVeiculo(List<Cliente> clientes, Cliente cliente) {
        /*mensagem de interface para digitar um novo veiculo*/
        interMensagem("Digite a placa: ");
        String placa = scanner.nextLine(); 
        
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                if(v.getPlaca().equals(placa)) {
                    /*Placa ja existente*/                           
                    return null;
                }
            }
        }
        
        interMensagem("Digite a cor: ");       
        String cor = scanner.nextLine();
        interMensagem("Digite a descrição: "); 
        String descricao = scanner.nextLine();
        Cor color = new Cor(cor, descricao);
        
        interMensagem("Digite a marca: "); 
        String marca = scanner.nextLine();
        interMensagem("Digite o modelo: "); 
        String modelo = scanner.nextLine();        
        interMensagem("Digite o tipo do veículo(MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
        String tipoV = scanner.nextLine();
        TipoVeiculo type = TipoVeiculo.valueOf(tipoV.toUpperCase());
        Modelo model = new Modelo(marca, modelo, type);

                                          
        Veiculo novoV = new Veiculo(placa, model, color);
                                
        return novoV;
    }
}
