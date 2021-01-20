package src.view;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import src.model.*;
import src.util.*;
import src.view.reports.ColaboradorReport;
import src.view.reports.Relatorio;

public class ColaboradorView {
  public static void menuColaboradores(Laboratorio lab, Scanner scanner) {
    Util.clrscr();

    loop: while (true) {
      System.out.println("Menu principal > Gerenciamento de colaboradores");

      System.out.println();

      System.out.println("Escolha uma opcao para continuar:");

      System.out.println();

      System.out.println(" [ 1 ] Cadastrar colaborador");
      System.out.println(" [ 2 ] Emitir relatorio de um colaborador");
      System.out.println(" [ 3 ] Listar colaboradores do laboratorio");
      System.out.println(" [ 4 ] Voltar para o menu principal");

      System.out.println();

      System.out.print(" - Digite sua opcao: ");
      String op = scanner.nextLine();

      switch (op) {
        case "1":
          try {
            Util.clrscr();
            System.out.println("Para cadastrar um novo colaborador, informe os dados pedidos:");

            System.out.println();

            System.out.print(" - Nome: ");
            String nome = scanner.nextLine();
            System.out.print(" - Email: ");
            String email = scanner.nextLine();
            System.out
                .print(" - Tipo (1 = Graduando / 2 = Mestrando / 3 = Doutorando / 4 = Professor / 5 = Pesquisador): ");
            String tipo = scanner.nextLine();

            lab.criarColaborador(nome, email, Integer.parseInt(tipo));

            Util.clrscr();
            System.out.println("Colaborador criado com sucesso!");
            System.out.println();
          } catch (IntercurrenceException e) {
            System.out.println(e.getMessage());
            System.out.println();
          }
          break;
        case "2":
          Util.clrscr();
          if (lab.getColaboradores().size() > 0) {
            System.out.print(" - Digite o ID do colaborador que deseja emitir o relatorio: ");
            String op2 = scanner.nextLine();

            if (op2.matches("-?\\d+")) {
              if (Integer.parseInt(op2) <= lab.getColaboradores().size() && Integer.parseInt(op2) > 0) {
                Util.clrscr();
                relatorio(lab.getColaborador(Integer.parseInt(op2)));
                System.out.println();
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine();
                Util.clrscr();
              } else {
                Util.clrscr();
                System.out.println("O ID informado nao pertence a nenhum colaborador");
                System.out.println();
              }
            } else {
              System.out.println("ID invalido! Tente novamente");
              System.out.println();
            }
          } else {
            System.out.println("Nao existem colaboradores cadastrados");
            System.out.println();
          }
          break;
        case "3":
          LaboratorioView.executaRelatorio(new Relatorio(new ColaboradorReport(lab)), scanner);
          break;
        case "4":
          break loop;
        default:
          Util.clrscr();
          System.out.println("Opcao invalida! Tente novamente");
          System.out.println();
      }
    }

  }

  public static void relatorio(Colaborador colaborador) {
    System.out.println("Mostrando dados do colaborador de ID " + colaborador.getId() + ":");

    System.out.println();

    System.out.println(" - Nome: " + colaborador.getNome());
    System.out.println(" - Email: " + colaborador.getEmail());
    System.out.println(" - Tipo: " + colaborador.getTipo().getDescricao());

    System.out.println();

    if (colaborador.getProjetos().size() > 0) {
      System.out.println("Historico de Projetos:");

      System.out.println();

      System.out.println("Status         |  Data de termino  |  Titulo");
      System.out.println("--------------------------------------------");

      colaborador.getProjetos().stream().filter(projeto -> projeto.getDataTermino() == null)
          .forEach(projeto -> System.out.println(String.format("%-15s", projeto.getStatus().getDescricao())
              + "|    Desconhecido   |  " + projeto.getTitulo()));

      colaborador.getProjetos().stream().filter(projeto -> projeto.getDataTermino() != null)
          .sorted(Comparator.comparing(Projeto::getDataTermino).reversed()).collect(Collectors.toList())
          .forEach(projeto -> System.out.println(String.format("%-15s", projeto.getStatus().getDescricao()) + "|     "
              + Util.formatDate(projeto.getDataTermino()) + "    |  " + projeto.getTitulo()));
    } else {
      System.out.println("O colaborador nao foi alocado em nenhum projeto ainda.");
    }

    System.out.println();

    if (colaborador.getProducoesAcademicas().size() > 0) {
      System.out.println("Historico de Producao Academica:");

      System.out.println();

      System.out.println("Tipo        |  Ano  |  Titulo");
      System.out.println("--------------------------------------------");

      colaborador.getProducoesAcademicas().stream()
          .sorted(Comparator.comparing(ProducaoAcademica::getAnoPublicacao).reversed()).collect(Collectors.toList())
          .forEach(producaoAcademica -> System.out.println(producaoAcademica.getTipo().getDescricao() + "  |  "
              + producaoAcademica.getAnoPublicacao() + " |  " + producaoAcademica.getTitulo()));
    } else {
      System.out.println("O colaborador nao possui producao academica ainda.");
    }
  }
}
