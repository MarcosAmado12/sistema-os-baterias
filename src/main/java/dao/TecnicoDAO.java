package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tecnico;

public class TecnicoDAO {

    public void inserir(Tecnico tecnico) {
        String sql = "INSERT INTO funcionario (nome, telefone, email, login, senha, cargo, especialidade) "
                + "VALUES (?,?,?,?,?,?,?)";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getTelefone());
            stmt.setString(3, tecnico.getEmail());
            stmt.setString(4, tecnico.getLogin());
            stmt.setString(5, tecnico.getSenha());
            stmt.setString(6, tecnico.getCargo());
            stmt.setString(7, tecnico.getEspecialidade());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Tecnico tecnico) {
        String sql = "UPDATE funcionario SET nome = ?, telefone = ?, email = ?, login = ?, senha = ?, "
                + "especialidade = ? WHERE id_funcionario = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getTelefone());
            stmt.setString(3, tecnico.getEmail());
            stmt.setString(4, tecnico.getLogin());
            stmt.setString(5, tecnico.getSenha());
            stmt.setString(6, tecnico.getEspecialidade());
            stmt.setInt(7, tecnico.getIdFuncionario());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(int idFuncionario) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tecnico> listar() {
        String sql = "SELECT * FROM funcionario WHERE cargo = 'Tecnico' ORDER BY nome";
        List<Tecnico> tecnicos = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tecnico tecnico = new Tecnico();
                tecnico.setIdFuncionario(rs.getInt("id_funcionario"));
                tecnico.setNome(rs.getString("nome"));
                tecnico.setTelefone(rs.getString("telefone"));
                tecnico.setEmail(rs.getString("email"));
                tecnico.setLogin(rs.getString("login"));
                tecnico.setSenha(rs.getString("senha"));
                tecnico.setEspecialidade(rs.getString("especialidade"));
                tecnicos.add(tecnico);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tecnicos;
    }
}
