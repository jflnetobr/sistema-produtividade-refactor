package src.view.reports;

public class Relatorio {
  private RelatorioStrategy relatorioStrategy;

  public Relatorio(RelatorioStrategy relatorioStrategy) {
    this.relatorioStrategy = relatorioStrategy;
  }

  public void execute() {
    relatorioStrategy.relatorio();
  }
}
