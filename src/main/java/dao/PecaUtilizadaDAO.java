package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PecaUtilizada;

public class PecaUtilizadaDAO {

    public void inserir(PecaUtilizada peca, int numeroOs) {
        String sql = "INSERT INTO peca_utilizada (descricao, quantidade, valor_unitario, numero_os) "
                + "VALUES (?,?,?,?)";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, peca.getDescricao());
            stmt.setInt(2, peca.getQuantidade());
            stmt.setDouble(3, peca.getValorUnitario());
            stmt.setInt(4, numeroOs);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PecaUtilizada> listarPorOrdem(int numeroOs) {
        String sql = "SELECT * FROM peca_utilizada WHERE numero_os = ? ORDER BY id_peca";
        List<PecaUtilizada> pecas = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numeroOs);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PecaUtilizada peca = new PecaUtilizada();
                peca.setDescricao(rs.getString("descricao"));
                peca.setQuantidade(rs.getInt("quantidade"));
                peca.setValorUnitario(rs.getDouble("valor_unitario"));
                pecas.add(peca);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pecas;
    }
}
