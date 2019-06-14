package sisac.models;

import java.util.ArrayList;

public class Aluno extends Pessoa {

    private int id;
    private Matricula matricula;
    private RegistroDePagamento registroPagamentos;
    private ArrayList<Certificado> certificados;
    private String faixa;
    private int graduacao;

    public Aluno() {

    }

    public Aluno(int id) {
        this.id = id;
        registroPagamentos = new RegistroDePagamento(id);
        certificados = new ArrayList<>();
        graduacao = 0;
    }

    public void promoverAluno() {
        faixa = Faixa.getFaixa(graduacao++);
    }

    public void pagarMensalidade(Pagamento pagamento) {

        registroPagamentos.adicionaPagamento(pagamento);
    }

    public int getId() {
        return id;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public RegistroDePagamento getRegistroPagamentos() {
        return registroPagamentos;
    }

    public void trancarMatricula() {
        matricula.trancar();
    }

    public void destrancarMatricula() {
        matricula.destrancar();
    }

    public ArrayList<Certificado> getCertificados() {
        return certificados;
    }

}


