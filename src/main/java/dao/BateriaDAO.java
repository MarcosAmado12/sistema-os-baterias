package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bateria;
import model.Cliente;

public class BateriaDAO {

    private static final String SELECT = "SELECT b.*, c.nome AS cliente_nome FROM bateria b "
            + "JOIN cliente c ON c.id_cliente = b.id_cliente ";

    public void inserir(Bateria bateria) {
        String sql = "INSERT INTO bateria (numero_serie, marca, modelo, tensao, capacidade_ah, id_cliente) "
                + "VALUES (?,?,?,?,?,?)";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, bateria.getNumeroSerie());
            stmt.setString(2, bateria.getMarca());
            stmt.setString(3, bateria.getModelo());
            stmt.setDouble(4, bateria.getTensao());
            stmt.setDouble(5, bateria.getCapacidadeAh());
            stmt.setInt(6, bateria.getCliente().getIdCliente());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Bateria bateria) {
        String sql = "UPDATE bateria SET numero_serie = ?, marca = ?, modelo = ?, tensao = ?, "
                + "capacidade_ah = ?, id_cliente = ? WHERE id_bateria = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, bateria.getNumeroSerie());
            stmt.setString(2, bateria.getMarca());
            stmt.setString(3, bateria.getModelo());
            stmt.setDouble(4, bateria.getTensao());
            stmt.setDouble(5, bateria.getCapacidadeAh());
            stmt.setInt(6, bateria.getCliente().getIdCliente());
            stmt.setInt(7, bateria.getIdBateria());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(int idBateria) {
        String sql = "DELETE FROM bateria WHERE id_bateria = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idBateria);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bateria> listar() {
        String sql = SELECT + "ORDER BY c.nome, b.numero_serie";
        List<Bateria> baterias = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                baterias.add(montarBateria(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return baterias;
    }

    public List<Bateria> listarPorCliente(int idCliente) {
        String sql = SELECT + "WHERE b.id_cliente = ? ORDER BY b.numero_serie";
        List<Bateria> baterias = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                baterias.add(montarBateria(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return baterias;
    }

    public Bateria buscarPorNumeroSerie(String numeroSerie) {
        String sql = SELECT + "WHERE b.numero_serie = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return montarBateria(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Bateria montarBateria(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNome(rs.getString("cliente_nome"));

        Bateria bateria = new Bateria();
        bateria.setIdBateria(rs.getInt("id_bateria"));
        bateria.setNumeroSerie(rs.getString("numero_serie"));
        bateria.setMarca(rs.getString("marca"));
        bateria.setModelo(rs.getString("modelo"));
        bateria.setTensao(rs.getDouble("tensao"));
        bateria.setCapacidadeAh(rs.getDouble("capacidade_ah"));
        bateria.setCliente(cliente);
        return bateria;
    }
}
