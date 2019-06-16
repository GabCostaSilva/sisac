package sisac.models;

import sisac.helpers.DataFormatada;

import java.util.ArrayList;

public class Aluno extends Pessoa {

    private long id;

    private RegistroDePagamento registroPagamentos;
    private ArrayList<Certificado> certificados;
    private String faixa;
    private int graduacao;
   private DataFormatada dataMatricula;
   private DataFormatada dataFimMatricula;
    private int status; // 0 - Desmatriculado, 1 - Matriculado, 2 - Matricula Trancada

    public Aluno() {
        registroPagamentos = new RegistroDePagamento();
        certificados = new ArrayList<>();
        status = 1;
        graduacao = 0;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        status = 2;
    }

    public void destrancarMatricula() {
        status = 1;
    }

    public DataFormatada getDataMatricula() {
        return dataMatricula;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusToString() {
        String stat = "Matriculado";
        switch (status) {
            case 0:
                stat = "Desmatriculado";
                break;
            case 1:
                stat = "Matriculado";
                break;
            case 2:
                stat = "Matrícula trancada";
                break;
            default:
                break;
        }
        return stat;
    }
    public DataFormatada getDataFimMatricula() {
        return dataFimMatricula;
    }

    public ArrayList<Certificado> getCertificados() {
        return certificados;
    }

    public String toString() {
        return String.format(
                "Nome: %s\n" +
                "CPF: %s\n" +
                "Data matricula %s\n" +
                "Faixa: %s\n" +
                "Endereco: %s\n" +
                "Telefone: %s\n" +
                "Status: %s\n",
                this.getNome(), this.getCpf(), getDataMatricula().toString(), getFaixa(),
                this.getEndereco(), this.getTelefone(), getStatus()
        );
    }
}


