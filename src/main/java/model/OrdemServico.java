package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class OrdemServico {

    public static final String ABERTA = "Aberta";
    public static final String EM_EXECUCAO = "Em execução";
    public static final String CONCLUIDA = "Concluída";

    protected int numero;
    protected LocalDate dataAbertura;
    protected LocalDate dataConclusao;
    protected String descricaoProblema;
    protected String status;
    protected double valorTotal;

    private Cliente cliente;
    private Bateria bateria;
    private Tecnico tecnico;
    private List<PecaUtilizada> pecas = new ArrayList<>();

    public abstract double calcularValorTotal();

    public abstract String getTipo();

    public void adicionarPeca(PecaUtilizada peca) {
        pecas.add(peca);
        valorTotal = calcularValorTotal();
    }

    public void alterarStatus(String novoStatus) {
        status = novoStatus;
        if (CONCLUIDA.equals(novoStatus)) {
            dataConclusao = LocalDate.now();
            valorTotal = calcularValorTotal();
        }
    }

    public boolean isConcluida() {
        return CONCLUIDA.equals(status);
    }

    protected double somarPecas() {
        double soma = 0;
        for (PecaUtilizada peca : pecas) {
            soma += peca.calcularSubtotal();
        }
        return soma;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Bateria getBateria() {
        return bateria;
    }

    public void setBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public List<PecaUtilizada> getPecas() {
        return pecas;
    }

    public void setPecas(List<PecaUtilizada> pecas) {
        this.pecas = pecas;
    }
}
