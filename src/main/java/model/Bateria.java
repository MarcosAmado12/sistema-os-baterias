package model;

public class Bateria {

    private int idBateria;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private double tensao;
    private double capacidadeAh;
    private Cliente cliente;

    public int getIdBateria() {
        return idBateria;
    }

    public void setIdBateria(int idBateria) {
        this.idBateria = idBateria;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getTensao() {
        return tensao;
    }

    public void setTensao(double tensao) {
        this.tensao = tensao;
    }

    public double getCapacidadeAh() {
        return capacidadeAh;
    }

    public void setCapacidadeAh(double capacidadeAh) {
        this.capacidadeAh = capacidadeAh;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return numeroSerie + " - " + marca + " " + modelo;
    }
}
