/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ufms.cptl.raymay.Externo.Automovel.Cor;
import ufms.cptl.raymay.Externo.Automovel.Modelo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interno.Tarifa;
import ufms.cptl.raymay.Interno.Ticket;
import ufms.cptl.raymay.Interno.Vaga;
import ufms.cptl.raymay.Interno.Vaga.VagaStatus;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;

/**
 *
 * @author maymi
 */
public class Interface {
    
    OperacoesVagas vag = new OperacoesVagas(); 
    OperacoesCliente clie = new OperacoesCliente();
    OperacoesTicket tic = new OperacoesTicket();
    byte opcao;
    byte opcao2;
    byte opcao3;
    Scanner op = new Scanner(System.in);
    
    public Veiculo receberVeiculo(List<Cliente> clientes, Cliente cliente) {
        /*mensagem de interface para digitar um novo veiculo*/
        System.out.println("Digite a Placa: ");
        String placa = op.nextLine(); 
        
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                    if(v.getPlaca().equals(placa)) {
                            /*Placa ja existente*/                           
                            return null;
                    }
            }
        }
        
        System.out.println("Digite a Cor: ");       
        String cor = op.nextLine();
        System.out.println("Digite a Descricao: "); 
        String descricao = op.nextLine();
        Cor color = new Cor(cor, descricao);
        
        System.out.println("Digite a Marca: "); 
        String marca = op.nextLine();
        System.out.println("Digite o Modelo: "); 
        String modelo = op.nextLine();        
        System.out.println("Tipo do Veiculo(MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
        String tipoV = op.nextLine();
        Modelo.Tipo type = Modelo.Tipo.valueOf(tipoV.toUpperCase());
        Modelo model = new Modelo(marca, modelo, type);

                                          
        Veiculo novoV = new Veiculo(placa, model, color);
                                
        return novoV;
    }

    public void primeirasOpcoes(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {  
        do {
            System.out.println("1 - Gerenciar cliente");
            System.out.println("2 - Gerenciar vagas");
            System.out.println("3 - Gerenciar estacionamento");
            System.out.println("4 - Cadastros gerais");
            System.out.println("5 - Consultar total faturado em um periodo");
            System.out.println("6 - Sair do programa");
            opcao = op.nextByte();
            op.nextLine();
            
            switch (opcao) {
                case 1:
                    opcoesCliente(clientes, vagas, tickets, tarifas);
                break;    
                case 2:
                    opcoesVaga(clientes, vagas, tickets, tarifas);
                break;
                case 3:
                    opcoesEstacionamento(clientes, vagas, tickets, tarifas);
                break;
                case 4:  
                break;    
      
                case 5:
                break;
                                      
            }
        }while (opcao != 6);
        op.close();
    }
    
    public void opcoesCliente(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {       
        do{
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por documento");
            System.out.println("3 - Excluir");
            System.out.println("4 - Editar");
            System.out.println("5 - Gerenciar veiculos");
            System.out.println("6 - Listar todos os cadastros");
            System.out.println("7 - Voltar");
            opcao2 = op.nextByte();
            op.nextLine();  
                switch (opcao2) {
                    case 1:
                       /*cadastrar cliente*/
                        System.out.println("Digite o nome:");
                        String nome = op.nextLine();
                        System.out.println("Digite o CPF:");
                        String cpf = op.nextLine();
                        
                        if(clie.verificarCliente(clientes, cpf) != null) {
                            System.out.println("\nErro ao cadastrar: CPF ja existente no sistema\n");
                            break;
                        }
                        System.out.println("Digite o telefone:");
                        String telefone = op.nextLine();
                        List<Veiculo> veiculos = new ArrayList<>();
                        Cliente novoCliente = new Cliente(nome, cpf, telefone, veiculos);
                         
                        Veiculo Novoveiculo = receberVeiculo(clientes, novoCliente);                      
                        
                         if(Novoveiculo != null) {
                             novoCliente.setVeiculoNaLista(Novoveiculo);
                             clientes.add(novoCliente);
                             System.out.println("\nCliente cadastrado com sucesso!!\n");
                         }
                         else {
                             /*limpar a variavel novo cliente*/
                             System.out.println("\nErro ao cadastrar: Placa ja existente no sistema\n");
                         }                   
                    break;    
                    case 2:
                        /*consultar cliente por documento*/
                        System.out.println("Digite o CPF:");
                        cpf = op.nextLine();
                        if(clie.relatorioCliente(clientes, cpf) == null){
                            System.out.println("\nCliente nao encontrado!!\n");
                        }                        
                    break;   
                    case 3:
                        /*excluir cliente*/
                        System.out.println("Digite o CPF:");
                        cpf = op.nextLine();
                        if(clie.excluirCliente(clientes, cpf) == true) {
                            System.out.println("\nCliente excluido com sucesso!!\n");
                        } else {
                            System.out.println("\nCliente não encotrado!\n");
                        }
                    break;    
                    case 4:
                        /*editar cliente*/
                        System.out.println("Digite o CPF:");
                        cpf = op.nextLine();
                        if(clie.verificarCliente(clientes, cpf) == null) {
                           System.out.println("\nErro: Cliente nao econtrado!!\n");
                            break; 
                        }                                                  
                        System.out.println("Digite o novo Nome:");
                        String novoNome = op.nextLine();
                        System.out.println("Digite o novo Telefone:");
                        String novoTelefone = op.nextLine();
                        clie.editarCliente(clie.verificarCliente(clientes, cpf), novoNome, novoTelefone);
                    break;    
                    case 5:
                        /*gerenciar veiculos do cliente*/
                        System.out.println("Digite o CPF do Cliente que deseja gerenciar os veiculos:");
                        cpf = op.nextLine();
                        Cliente operador = clie.verificarCliente(clientes, cpf);
                        if(operador == null) {
                           System.out.println("\nErro: Cliente nao econtrado!!\n");
                            break; 
                        } 
                        do{
                            System.out.println("1 - Cadastrar novo veiculo");
                            System.out.println("2 - Excluir Veiculo");
                            System.out.println("3 - Editar Veiculo");
                            System.out.println("4 - Voltar");
                            opcao3 = op.nextByte();
                            op.nextLine();
                            switch(opcao3){
                                case 1: /*Adicionar um veiculo*/
                                    Veiculo veiculoAdicional = receberVeiculo(clientes, operador); 
                                    if(veiculoAdicional != null) {
                                        operador.setVeiculoNaLista(veiculoAdicional);
                                        System.out.println("\nVeiculo Cadastrado com sucesso!!\n");
                                    }
                                    else {
                                        System.out.println("\nErro: Placa ja registrada no sistema!\n");
                                    }
                                break;
                                case 2: /*Remover um veiculo*/
                                    System.out.println("Digite a Placa:");
                                    String placa = op.nextLine();
                                    if(clie.apagaVeiculo(clientes, placa) == true) {
                                        System.out.println("\nVeiculo excluido!!\n");
                                    } else {
                                        System.out.println("\nVeiculo nao encontrado!\n");
                                    }
                                break;
                                case 3: /*Editar um veiculo*/
                                    System.out.println("Digite a Placa:");
                                    placa = op.nextLine();
                                    if(clie.verificarVeiculo(clientes, placa) != null) {                                   
                                        Veiculo auxVeiculo = clie.verificarVeiculo(clientes, placa);

                                        System.out.println("Digita a nova Cor e Descricao:");
                                        String cor = op.nextLine();
                                        String descricao = op.nextLine();
                                        Cor ediColor = new Cor(cor, descricao);

                                        auxVeiculo.setColor(ediColor);

                                        System.out.println("\nVeiculo editado com sucesso!!\n");
                                    } else {
                                        System.out.println("\nVeiculo nao encontrado\n");
                                    }
                                break;
                            }
                        }while(opcao3 != 4);
                    break;    
                    case 6: 
                        /*listar todos os cadastros do(s)? cliente*/
                        clie.relatorioCliente(clientes);
                    break;    
                }
        }while(opcao2 != 7);
        
    }
    
    public  void opcoesVaga(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {      
        do{
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por numero");
            System.out.println("3 - Excluir");
            System.out.println("4 - Editar");
            System.out.println("5 - Alterar disponibilidade");
            System.out.println("6 - Voltar"); 
            opcao2 = op.nextByte();
            op.nextLine();  
            switch (opcao2) {
                case 1:
                    /*cadastrar vaga*/
                    System.out.println("Digite a Rua, Numero e Tipo de Vaga(MOTOCICLETA, MEDIOPORTE, GRANDEPORTE):");
                    String rua = op.nextLine();                   
                    
                    int numero = op.nextInt();
                    op.nextLine();
                    
                    
                    Vaga.VagaStatus vagastatus = Vaga.VagaStatus.DISPONIVEL;
                    
                    String tipo = op.nextLine();
                    Modelo.Tipo tipoV = Modelo.Tipo.valueOf(tipo.toUpperCase());
                                        
                    Vaga novaVaga = new Vaga(numero, rua, vagastatus, tipoV);
                    
                    if (vag.cadastrarVaga(vagas, novaVaga, rua, numero) == true){
                        System.out.println("\nVaga cadastrada com Sucesso!\n");
                    }
                    else{
                        System.out.println("\nVaga ja existente!\n");
                    }   
                break;  
                
                case 2:
                    /*consultar vaga por numero*/
                    System.out.println("Insira o numero da vaga e rua a ser consultada:");
                    numero = op.nextInt();
                    op.nextLine();
                    rua = op.nextLine();
                    Vaga vaga = vag.consultarVaga(vagas, numero, rua); 
                    if(vaga == null){
                        System.out.println("\nVaga nao existente!\n");
                        break;
                    }
                    System.out.println(vaga.toString()); 
               break;    
    
                case 3:
                    /*excluir vaga*/
                    System.out.println("Insira a rua e o numero da vaga a ser excluida:");
                    rua = op.nextLine();
                    numero = op.nextInt();
                    op.nextLine();
                    if(vag.excluirVaga(vagas, rua, numero) == true) {
                        System.out.println("Vaga rua:" + rua + " numero:" + numero + " excluida com sucesso!");
                    }
                break;  
                
                case 4:
                    /*editar vaga*/
                    System.out.println("Insira a rua e o numero da vaga a ser editada:");
                    rua = op.nextLine();
                    numero = op.nextInt();
                    op.nextLine();
                    
                    System.out.println("Agora insira a nova rua, o novo numero e o novo tipo da vaga (MOTOCICLETA, MEDIOPORTE, GRANDEPORTE): ");
                    String ruaNova = op.nextLine();
                    int numeroNovo = op.nextInt();
                    op.nextLine();
                    
                    tipo = op.nextLine();
                    Modelo.Tipo tipoN = Modelo.Tipo.valueOf(tipo.toUpperCase());
                    if(vag.editarVaga(vagas, rua, numero, ruaNova, numeroNovo, tipoN) == true) {
                        System.out.println("\nVaga editada com sucesso!\n");
                    }
                    else {
                        System.out.println("\nVaga nao existente!\n");
                    }
                break;  
                
                case 5:
                    /*alterar disponibilidade da vaga*/
                    System.out.println("Insira a rua, numero e o novo status da vaga (DISPONIVEL, OCUPADA ou INDISPONIVEL)");
                    rua = op.nextLine();
                    numero = op.nextInt();
                    op.nextLine();
                    
                    String status = op.nextLine();
                    Vaga.VagaStatus statusV = Vaga.VagaStatus.valueOf(status.toUpperCase());
                    
                    if(vag.alterarDispinibilidade(vagas, rua, numero, statusV) == true){
                        System.out.println("\nDisponibilidade da vaga alterada com sucesso!\n");
                    }
                break;    
            }    
        }while(opcao2 != 6);
               
    }
    
    public void opcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {
        do{
            System.out.println("1 - Estacionar");
            System.out.println("2 - Retirar");
            System.out.println("3 - Listar todas as vagas disponiveis");
            System.out.println("4 - Gerenciar tarifas");
            System.out.println("5 - Voltar");
            opcao2 = op.nextByte();
            op.nextLine();  
            switch (opcao2) {
                case 1:
                    /*estacionar*/                    
                    if(tarifas.isEmpty() == true) {
                        System.out.println("\nCadastre uma tarifa primeiro!!\n");
                        break;
                    }
                    
                    System.out.println("Digite o numero e a rua da vaga que pretende ser estacionada:");
                    int numeroRua = op.nextInt();
                    op.nextLine(); 
                    String ruaVaga = op.nextLine();
                    
                    Vaga vaga = vag.consultarVaga(vagas, numeroRua, ruaVaga);
                    if(vaga == null || vaga.getStatus() != Vaga.VagaStatus.DISPONIVEL) {
                        System.out.println("\nErro: Vaga nao econtrada ou indisponivel!!\n");
                        break;
                    }
                    vaga.setStatus(VagaStatus.OCUPADA);
                    
                    System.out.println("Digite a placa do veiculo:");
                    String placa = op.nextLine();
                    Veiculo veiculo = clie.verificarVeiculo(clientes, placa);
                    if(veiculo == null || 
                    veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {
                        /*Ja comentando que bagunça eh essa ai em cima no if, eh literalmente vendo se o veiculo
                        e vaga sao do mesmo tipo para poder estacionar*/
                        System.out.println("\nErro: Veiculo nao econtrado ou o tipo de Veiculo nao eh Compativel a o tipo de Vaga!!\n");
                        break;
                    }
                    /*Ultima tarifa cadastrada*/
                    Tarifa atual = tarifas.get(tarifas.size()-1);
                    
                    Ticket novoTicket = new Ticket(numeroRua, ruaVaga, placa, atual);
                    
                    novoTicket.setStatus(Ticket.Operando.ATIVO);
                    novoTicket.setInicio(LocalDateTime.now());
                    
                    tickets.add(novoTicket);
                    
                    System.out.println("\nTicket criado com sucesso!!\n");                   
                break;    
                case 2:
                    /*retirar*/
                    System.out.println("Digite o numero e a rua que deseja retirar o veiculo:");
                    int numero = op.nextInt();
                    op.nextLine();
                    String rua = op.nextLine();
                     
                    if(tic.retirar(tickets, vagas, numero, rua) == false) {
                        System.out.println("\nVaga nao encontrada!!\n");
                    } else {
                        System.out.println("\nTicket liberado e Vaga Liberada!!\n");
                    }
                break;    
                case 3:
                    /*Listar todas as vagas disponíveis do estacionamento*/
                    vag.listarVagasDisponiveis(vagas);
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                    do{ 
                        System.out.println("1 - Cadastrar Tarifa");
                        System.out.println("2 - Excluir Tarifa");
                        System.out.println("3 - Editar tarifa");
                        System.out.println("4 - Voltar");
                        opcao3 = op.nextByte();
                        op.nextLine();
                        switch(opcao3){
                            case 1: /*adicionar tarifa*/
                                LocalDateTime agora = LocalDateTime.now();

                                List<Double> precosV = new ArrayList<>();
                                System.out.println("Digite o juros de Tipo de Veiculo");
                                System.out.println("MOTOCICLETA:");
                                double preco = op.nextDouble();
                                op.nextLine();
                                precosV.add(preco);
                                System.out.println("MEDIOPORTE:");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosV.add(preco);
                                System.out.println("GRANDEPORTE:");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosV.add(preco);
                                List<Double> precosPD = new ArrayList<>();
                                List<Double> precosD = new ArrayList<>();
                                System.out.println("Digite o preco de primeira hora, e logo em "
                                + "seguida das horas subsquentes:");

                                System.out.println("Domingo: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                System.out.println("Segunda-Feira: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                 System.out.println("Terca-Feira: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                 System.out.println("Quarta-Feira: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                 System.out.println("Quinta-Feira: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                System.out.println("Sexta-Feira: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                System.out.println("Sabado: ");
                                preco = op.nextDouble();
                                op.nextLine();
                                precosPD.add(preco);

                                preco = op.nextDouble();
                                op.nextLine();
                                precosD.add(preco);

                                Tarifa novaTarifa = new Tarifa(agora, precosV, precosPD, precosD);

                                tarifas.add(novaTarifa);

                                DateTimeFormatter dataBonitinha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                                String dataFormatada = agora.format(dataBonitinha);

                                System.out.println("\nTarifa de "+ dataFormatada + " cadastrada com sucesso!!\n");                                                     
                            break; 
                            case 2: /*excluir tarifa*/

                            break;
                            case 3: /*editar tarifa*/

                            break;
                        }
                    }while(opcao3 != 4);
                break;    
            }    
        }while(opcao2 != 5);
       
    }
}
