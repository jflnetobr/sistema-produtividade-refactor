package src.view;

import java.util.Scanner;

import src.model.*;
import src.util.Util;
import src.view.reports.*;

/*
* Nesta classe implementam-se os padrões:
* - Extract Method
* - Strategy
*/

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
          executaRelatorio(new Relatorio(new LaboratorioReport(lab)), scanner);
          break;
        case "2":
          executaRelatorio(new Relatorio(new ColaboradorReport(lab)), scanner);
          break;
        case "3":
          executaRelatorio(new Relatorio(new ProjetoReport(lab)), scanner);
          break;
        case "4":
          executaRelatorio(new Relatorio(new ProducaoAcademicaReport(lab)), scanner);
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

  public static void executaRelatorio(Relatorio relatorio, Scanner scanner) {
    Util.clrscr();
    relatorio.execute();
    System.out.println();
    System.out.println("Pressione ENTER para continuar...");
    scanner.nextLine();
    Util.clrscr();
  }
}
