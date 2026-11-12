package controller;

import java.time.LocalDate;
import java.util.List;

import dao.OrdemServicoDAO;
import dao.PecaUtilizadaDAO;
import model.Bateria;
import model.Cliente;
import model.OrdemServico;
import model.OrdemServicoCorretiva;
import model.OrdemServicoGarantia;
import model.OrdemServicoPreventiva;
import model.PecaUtilizada;
import model.Tecnico;
import util.Conversor;

public class OrdemServicoController {

    private OrdemServicoDAO dao = new OrdemServicoDAO();
    private PecaUtilizadaDAO pecaDAO = new PecaUtilizadaDAO();

    public OrdemServico abrir(Cliente cliente, Bateria bateria, Tecnico tecnico, String tipo,
            String complemento, String descricao) {
        if (cliente == null) {
            throw new IllegalArgumentException("Busque o cliente pelo CPF/CNPJ.");
        }
        if (bateria == null) {
            throw new IllegalArgumentException("Selecione a bateria.");
        }
        if (tecnico == null) {
            throw new IllegalArgumentException("Selecione o técnico responsável.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Selecione o tipo de manutenção.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Informe a descrição do problema.");
        }

        OrdemServico os = criarOrdem(tipo, complemento);
        os.setCliente(cliente);
        os.setBateria(bateria);
        os.setTecnico(tecnico);
        os.setDescricaoProblema(descricao.trim());
        os.setDataAbertura(LocalDate.now());
        os.setStatus(OrdemServico.ABERTA);
        os.setValorTotal(os.calcularValorTotal());

        os.setNumero(dao.inserir(os));
        return os;
    }

    private OrdemServico criarOrdem(String tipo, String complemento) {
        switch (tipo) {
            case "Preventiva":
                OrdemServicoPreventiva preventiva = new OrdemServicoPreventiva();
                preventiva.setValorRevisao(Conversor.lerDecimal(complemento, "Valor da revisão"));
                return preventiva;
            case "Corretiva":
                OrdemServicoCorretiva corretiva = new OrdemServicoCorretiva();
                corretiva.setValorMaoDeObra(Conversor.lerDecimal(complemento, "Valor da mão de obra"));
                return corretiva;
            case "Garantia":
                if (complemento.isBlank()) {
                    throw new IllegalArgumentException("Informe o número da garantia.");
                }
                OrdemServicoGarantia garantia = new OrdemServicoGarantia();
                garantia.setNumeroGarantia(complemento.trim());
                return garantia;
            default:
                throw new IllegalArgumentException("Tipo de manutenção inválido.");
        }
    }

    public OrdemServico buscarPorNumero(int numero) {
        OrdemServico os = dao.buscarPorNumero(numero);
        if (os == null) {
            throw new IllegalArgumentException("Ordem de serviço não encontrada.");
        }
        os.setPecas(pecaDAO.listarPorOrdem(numero));
        return os;
    }

    public void adicionarPeca(OrdemServico os, String descricao, String quantidade, String valorUnitario) {
        if (os.isConcluida()) {
            throw new IllegalArgumentException("A OS já está concluída e não aceita novas peças.");
        }
        if (descricao.isBlank()) {
            throw new IllegalArgumentException("Informe a descrição da peça.");
        }
        int qtd = Conversor.lerInteiro(quantidade, "Quantidade");
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        double valor = Conversor.lerDecimal(valorUnitario, "Valor unitário");

        PecaUtilizada peca = new PecaUtilizada(descricao.trim(), qtd, valor);
        os.adicionarPeca(peca);
        pecaDAO.inserir(peca, os.getNumero());
        dao.atualizar(os);
    }

    public void alterarStatus(OrdemServico os, String novoStatus) {
        if (os.isConcluida()) {
            throw new IllegalArgumentException("OS já concluída.");
        }
        os.alterarStatus(novoStatus);
        dao.atualizar(os);
    }

    public List<OrdemServico> listar() {
        return dao.listar();
    }

    public List<OrdemServico> listarPorCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Selecione o cliente.");
        }
        return dao.listarPorCliente(cliente.getIdCliente());
    }

    public List<OrdemServico> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Informe a data inicial e a data final.");
        }
        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        }
        return dao.listarPorPeriodo(inicio, fim);
    }

    public double somarValores(List<OrdemServico> ordens) {
        double soma = 0;
        for (OrdemServico os : ordens) {
            soma += os.getValorTotal();
        }
        return soma;
    }
}
