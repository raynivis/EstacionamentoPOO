package ufms.cptl.raymay.Operacoes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;
import ufms.cptl.raymay.Enum.Operando;
import ufms.cptl.raymay.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Interno.Ticket;
import static ufms.cptl.raymay.Operacoes.OperacaoMostraMensagem.operacaoMensagem;
/**
 *
 * @author ra
 */

public class OperacoesCliente {
/* Essa Classe possui métodos que serão realizados com o cliente, de acordo com a opção escolhida no Menu */
    OperacoesTicket opTic = new OperacoesTicket();
    
    /* O método procura um Cliente na lista de Clientes e mostra na tela a suas informaçoes */
    /* Retorna o Cliente se conseguir encontrar o cliente e NULO se o CPF do cliente não 
    estiver cadastrado */
    public Cliente verificarCliente(List<Cliente> clientes, String documento){
        for(Cliente i : clientes) {
            if (i.getCpf().equals(documento)) {
                return i;
            }
        }
        return null;
    }
    
    /* O método exclui o Cliente da lista de Clientes pelo CPF e retorna verdadeiro se o cliente for
    excluido e falso se o cliente não estiver com CPF cadastrado ou se possuir algum ticket de estacionamento
    ATIVO ou DESATIVO relacionado a esse cliente */
    public boolean excluirCliente(List<Cliente> clientes, String documento, List<Ticket> tickets){
        for(Cliente i : clientes) {
            if (i.getCpf().equals(documento)) {
                for(Veiculo v : i.getVeiculos()) {
                    if(opTic.verificaTicketVeiculo(clientes, v.getPlaca(), tickets) == null) {
                        operacaoMensagem("\nO cliente não pode ser excluído pois ele já cadastrou um ticket de estacionamento!\n");
                        return false;
                    }    
                }
                return true;
            }
        }                           
        operacaoMensagem("\nO cliente não pode ser excluído pois seu CPF não está cadastrado (inexistente)!\n");
        return false;
    }
    
    /*Editar dados dos clientes (não é recomendavel mudar o cpf, pelo menos em alguns sistemas) e 
    não devemos gerenciar os veiculos do cliente, pois tem outro metodo para isso*/
    /*retorna vazio pois nao eh necessario nenhuma verificacao*/
    public void editarCliente(Cliente editarC, String nomeNovo, String telefoneNovo) {                                                        
        editarC.setNome(nomeNovo);                                                         
        editarC.setTelefone(telefoneNovo);                                                                                           
    }
    
    /*Gerenciar veiculos do cliente a partir do documento*/
    /*Operacao de gerenciar os veiculos do Cliente, nele voce pode adicionar ou excluir veiculo*/
    public Veiculo verificarVeiculo(List<Cliente> clientes, String placa){
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                if(v.getPlaca().equals(placa)) {                                               
                    return v;
                }
            }
        }
        return null;
    }
    
    public boolean apagaVeiculo(List<Cliente> clientes, String placa){
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                if(v.getPlaca().equals(placa)) {                                               
                    c.getVeiculos().remove(v);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void relatorioCliente(List<Cliente> clientes) {
        for(Cliente c : clientes) {
            System.out.println(c.toString());
            operacaoMensagem("Veículos:");
            for(Veiculo v : c.getVeiculos()){
                operacaoMensagem("        Placa: " + v.getPlaca() + "       Tipo: " + v.getModel().getTipoVeiculo());
            }
            operacaoMensagem("///////////////////////////////////////////////////");
        }
    }
    public Cliente relatorioCliente(List<Cliente> clientes, String documento) {
        for(Cliente c : clientes) {
            if(c.getCpf().equals(documento)) {
                operacaoMensagem("///////////////////////////////////////////////////");
                operacaoMensagem(c.toString());
                operacaoMensagem("Veículos:");
                for(Veiculo v : c.getVeiculos()){
                    operacaoMensagem("        Placa: " + v.getPlaca() + "       Tipo:" + v.getModel().getTipoVeiculo() );
                }
                operacaoMensagem("///////////////////////////////////////////////////");
                return c;
            }
        }
        return null;
    }
}    
         
