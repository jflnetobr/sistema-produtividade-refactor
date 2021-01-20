package src.view;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import src.model.*;
import src.util.*;
import src.model.enums.TipoColaborador;
import src.model.enums.TipoProducao;
import src.view.reports.ProducaoAcademicaReport;
import src.view.reports.Relatorio;

public class ProducaoAcademicaView {
  public static void menuProducoesAcademicas(Laboratorio lab, Scanner scanner) {
    Util.clrscr();

    loop: while (true) {
      System.out.println("Menu principal > Gerenciamento de producoes academicas");

      System.out.println();

      System.out.println("Escolha uma opcao para continuar:");

      System.out.println();

      System.out.println(" [ 1 ] Cadastrar producao academica");
      System.out.println(" [ 2 ] Emitir relatorio de uma producao academica");
      System.out.println(" [ 3 ] Listar producoes academicas do laboratorio");
      System.out.println(" [ 4 ] Voltar para o menu principal");

      System.out.println();

      System.out.print(" - Digite sua opcao: ");
      String op = scanner.nextLine();

      switch (op) {
        case "1":
          Util.clrscr();

          System.out.println("Escolha uma opcao para continuar:");

          System.out.println();

          System.out.println(" [ 1 ] Cadastrar publicacao");
          System.out.println(" [ 2 ] Cadastrar orientacao");

          System.out.println();

          System.out.print(" - Digite sua opcao: ");
          String op2 = scanner.nextLine();

          String titulo, anoPublicacao;

          Util.clrscr();

          try {
            switch (op2) {
              case "1":
                System.out.println("Para cadastrar uma nova publicacao, informe os dados pedidos:");

                System.out.println();

                System.out.print(" - Titulo: ");
                titulo = scanner.nextLine();
                System.out.print(" - Ano de publicacao (apenas numeros): ");
                anoPublicacao = scanner.nextLine();
                System.out.print(" - Nome da conferencia onde foi publicada: ");
                String nomeConferencia = scanner.nextLine();
                System.out.print(" - ID do projeto de pesquisa associado (se nao houver, digite 0): ");
                String idProjeto = scanner.nextLine();

                lab.criarPublicacao(titulo, Integer.parseInt(anoPublicacao), nomeConferencia);

                if (idProjeto.matches("-?\\d+")) {
                  if (Integer.parseInt(idProjeto) <= lab.getProjetos().size() && Integer.parseInt(idProjeto) > 0) {
                    Publicacao p = (Publicacao) lab.getProducaoAcademica(lab.getProducoesAcademicas().size());
                    lab.getProjeto(Integer.parseInt(idProjeto)).associaPublicacao(p);
                    System.out.println();
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine();
                  } else {
                    System.out.println("O ID informado nao pertence a nenhum projeto");
                    System.out.println();
                  }
                } else {
                  System.out.println("ID de projeto invalido! Tente novamente");
                  System.out.println();
                }

                menuAdicionarAutores(lab, scanner,
                    (Publicacao) lab.getProducaoAcademica(lab.getProducoesAcademicas().size()));
                break;
              case "2":
                if (lab.getColaboradores().stream().filter(colaborador -> colaborador.getTipo() == TipoColaborador.Prof)
                    .count() > 0) {
                  System.out.println("Para cadastrar uma nova orientacao, informe os dados pedidos:");

                  System.out.println();

                  System.out.print(" - Titulo: ");
                  titulo = scanner.nextLine();
                  System.out.print(" - Ano de publicacao (apenas numeros): ");
                  anoPublicacao = scanner.nextLine();
                  System.out.print(" - ID do orientardor (deve ser um professor, informe um numero): ");
                  String idOrientador = scanner.nextLine();

                  lab.criarOrientacao(titulo, Integer.parseInt(anoPublicacao), Integer.parseInt(idOrientador));

                  lab.getColaborador(Integer.parseInt(idOrientador))
                      .adicionaProducaoAcademica(lab.getProducaoAcademica(lab.getProducoesAcademicas().size()));
                  menuAdicionarOrientados(lab, scanner,
                      (Orientacao) lab.getProducaoAcademica(lab.getProducoesAcademicas().size()));
                } else {
                  throw new IntercurrenceException(
                      "Ainda nao existem professores cadastrados. Cadastre um professor para continuar");
                }

                break;
              default:
                throw new IntercurrenceException("Opcao invalida! Tente novamente");
            }

            Util.clrscr();
            System.out.println("Producao academica criada com sucesso!");
            System.out.println();
          } catch (IntercurrenceException e) {
            System.out.println(e.getMessage());
            System.out.println();
          }
          break;
        case "2":
          Util.clrscr();
          if (lab.getProducoesAcademicas().size() > 0) {
            System.out.print(" - Digite o ID da producao academica que deseja emitir o relatorio: ");
            String op3 = scanner.nextLine();

            if (op3.matches("-?\\d+")) {
              if (Integer.parseInt(op3) <= lab.getProducoesAcademicas().size() && Integer.parseInt(op3) > 0) {
                Util.clrscr();
                relatorio(lab.getProducaoAcademica(Integer.parseInt(op3)));
                System.out.println();
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine();
                Util.clrscr();
              } else {
                Util.clrscr();
                System.out.println("O ID informado nao pertence a nenhuma producao academica");
                System.out.println();
              }
            } else {
              System.out.println("ID invalido! Tente novamente");
              System.out.println();
            }
          } else {
            System.out.println("Nao existem producoes academicas cadastradas");
            System.out.println();
          }
          break;
        case "3":
          LaboratorioView.executaRelatorio(new Relatorio(new ProducaoAcademicaReport(lab)), scanner);
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

  public static void menuAdicionarAutores(Laboratorio lab, Scanner scanner, Publicacao publicacao) {
    try {
      if (lab.getColaboradores().size() > 0) {
        loopAutores: while (true) {
          System.out.println();

          System.out.print(" - Digite o ID de um autor (colaborador) para associar a publicacao ou P para encerrar: ");
          String op = scanner.nextLine();

          switch (op) {
            case "P":
              Util.clrscr();
              break loopAutores;
            default:
              if (op.matches("-?\\d+")) {
                if (Integer.parseInt(op) <= lab.getColaboradores().size() && Integer.parseInt(op) > 0) {
                  publicacao.adicionaAutor(lab.getColaborador(Integer.parseInt(op)));
                } else {
                  throw new IntercurrenceException("O ID informado nao pertence a nenhum colaborador");
                }
                System.out.println();
                System.out.println("Autor associado com sucesso!");
              } else {
                throw new IntercurrenceException("Opcao invalida! Tente novamente");
              }
          }
        }
      } else {
        Util.clrscr();
        System.out.println(
            "Nao existem colaboradores cadastrados. Cadastre colaboradores antes de associar a esta publicacao.");
        System.out.println();
      }
    } catch (IntercurrenceException e) {
      System.out.println(e.getMessage());
      System.out.println();
    }
  }

  public static void menuAdicionarOrientados(Laboratorio lab, Scanner scanner, Orientacao orientacao) {
    if (lab.getColaboradores().size() > 0) {
      loopOrientados: while (true) {
        System.out.println();

        System.out.print(
            " - Digite o ID de um orientado (que nao seja professor) para associar a orientacao ou P para encerrar: ");
        String op = scanner.nextLine();

        try {
          switch (op) {
            case "P":
              Util.clrscr();
              break loopOrientados;
            default:
              if (op.matches("-?\\d+")) {
                if (Integer.parseInt(op) <= lab.getColaboradores().size() && Integer.parseInt(op) > 0) {
                  orientacao.adicionaOrientado(lab.getColaborador(Integer.parseInt(op)));
                } else {
                  throw new IntercurrenceException("O ID informado nao pertence a nenhum colaborador");
                }
                System.out.println();
                System.out.println("Orientado associado com sucesso!");
              } else {
                throw new IntercurrenceException("Opcao invalida! Tente novamente");
              }
          }
        } catch (IntercurrenceException e) {
          System.out.println(e.getMessage());
          System.out.println();
        }
      }
    } else {
      Util.clrscr();
      System.out.println(
          "Nao existem colaboradores cadastrados. Cadastre colaboradores antes de associar a esta orientacao.");
      System.out.println();
    }
  }

  public static void relatorio(ProducaoAcademica producaoAcademica) {
    System.out.println("Mostrando dados da producao academica de ID " + producaoAcademica.getId() + ":");

    System.out.println();

    System.out.println(" * Tipo: " + producaoAcademica.getTipo().getDescricao());

    System.out.println();

    if (producaoAcademica.getTipo() == TipoProducao.P) {
      Publicacao p = (Publicacao) producaoAcademica;
      System.out.println(" - Titulo: " + p.getTitulo());
      System.out.println(" - Ano de publicacao: " + p.getAnoPublicacao());
      System.out.println(" - Nome da conferencia: " + p.getNomeConferencia());

      System.out.println();

      if (p.getProjeto() != null) {
        System.out.println(" * Projeto: " + p.getProjeto().getTitulo());
      } else {
        System.out.println(" * A publicacao nao foi associada a nenhum projeto");
      }

      System.out.println();

      if (p.getAutores().size() > 0) {
        System.out.println("Autores:");

        System.out.println();

        System.out.println("Tipo         |  Nome");
        System.out.println("--------------------");

        p.getAutores().stream().sorted(Comparator.comparing(Colaborador::getNome)).collect(Collectors.toList())
            .forEach(colaborador -> System.out
                .println(String.format("%-13s", colaborador.getTipo().getDescricao()) + "|  " + colaborador.getNome()));
      } else {
        System.out.println(" * Os autores ainda nao foram cadastrados");
      }
    } else {
      Orientacao o = (Orientacao) producaoAcademica;
      System.out.println(" - Titulo: " + o.getTitulo());
      System.out.println(" - Ano de publicacao: " + o.getAnoPublicacao());

      System.out.println();

      System.out.println(" * Orientador: " + o.getOrientador().getNome());

      System.out.println();

      if (o.getOrientados().size() > 0) {
        System.out.println("Orientados:");

        System.out.println();

        System.out.println("Tipo         |  Nome");
        System.out.println("--------------------");

        o.getOrientados().stream().sorted(Comparator.comparing(Colaborador::getNome)).collect(Collectors.toList())
            .forEach(colaborador -> System.out
                .println(String.format("%-13s", colaborador.getTipo().getDescricao()) + "|  " + colaborador.getNome()));
      } else {
        System.out.println(" * Os orientados ainda nao foram cadastrados");
      }
    }
  }
}
