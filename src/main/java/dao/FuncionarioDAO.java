package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Funcionario;
import model.Tecnico;

public class FuncionarioDAO {

    public Funcionario buscarPorLogin(String login) {
        String sql = "SELECT * FROM funcionario WHERE login = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Funcionario funcionario;
            if ("Tecnico".equals(rs.getString("cargo"))) {
                Tecnico tecnico = new Tecnico();
                tecnico.setEspecialidade(rs.getString("especialidade"));
                funcionario = tecnico;
            } else {
                funcionario = new Funcionario();
            }
            funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setCargo(rs.getString("cargo"));
            return funcionario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
