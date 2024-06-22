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
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;

/**
 *
 * @author nivis
 */
public class ListasProntas {
    
    /* Instancia Clientes e Vagas para auxiliar os testes das operações/métodos a serem realizados */
    /* Adiciona os objetos instanciados na lista de Clientes e Vagas */
    public static void gerarListas(List<Cliente> clientes, List<Vaga> vagas) {
       List<Veiculo> veiculos1 = new ArrayList<>();
       Cor color = new Cor("Roxo", "Velho");
       Modelo model = new Modelo("Celta", "A12", TipoVeiculo.MOTO);
      
       Veiculo novoV = new Veiculo("ABC", model, color);
       veiculos1.add(novoV);     
       Cliente novoCliente1 = new Cliente("Ray", "1", "18", veiculos1);
       
       
       List<Veiculo> veiculos2 = new ArrayList<>();     
       Cor color2 = new Cor("Vinho", "Velho");

       Modelo model2 = new Modelo("Yamaha", "A2", TipoVeiculo.MOTO);
       Veiculo novoV2 = new Veiculo("CDE", model2, color2);
       veiculos2.add(novoV2);
       Cor color3 = new Cor("Vinho", "Novo");
       Modelo model3 = new Modelo("Motorola", "3", TipoVeiculo.CARRO);

       Veiculo novoV3 = new Veiculo("FGH", model3, color3);
       veiculos2.add(novoV3);
       Cliente novoCliente2 = new Cliente("May", "2", "20", veiculos2);
       
          
       List<Veiculo> veiculos3 = new ArrayList<>();
       Cor color4 = new Cor("Azul Marinho", "Novo");
       Modelo model4 = new Modelo("Yamaha", "C166", TipoVeiculo.ONIBUS);

       Veiculo novoV4 = new Veiculo("IJK", model4, color4);
       veiculos3.add(novoV4);
       
       Cor color5 = new Cor("Azul Marinho", "Novo");
       Modelo model5 = new Modelo("Yamaha", "C166", TipoVeiculo.CARRO);

       Veiculo novoV5 = new Veiculo("LMN", model5, color5);
       veiculos3.add(novoV5);
       
       Cor color6 = new Cor("Azul Marinho", "Novo");
       Modelo model6 = new Modelo("Yamaha", "C166", TipoVeiculo.CARRO);

       Veiculo novoV6 = new Veiculo("OPQ", model6, color6);
       veiculos3.add(novoV6);
       
       Cliente novoCliente3 = new Cliente("Humberto", "3", "21", veiculos3);
       
       clientes.add(novoCliente1);
       clientes.add(novoCliente2);
       clientes.add(novoCliente3);
       
       Vaga novaVaga1 = new Vaga(1, "Jose", TipoVeiculo.MOTO);
       Vaga novaVaga2 = new Vaga(1, "Maria", TipoVeiculo.MOTO);
       Vaga novaVaga3 = new Vaga(2, "Jose", TipoVeiculo.CARRO);
       Vaga novaVaga4 = new Vaga(2, "Maria", TipoVeiculo.CARRO);
       Vaga novaVaga5 = new Vaga(3, "Jose", TipoVeiculo.ONIBUS);
       Vaga novaVaga6 = new Vaga(3, "Maria", TipoVeiculo.ONIBUS);
       Vaga novaVaga7 = new Vaga(3, "Nana", TipoVeiculo.CARRO);

       
       vagas.add(novaVaga1);
       vagas.add(novaVaga2);
       vagas.add(novaVaga3);
       vagas.add(novaVaga4);
       vagas.add(novaVaga5);
       vagas.add(novaVaga6);
       vagas.add(novaVaga7);
    }
}
