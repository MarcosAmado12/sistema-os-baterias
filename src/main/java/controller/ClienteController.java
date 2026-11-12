package controller;

import java.util.List;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

    private ClienteDAO dao = new ClienteDAO();

    public void salvar(Cliente cliente) {
        if (cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome do cliente.");
        }
        if (cliente.getCpfCnpj().isBlank()) {
            throw new IllegalArgumentException("Informe o CPF/CNPJ do cliente.");
        }
        if (cliente.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Informe o telefone do cliente.");
        }

        Cliente existente = dao.buscarPorCpfCnpj(cliente.getCpfCnpj());
        if (existente != null && existente.getIdCliente() != cliente.getIdCliente()) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF/CNPJ.");
        }

        if (cliente.getIdCliente() == 0) {
            dao.inserir(cliente);
        } else {
            dao.alterar(cliente);
        }
    }

    public void excluir(Cliente cliente) {
        try {
            dao.excluir(cliente.getIdCliente());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(
                    "Não foi possível excluir. O cliente possui baterias ou ordens de serviço.");
        }
    }

    public List<Cliente> listar() {
        return dao.listar();
    }

    public Cliente buscarPorCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.isBlank()) {
            throw new IllegalArgumentException("Informe o CPF/CNPJ do cliente.");
        }
        return dao.buscarPorCpfCnpj(cpfCnpj.trim());
    }
}
