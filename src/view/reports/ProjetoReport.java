package src.view.reports;

import src.model.Laboratorio;

public class ProjetoReport implements RelatorioStrategy {
  private Laboratorio laboratorio;

  public ProjetoReport(Laboratorio laboratorio) {
    this.laboratorio = laboratorio;
  }

  public void relatorio() {
    System.out.println("O laboratorio " + laboratorio.getNome() + " possui atualmente "
        + String.format("%02d", laboratorio.getProjetos().size()) + " projetos, listados abaixo:");

    System.out.println();

    System.out.print("[ " + String.format("%02d",
        laboratorio.getProjetos().stream().filter(projeto -> projeto.getStatus() == projeto.getElaboracao()).count())
        + " ] Em Elaboracao ");

    System.out.print("[ "
        + String.format("%02d",
            laboratorio.getProjetos().stream().filter(projeto -> projeto.getStatus() == projeto.getAndamento()).count())
        + " ] Em Andamento ");

    System.out.print("[ "
        + String.format("%02d",
            laboratorio.getProjetos().stream().filter(projeto -> projeto.getStatus() == projeto.getConcluido()).count())
        + " ] Concluido ");

    System.out.println();
    System.out.println();
    System.out.println("  ID  |     Status      |  Titulo");
    System.out.println("--------------------------------------");

    laboratorio.getProjetos().forEach(projeto -> System.out.println("  " + String.format("%02d", projeto.getId())
        + "  |  " + String.format("%-15s", projeto.getStatus().getDescricao()) + "|  " + projeto.getTitulo()));
  }
}
