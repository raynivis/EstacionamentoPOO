/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufms.cptl.raymay.InterfaceOpcoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.DiaSemana;
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
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceGrafica;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.InterfaceTerminal;
import ufms.cptl.raymay.Interface.InterfaceDoUsuario.UserInterface;
import ufms.cptl.raymay.Operacoes.OperacoesCliente;
import ufms.cptl.raymay.Operacoes.OperacoesTarifa;
import ufms.cptl.raymay.Operacoes.OperacoesTicket;
import ufms.cptl.raymay.Operacoes.OperacoesVagas;

/**
 *
 * @author maymi
 */
public class InterfaceOpcaoEstacionamento{
    OperacoesVagas opVaga = new OperacoesVagas(); 
    OperacoesCliente opCliente = new OperacoesCliente();
    OperacoesTicket opTicket = new OperacoesTicket();
    OperacoesTarifa opTarifa = new OperacoesTarifa();
    
    AuxiliarInterfarceListaDiasSemanas listasVS = new AuxiliarInterfarceListaDiasSemanas();
    
    int opcao2;
    int opcao3;
    DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    
    /* Método geral das opções do estacionamento que será chamado na Classe InterfaceInicial e permite a realização das operações
    relacionadas ao estacionamento */
    public void realizarOpcoesEstacionamento(List<Cliente> clientes, List<Vaga> vagas, List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {
        do{
            /* Utiliza o método criado em OpcaoEstacionamento no package InterfaceEnumOpcao, reduzindo o tamanho
            de linhas das classes da interface */
<<<<<<< Updated upstream
            if(inter instanceof InterfaceTerminal){
                inter = (InterfaceTerminal) inter;
                opcao2 = inter.imprimeEstacionamento();
            }
            else {
                inter = (InterfaceGrafica) inter;
                opcao2 = inter.imprimeEstacionamento();
            }
=======
            opcao2 = inter.imprimirEstacionamento();
>>>>>>> Stashed changes
            switch (opcao2) {
                case 1:
                    /*estacionar*/
                    
                    if(tarifas.isEmpty() == true) { /*verificando se tem tarifa*/
                        inter.imprimirMensagem("Cadastre uma tarifa primeiro!!");                       
                        break;
                    }
                    String placa;
                    placa = inter.receberString("Digite a placa do veículo a ser estacionado:");
                                
                    Veiculo veiculo = opCliente.buscarVeiculo(clientes, placa);
                    
                    if(veiculo == null) {
                       inter.imprimirMensagem("Erro: Veículo não econtrado!");                         
                       break;
                    }
                    
                    Ticket veriTicket = opTicket.verificarUtilizacaoParaVeiculo(clientes, placa, tickets);
                    if(veriTicket != null){
                       inter.imprimirMensagem("Erro: O veículo já está estacionado!");                      
                       break;                     
                    }                                      
                    
                    if(opTicket.verificarMensalistaParaVeiculo(tickets, veiculo)){ /*verificando o veiculo possui um ticket mensalista*/
                        inter.imprimirMensagem("Veículo Mensalista estacionado com sucesso!");                        
                        break;
                    }
                    String numeroRua, ruaVaga;
                    Vaga vaga;
                    numeroRua = inter.receberString("Digite o número da vaga que pretende ser estacionada:");
                    int rua = Integer.parseInt(numeroRua);
                    ruaVaga = inter.receberString("Digite a rua da vaga que pretende ser estacionada:");
                    vaga = opVaga.consultar(vagas, rua, ruaVaga);

                    
                    if(vaga == null) { 
                       inter.imprimirMensagem("Erro: Vaga não econtrada!");                                  
                       break;
                    }
                    
                    if(veiculo.getModel().getTipoVeiculo() != vaga.getTipo()) {
                       inter.imprimirMensagem("Erro: O tipo de veículo não é compatível com o tipo de vaga!");                        
                       break;
                    }
                    
                    if(vaga.getStatus() != VagaStatus.DISPONIVEL) {
                        if(vaga.getStatus() == VagaStatus.OCUPADA) { 
                            inter.imprimirMensagem("Erro: A vaga possui um ticket de estacionamento ATIVO (OCUPADA)!");                                  
                            break;
                        }
                       inter.imprimirMensagem("Erro: Vaga indisponível!");                               
                       break;
                    }                
                    String tipoTi; 
                    tipoTi = inter.receberString("O cliente deseja estacionar como Horista ou Mensalista?"); 
                    /*Achar a tarifa que pertence ao ticket*/                  
                    Tarifa atual = opTarifa.buscarMaisProxima(tarifas, LocalDateTime.now(), tipoTi);

                    if(atual == null){
                        inter.imprimirMensagem("Erro: Não existe uma tarifa para esse tipo de vaga nesse período!");  
                        break;
                    }
                    
                    if(tipoTi.equalsIgnoreCase("HORISTA")){
                        TicketHorista novoTicket = new TicketHorista((TarifaHorista)atual, veiculo, vaga);
                        tickets.add(novoTicket);
                        inter.imprimirMensagem("Ticket Horista de código " + novoTicket.getCodigo() + " criado com sucesso!");                           
                    }
                    else {
                        TicketMensalista novoTicket = new TicketMensalista((TarifaMensalista)atual, veiculo, vaga);
                        tickets.add(novoTicket);
                        inter.imprimirMensagem("Ticket Mensalista de código " + novoTicket.getCodigo() + " criado com sucesso!");                  
                    }                                                     
                break;    
    
                case 2:
                    /*retirar*/
                    placa = inter.receberString("Digite a placa do veículo que deseja retirar:");
                    
                    Veiculo veiculoRetirar = opCliente.buscarVeiculo(clientes, placa);
                    if(veiculoRetirar == null) {
                        inter.imprimirMensagem("Veiculo não encontrado nos Clientes!");  
                        break;
                    }
                    
                    if(opTicket.retirar(tickets, veiculoRetirar) == false) {
                         inter.imprimirMensagem("O Veiculo não pode ser retirado!");       
                    } else {
                        inter.imprimirMensagem("Veiculo Retirado com sucesso!");                 
                    }
                break;    
                case 3:
                    /*listar todas as vagas disponíveis do estacionamento*/
                    List<String> lista = opVaga.listarDisponiveis(vagas);
                    for(String s : lista){
                        inter.imprimirMensagem(s);
                    }
                break;    
                case 4:
                    /*Gerenciar tarifas*/
                    do{ 
                        /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
                        de linhas das Classes da interface */                       
                        opTicket.verificarMensalista30dias(tickets);
                        opcao3 = inter.imprimeTarifa();
                        switch(opcao3){
                            case 1: /*adicionar tarifa*/                                                      
                                String tipo; 
                                tipo = inter.receberString("Digite o Tipo de Tarifa que deseja cadastrar (Horista ou Mensalista):");
                                
                                String data;
                                inter.mensagem("Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos) :");
                                data = inter.receberString("Se deseja cadastrar uma tarifa instantânea, digite: Agora"); 
                  
                                LocalDateTime inicio;
                                
                                if(data.toUpperCase().equals("AGORA")) {
                                    inicio = LocalDateTime.now();
                                } else {
                                    inicio = LocalDateTime.parse(data, dataFormata);
                                    if(inicio.isBefore(LocalDateTime.now())) {                                       
                                        inter.mensagem("Erro: Nao é possível cadastrar uma tarifa no passado!");  
                                        break;
                                    }
                                }                                                                                            
                                 
                                if(tipo.equalsIgnoreCase("HORISTA")){    
                                    List<DiaSemana> diaSmns = new  ArrayList<>();                                                     
                                    listasVS.OperacaoListaDiasSemanas(diaSmns, inter);
                                    String priHora;
                                    priHora = inter.receberString("Digite o valor da primeira hora:");  
                                    double precoPrimeira =  Double.parseDouble(priHora);
                                                                     
                                    String subHora;
                                    subHora = inter.receberString("Digite o valor das horas subsequentes:");
                                    double precoHora = Double.parseDouble(subHora);
                                    
                                    if(opTicket.buscarTarifaHorista(tarifas, inicio.format(dataFormata), diaSmns) != null){                                      
                                        inter.mensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");   
                                        break;
                                    }
                                    TarifaHorista novaTarifa = new TarifaHorista(precoPrimeira, precoHora, inicio, diaSmns);
                                    tarifas.add(novaTarifa);
                                    inter.mensagem("Tarifa Horista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");     
                                }                               
                                else{                                                              
                                    String Hora;
                                    Hora = inter.receberString("Digite o valor da Tarifa:"); 
                                    double preco = Double.parseDouble(Hora);
                                    
                                    if(opTicket.buscarTarifaMensalista(tarifas, inicio.format(dataFormata)) != null){
                                        inter.mensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");    
                                        break;
                                    }
                                    TarifaMensalista novaTarifa = new TarifaMensalista(preco, inicio);
                                    tarifas.add(novaTarifa);
                                    inter.mensagem("Tarifa Mensalista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");     
                                }                                                              
                            break; 
                            case 2: /*excluir tarifa*/                                                            
                                tipo = inter.receberString("Digite o Tipo de Tarifa que deseja excluir (Horista ou Mensalista):");
                                
                                data = inter.receberString("Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos) :");                                      
                                                                                       
                                if(tipo.equalsIgnoreCase("HORISTA")){ 
                                    List<DiaSemana> dias = new ArrayList<>();                           
                                    listasVS.OperacaoListaDiasSemanas(dias, inter);
                                    TarifaHorista tarifaEx = opTicket.buscarTarifaHorista(tarifas, data, dias);
                                    if(tarifaEx == null){                                        
                                        inter.mensagem("Erro: Tarifa não encontrada!!");        
                                        break;
                                    }
                                     
                                    if(opTicket.procurarTarifa(tarifaEx, tickets) == true) {                                     
                                        inter.mensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");        
                                        break;
                                    }
                                    tarifas.remove(tarifaEx);  
                                }                               
                                else{                            
                                    TarifaMensalista tarifaEx = opTicket.buscarTarifaMensalista(tarifas, data);
                                     if(tarifaEx == null){
                                        inter.mensagem("Erro: Tarifa não encontrada!!");                 
                                        break;
                                     }
                                     /*Ver se ta fufando*/
                                     if(opTicket.procurarTarifa(tarifaEx, tickets) == true) {
                                        inter.mensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");      
                                        break;
                                     }
                                     tarifas.remove(tarifaEx);
                                }
                   
                                inter.mensagem("Tarifa removida com Sucesso!");    
                                        
                            break;
                            case 3: /*editar tarifa*/
                                tipo = inter.receberString("Digite o Tipo de Tarifa que deseja editar (Horista ou Mensalista):");                   
                                data = inter.receberString("Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos) :");                                    
                                                                                                                                                
                                if(tipo.equalsIgnoreCase("HORISTA")){   
                                    List<DiaSemana> dias = new ArrayList<>();                           
                                    listasVS.OperacaoListaDiasSemanas(dias, inter);
                                    TarifaHorista tarifaEx = opTicket.buscarTarifaHorista(tarifas, data, dias);
                                    if(tarifaEx == null){
                                        inter.mensagem("Erro: Tarifa não encontrada!!");   
                                        break;
                                    }
                                    String novaData = inter.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                                    tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));

                                    String ph = inter.receberString("Digite o novo valor da primeira hora:");
                                    double novaPH = Double.parseDouble(ph);                                       
                                    tarifaEx.setValorPrimeiraHora(novaPH);

                                    String sh = inter.receberString("Digite o novo valor da horas subsequentes");
                                    double novaHS = Double.parseDouble(sh);
                                    tarifaEx.setValorHoraSubsequente(novaHS); 
                                }                               
                                else{                            
                                    TarifaMensalista tarifaEx = opTicket.buscarTarifaMensalista(tarifas, data);
                                    if(tarifaEx == null){
                                        inter.mensagem("Erro: Tarifa não encontrada!!");    
                                        break;
                                    }
                                    
                                    String novaData = inter.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                                    tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));

                                    String vh = inter.receberString("Digite o novo valor da tarifa");
                                    double novaH = Double.parseDouble(vh);                                       
                                    tarifaEx.setValorUnico(novaH);                                  
                                        
                                }                                                                                                                           
                                inter.mensagem("Tarifa editada com Sucesso!");  
                            break;
                            case 4: /*imprimir tarifas*/                                
                                lista = opTicket.listarTarifasCadastradas(tarifas);
                                for(String s : lista){
                                    inter.mensagem(s);
                                }
                            break;
                            case 5:
                            break;
                            default:
                                inter.mensagem("Insira uma opção válida!"); 
                            break;
                        }
                    }while(opcao3 != 5);
                break;
                case 5:
                break;
                default:
                   inter.imprimirMensagem("Insira uma opção válida!");     
                break;
            }    
