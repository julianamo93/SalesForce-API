package org.example.services;

import org.example.entities.ContaCliente;
import org.example.entities.dtos.SearchContaClienteDto;
import org.example.repositories.ContaClienteRepository;

import java.util.List;

public class ContaClienteService {

    private final ContaClienteRepository contaClienteRepository;

    public ContaClienteService(){
        contaClienteRepository = new ContaClienteRepository();
    }

    public ContaCliente addCliente(ContaCliente cliente) {
        return contaClienteRepository.save(cliente);
    }

    public SearchContaClienteDto getAll(String nome, String sobrenome, String cargo, String nomeEmpresa,
                                        String telefone, String email, String cep, String logradouro,
                                        String numero, String complemento, String bairro, String cidade,
                                        String estado, String orderBy, String direction, int limit, int offset) {

        List<ContaCliente> clientes = contaClienteRepository.getAll(nome, sobrenome, cargo, nomeEmpresa, telefone, email, cep, logradouro,
                numero, complemento, bairro, cidade, estado, limit, offset);

        int totalItems = contaClienteRepository.count(nome, sobrenome, cargo, nomeEmpresa, telefone, email);

        return new SearchContaClienteDto(nome, sobrenome, cargo, nomeEmpresa, telefone, email, cep, logradouro, numero,
                complemento, bairro, cidade, estado, orderBy, direction, limit, offset, totalItems, clientes);
    }

    public void create(ContaCliente contaCliente){
        var validation = contaCliente.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            contaClienteRepository.create(contaCliente);
    }

    public void update(int id, ContaCliente contaCliente){
        var validation = contaCliente.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            contaClienteRepository.update(id, contaCliente);
    }

    public void delete(int id) {
        contaClienteRepository.delete(id);
    }
}
