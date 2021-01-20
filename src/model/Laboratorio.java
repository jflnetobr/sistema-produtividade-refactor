package src.model;

import java.util.ArrayList;
import java.util.Date;

import src.model.enums.*;
import src.util.*;

public class Laboratorio {
  private String nome;
  private String instituicao;
  private String nomeAdm;
  private ArrayList<Colaborador> colaboradores = new ArrayList<Colaborador>();
  private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
  private ArrayList<ProducaoAcademica> producoesAcademicas = new ArrayList<ProducaoAcademica>();

  public Laboratorio(String nome, String instituicao, String nomeAdm) {
    this.nome = nome;
    this.instituicao = instituicao;
    this.nomeAdm = nomeAdm;
  }

  public String getNome() {
    return nome;
  }

  public String getInstituicao() {
    return instituicao;
  }

  public String getNomeAdm() {
    return nomeAdm;
  }

  public ArrayList<Colaborador> getColaboradores() {
    return colaboradores;
  }

  public ArrayList<Projeto> getProjetos() {
    return projetos;
  }

  public ArrayList<ProducaoAcademica> getProducoesAcademicas() {
    return producoesAcademicas;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setInstituicao(String instituicao) {
    this.instituicao = instituicao;
  }

  public void setNomeAdm(String nomeAdm) {
    this.nomeAdm = nomeAdm;
  }

  public void setColaboradores(ArrayList<Colaborador> colaboradores) {
    this.colaboradores = colaboradores;
  }

  public void setProjetos(ArrayList<Projeto> projetos) {
    this.projetos = projetos;
  }

  public void setProducoesAcademicas(ArrayList<ProducaoAcademica> producoesAcademicas) {
    this.producoesAcademicas = producoesAcademicas;
  }

  public Colaborador getColaborador(int id) {
    return colaboradores.get(id - 1);
  }

  public Projeto getProjeto(int id) {
    return projetos.get(id - 1);
  }

  public ProducaoAcademica getProducaoAcademica(int id) {
    return producoesAcademicas.get(id - 1);
  }

  public void criarColaborador(String nome, String email, int tipo) throws IntercurrenceException {
    TipoColaborador t;

    switch (tipo) {
      case 1:
        t = TipoColaborador.G;
        break;
      case 2:
        t = TipoColaborador.M;
        break;
      case 3:
        t = TipoColaborador.D;
        break;
      case 4:
        t = TipoColaborador.Prof;
        break;
      case 5:
        t = TipoColaborador.Pesq;
        break;
      default:
        throw new IntercurrenceException("Tipo informado invalido!");
    }

    this.colaboradores.add(new Colaborador(colaboradores.size() + 1, nome, email, t));
  }

  public void criarProjetoResumido(String titulo, int idResponsavel) throws IntercurrenceException {
    if (colaboradores.get(idResponsavel - 1).getTipo() == TipoColaborador.Prof) {
      this.projetos.add(new Projeto(projetos.size() + 1, titulo, colaboradores.get(idResponsavel - 1)));
    } else {
      throw new IntercurrenceException("O responsavel informado nao e um professor");
    }
  }

  public void criarProjetoCompleto(String titulo, String dataInicio, String dataTermino, String agenciaFinanciadora,
      float valorFinanciado, String objetivo, String descricao, int idResponsavel) throws IntercurrenceException {
    if (colaboradores.get(idResponsavel - 1).getTipo() == TipoColaborador.Prof) {
      try {
        Date dI = Util.parseDate(dataInicio);
        Date dT = Util.parseDate(dataTermino);

        this.projetos.add(new Projeto(projetos.size() + 1, titulo, dI, dT, agenciaFinanciadora, valorFinanciado,
            objetivo, descricao, colaboradores.get(idResponsavel - 1)));
      } catch (Exception e) {
        throw new IntercurrenceException("Nao foi possivel criar o projeto. Voce informou uma data invalida!");
      }
    }
    throw new IntercurrenceException("O responsavel informado nao e um professor");
  }

  public void criarPublicacao(String titulo, int anoPublicacao, String nomeConferencia) {
    this.producoesAcademicas
        .add(new Publicacao(producoesAcademicas.size() + 1, titulo, anoPublicacao, nomeConferencia));
  }

  public void criarOrientacao(String titulo, int anoPublicacao, int idOrientador) throws IntercurrenceException {
    if (colaboradores.get(idOrientador - 1).getTipo() == TipoColaborador.Prof) {
      this.producoesAcademicas.add(
          new Orientacao(producoesAcademicas.size() + 1, titulo, anoPublicacao, colaboradores.get(idOrientador - 1)));
    } else {
      throw new IntercurrenceException("O orientador informado nao e um professor");
    }
  }
}