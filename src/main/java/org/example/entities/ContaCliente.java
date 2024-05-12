package org.example.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Map;

@JsonPropertyOrder({
        "nome",
        "sobrenome",
        "cargo",
        "nomeEmpresa",
        "telefone",
        "email",
        "cep",
        "rua",
        "bairro",
        "cidade",
        "estado",
        "senha"
})

public class ContaCliente {
    private int id;
    private String nome;
    private String sobrenome;
    private String cargo;
    private String nomeEmpresa;
    private String telefone;
    private String email;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String login;
    private String senha;
    private transient RegistroAcesso registroAcesso;

    public ContaCliente(){}

    public ContaCliente(int id, String nome, String sobrenome, String cargo, String nomeEmpresa, String telefone, String email, String cep,
                        String rua, String bairro, String cidade, String estado, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.nomeEmpresa = nomeEmpresa;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.senha = senha;
    }

    public ContaCliente(int id, String nome, String sobrenome, String cargo, String nomeEmpresa, String telefone, String email, String cep,
                        String rua, String bairro, String cidade, String estado, String senha, RegistroAcesso registroAcesso) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.nomeEmpresa = nomeEmpresa;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.senha = senha;
        this.registroAcesso = registroAcesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RegistroAcesso getRegistroAcesso() {
        return registroAcesso;
    }

    public void setRegistroAcesso(RegistroAcesso registroAcesso) {
        this.registroAcesso = registroAcesso;
    }

    @Override
    public String toString() {
        return "ContaCliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", senha='" + senha + '\'' +
                ", registroAcesso=" + registroAcesso +
                '}';
    }

    public Map<Boolean, ArrayList<String>> validate() {
        var errors = new ArrayList<String>();
        if (nome == null || nome.isBlank())
            errors.add("Nome não pode ser vazio");
        if (email == null || email.isBlank())
            errors.add("Email não pode ser vazio");
        if (cep == null || cep.isBlank())
            errors.add("CEP não pode ser vazio");
        if (rua == null || rua.isBlank())
            errors.add("Rua não pode ser vazia");
        if (bairro == null || bairro.isBlank())
            errors.add("Bairro não pode ser vazio");
        if (cidade == null || cidade.isBlank())
            errors.add("Cidade não pode ser vazia");
        if (estado == null || estado.isBlank())
            errors.add("Estado não pode ser vazio");
        if (senha == null || senha.isBlank())
            errors.add("Senha não pode ser vazia");

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }
}
