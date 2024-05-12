package org.example.entities.dtos;

import org.example.entities.ContaCliente;

import java.util.List;

public record SearchContaClienteDto(String nome, String sobrenome, String cargo, String nomeEmpresa, String telefone,
                                    String email, String cep, String rua, String bairro, String cidade,
                                    String estado, String orderBy, String direction,
                                    int limit, int offset, int totalItems, List<ContaCliente> clientes) {}
