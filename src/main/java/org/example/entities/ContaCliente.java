package org.example.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.mindrot.jbcrypt.BCrypt;

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
        "logradouro",
        "numero",
        "complemento",
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
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String senha;
    private transient RegistroAcesso registroAcesso;

    public ContaCliente(){}

    public ContaCliente(int id, String nome, String sobrenome, String cargo, String nomeempresa, String telefone, String email, String cep, String numero, String complemento, String bairro, String cidade, String estado, String senha){}

    public ContaCliente(int id, String nome, String sobrenome, String cargo, String nomeEmpresa, String telefone, String email, String cep,
                        String logradouro, String numero, String complemento,
                        String bairro, String cidade, String estado, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.nomeEmpresa = nomeEmpresa;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.senha = senha;
    }

    public ContaCliente(int id, String nome, String sobrenome, String cargo, String nomeEmpresa, String telefone, String email, String cep,
                        String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String senha, RegistroAcesso registroAcesso) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.nomeEmpresa = nomeEmpresa;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    // Método para verificar a senha em texto plano contra o hash armazenado
    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }


    @Override
    public String toString() {
        return "ContaCliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
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
        if (logradouro == null || logradouro.isBlank())
            errors.add("Logradouro não pode ser vazia");
        if (numero == null || numero.isBlank())
            errors.add("Número não pode ser vazia");
        if (complemento == null || complemento.isBlank())
            errors.add("Complemento não pode ser vazio");
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
