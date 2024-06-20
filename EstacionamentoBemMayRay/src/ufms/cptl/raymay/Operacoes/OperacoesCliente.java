package ufms.cptl.raymay.Operacoes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
import ufms.cptl.raymay.Classes.Enum.Operando;
import ufms.cptl.raymay.Classes.Externo.Automovel.Veiculo;
import ufms.cptl.raymay.Classes.Externo.Individuo.Cliente;
import ufms.cptl.raymay.Classes.Interno.Tickets.Ticket;
import static ufms.cptl.raymay.Operacoes.OperacaoMostraMensagem.operacaoMensagem;
/**
 *
 * @author ra
 */

public class OperacoesCliente {
    /* Essa Classe possui métodos que serão realizados com o cliente e seus veículos,
    de acordo com a opção escolhida no Menu */
   
    
    /* O método procura um Cliente na lista de Clientes e mostra na tela a suas informações */
    /* Retorna o Cliente se conseguir encontrar o cliente e null se o CPF do cliente não 
    estiver cadastrado */
    public Cliente buscar(List<Cliente> clientes, String documento){
        for(Cliente i : clientes) {
            if (i.getCpf().equals(documento)) {
                return i;
            }
        }
        return null;
    }
    
    
    /* O método percorre a lista de veículos do cliente inserido para verificar se possui algum ticket
    ATIVO ou DESATIVO cadastrado em relação a esse cliente (a exclusão não pode ser feita caso exista um
    ticket cadastrado), se existir retorna false, se não, realiza a exclusão do cliente, limpa lista de
    veículos e retorna true */
    public boolean excluir(List<Cliente> clientes, Cliente cliente, List<Ticket> tickets){
        for(Veiculo v : cliente.getVeiculos()){
            for(Ticket t : tickets){
                if(t.getVeiculoTicket().equals(v)) {
                    return false;
                }
            }          
        }
        cliente.getVeiculos().clear();
        clientes.remove(cliente);
        return true;       
    }
    
    
    /* Método responsável por editar dados dos clientes (não é recomendavel mudar o cpf, pelo menos
    em alguns sistemas) e não devemos gerenciar os veículos do cliente, pois tem outro método para isso */
    /* Retorna vazio pois nao é necessário nenhuma verificação */
    public void editar(Cliente editarC, String nomeNovo, String telefoneNovo) {                                                        
        editarC.setNome(nomeNovo);                                                         
        editarC.setTelefone(telefoneNovo);                                                                                           
    }
    
    
    /* Método que gerencia os veículos do cliente a partir do documento */
    /* Operacao de gerenciar os veiculos do Cliente, nele voce pode adicionar ou excluir veiculo */
    /* O método percorre a lista de clientes a procura da placa inserida */
    public Veiculo buscarVeiculo(List<Cliente> clientes, String placa){
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                if(v.getPlaca().equals(placa)) {                                               
                    return v;
                }
            }
        }
        return null;
    }
    
    
    /* O método recebe a lista de clientes, a placa do veículo com a intenção de ser excluído e 
    a lista de tickets. Verifica se o veículo existe em algum cliente percorrendo a lista de clientes, quando
    encontrar, percorre a lista de tickets e verifica se possui tickets ATIVO com a respectiva placa, caso
    exista, a exclusão não pode ser feita, se não, o veículo é excluído */
    
    /* A passagem pela lista de clientes é necessária pois o veículo cadastrado pode não ter um ticket
    cadastrado ainda!!! */
    public boolean apagarVeiculo(List<Cliente> clientes, String placa, List<Ticket> tickets){
        for(Cliente c : clientes) {
            for(Veiculo v : c.getVeiculos() ) {
                if(v.getPlaca().equals(placa)) { 
                    for (Ticket t : tickets) {
                        if(t.getVeiculoTicket().equals(v) && t.getStatus().equals(Operando.ATIVO)) {
                            operacaoMensagem("\nO veículo não pode ser excluído pois possui um ticket ATIVO (está estacionado)!\n");
                            return false;     
                        } 
                    }
                    c.getVeiculos().remove(v);
                    return true;
                }
            }
        }
        operacaoMensagem("\nVeículo não encontrado!\n");
        return false;
    }
    
    
    /* O método recebe a lista de clientes e um CPF (chave primaria de um cliente) e percorre a lista
    de clientes até encontrar o CPF, ao encontrar imprime retorna uma String com todos os veiculos vinculados ao
    documento inserido, esse tipo de retorno auxilia para a troca de interfaces (terminal e gráfica) */
    public String listarVeiculos(List<Cliente> clientes, String documento) {
        String veiculo = "Os veículos do cliente são:\n";
        for(Cliente c : clientes) {
            if(c.getCpf().equals(documento)) {
                if(c.getVeiculos() != null) {
                    for(Veiculo v : c.getVeiculos()) {
                        veiculo =  veiculo + "\n" + v.toString() + "\n";
                    }
                    return veiculo;
                }
            }
        }
        return "";
    }
    
    
    /*Método para imprimir todos os clientes cadastrados no sistema, retorna a lista de clientes auxiliando a troca 
    de interfaces (terminal e gráfica) */
    public List<String> listarCadastros(List<Cliente> clientes) {
        List<String> lista = new ArrayList<>();
        String cliente;
        for(Cliente c : clientes) {
            cliente = c.toString() + "\n";
            for(Veiculo v : c.getVeiculos()){
                cliente = cliente + "\n   Veículo " + v.toString();               
            }
            lista.add(cliente);
        }
        return lista;
    }
    
    
    /*Método com overload ou polimorfismo para retornar uma String com apenas o cliente que possui o documento
    passado por referencia, (overload do metodo acima).*/
    public String buscarDocumentoParaRelatorio(List<Cliente> clientes, String documento) {
        for(Cliente c : clientes) {
            if(c.getCpf().equals(documento)) {
                String cliente;
                cliente = c.toString() + "\n";
                for(Veiculo v : c.getVeiculos()){
                    cliente = cliente + "\n   Veículo " + v.toString();               
                }
                return cliente;
            }
        }
        return null;
    }
}    
         
