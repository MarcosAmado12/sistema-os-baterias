package model;

public class OrdemServicoGarantia extends OrdemServico {

    private String numeroGarantia;

    @Override
    public double calcularValorTotal() {
        return 0;
    }

    @Override
    public String getTipo() {
        return "Garantia";
    }

    public String getNumeroGarantia() {
        return numeroGarantia;
    }

    public void setNumeroGarantia(String numeroGarantia) {
        this.numeroGarantia = numeroGarantia;
    }
}
