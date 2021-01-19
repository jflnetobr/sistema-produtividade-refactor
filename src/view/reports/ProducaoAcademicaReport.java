package src.view.reports;

import src.model.Laboratorio;
import src.model.enums.TipoProducao;

public class ProducaoAcademicaReport implements RelatorioStrategy {
  private Laboratorio laboratorio;

  public ProducaoAcademicaReport(Laboratorio laboratorio) {
    this.laboratorio = laboratorio;
  }

  public void relatorio() {
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
