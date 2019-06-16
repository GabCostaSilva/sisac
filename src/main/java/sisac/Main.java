package sisac;
import sisac.dao.AlunoDAO;
import sisac.dao.ExameDao;
import sisac.dao.PagamentoDAO;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;
import sisac.models.Pagamento;

import java.time.LocalDate;
import java.util.List;
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
            System.out.println("5 - Promover aluno");
            System.out.println("6 - Trancar matrícula de aluno");
            System.out.println("7 - Destrancar matrícula de aluno");
            System.out.println("8 - Convidar aluno para exame de faixa");
            System.out.println("9 - Pagar mensalidade");
            System.out.println("10 - Gerar relatorio de aluno");
            System.out.println("11 - Emitir certificado");
            System.out.println("0- Terminar");

            int option = 0;
            try {
                option = Integer.parseInt(in.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Tente novamente.");
                continue;
            }
            Aluno aluno;
            List<Aluno> alunos;
            switch (option) {
                case 0:
                    System.out.println("Encerrando.");
                    execute = false;
                    break;

                case 1:
                    System.out.println("Matricular novo aluno");
                     aluno = new Aluno();
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

                case 2:
                    System.out.println("Atualizar cadastro de aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    alunos = alunoDao.findByCpf(in.nextLine());
                    break;

                case 3:
                    System.out.println("Lista de alunos");
                    System.out.println("--------------------------------------------");
                    for(Aluno a : alunoDao.get()) {
                        System.out.println(a.toString());
                        System.out.println("---------------------------------------");
                    }
                    break;

                case 4:
                    System.out.println("Desmatricular aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    alunoDao.delete(in.nextLine());
                    break;

                case 5:
                    System.out.println("Promover aluno");
                    System.out.println("Digite o cpf do aluno a ser promovido");
                    alunos = alunoDao.findByCpf(in.nextLine());
                    aluno = alunos.get(0);
                    aluno.promoverAluno();
                    alunoDao.update(aluno);

                    System.out.println("Aluno promovido com sucesso.");
                    break;

                case 6:
                    System.out.println("Trancar matrícula de aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    alunos = alunoDao.findByCpf(in.nextLine());
                    aluno = alunos.get(0);
                    aluno.setStatus(2);
                    alunoDao.update(aluno);
                    System.out.printf("A matrícula de %s foi trancada.\n", aluno.getNome());
                    break;

                case 7:
                    System.out.println("Destrancar matrícula de aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    alunos = alunoDao.findByCpf(in.nextLine());
                    aluno = alunos.get(0);
                    aluno.setStatus(1);
                    alunoDao.update(aluno);
                    System.out.printf("A matrícula de %s foi destrancada.\n", aluno.getNome());
                    break;

                case 8:
                    System.out.println("Digite o cpf do aluno a ser convidado para o exame");
                    alunos = alunoDao.findByCpf(in.nextLine());
                    System.out.printf("Email com convite enviado à %s\n", alunos.get(0).getNome());
                    break;

                case 9:
                    System.out.println("Efetuar Pagamento");
                    Pagamento pagamento = new Pagamento();
                    System.out.println("Tipo do pagamento (0 - Cartão, 1 - Dinheiro)");

                    int tipo = 0;
                    try {
                        tipo = Integer.parseInt(in.nextLine());
                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    pagamento.setTipo(tipo);
                    System.out.println("Valor do pagamento: ");

                    double valor = 0;
                    try {
                        valor = Integer.parseInt(in.nextLine());
                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    pagamento.setValor(valor);
                    pagamento.setData(new DataFormatada(LocalDate.now()));

                    System.out.println("Digite o cpf do aluno que efetuou o pagamento:");
                    alunos = alunoDao.findByCpf(in.nextLine());
                    try {
                        aluno = alunos.get(0);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Aluno não encontrado, tente novamente");
                        break;
                    }

                    aluno.pagarMensalidade(pagamento);
                    System.out.println("Pagamento efetuado com sucesso.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
