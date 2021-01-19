package src.view.reports;

import src.model.Laboratorio;
import src.model.enums.TipoColaborador;

public class ColaboradorReport implements RelatorioStrategy {
  private Laboratorio laboratorio;

  public ColaboradorReport(Laboratorio laboratorio) {
    this.laboratorio = laboratorio;
  }

  public void relatorio() {
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

}
