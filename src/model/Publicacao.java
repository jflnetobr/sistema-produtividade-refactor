package src.model;

import java.util.ArrayList;

import src.model.enums.TipoProducao;
import src.util.IntercurrenceException;

public class Publicacao extends ProducaoAcademica {
  private String nomeConferencia;
  private Projeto projeto;
  private ArrayList<Colaborador> autores = new ArrayList<Colaborador>();

  public Publicacao(int id, String titulo, int anoPublicacao, String nomeConferencia) {
    super(id, titulo, anoPublicacao, TipoProducao.P);
    this.nomeConferencia = nomeConferencia;
  }

  public String getNomeConferencia() {
    return nomeConferencia;
  }

  public Projeto getProjeto() {
    return projeto;
  }

  public ArrayList<Colaborador> getAutores() {
    return autores;
  }

  public void setNomeConferencia(String nomeConferencia) {
    this.nomeConferencia = nomeConferencia;
  }

  public void setProjeto(Projeto projeto) {
    this.projeto = projeto;
  }

  public void setAutores(ArrayList<Colaborador> autores) {
    this.autores = autores;
  }

  public void adicionaAutor(Colaborador autor) throws IntercurrenceException {
    if (!autores.contains(autor)) {
      autores.add(autor);
      autor.adicionaProducaoAcademica(this);
    } else {
      throw new IntercurrenceException("O autor informado ja esta na publicacao");
    }
  }
}
