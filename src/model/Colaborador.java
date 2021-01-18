package src.model;

import java.util.ArrayList;

import src.model.enums.TipoColaborador;

public class Colaborador {
  private int id;
  private String nome;
  private String email;
  private TipoColaborador tipo;
  private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
  private ArrayList<ProducaoAcademica> producoesAcademicas = new ArrayList<ProducaoAcademica>();

  public Colaborador(int id, String nome, String email, TipoColaborador tipo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.tipo = tipo;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public TipoColaborador getTipo() {
    return tipo;
  }

  public ArrayList<Projeto> getProjetos() {
    return projetos;
  }

  public ArrayList<ProducaoAcademica> getProducoesAcademicas() {
    return producoesAcademicas;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setTipo(TipoColaborador tipo) {
    this.tipo = tipo;
  }

  public void setProjetos(ArrayList<Projeto> projetos) {
    this.projetos = projetos;
  }

  public void setProducoesAcademicas(ArrayList<ProducaoAcademica> producoesAcademicas) {
    this.producoesAcademicas = producoesAcademicas;
  }

  public void adicionaProjeto(Projeto projeto) {
    this.projetos.add(projeto);
  }

  public void adicionaProducaoAcademica(ProducaoAcademica producaoAcademica) {
    this.producoesAcademicas.add(producaoAcademica);
  }
}
