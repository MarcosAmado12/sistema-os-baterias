package model;

public class Tecnico extends Funcionario {

    private String especialidade;

    public Tecnico() {
        setCargo("Tecnico");
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return nome;
    }
}
