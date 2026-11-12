package model;

public class OrdemServicoCorretiva extends OrdemServico {

    private double valorMaoDeObra;

    @Override
    public double calcularValorTotal() {
        return valorMaoDeObra + somarPecas();
    }

    @Override
    public String getTipo() {
        return "Corretiva";
    }

    public double getValorMaoDeObra() {
        return valorMaoDeObra;
    }

    public void setValorMaoDeObra(double valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
    }
}
