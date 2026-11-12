package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO {

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, telefone, email, cpf_cnpj, endereco) VALUES (?,?,?,?,?)";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpfCnpj());
            stmt.setString(5, cliente.getEndereco());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, email = ?, cpf_cnpj = ?, endereco = ? "
                + "WHERE id_cliente = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpfCnpj());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getIdCliente());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente ORDER BY nome";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(montarCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public Cliente buscarPorCpfCnpj(String cpfCnpj) {
        String sql = "SELECT * FROM cliente WHERE cpf_cnpj = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return montarCliente(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Cliente montarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
        cliente.setEndereco(rs.getString("endereco"));
        return cliente;
    }
}
