package src.model.enums;

public enum Status {
  E("Em Elaboracao"), A("Em Andamento"), C("Concluido");

  private final String descricao;

  Status(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