<<<<<<< Updated upstream
        }while(opcao2 != 5);
       
=======
        }while(opcao2 != 5);     
    }
    
    private void opcoesTarifa(List<Ticket> tickets, List<Tarifa> tarifas, UserInterface inter) {
        do{ 
            /* Utiliza o método criado em ItensMenu, reduzindo o tamanho
            de linhas das Classes da interface */                       
            opTicket.verificarMensalista30dias(tickets);
            opcao3 = inter.imprimirTarifa();
            switch(opcao3){
                case 1: /*adicionar tarifa*/                                                      
                    String tipo; 
                    tipo = inter.receberString("Digite o Tipo de Tarifa que deseja cadastrar (Horista ou Mensalista):");

                    String data;
                    inter.imprimirMensagem("Digite a data que deseja iniciar tarifa (em dia/mês/ano horas:minutos) :");
                    data = inter.receberString("Se deseja cadastrar uma tarifa instantânea, digite: Agora"); 

                    LocalDateTime inicio;

                    if(data.toUpperCase().equals("AGORA")) {
                        inicio = LocalDateTime.now();
                    } else {
                        inicio = LocalDateTime.parse(data, dataFormata);
                        if(inicio.isBefore(LocalDateTime.now())) {                                       
                            inter.imprimirMensagem("Erro: Nao é possível cadastrar uma tarifa no passado!");  
                            break;
                        }
                    }                                                                                            

                    if(tipo.equalsIgnoreCase("HORISTA")){    
                        List<DiaSemana> diaSmns = new  ArrayList<>();                                                     
                        listasVS.gerenciarDias(diaSmns, inter);
                        String priHora;
                        priHora = inter.receberString("Digite o valor da primeira hora:");  
                        double precoPrimeira =  Double.parseDouble(priHora);

                        String subHora;
                        subHora = inter.receberString("Digite o valor das horas subsequentes:");
                        double precoHora = Double.parseDouble(subHora);

                        if(opTarifa.buscarHorista(tarifas, inicio.format(dataFormata), diaSmns) != null){                                      
                            inter.imprimirMensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");   
                            break;
                        }
                        TarifaHorista novaTarifa = new TarifaHorista(precoPrimeira, precoHora, inicio, diaSmns);
                        tarifas.add(novaTarifa);
                        inter.imprimirMensagem("Tarifa Horista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");     
                    }                               
                    else{                                                              
                        String Hora;
                        Hora = inter.receberString("Digite o valor da Tarifa:"); 
                        double preco = Double.parseDouble(Hora);

                        if(opTarifa.buscarMensalista(tarifas, inicio.format(dataFormata)) != null){
                            inter.imprimirMensagem("Erro: Você ja cadastrou uma Tarifa desse tipo para essa data!");    
                            break;
                        }
                        TarifaMensalista novaTarifa = new TarifaMensalista(preco, inicio);
                        tarifas.add(novaTarifa);
                        inter.imprimirMensagem("Tarifa Mensalista de " + inicio.format(dataFormata) + " cadastrada com sucesso!\n");     
                    }                                                              
                break; 
                case 2: /*excluir tarifa*/                                                            
                    tipo = inter.receberString("Digite o Tipo de Tarifa que deseja excluir (Horista ou Mensalista):");

                    data = inter.receberString("Digite a data da tarifa que deseja excluir tarifa (em dia/mês/ano horas:minutos) :");                                      

                    if(tipo.equalsIgnoreCase("HORISTA")){ 
                        List<DiaSemana> dias = new ArrayList<>();                           
                        listasVS.gerenciarDias(dias, inter);
                        TarifaHorista tarifaEx = opTarifa.buscarHorista(tarifas, data, dias);
                        if(tarifaEx == null){                                        
                            inter.imprimirMensagem("Erro: Tarifa não encontrada!!");        
                            break;
                        }

                        if(opTarifa.procurar(tarifaEx, tickets) == true) {                                     
                            inter.imprimirMensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");        
                            break;
                        }
                        tarifas.remove(tarifaEx);  
                    }                               
                    else{                            
                        TarifaMensalista tarifaEx = opTarifa.buscarMensalista(tarifas, data);
                         if(tarifaEx == null){
                            inter.imprimirMensagem("Erro: Tarifa não encontrada!!");                 
                            break;
                         }
                         /*Ver se ta fufando*/
                         if(opTarifa.procurar(tarifaEx, tickets) == true) {
                            inter.imprimirMensagem("A tarifa não pode ser excluída pois ela possui um ticket cadastrado!");      
                            break;
                         }
                         tarifas.remove(tarifaEx);
                    }

                    inter.imprimirMensagem("Tarifa removida com Sucesso!");    

                break;
                case 3: /*editar tarifa*/
                    tipo = inter.receberString("Digite o Tipo de Tarifa que deseja editar (Horista ou Mensalista):");                   
                    data = inter.receberString("Digite a data da tarifa que deseja editar tarifa (em dia/mês/ano horas:minutos) :");                                    

                    if(tipo.equalsIgnoreCase("HORISTA")){   
                        List<DiaSemana> dias = new ArrayList<>();                           
                        listasVS.gerenciarDias(dias, inter);
                        TarifaHorista tarifaEx = opTarifa.buscarHorista(tarifas, data, dias);
                        if(tarifaEx == null){
                            inter.imprimirMensagem("Erro: Tarifa não encontrada!!");   
                            break;
                        }
                        String novaData = inter.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                        tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));

                        String ph = inter.receberString("Digite o novo valor da primeira hora:");
                        double novaPH = Double.parseDouble(ph);                                       
                        tarifaEx.setValorPrimeiraHora(novaPH);

                        String sh = inter.receberString("Digite o novo valor da horas subsequentes");
                        double novaHS = Double.parseDouble(sh);
                        tarifaEx.setValorHoraSubsequente(novaHS); 
                    }                               
                    else{                            
                        TarifaMensalista tarifaEx = opTarifa.buscarMensalista(tarifas, data);
                        if(tarifaEx == null){
                            inter.imprimirMensagem("Erro: Tarifa não encontrada!!");    
                            break;
                        }

                        String novaData = inter.receberString("Digite a nova data (em dia/mês/ano horas:minutos):");
                        tarifaEx.setInicio(LocalDateTime.parse(novaData, dataFormata));

                        String vh = inter.receberString("Digite o novo valor da tarifa");
                        double novaH = Double.parseDouble(vh);                                       
                        tarifaEx.setValorUnico(novaH);                                  

                    }                                                                                                                           
                    inter.imprimirMensagem("Tarifa editada com Sucesso!");  
                break;
                case 4: /*imprimir tarifas*/                                
                    List<String> lista = opTarifa.listarCadastradas(tarifas);
                    for(String s : lista){
                        inter.imprimirMensagem(s);
                    }
                break;
                case 5:
                break;
                default:
                    inter.imprimirMensagem("Insira uma opção válida!"); 
                break;
            }
        }while(opcao3 != 5);
>>>>>>> Stashed changes
    }
}
