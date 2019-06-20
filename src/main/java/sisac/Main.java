package sisac;
import sisac.dao.AlunoDAO;
import sisac.dao.ExameDao;
import sisac.dao.PagamentoDAO;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;
import sisac.models.Certificado;
import sisac.models.Pagamento;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static AlunoDAO alunoDao;
    private static final String NOT_FOUND = "Aluno não encontrado, tente novamente";

    public Main() {
        alunoDao = new AlunoDAO();
    }


    public static Aluno getAluno(String cpf){
        List<Aluno> alunos;
        Aluno aluno = null;
        try {
            alunos = alunoDao.findByCpf(cpf);
            aluno = alunos.get(0);
        } catch(NullPointerException | SQLException | IndexOutOfBoundsException e) {
            System.out.printf("%s\n", NOT_FOUND);
        }
        return aluno;
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean execute = true;

        System.out.println("\n***************************************");
        System.out.println("Bem Vindo ao SISAC!\n" +
                "***************************************");
        while(execute) {
            System.out.println();
            System.out.println("Escolha uma opção");
            System.out.println();
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
            System.out.println();
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
                    try {
                        aluno.setDataFimMatricula(new DataFormatada(in.nextLine()));
                        aluno.setDataMatricula(new DataFormatada(LocalDate.now()));
                        alunoDao.create(aluno);
                        System.out.println("Aluno matriculado com sucesso.");

                    } catch (NullPointerException | DateTimeParseException e) {
                        System.out.println("\nOps... Insira a data no formato dia/mes/ano");
                    }

                    break;

                case 2:
                    System.out.println("Atualizar cadastro de aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    aluno = getAluno(in.nextLine());
                    System.out.println(aluno.toString());
                    break;

                case 3:
                    System.out.println("Lista de alunos");
                    System.out.println("--------------------------------------------");
                    try {
                        for(Aluno a : alunoDao.get()) {
                            System.out.println(a.toString());
                            System.out.println("---------------------------------------");
                        }
                    } catch (SQLException e) {
                        System.out.printf("%s\n", NOT_FOUND);
                    }
                    break;

                case 4:
                    System.out.println("Desmatricular aluno");
                    System.out.println("Digite o cpf do aluno a ser desmatriculado");
                    alunoDao.delete(in.nextLine());
                    System.out.println("Aluno desmatriculado com sucesso.");
                    break;

                case 5:
                    System.out.println("Promover aluno");
                    System.out.println("Digite o cpf do aluno a ser promovido");
                    aluno = getAluno(in.nextLine());
                    aluno.promoverAluno();
                    alunoDao.update(aluno);

                    System.out.println("Aluno promovido com sucesso.");
                    break;

                case 6:
                    System.out.println("Trancar matrícula de aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    aluno = getAluno(in.nextLine());
                    aluno.setStatus(2);
                    alunoDao.update(aluno);

                    System.out.printf("A matrícula de %s foi trancada.\n", aluno.getNome());
                    break;

                case 7:
                    System.out.println("Destrancar matrícula de aluno");
                    System.out.println("Digite o cpf do aluno a ser alterado");
                    aluno = getAluno(in.nextLine());
                    aluno.setStatus(1);
                    alunoDao.update(aluno);
                    System.out.printf("A matrícula de %s foi destrancada.\n", aluno.getNome());
                    break;

                case 8:
                    System.out.println("Digite o cpf do aluno a ser convidado para o exame");
                    aluno = getAluno(in.nextLine());
                    System.out.printf("Email com convite enviado à %s\n", aluno.getNome());
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
                        System.out.println("Insira um valor válido.");
                    }
                    pagamento.setValor(valor);
                    pagamento.setData(new DataFormatada(LocalDate.now()));

                    System.out.println("Digite o cpf do aluno que efetuou o pagamento:");
                    aluno = getAluno(in.nextLine());
                    pagamento.setIdAluno(aluno.getId());
                    aluno.pagarMensalidade(pagamento);
                    System.out.println("Pagamento efetuado com sucesso.");
                    break;

                case 10:
                    System.out.println("Digite o cpf do aluno a ser gerado relatório");
                    aluno = getAluno(in.nextLine());


                    for(Pagamento p : aluno.getRegistroPagamentos().getPagamentos()){
                        System.out.println(p.toString());
                    }
                    break;

                case 11:
                    System.out.println("Digite o cpf do aluno a ser gerado certificado");
                    aluno = getAluno(in.nextLine());
                    aluno.adicionarCertificado(new Certificado(aluno));
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
