package src.view.reports;

import src.model.Laboratorio;
import src.model.enums.*;

public class LaboratorioReport implements RelatorioStrategy {
  private Laboratorio laboratorio;

  public LaboratorioReport(Laboratorio laboratorio) {
    this.laboratorio = laboratorio;
  }

  public void relatorio() {
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
}
