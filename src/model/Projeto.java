package src.model;

import java.util.ArrayList;
import java.util.Date;

import src.model.states.*;
import src.util.Util;

/*
* Nesta classe implementam-se os padrões:
* - Chain constructors
* - State
*/

public class Projeto {
  State elaboracao;
  State andamento;
  State concluido;

  private int id;
  private String titulo;
  private Date dataInicio;
  private Date dataTermino;
  private String agenciaFinanciadora;
  private float valorFinanciado;
  private String objetivo;
  private String descricao;
  private State status;
  private boolean completo;
  private ArrayList<Colaborador> colaboradores = new ArrayList<Colaborador>();
  private ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();

  public Projeto(int id, String titulo, Colaborador responsavel) {
    this(id, titulo, null, null, "", 0, "", "", responsavel);
  }

  public Projeto(int id, String titulo, Date dataInicio, Date dataTermino, String agenciaFinanciadora,
      float valorFinanciado, String objetivo, String descricao, Colaborador responsavel) {
    elaboracao = new Elaboracao(this);
    andamento = new Andamento(this);
    concluido = new Concluido(this);

    this.id = id;
    this.titulo = titulo;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.agenciaFinanciadora = agenciaFinanciadora;
    this.valorFinanciado = valorFinanciado;
    this.objetivo = objetivo;
    this.descricao = descricao;
    this.status = elaboracao;
    this.completo = dataInicio != null ? true : false;
    colaboradores.add(responsavel);
  }

  public State getElaboracao() {
    return elaboracao;
  }

  public State getAndamento() {
    return andamento;
  }

  public State getConcluido() {
    return concluido;
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public Date getDataInicio() {
    return dataInicio;
  }

  public Date getDataTermino() {
    return dataTermino;
  }

  public String getAgenciaFinanciadora() {
    return agenciaFinanciadora;
  }

  public float getValorFinanciado() {
    return valorFinanciado;
  }

  public String getObjetivo() {
    return objetivo;
  }

  public String getDescricao() {
    return descricao;
  }

  public State getStatus() {
    return status;
  }

  public boolean isCompleto() {
    return completo;
  }

  public ArrayList<Colaborador> getColaboradores() {
    return colaboradores;
  }

  public ArrayList<Publicacao> getPublicacoes() {
    return publicacoes;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDataInicio(Date dataInicio) {
    this.dataInicio = dataInicio;
  }

  public void setDataTermino(Date dataTermino) {
    this.dataTermino = dataTermino;
  }

  public void setAgenciaFinanciadora(String agenciaFinanciadora) {
    this.agenciaFinanciadora = agenciaFinanciadora;
  }

  public void setValorFinanciado(float valorFinanciado) {
    this.valorFinanciado = valorFinanciado;
  }

  public void setObjetivo(String objetivo) {
    this.objetivo = objetivo;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setStatus(State status) {
    this.status = status;
  }

  public void setCompleto(boolean completo) {
    this.completo = completo;
  }

  public void setColaboradores(ArrayList<Colaborador> colaboradores) {
    this.colaboradores = colaboradores;
  }

  public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
    this.publicacoes = publicacoes;
  }

  public void adicionaColaborador(Colaborador colaborador) {
    this.colaboradores.add(colaborador);
  }

  public void adicionaPublicacao(Publicacao publicacao) {
    this.publicacoes.add(publicacao);
  }

  public String complementarDados(String dataInicio, String dataTermino, String agenciaFinanciadora,
      float valorFinanciado, String objetivo, String descricao) {
    if (!this.isCompleto()) {
      try {
        Date dI = Util.parseDate(dataInicio);
        Date dT = Util.parseDate(dataTermino);

        this.setDataInicio(dI);
        this.setDataTermino(dT);
        this.setAgenciaFinanciadora(agenciaFinanciadora);
        this.setValorFinanciado(valorFinanciado);
        this.setObjetivo(objetivo);
        this.setDescricao(descricao);

        this.setCompleto(true);

        return "";
      } catch (Exception e) {
        return "Nao foi possivel completar as informacoes. Voce informou uma data invalida!";
      }
    }
    return "O Projeto ja tem as informacoes basicas";
  }

  public String avancaStatus() {
    return status.avancaStatus();
  }

  public String alocaParticipante(Colaborador participante) {
    return status.alocaParticipante(participante);
  }

  public String associaPublicacao(Publicacao publicacao) {
    return status.associaPublicacao(publicacao);
  }
}
