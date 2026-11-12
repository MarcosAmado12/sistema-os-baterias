package controller;

import java.util.List;

import dao.FuncionarioDAO;
import dao.TecnicoDAO;
import model.Funcionario;
import model.Tecnico;

public class TecnicoController {

    private TecnicoDAO dao = new TecnicoDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void salvar(Tecnico tecnico) {
        if (tecnico.getNome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome do técnico.");
        }
        if (tecnico.getLogin().isBlank() || tecnico.getSenha().isBlank()) {
            throw new IllegalArgumentException("Informe o login e a senha do técnico.");
        }

        Funcionario existente = funcionarioDAO.buscarPorLogin(tecnico.getLogin());
        if (existente != null && existente.getIdFuncionario() != tecnico.getIdFuncionario()) {
            throw new IllegalArgumentException("Este login já está em uso.");
        }

        if (tecnico.getIdFuncionario() == 0) {
            dao.inserir(tecnico);
        } else {
            dao.alterar(tecnico);
        }
    }

    public void excluir(Tecnico tecnico) {
        try {
            dao.excluir(tecnico.getIdFuncionario());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Não foi possível excluir. O técnico possui ordens de serviço.");
        }
    }

    public List<Tecnico> listar() {
        return dao.listar();
    }
}
