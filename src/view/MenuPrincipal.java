package src.view;

import java.util.Scanner;

import src.model.*;
import src.util.Util;

public class MenuPrincipal {
  static Scanner scanner = new Scanner(System.in);

  public static Laboratorio primeiroAcesso() {
    Util.clrscr();

    System.out.println("Ola, seja bem vindo ao sistema de Gestao de Produtividade Academica");
    System.out.println("Para iniciar a utilizar o sistema, informe os dados pedidos:");

    System.out.println();

    System.out.print(" - Nome do laboratorio: ");
    String nome = scanner.nextLine();
    System.out.print(" - Instituicao: ");
    String instituicao = scanner.nextLine();
    System.out.print(" - Nome do administrador: ");
    String nomeAdm = scanner.nextLine();

    return new Laboratorio(nome, instituicao, nomeAdm);
  }

  public static void menuPrincipal() {
    Laboratorio lab = primeiroAcesso();

    Util.clrscr();

    loop: while (true) {
      System.out.println("Laboratorio: " + lab.getNome() + " / Instituicao: " + lab.getInstituicao());
      System.out.println("Ola " + lab.getNomeAdm() + ", escolha uma opcao para continuar:");

      System.out.println();

      System.out.println(" [ 1 ] Gerenciar projetos");
      System.out.println(" [ 2 ] Gerenciar colaboradores");
      System.out.println(" [ 3 ] Gerenciar producao academica");
      System.out.println(" [ 4 ] Emitir relatorios");
      System.out.println(" [ 5 ] Sair");

      System.out.println();

      System.out.print(" - Digite sua opcao: ");
      String op = scanner.nextLine();

      switch (op) {
        case "1":
          ProjetoView.menuProjetos(lab, scanner);
          Util.clrscr();
          break;
        case "2":
          ColaboradorView.menuColaboradores(lab, scanner);
          Util.clrscr();
          break;
        case "3":
          ProducaoAcademicaView.menuProducoesAcademicas(lab, scanner);
          Util.clrscr();
          break;
        case "4":
          LaboratorioView.menuRelatorios(lab, scanner);
          Util.clrscr();
          break;
        case "5":
          System.out.println();
          System.out.println("Obrigado por usar o nosso sistema!");
          scanner.close();
          break loop;
        default:
          Util.clrscr();
          System.out.println("Opcao invalida! Tente novamente");
          System.out.println();
      }
    }
  }
}
