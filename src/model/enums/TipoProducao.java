package src.model.enums;

public enum TipoProducao {
  P("Publicacao"), O("Orientacao");

  private final String descricao;

  TipoProducao(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
