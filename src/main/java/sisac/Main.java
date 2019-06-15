package sisac;
import sisac.dao.AlunoDAO;
import sisac.dao.ExameDao;
import sisac.dao.PagamentoDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean execute = true;

        AlunoDAO alunoDao = new AlunoDAO();
        PagamentoDAO pagamentoDao = new PagamentoDAO();
        ExameDao exameDao = new ExameDao();

        System.out.println("Bem Vindo ao SISAC!\n" +
                "***************************************");
        while(execute) {
            System.out.println("Escolha uma opção");
            System.out.println("1 - Matricular novo aluno");
            System.out.println("2 - Alterar cadastro de aluno");
            System.out.println("3 - Listar alunos");
            System.out.println("4 - Desmatricular aluno");
            System.out.println("5 - Trancar matrícula de aluno");
            System.out.println("6 - Destrancar matrícula de aluno");
            System.out.println("7 - Convidar aluno para exame de aluno");
            System.out.println("8 -  matrícula de aluno");
            System.out.println("9 - Pagar mensalidade");
            System.out.println("10 - Gerar Relatorio de aluno");
            System.out.println("11 - Emitir certificado");
            System.out.println("0- Terminar");


        }
    }
}
