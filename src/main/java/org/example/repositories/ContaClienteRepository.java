package org.example.repositories;

import org.example.entities.ContaCliente;
import org.example.infrastructure.OracleDbConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContaClienteRepository {

    public static final String TB_NAME = "TB_CONTACLIENTE";

    public List<ContaCliente> getAll() {
        List<ContaCliente> clientes = new ArrayList<>();
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(new ContaCliente(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("SOBRENOME"),
                        rs.getString("CARGO"),
                        rs.getString("NOMEEMPRESA"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("CEP"),
                        rs.getString("RUA"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("SENHA")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<ContaCliente> getAll(String nome, String sobrenome, String cargo, String nomeEmpresa,
                                     String telefone, String email, String cep, String rua,
                                     String bairro, String cidade, String estado, int limit, int offset) {
        List<ContaCliente> clientes = new ArrayList<>();
        try {
            String sql = String.format("SELECT ID, NOME, SOBRENOME, CARGO, NOMEEMPRESA, TELEFONE, EMAIL, " +
                    "CEP, RUA, BAIRRO, CIDADE, ESTADO, SENHA " +
                    "FROM %s WHERE NOME LIKE ? AND SOBRENOME LIKE ? " +
                    "AND CARGO LIKE ? AND NOMEEMPRESA LIKE ? AND TELEFONE LIKE ? AND EMAIL LIKE ? " +
                    "AND CEP LIKE ? AND RUA LIKE ? AND BAIRRO LIKE ? AND CIDADE LIKE ? AND ESTADO LIKE ? " +
                    "ORDER BY NOME ASC OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", TB_NAME, offset, limit == 0 ? 10 : limit);

            try (var conn = new OracleDbConfiguration().getConnection();
                 var stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + nome + "%");
                stmt.setString(2, "%" + sobrenome + "%");
                stmt.setString(3, "%" + cargo + "%");
                stmt.setString(4, "%" + nomeEmpresa + "%");
                stmt.setString(5, "%" + telefone + "%");
                stmt.setString(6, "%" + email + "%");
                stmt.setString(7, "%" + cep + "%");
                stmt.setString(8, "%" + rua + "%");
                stmt.setString(9, "%" + bairro + "%");
                stmt.setString(10, "%" + cidade + "%");
                stmt.setString(11, "%" + estado + "%");

                var rs = stmt.executeQuery();
                while (rs.next()) {
                    ContaCliente cliente = new ContaCliente(
                            rs.getInt("ID"),
                            rs.getString("NOME"),
                            rs.getString("SOBRENOME"),
                            rs.getString("CARGO"),
                            rs.getString("NOMEEMPRESA"),
                            rs.getString("TELEFONE"),
                            rs.getString("EMAIL"),
                            rs.getString("CEP"),
                            rs.getString("RUA"),
                            rs.getString("BAIRRO"),
                            rs.getString("CIDADE"),
                            rs.getString("ESTADO"),
                            rs.getString("SENHA"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Object> getAllByRegistroAcesso(int idRegistroAcesso) {
        List<Object> clientes = new ArrayList<>();
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE IDREGISTROACESSO = ?");) {
            stmt.setInt(1, idRegistroAcesso);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(new ContaCliente(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("SOBRENOME"),
                        rs.getString("CARGO"),
                        rs.getString("NOMEEMPRESA"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("CEP"),
                        rs.getString("RUA"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("SENHA")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Optional<ContaCliente> get(int id) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ContaCliente(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("SOBRENOME"),
                        rs.getString("CARGO"),
                        rs.getString("NOMEEMPRESA"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("CEP"),
                        rs.getString("RUA"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("SENHA")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void create(ContaCliente contaCliente) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("INSERT INTO " + TB_NAME + " (NOME, SOBRENOME, CARGO, NOMEEMPRESA, TELEFONE, " +
                     "EMAIL, CEP, RUA, BAIRRO, CIDADE, ESTADO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, contaCliente.getNome());
            stmt.setString(2, contaCliente.getSobrenome());
            stmt.setString(3, contaCliente.getCargo());
            stmt.setString(4, contaCliente.getNomeEmpresa());
            stmt.setString(5, contaCliente.getTelefone());
            stmt.setString(6, contaCliente.getEmail());
            stmt.setString(7, contaCliente.getCep());
            stmt.setString(8, contaCliente.getRua());
            stmt.setString(9, contaCliente.getBairro());
            stmt.setString(10, contaCliente.getCidade());
            stmt.setString(11, contaCliente.getEstado());
            stmt.setString(12, contaCliente.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, ContaCliente contaCliente) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("UPDATE " + TB_NAME + " SET NOME = ?, SOBRENOME = ?, CARGO = ?, NOMEEMPRESA = ?, " +
                     "TELEFONE = ?, EMAIL = ?, CEP = ?, RUA = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, SENHA = ? " +
                     "WHERE ID = ?");) {
            stmt.setString(1, contaCliente.getNome());
            stmt.setString(2, contaCliente.getSobrenome());
            stmt.setString(3, contaCliente.getCargo());
            stmt.setString(4, contaCliente.getNomeEmpresa());
            stmt.setString(5, contaCliente.getTelefone());
            stmt.setString(6, contaCliente.getEmail());
            stmt.setString(7, contaCliente.getCep());
            stmt.setString(8, contaCliente.getRua());
            stmt.setString(9, contaCliente.getBairro());
            stmt.setString(10, contaCliente.getCidade());
            stmt.setString(11, contaCliente.getEstado());
            stmt.setString(12, contaCliente.getSenha());
            stmt.setInt(13, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(String nome, String sobrenome, String cargo, String nomeEmpresa,
                     String telefone, String email) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("SELECT COUNT(*) FROM " +
                     TB_NAME + " WHERE nome LIKE ? " +
                     "AND sobrenome LIKE ? " +
                     "AND cargo LIKE ? " +
                     "AND nomeempresa LIKE ? " +
                     "AND telefone LIKE ? " +
                     "AND email LIKE ?")) {
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + sobrenome + "%");
            stmt.setString(3, "%" + cargo + "%");
            stmt.setString(4, "%" + nomeEmpresa + "%");
            stmt.setString(5, "%" + telefone + "%");
            stmt.setString(6, "%" + email + "%");
            var result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void delete(int id) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("DELETE FROM " + TB_NAME + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
