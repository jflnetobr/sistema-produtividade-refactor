package src.model.enums;

public enum TipoColaborador {
  G("Graduando"), M("Mestrando"), D("Doutorando"), Prof("Professor"), Pesq("Pesquisador");

  private final String descricao;

  TipoColaborador(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
