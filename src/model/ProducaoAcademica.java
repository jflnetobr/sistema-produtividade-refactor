package src.model;

import src.model.enums.TipoProducao;

public class ProducaoAcademica {
  private int id;
  private String titulo;
  private int anoPublicacao;
  private TipoProducao tipo;

  public ProducaoAcademica(int id, String titulo, int anoPublicacao, TipoProducao tipo) {
    this.id = id;
    this.titulo = titulo;
    this.anoPublicacao = anoPublicacao;
    this.tipo = tipo;
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public int getAnoPublicacao() {
    return anoPublicacao;
  }

  public TipoProducao getTipo() {
    return tipo;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setAnoPublicacao(int anoPublicacao) {
    this.anoPublicacao = anoPublicacao;
  }

  public void setTipo(TipoProducao tipo) {
    this.tipo = tipo;
  }
}
