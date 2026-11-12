package model;

public class OrdemServicoPreventiva extends OrdemServico {

    private double valorRevisao;

    @Override
    public double calcularValorTotal() {
        return valorRevisao + somarPecas();
    }

    @Override
    public String getTipo() {
        return "Preventiva";
    }

    public double getValorRevisao() {
        return valorRevisao;
    }

    public void setValorRevisao(double valorRevisao) {
        this.valorRevisao = valorRevisao;
    }
}
