package org.example.repositories;

import org.example.entities.ContaCliente;
import org.example.infrastructure.OracleDbConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContaClienteRepository {

    public static final String TB_NAME = "TB_CONTACLIENTE";

    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
        String username = "rm554113";
        String password = "081093";
        return DriverManager.getConnection(url, username, password);
    }

    public ContaCliente save(ContaCliente cliente) {
        String sql = "INSERT INTO tb_contacliente (nome, sobrenome, cargo, nomeempresa, telefone, email, cep, logradouro, numero, complemento, bairro, cidade, estado, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getSobrenome());
            statement.setString(3, cliente.getCargo());
            statement.setString(4, cliente.getNomeEmpresa());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getEmail());
            statement.setString(7, cliente.getCep());
            statement.setString(8, cliente.getLogradouro());
            statement.setString(9, cliente.getNumero());
            statement.setString(10, cliente.getComplemento());
            statement.setString(11, cliente.getBairro());
            statement.setString(12, cliente.getCidade());
            statement.setString(13, cliente.getEstado());
            statement.setString(14, cliente.getSenha());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    cliente.setId(generatedId);
                } else {
                    throw new SQLException("Falha ao inserir cliente. ID não obtido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public List<ContaCliente> getAll(String nome, String sobrenome, String cargo, String nomeEmpresa,
                                     String telefone, String email, String cep, String logradouro,
                                     String numero, String complemento, String bairro, String cidade,
                                     String estado, int limit, int offset) {

        List<ContaCliente> clientes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM TB_CONTACLIENTE WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            query.append(" AND nome LIKE ?");
            parameters.add("%" + nome + "%");
        }
        if (sobrenome != null && !sobrenome.isEmpty()) {
            query.append(" AND sobrenome LIKE ?");
            parameters.add("%" + sobrenome + "%");
        }
        if (cargo != null && !cargo.isEmpty()) {
            query.append(" AND cargo LIKE ?");
            parameters.add("%" + cargo + "%");
        }
        if (nomeEmpresa != null && !nomeEmpresa.isEmpty()) {
            query.append(" AND nomeempresa LIKE ?");
            parameters.add("%" + nomeEmpresa + "%");
        }
        if (telefone != null && !telefone.isEmpty()) {
            query.append(" AND telefone LIKE ?");
            parameters.add("%" + telefone + "%");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
            parameters.add("%" + email + "%");
        }
        if (cep != null && !cep.isEmpty()) {
            query.append(" AND cep LIKE ?");
            parameters.add("%" + cep + "%");
        }
        if (logradouro != null && !logradouro.isEmpty()) {
            query.append(" AND logradouro LIKE ?");
            parameters.add("%" + logradouro + "%");
        }
        if (numero != null && !numero.isEmpty()) {
            query.append(" AND numero LIKE ?");
            parameters.add("%" + numero + "%");
        }
        if (complemento != null && !complemento.isEmpty()) {
            query.append(" AND complemento LIKE ?");
            parameters.add("%" + complemento + "%");
        }
        if (bairro != null && !bairro.isEmpty()) {
            query.append(" AND bairro LIKE ?");
            parameters.add("%" + bairro + "%");
        }
        if (cidade != null && !cidade.isEmpty()) {
            query.append(" AND cidade LIKE ?");
            parameters.add("%" + cidade + "%");
        }
        if (estado != null && !estado.isEmpty()) {
            query.append(" AND estado LIKE ?");
            parameters.add("%" + estado + "%");
        }

        query.append(" ORDER BY id ASC");
        query.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        parameters.add(offset);
        parameters.add(limit);

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ContaCliente cliente = new ContaCliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getString("cargo"),
                        resultSet.getString("nomeempresa"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("cep"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("numero"),
                        resultSet.getString("complemento"),
                        resultSet.getString("bairro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("senha")
                );
                clientes.add(cliente);
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
                        rs.getString("NUMERO"),
                        rs.getString("COMPLEMENTO"),
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
                        rs.getString("LOGRADOURO"),
                        rs.getString("NUMERO"),
                        rs.getString("COMPLEMENTO"),
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
                     "EMAIL, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, contaCliente.getNome());
            stmt.setString(2, contaCliente.getSobrenome());
            stmt.setString(3, contaCliente.getCargo());
            stmt.setString(4, contaCliente.getNomeEmpresa());
            stmt.setString(5, contaCliente.getTelefone());
            stmt.setString(6, contaCliente.getEmail());
            stmt.setString(7, contaCliente.getCep());
            stmt.setString(8, contaCliente.getLogradouro());
            stmt.setString(9, contaCliente.getNumero());
            stmt.setString(10, contaCliente.getComplemento());
            stmt.setString(11, contaCliente.getBairro());
            stmt.setString(12, contaCliente.getCidade());
            stmt.setString(13, contaCliente.getEstado());
            stmt.setString(14, contaCliente.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, ContaCliente contaCliente) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("UPDATE " + TB_NAME + " SET NOME = ?, SOBRENOME = ?, CARGO = ?, NOMEEMPRESA = ?, " +
                     "TELEFONE = ?, EMAIL = ?, CEP = ?, LOGRADOURO = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, SENHA = ? " +
                     "WHERE ID = ?");) {
            stmt.setString(1, contaCliente.getNome());
            stmt.setString(2, contaCliente.getSobrenome());
            stmt.setString(3, contaCliente.getCargo());
            stmt.setString(4, contaCliente.getNomeEmpresa());
            stmt.setString(5, contaCliente.getTelefone());
            stmt.setString(6, contaCliente.getEmail());
            stmt.setString(7, contaCliente.getCep());
            stmt.setString(8, contaCliente.getLogradouro());
            stmt.setString(9, contaCliente.getNumero());
            stmt.setString(10, contaCliente.getComplemento());
            stmt.setString(11, contaCliente.getBairro());
            stmt.setString(12, contaCliente.getCidade());
            stmt.setString(13, contaCliente.getEstado());
            stmt.setString(14, contaCliente.getSenha());
            stmt.setInt(15, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(String nome, String sobrenome, String cargo, String nomeEmpresa, String telefone, String email) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM TB_CONTACLIENTE WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            query.append(" AND nome LIKE ?");
            parameters.add("%" + nome + "%");
        }
        if (sobrenome != null && !sobrenome.isEmpty()) {
            query.append(" AND sobrenome LIKE ?");
            parameters.add("%" + sobrenome + "%");
        }
        if (cargo != null && !cargo.isEmpty()) {
            query.append(" AND cargo LIKE ?");
            parameters.add("%" + cargo + "%");
        }
        if (nomeEmpresa != null && !nomeEmpresa.isEmpty()) {
            query.append(" AND nomeempresa LIKE ?");
            parameters.add("%" + nomeEmpresa + "%");
        }
        if (telefone != null && !telefone.isEmpty()) {
            query.append(" AND telefone LIKE ?");
            parameters.add("%" + telefone + "%");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
            parameters.add("%" + email + "%");
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
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
