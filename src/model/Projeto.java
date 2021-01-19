package src.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import src.model.enums.*;

/*
* Nesta classe implementa-se o padrão Chain constructors
*/

public class Projeto {
  private int id;
  private String titulo;
  private Date dataInicio;
  private Date dataTermino;
  private String agenciaFinanciadora;
  private float valorFinanciado;
  private String objetivo;
  private String descricao;
  private Status status;
  private boolean completo;
  private ArrayList<Colaborador> colaboradores = new ArrayList<Colaborador>();
  private ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();

  public Projeto(int id, String titulo, Colaborador responsavel) {
    this(id, titulo, null, null, "", 0, "", "", responsavel);
  }

  public Projeto(int id, String titulo, Date dataInicio, Date dataTermino, String agenciaFinanciadora,
      float valorFinanciado, String objetivo, String descricao, Colaborador responsavel) {
    this.id = id;
    this.titulo = titulo;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.agenciaFinanciadora = agenciaFinanciadora;
    this.valorFinanciado = valorFinanciado;
    this.objetivo = objetivo;
    this.descricao = descricao;
    this.status = Status.E;
    this.completo = dataInicio != null ? true : false;
    colaboradores.add(responsavel);
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

  public Status getStatus() {
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

  public void setStatus(Status status) {
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

  public String complementarDados(String dataInicio, String dataTermino, String agenciaFinanciadora,
      float valorFinanciado, String objetivo, String descricao) {
    if (!this.isCompleto()) {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      sdf.setLenient(false);
      Date dI, dT;

      try {
        dI = sdf.parse(dataInicio);
        dT = sdf.parse(dataTermino);
      } catch (Exception e) {
        return "Uma das datas informadas nao e valida";
      }

      this.setDataInicio(dI);
      this.setDataTermino(dT);
      this.setAgenciaFinanciadora(agenciaFinanciadora);
      this.setValorFinanciado(valorFinanciado);
      this.setObjetivo(objetivo);
      this.setDescricao(descricao);

      this.setCompleto(true);

      return "";
    }
    return "O Projeto ja tem as informacoes basicas";
  }

  public String avancaStatus() {
    if (status == Status.E) {
      if (this.isCompleto()) {
        for (int i = 0; i < colaboradores.size(); i++) {
          if (colaboradores.get(i).getTipo() == TipoColaborador.G) {
            if (colaboradores.get(i).getProjetos().stream().filter(projeto -> projeto.getStatus() == Status.A)
                .count() >= 2) {
              return "Um ou mais Graduandos da equipe esta(ao) alocado(s) em dois projetos em andamento";
            }
          }
        }
        this.status = Status.A;
        return "";
      }
      return "As informacoes basicas do projeto nao estao presentes";
    } else if (status == Status.A) {
      if (publicacoes.size() > 0) {
        this.status = Status.C;
        return "";
      }
      return "Nao existem publicacoes associadas ao projeto";
    }
    return "O projeto ja foi concluido";
  }

  public String alocaParticipante(Colaborador participante) {
    if (status == Status.E) {
      if (!colaboradores.contains(participante)) {
        colaboradores.add(participante);
        participante.adicionaProjeto(this);
        return "";
      }
      return "O colaborador informado ja esta alocado no projeto";
    }
    return "Nao e possivel alocar colaboradores, pois o projeto nao esta mais em elaboracao";
  }

  public String associaPublicacao(Publicacao publicacao) {
    if (status == Status.A) {
      if (!publicacoes.contains(publicacao)) {
        publicacoes.add(publicacao);
        publicacao.setProjeto(this);
        return "";
      }
      return "A publicacao informada ja esta associada ao Projeto";
    }
    return "Nao foi possivel associar a publicacao, pois o projeto nao esta em andamento";
  }
}
