package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Bateria;
import model.Cliente;
import model.OrdemServico;
import model.OrdemServicoCorretiva;
import model.OrdemServicoGarantia;
import model.OrdemServicoPreventiva;
import model.Tecnico;

public class OrdemServicoDAO {

    private static final String SELECT = "SELECT os.*, c.nome AS cliente_nome, c.cpf_cnpj, "
            + "c.telefone AS cliente_telefone, b.numero_serie, b.marca, b.modelo, f.nome AS tecnico_nome "
            + "FROM ordem_servico os "
            + "JOIN cliente c ON c.id_cliente = os.id_cliente "
            + "JOIN bateria b ON b.id_bateria = os.id_bateria "
            + "JOIN funcionario f ON f.id_funcionario = os.id_tecnico ";

    public int inserir(OrdemServico os) {
        String sql = "INSERT INTO ordem_servico (data_abertura, descricao_problema, status, valor_total, tipo, "
                + "valor_revisao, valor_mao_de_obra, numero_garantia, id_cliente, id_bateria, id_tecnico) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING numero";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(os.getDataAbertura()));
            stmt.setString(2, os.getDescricaoProblema());
            stmt.setString(3, os.getStatus());
            stmt.setDouble(4, os.getValorTotal());
            stmt.setString(5, os.getTipo());
            stmt.setDouble(6, 0);
            stmt.setDouble(7, 0);
            stmt.setString(8, null);
            if (os instanceof OrdemServicoPreventiva) {
                stmt.setDouble(6, ((OrdemServicoPreventiva) os).getValorRevisao());
            } else if (os instanceof OrdemServicoCorretiva) {
                stmt.setDouble(7, ((OrdemServicoCorretiva) os).getValorMaoDeObra());
            } else if (os instanceof OrdemServicoGarantia) {
                stmt.setString(8, ((OrdemServicoGarantia) os).getNumeroGarantia());
            }
            stmt.setInt(9, os.getCliente().getIdCliente());
            stmt.setInt(10, os.getBateria().getIdBateria());
            stmt.setInt(11, os.getTecnico().getIdFuncionario());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("numero");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(OrdemServico os) {
        String sql = "UPDATE ordem_servico SET status = ?, data_conclusao = ?, valor_total = ? WHERE numero = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, os.getStatus());
            if (os.getDataConclusao() != null) {
                stmt.setDate(2, Date.valueOf(os.getDataConclusao()));
            } else {
                stmt.setDate(2, null);
            }
            stmt.setDouble(3, os.getValorTotal());
            stmt.setInt(4, os.getNumero());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OrdemServico buscarPorNumero(int numero) {
        String sql = SELECT + "WHERE os.numero = ?";
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return montarOrdem(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrdemServico> listar() {
        String sql = SELECT + "ORDER BY os.numero DESC";
        List<OrdemServico> ordens = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ordens.add(montarOrdem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }

    public List<OrdemServico> listarPorCliente(int idCliente) {
        String sql = SELECT + "WHERE os.id_cliente = ? ORDER BY os.numero DESC";
        List<OrdemServico> ordens = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ordens.add(montarOrdem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }

    public List<OrdemServico> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        String sql = SELECT + "WHERE os.data_abertura BETWEEN ? AND ? ORDER BY os.data_abertura, os.numero";
        List<OrdemServico> ordens = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(inicio));
            stmt.setDate(2, Date.valueOf(fim));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ordens.add(montarOrdem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }

    private OrdemServico montarOrdem(ResultSet rs) throws SQLException {
        OrdemServico os;
        switch (rs.getString("tipo")) {
            case "Preventiva":
                OrdemServicoPreventiva preventiva = new OrdemServicoPreventiva();
                preventiva.setValorRevisao(rs.getDouble("valor_revisao"));
                os = preventiva;
                break;
            case "Corretiva":
                OrdemServicoCorretiva corretiva = new OrdemServicoCorretiva();
                corretiva.setValorMaoDeObra(rs.getDouble("valor_mao_de_obra"));
                os = corretiva;
                break;
            case "Garantia":
                OrdemServicoGarantia garantia = new OrdemServicoGarantia();
                garantia.setNumeroGarantia(rs.getString("numero_garantia"));
                os = garantia;
                break;
            default:
                throw new SQLException("Tipo de ordem de serviço desconhecido: " + rs.getString("tipo"));
        }

        os.setNumero(rs.getInt("numero"));
        os.setDataAbertura(rs.getDate("data_abertura").toLocalDate());
        if (rs.getDate("data_conclusao") != null) {
            os.setDataConclusao(rs.getDate("data_conclusao").toLocalDate());
        }
        os.setDescricaoProblema(rs.getString("descricao_problema"));
        os.setStatus(rs.getString("status"));
        os.setValorTotal(rs.getDouble("valor_total"));

        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNome(rs.getString("cliente_nome"));
        cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
        cliente.setTelefone(rs.getString("cliente_telefone"));
        os.setCliente(cliente);

        Bateria bateria = new Bateria();
        bateria.setIdBateria(rs.getInt("id_bateria"));
        bateria.setNumeroSerie(rs.getString("numero_serie"));
        bateria.setMarca(rs.getString("marca"));
        bateria.setModelo(rs.getString("modelo"));
        os.setBateria(bateria);

        Tecnico tecnico = new Tecnico();
        tecnico.setIdFuncionario(rs.getInt("id_tecnico"));
        tecnico.setNome(rs.getString("tecnico_nome"));
        os.setTecnico(tecnico);

        return os;
    }
}
