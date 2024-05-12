package org.example.services;

import org.example.entities.ContaCliente;
import org.example.entities.dtos.SearchContaClienteDto;
import org.example.repositories.ContaClienteRepository;

public class ContaClienteService {

    private ContaClienteRepository contaClienteRepository;

    public ContaClienteService(){
        contaClienteRepository = new ContaClienteRepository();
    }

    public SearchContaClienteDto getAll(String nome, String sobrenome, String cargo, String nomeEmpresa,
                                        String telefone, String email, String cep, String rua,
                                        String bairro, String cidade, String estado, String orderBy,
                                        String direction, String by, int limit, int offset) {
        var clientes = contaClienteRepository.getAll(nome, sobrenome, cargo, nomeEmpresa, telefone, email, cep, rua,
                bairro, cidade, estado, limit, offset);
        var totalItems = contaClienteRepository.count(nome, sobrenome, cargo, nomeEmpresa, telefone, email);
        return new SearchContaClienteDto(nome, sobrenome, cargo, nomeEmpresa, telefone, email, cep, rua,
                bairro, cidade, estado, orderBy, direction, limit, offset, totalItems, clientes);
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
