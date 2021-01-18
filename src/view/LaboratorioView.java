package src.view;

import java.util.Scanner;

import src.model.*;
import src.model.enums.*;
import src.util.Util;

public class LaboratorioView {
  public static void menuRelatorios(Laboratorio lab, Scanner scanner) {
    Util.clrscr();

    loop: while (true) {
      System.out.println("Menu principal > Emissao de relatorios");

      System.out.println();

      System.out.println("Escolha uma opcao para continuar:");

      System.out.println();

      System.out.println(" [ 1 ] Relatorio de Producao Academica do laboratorio");
      System.out.println(" [ 2 ] Relatorio de Colaboradores");
      System.out.println(" [ 3 ] Relatorio de Projetos");
      System.out.println(" [ 4 ] Relatorio de Producoes Academicas");
      System.out.println(" [ 5 ] Voltar para o menu principal");

      System.out.println();

      System.out.print(" - Digite sua opcao: ");
      String op = scanner.nextLine();

      switch (op) {
        case "1":
          Util.clrscr();
          relatorio(lab);
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "2":
          Util.clrscr();
          relatorioColaboradores(lab);
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "3":
          Util.clrscr();
          relatorioProjetos(lab);
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "4":
          Util.clrscr();
          relatorioProducaoAcademica(lab);
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "5":
          break loop;
        default:
          Util.clrscr();
          System.out.println("Opcao invalida! Tente novamente");
          System.out.println();
      }
    }

  }

  public static void relatorio(Laboratorio laboratorio) {
    System.out.println("O laboratorio " + laboratorio.getNome() + " da instituicao " + laboratorio.getInstituicao()
        + " tem atualmente os seguintes numeros:");

    System.out.println();

    System.out.println(" - " + String.format("%02d", laboratorio.getColaboradores().size()) + " Colaboradores, sendo:");
    for (TipoColaborador tipo : TipoColaborador.values()) {
      System.out.print("[ "
          + String.format("%02d",
              laboratorio.getColaboradores().stream().filter(colaborador -> colaborador.getTipo() == tipo).count())
          + " ] " + tipo.getDescricao() + "  ");
    }

    System.out.println();
    System.out.println();

    System.out.println(" - " + String.format("%02d", laboratorio.getProjetos().size()) + " Projetos, sendo:");
    for (Status status : Status.values()) {
      System.out.print("[ "
          + String.format("%02d",
              laboratorio.getProjetos().stream().filter(projeto -> projeto.getStatus() == status).count())
          + " ] " + status.getDescricao() + "  ");
    }

    System.out.println();
    System.out.println();

    System.out.println(
        " - " + String.format("%02d", laboratorio.getProducoesAcademicas().size()) + " Producoes Academicas, sendo:");
    for (TipoProducao tipo : TipoProducao.values()) {
      System.out.print("[ "
          + String.format("%02d",
              laboratorio.getProducoesAcademicas().stream()
                  .filter(producaoAcademica -> producaoAcademica.getTipo() == tipo).count())
          + " ] " + tipo.getDescricao() + "  ");
    }

    System.out.println();
  }

  public static void relatorioColaboradores(Laboratorio laboratorio) {
    System.out.println("O laboratorio " + laboratorio.getNome() + " possui atualmente "
        + String.format("%02d", laboratorio.getColaboradores().size()) + " colaboradores, listados abaixo:");

    System.out.println();

    for (TipoColaborador tipo : TipoColaborador.values()) {
      System.out.print("[ "
          + String.format("%02d",
              laboratorio.getColaboradores().stream().filter(colaborador -> colaborador.getTipo() == tipo).count())
          + " ] " + tipo.getDescricao() + "  ");
    }

    System.out.println();
    System.out.println();
    System.out.println("  ID  |     Tipo      |  Nome");
    System.out.println("--------------------------------------");

    laboratorio.getColaboradores()
        .forEach(colaborador -> System.out.println("  " + String.format("%02d", colaborador.getId()) + "  |  "
            + String.format("%-13s", colaborador.getTipo().getDescricao()) + "|  " + colaborador.getNome()));
  }

  public static void relatorioProjetos(Laboratorio laboratorio) {
    System.out.println("O laboratorio " + laboratorio.getNome() + " possui atualmente "
        + String.format("%02d", laboratorio.getProjetos().size()) + " projetos, listados abaixo:");

    System.out.println();

    for (Status status : Status.values()) {
      System.out.print("[ "
          + String.format("%02d",
              laboratorio.getProjetos().stream().filter(projeto -> projeto.getStatus() == status).count())
          + " ] " + status.getDescricao() + "  ");
    }

    System.out.println();
    System.out.println();
    System.out.println("  ID  |     Status      |  Titulo");
    System.out.println("--------------------------------------");

    laboratorio.getProjetos().forEach(projeto -> System.out.println("  " + String.format("%02d", projeto.getId())
        + "  |  " + String.format("%-15s", projeto.getStatus().getDescricao()) + "|  " + projeto.getTitulo()));
  }

  public static void relatorioProducaoAcademica(Laboratorio laboratorio) {
    System.out.println("O laboratorio " + laboratorio.getNome() + " possui atualmente "
        + String.format("%02d", laboratorio.getProducoesAcademicas().size())
        + " producoes academicas, listadas abaixo:");

    System.out.println();

    for (TipoProducao tipo : TipoProducao.values()) {
      System.out.print("[ "
          + String.format("%02d",
              laboratorio.getProducoesAcademicas().stream()
                  .filter(producaoAcademica -> producaoAcademica.getTipo() == tipo).count())
          + " ] " + tipo.getDescricao() + "  ");
    }

    System.out.println();
    System.out.println();
    System.out.println("  ID  |     Tipo     |  Ano   |  Titulo");
    System.out.println("---------------------------------------");

    laboratorio.getProducoesAcademicas()
        .forEach(producaoAcademica -> System.out.println("  " + String.format("%02d", producaoAcademica.getId())
            + "  |  " + String.format("%-12s", producaoAcademica.getTipo().getDescricao()) + "|  "
            + producaoAcademica.getAnoPublicacao() + "  |  " + producaoAcademica.getTitulo()));
  }
}
