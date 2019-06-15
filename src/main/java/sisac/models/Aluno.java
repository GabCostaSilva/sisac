package sisac.models;

import sisac.helpers.DataFormatada;

import java.util.ArrayList;

public class Aluno extends Pessoa {

    private RegistroDePagamento registroPagamentos;
    private ArrayList<Certificado> certificados;
    private String faixa;
    private int graduacao;
    private DataFormatada dataMatricula;
    private DataFormatada dataFimMatricula;
    private boolean matriculado;


    public Aluno() {
        registroPagamentos = new RegistroDePagamento();
        certificados = new ArrayList<>();
        matriculado = true;
        graduacao = 0;
    }


    public boolean isMatriculado() {
        return matriculado;
    }

    public void setCertificados(ArrayList<Certificado> certificados) {
        this.certificados = certificados;
    }

    public void setFaixa(String faixa) {
        this.faixa = faixa;
    }

    public void setGraduacao(int graduacao) {
        this.graduacao = graduacao;
        this.faixa = Faixa.getFaixa(graduacao);
    }

    public void setDataMatricula(DataFormatada dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public void setDataFimMatricula(DataFormatada dataFimMatricula) {
        this.dataFimMatricula = dataFimMatricula;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }

    public String getFaixa() {
        return faixa;
    }

    public void promoverAluno() {
        faixa = Faixa.getFaixa(graduacao++);
    }

    public void pagarMensalidade(Pagamento pagamento) {

        registroPagamentos.adicionaPagamento(pagamento);
    }


    public RegistroDePagamento getRegistroPagamentos() {
        return registroPagamentos;
    }

    public void trancarMatricula() {
        matriculado = false;
    }

    public void destrancarMatricula() {
        matriculado = true;
    }

    public DataFormatada getDataMatricula() {
        return dataMatricula;
    }

    public DataFormatada getDataFimMatricula() {
        return dataFimMatricula;
    }

    public ArrayList<Certificado> getCertificados() {
        return certificados;
    }

}


