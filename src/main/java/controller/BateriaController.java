package controller;

import java.util.List;

import dao.BateriaDAO;
import model.Bateria;

public class BateriaController {

    private BateriaDAO dao = new BateriaDAO();

    public void salvar(Bateria bateria) {
        if (bateria.getCliente() == null) {
            throw new IllegalArgumentException("Selecione o cliente da bateria.");
        }
        if (bateria.getNumeroSerie().isBlank()) {
            throw new IllegalArgumentException("Informe o número de série.");
        }
        if (bateria.getMarca().isBlank() || bateria.getModelo().isBlank()) {
            throw new IllegalArgumentException("Informe a marca e o modelo.");
        }
        if (bateria.getTensao() <= 0 || bateria.getCapacidadeAh() <= 0) {
            throw new IllegalArgumentException("Tensão e capacidade devem ser maiores que zero.");
        }

        Bateria existente = dao.buscarPorNumeroSerie(bateria.getNumeroSerie());
        if (existente != null && existente.getIdBateria() != bateria.getIdBateria()) {
            throw new IllegalArgumentException("Já existe uma bateria com este número de série.");
        }

        if (bateria.getIdBateria() == 0) {
            dao.inserir(bateria);
        } else {
            dao.alterar(bateria);
        }
    }

    public void excluir(Bateria bateria) {
        try {
            dao.excluir(bateria.getIdBateria());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Não foi possível excluir. A bateria possui ordens de serviço.");
        }
    }

    public List<Bateria> listar() {
        return dao.listar();
    }

    public List<Bateria> listarPorCliente(int idCliente) {
        return dao.listarPorCliente(idCliente);
    }
}
