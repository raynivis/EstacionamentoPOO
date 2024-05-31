/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Teste;

import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Externo.Automovel.Cor;
import ufms.cptl.raymay.Classes.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.Enum.TipoVeiculo;
import ufms.cptl.raymay.Enum.VagaStatus;

/**
 *
 * @author nivis
 */
public class ListasProntas {
    
    /* Instancia Clientes e Vagas para auxiliar os testes das operações/métodos a serem realizados */
    /* Adiciona os objetos instanciados na lista de Clientes e Vagas */
    public void GerarListas(List<Cliente> clientes, List<Vaga> vagas) {
       List<Veiculo> veiculos1 = new ArrayList<>();
       Cor color = new Cor("Roxo", "Velho");
       Modelo model = new Modelo("Celta", "A12", TipoVeiculo.MOTOCICLETA);
       Veiculo novoV = new Veiculo("ABC", model, color);
       veiculos1.add(novoV);     
       Cliente novoCliente1 = new Cliente("Ray", "1", "18", veiculos1);
       
       List<Veiculo> veiculos2 = new ArrayList<>();     
       Cor color2 = new Cor("Vinho", "Velho");
       Modelo model2 = new Modelo("Yamaha", "A2", TipoVeiculo.MOTOCICLETA);
       Veiculo novoV2 = new Veiculo("CDE", model2, color2);
       veiculos2.add(novoV2);
       Cor color3 = new Cor("Vinho", "Novo");
       Modelo model3 = new Modelo("Motorola", "3", TipoVeiculo.MEDIOPORTE);
       Veiculo novoV3 = new Veiculo("FGH", model3, color3);
       veiculos2.add(novoV3);
       Cliente novoCliente2 = new Cliente("May", "2", "20", veiculos2);
       
       List<Veiculo> veiculos3 = new ArrayList<>();
       Cor color4 = new Cor("Azul Marinho", "Novo");
       Modelo model4 = new Modelo("Yamaha", "C166", TipoVeiculo.GRANDEPORTE);
       Veiculo novoV4 = new Veiculo("IJK", model4, color4);
       veiculos3.add(novoV4);
       Cliente novoCliente3 = new Cliente("Humberto", "3", "21", veiculos3);
       
       clientes.add(novoCliente1);
       clientes.add(novoCliente2);
       clientes.add(novoCliente3);
       
       Vaga novaVaga1 = new Vaga(1, "Jose", TipoVeiculo.MOTOCICLETA);
       Vaga novaVaga2 = new Vaga(2, "Jose", TipoVeiculo.MEDIOPORTE);
       Vaga novaVaga3 = new Vaga(1, "Maria", TipoVeiculo.GRANDEPORTE);
       Vaga novaVaga4 = new Vaga(3, "Nana", TipoVeiculo.MEDIOPORTE);
       
       vagas.add(novaVaga1);
       vagas.add(novaVaga2);
       vagas.add(novaVaga3);
       vagas.add(novaVaga4);
        
    }
}
