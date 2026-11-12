package controller;

import dao.FuncionarioDAO;
import model.Funcionario;

public class LoginController {

    private FuncionarioDAO dao = new FuncionarioDAO();

    public Funcionario entrar(String login, String senha) {
        if (login.isBlank() || senha.isBlank()) {
            throw new IllegalArgumentException("Informe o login e a senha.");
        }
        Funcionario funcionario = dao.buscarPorLogin(login.trim());
        if (funcionario == null || !funcionario.autenticar(login.trim(), senha)) {
            throw new IllegalArgumentException("Login ou senha inválidos.");
        }
        return funcionario;
    }
}
