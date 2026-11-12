package app;

import java.sql.Connection;

import dao.Conexao;

public class TesteConexao {

    public static void main(String[] args) {
        try {
            System.out.println("Conectando ao BD...");
            Connection conexao = Conexao.getConexao();
            System.out.println("Conexao realizada com sucesso");
            conexao.close();
        } catch (Exception e) {
            System.out.println("Falha na conexao com o BD");
            System.out.println(e.getMessage());
        }
    }
}
