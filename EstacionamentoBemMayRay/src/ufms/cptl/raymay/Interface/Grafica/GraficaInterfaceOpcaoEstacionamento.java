/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.Interface.Grafica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
import ufms.cptl.raymay.Classes.Enum.TipoVeiculo;
import ufms.cptl.raymay.Classes.Enum.VagaStatus;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tarifas.Tarifa;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaHorista;
import ufms.cptl.raymay.Classes.Interno.Tarifas.TarifaMensalista;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketHorista;
import ufms.cptl.raymay.Classes.Interno.Tickets.TicketMensalista;
import ufms.cptl.raymay.Classes.Interno.Vaga;
import ufms.cptl.raymay.InterfaceOp.AuxiliarInterfarceListaTipoSemanas;
import ufms.cptl.raymay.InterfaceOp.ItensMenu;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class GraficaInterfaceOpcaoEstacionamento {
    OperacoesVagas opVaga = new OperacoesVagas(); 
    OperacoesCliente opCliente = new OperacoesCliente();
    OperacoesTicket opTicket = new OperacoesTicket();
    
    AuxiliarInterfarceListaTipoSemanas listasVS = new AuxiliarInterfarceListaTipoSemanas();
    
    ItensMenu menue = new ItensMenu(); /* menue = Menu de gerencia do Estacionamento */
    ItensMenu menut = new ItensMenu(); /*menut = Menu de gerencia das Tarifas */
    
    int opcao2;
    int opcao3;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    
    /* Método geral das opções do estacionamento que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao estacionamento */
    public void opcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas) {
        do{
            /* Utiliza o método criado em OpcaoEstacionamento no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
            opcao2 = menue.imprimeEstacionamento(1);
            opTicket.verificarTicketsMensalista30dias(tickets);
  
            switch (opcao2) {
                case 1:
                    /*estacionar*/
                    
                    if(tarifas.isEmpty() == true) { /*verificando se tem tarifa*/
                        JOptionPane.showMessageDialog(null, "Cadastre uma tarifa primeiro!",
                    "Estacionamento do veículo", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    String placa = JOptionPane.showInputDialog(
                    null,
                    "Digite a placa do veículo a ser estacionado",
                    "Estacionamento do veículo",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(placa == null) {
                        break;
                    }
                  
                    Veiculo veiculo = opCliente.verificarVeiculo(clientes, placa);
                    
                    if(veiculo == null) {
                       JOptionPane.showMessageDialog(null, "Veículo não encontrado!",
                    "Estacionamento do veículo", JOptionPane.ERROR_MESSAGE); 
                       break;
                    }
                    
                    Ticket veriTicket = opTicket.verificaTicketVeiculo(clientes, placa, tickets);
                    if(veriTicket != null){                       
                        JOptionPane.showMessageDialog(null, "O veículo já está estacionado!",
                    "Estacionamento do veículo", JOptionPane.ERROR_MESSAGE);
                        break;
                    }                                      
                   
                    if(opTicket.verificarEstacionarTicketMensalistaParaVeiculo(tickets, veiculo)){ /*verificando o veiculo possui um ticket mensalista*/
                        JOptionPane.showMessageDialog(null, "Veículo mensalista estacionado com sucesso!",
                    "Estacionamento do veículo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    
                    String numeroRuaJO = JOptionPane.showInputDialog(
                    null,
                    "Digite o número da vaga que pretende ser estacionada",
                    "Estacionamento do veículo",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(numeroRuaJO == null) {
                        break;
                    }
                    int numeroRua = Integer.parseInt(numeroRuaJO);
                    
                    String ruaVaga = JOptionPane.showInputDialog(
                    null,
                    "Digite a rua da vaga que pretende ser estacionada",
                    "Estacionamento do veículo",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(ruaVaga == null) {
                        break;
                    }
                    
                    Vaga vaga = opVaga.consultarVaga(vagas, numeroRua, ruaVaga);
                    if(vaga == null) {                       
                        JOptionPane.showMessageDialog(null, "Vaga não encontrada!!",
                    "Estacionamento do veículo", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                     if(veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {                       
                        JOptionPane.showMessageDialog(null, "O tipo de veículo não é compatível com o tipo de vaga!",
                    "Estacionamento do veículo", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                    if(vaga.getStatus() != VagaStatus.DISPONIVEL) {
                        if(vaga.getStatus() == VagaStatus.OCUPADA) { 
                            JOptionPane.showMessageDialog(null, "A vaga possui um ticket de estacionamento ATIVO (OCUPADA)!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Vaga indisponível!",
                        "Estacionamento do veículo", JOptionPane.ERROR_MESSAGE);
                        break;
                    }                
                    
                    /*Achar a tarifa que pertence ao ticket*/   
                    String tipoTi = JOptionPane.showInputDialog(
                    null,
                    "O cliente deseja estacionar como Horista ou Mensalista?",
                    "Estacionamento do veículo",
                    JOptionPane.QUESTION_MESSAGE
                    );                   
                    if(tipoTi == null) {
                        break;
                    }
                    Tarifa atual = opTicket.tarifaProxima(tarifas, LocalDateTime.now(), veiculo, tipoTi);

                    if(atual == null){
                        JOptionPane.showMessageDialog(null, "Não existe uma tarifa para esse tipo de vaga nesse período!",
                        "Estacionamento do veículo", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                    if(tipoTi.equalsIgnoreCase("HORISTA")){
                        TicketHorista novoTicket = new TicketHorista(atual, veiculo, vaga);
                        tickets.add(novoTicket);
                        JOptionPane.showMessageDialog(null, "Ticket Horista de código " + novoTicket.getCodigo() + " criado com sucesso!",
                        "Estacionamento do veículo", JOptionPane.INFORMATION_MESSAGE);  
                    }
                    else {
                        TicketMensalista novoTicket = new TicketMensalista(atual, veiculo, vaga);
                        tickets.add(novoTicket);
                        JOptionPane.showMessageDialog(null, "Ticket Mensalista de código " + novoTicket.getCodigo() + " criado com sucesso!",
                        "Estacionamento do veículo", JOptionPane.INFORMATION_MESSAGE); 
                    }                                                     
                break;    
    
                case 2:
                    /*retirar*/
                    placa = JOptionPane.showInputDialog(
                    null,
                    "Digite a placa do veículo que deseja retirar",
                    "Retirar veículo do estacionamento",
                    JOptionPane.PLAIN_MESSAGE
                    );                   
                    if(placa == null) {
                        break;
                    }
                    
                    Veiculo veiculoRetirar = opCliente.verificarVeiculo(clientes, placa);
                    if(veiculoRetirar == null) {
                        JOptionPane.showMessageDialog(null, "Veículo não encontrado nos Clientes!",
                        "Retirar veículo do estacionamento", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                    if(opTicket.retirar(tickets, veiculoRetirar) == false) {
                        JOptionPane.showMessageDialog(null, "O veículo não pode ser retirado!",
                        "Retirar veículo do estacionamento", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Veículo retirado com sucesso!",
                        "Retirar veículo do estacionamento", JOptionPane.INFORMATION_MESSAGE);
                    }
                break;    
    
                case 3:
                    /*listar todas as vagas disponíveis do estacionamento*/
                    opVaga.listarVagasDisponiveis(vagas);
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                    do{ 
                        /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                        de linhas das Classes da interface */
                        menut.imprimeTarifa(1);
                        opTicket.verificarTicketsMensalista30dias(tickets);
                        
                        opcao3 = scanner.nextByte();
                        scanner.nextLine();
                        switch(opcao3){
                            case 1: /*adicionar tarifa*/
                                
                                String tipo = JOptionPane.showInputDialog(
                                null,
                                "Digite o Tipo de Tarifa que deseja cadastrar (Horista ou Mensalista)",
                                "Adicionar tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(tipo == null) {
                                    break;
                                }
                                
                                String data = JOptionPane.showInputDialog(
                                null,
                                "Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos)\n"
                                + "Se deseja cadastrar uma tarifa instantânea, digite: Agora",
                                "Adicionar tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(data == null) {
                                    break;
                                }
                                
                                LocalDateTime inicio;
                                
                                if(data.toUpperCase().equals("AGORA")) {
                                    inicio = LocalDateTime.now();
                                } else {
                                    inicio = LocalDateTime.parse(data, dataFormata);
                                    if(inicio.isBefore(LocalDateTime.now())) {
                                        JOptionPane.showMessageDialog(null, "Não é possível cadastrar uma tarifa no passado!",
                                        "Adicionar tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                }                                                              
                                List<DiaSemana> diaSmns = new  ArrayList<>();                        
                                List<TipoVeiculo> listaTps = new  ArrayList<>();
                                listasVS.OperacaoListaTVDS(diaSmns, listaTps);
                                 
                                if(tipo.equalsIgnoreCase("HORISTA")){  
                                    String precoPrimeiraJO = JOptionPane.showInputDialog(
                                    null,
                                    "Digite o valor da primeira hora",
                                    "Adicionar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(precoPrimeiraJO == null) {
                                        break;
                                    }
                                    
                                    double precoPrimeira = Double.parseDouble(precoPrimeiraJO);

                                    String precoHoraJO = JOptionPane.showInputDialog(
                                    null,
                                    "Digite o valor das horas subsequentes",
                                    "Adicionar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(precoHoraJO == null) {
                                        break;
                                    }
                                    
                                    double precoHora = Double.parseDouble(precoHoraJO);
                                    
                                    if(opTicket.buscarTarifaHorista(tarifas, inicio.format(dataFormata), diaSmns, listaTps) != null){
                                        JOptionPane.showMessageDialog(null, "Você ja cadastrou uma Tarifa desse tipo para essa data!",
                                        "Adicionar tarifa", JOptionPane.ERROR_MESSAGE);
                                    break;
                                    }
                                    TarifaHorista novaTarifa = new TarifaHorista(precoPrimeira, precoHora, inicio, diaSmns, listaTps);
                                    tarifas.add(novaTarifa);
                                    JOptionPane.showMessageDialog(null, "Tarifa Horista de " + inicio.format(dataFormata) + " cadastrada com sucesso!",
                                    "Adicionar tarifa", JOptionPane.INFORMATION_MESSAGE);    
                                }                               
                                else{                   
                                    String precoJO = JOptionPane.showInputDialog(
                                    null,
                                    "Digite o valor da tarifa",
                                    "Adicionar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(precoJO == null) {
                                        break;
                                    }
                                    
                                    double preco = Double.parseDouble(precoJO);
                                    
                                    if(opTicket.buscarTarifaMensalista(tarifas, inicio.format(dataFormata), diaSmns, listaTps) != null){
                                        JOptionPane.showMessageDialog(null, "Você ja cadastrou uma Tarifa desse tipo para essa data!",
                                        "Excluir tarifa", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    }
                                    TarifaMensalista novaTarifa = new TarifaMensalista(preco, inicio, diaSmns, listaTps);
                                    tarifas.add(novaTarifa);
                                    JOptionPane.showMessageDialog(null, "Tarifa Mensalista de " + inicio.format(dataFormata) + " cadastrada com sucesso!",
                                    "Excluir tarifa", JOptionPane.INFORMATION_MESSAGE);   
                                }                                                              
                            break; 
                            case 2: /*excluir tarifa*/     
                                tipo = JOptionPane.showInputDialog(
                                null,
                                "Digite o tipo de tarifa que deseja excluir(Horista ou Mensalista)",
                                "Excluir tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(tipo == null) {
                                    break;
                                }
                                
                                data = JOptionPane.showInputDialog(
                                null,
                                "Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos)",
                                "Excluir tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(data == null) {
                                    break;
                                }                             
                                List<DiaSemana> dias = new ArrayList<>();                        
                                List<TipoVeiculo> tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                
                                if(tipo.equalsIgnoreCase("HORISTA")){                            
                                     TarifaHorista tarifaEx = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                     if(tarifaEx == null){
                                         JOptionPane.showMessageDialog(null, "Tarifa não encontrada!",
                                        "Excluir tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                     }
                                     
                                     if(opTicket.procuraTarifaEmTicket(tarifaEx, tickets) == true) {
                                         JOptionPane.showMessageDialog(null, "A tarifa não pode ser excluída pois ela possui um ticket cadastrado!",
                                        "Excluir tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                     }
                                     tarifas.remove(tarifaEx);  
                                }                               
                                else{                            
                                    TarifaMensalista tarifaEx = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                     if(tarifaEx == null){
                                         JOptionPane.showMessageDialog(null, "Tarifa não encontrada!",
                                "Excluir tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                     }
                                     /*Ver se ta fufando*/
                                     if(opTicket.procuraTarifaEmTicket(tarifaEx, tickets) == true) {
                                         JOptionPane.showMessageDialog(null, "A tarifa não pode ser excluída pois ela possui um ticket cadastrado!",
                                "Excluir tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                     }
                                     tarifas.remove(tarifaEx);
                                }
                                                            

                                JOptionPane.showMessageDialog(null, "Tarifa excluída com sucesso!",
                                "Excluir tarifa", JOptionPane.INFORMATION_MESSAGE);
                            break;
                            case 3: /*editar tarifa*/
                                tipo = JOptionPane.showInputDialog(
                                null,
                                "Digite o Tipo de Tarifa que deseja editar (Horista ou Mensalista)",
                                "Editar tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(tipo == null) {
                                    break;
                                }
                                
                                data = JOptionPane.showInputDialog(
                                null,
                                "Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos)",
                                "Editar tarifa",
                                JOptionPane.PLAIN_MESSAGE
                                );                   
                                if(data == null) {
                                    break;
                                }
                                
                                dias = new ArrayList<>();                        
                                tps = new ArrayList<>();
                                listasVS.OperacaoListaTVDS(dias, tps);
                                
                                if(tipo.equalsIgnoreCase("HORISTA")){                            
                                    TarifaHorista tarifaEx = opTicket.buscarTarifaHorista(tarifas, data, dias, tps);
                                    if(tarifaEx == null){
                                        JOptionPane.showMessageDialog(null, "Tarifa não encontrada!",
                                        "Editar tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    String novaData = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a nova data (em dia/mês/ano horas:minutos)",
                                    "Editar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(novaData == null) {
                                        break;
                                    }
                                    tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                    
                                    String novaPHJO = JOptionPane.showInputDialog(
                                    null,
                                    "Digite o novo valor da primeira hora:",
                                    "Editar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(novaPHJO == null) {
                                        break;
                                    }
                                    double novaPH = Double.parseDouble(novaPHJO);
                                    tarifaEx.setValorPrimeiraHora(novaPH);
                                    
                                    String novaHSJO = JOptionPane.showInputDialog(
                                    null,
                                    "Digite o novo valor das horas subsequentes",
                                    "Editar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );
                                    double novaHS = Double.parseDouble(novaHSJO);
                                    tarifaEx.setValorHoraSubsequente(novaHS);  
                                }                               
                                else{                            
                                    TarifaMensalista tarifaEx = opTicket.buscarTarifaMensalista(tarifas, data, dias, tps);
                                    if(tarifaEx == null){
                                        JOptionPane.showMessageDialog(null, "Tarifa não encontrada!",
                                        "Editar tarifa", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    String novaData = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a nova data (em dia/mês/ano horas:minutos)",
                                    "Editar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(novaData == null) {
                                        break;
                                    }
                                    tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));
                                    
                                    String novaHJO = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a nova data (em dia/mês/ano horas:minutos)",
                                    "Editar tarifa",
                                    JOptionPane.PLAIN_MESSAGE
                                    );                   
                                    if(novaHJO == null) {
                                        break;
                                    }
                                    double novaH = Double.parseDouble(novaHJO);
                                    tarifaEx.setValorUnico(novaH);

                                }
                                                                
                                JOptionPane.showMessageDialog(null, "Tarifa editada com sucesso!",
                                "Editar tarifa", JOptionPane.INFORMATION_MESSAGE);
                            break;
                            case 4: /*imprimir tarifas*/
                                opTicket.relatorioTarifa(tarifas);
                            break;
                            case 5:
                            break;
                            default:
                                JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                                "Alerta", JOptionPane.WARNING_MESSAGE);
                                break;
                        }
                    }while(opcao3 != 5);
                break;
                case 5:
                break;
                default:
                    JOptionPane.showMessageDialog(null, "Se você deseja voltar/sair do menu, selecione a ultima opção",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
                break;
            }    
        }while(opcao2 != 5);
       
    }
}
