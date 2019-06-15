package sisac;
import sisac.dao.AlunoDAO;
import sisac.dao.ExameDao;
import sisac.dao.PagamentoDAO;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;

import java.time.LocalDate;
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

            int option = 0;
            try {
                option = Integer.parseInt(in.nextLine());
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }

            switch (option) {
                case 0:
                    System.out.println("Encerrando.");
                    execute = false;
                    break;
                case 1:
                    System.out.println("Matricular novo aluno");
                    Aluno aluno = new Aluno();
                    System.out.println("Nome: ");
                    aluno.setNome(in.nextLine());
                    System.out.println("CPF: ");
                    aluno.setCpf(in.nextLine());
                    System.out.println("Telefone: ");
                    aluno.setTelefone(in.nextLine());
                    System.out.println("Endereço: ");
                    aluno.setEndereco(in.nextLine());
                    System.out.println("Faixa: ");
                    aluno.setFaixa(in.nextLine());
                    System.out.println("Data fim da matricula: (dd/mm/aaaa)");
                    aluno.setDataFimMatricula(new DataFormatada(in.nextLine()));
                    aluno.setDataMatricula(new DataFormatada(LocalDate.now()));

                    alunoDao.create(aluno);
                    System.out.println();
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
