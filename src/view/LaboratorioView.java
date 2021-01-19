package src.view;

import java.util.Scanner;

import src.model.*;
import src.util.Util;
import src.view.reports.*;

public class LaboratorioView {
  public static void menuRelatorios(Laboratorio lab, Scanner scanner) {
    Relatorio relatorio;

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
          relatorio = new Relatorio(new LaboratorioReport(lab));
          relatorio.execute();
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "2":
          Util.clrscr();
          relatorio = new Relatorio(new ColaboradorReport(lab));
          relatorio.execute();
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "3":
          Util.clrscr();
          relatorio = new Relatorio(new ProjetoReport(lab));
          relatorio.execute();
          System.out.println();
          System.out.println("Pressione ENTER para continuar...");
          scanner.nextLine();
          Util.clrscr();
          break;
        case "4":
          Util.clrscr();
          relatorio = new Relatorio(new ProducaoAcademicaReport(lab));
          relatorio.execute();
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

}
