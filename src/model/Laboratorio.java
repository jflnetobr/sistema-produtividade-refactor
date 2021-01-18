package src.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import src.model.enums.*;

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

  public ArrayList<Colaborador> buscaColaboradores(String termo) {
    ArrayList<Colaborador> result = new ArrayList<Colaborador>();

    colaboradores.forEach(colaborador -> {
      if (colaborador.getNome().matches("(?i).*" + termo + ".*")
          || colaborador.getEmail().matches(".*" + termo + ".*")) {
        result.add(colaborador);
      }
    });

    return result;
  }

  public ArrayList<Projeto> buscaProjetos(String termo) {
    ArrayList<Projeto> result = new ArrayList<Projeto>();

    projetos.forEach(projeto -> {
      if (projeto.getTitulo().matches("(?i).*" + termo + ".*") || (projeto.getAgenciaFinanciadora() != null
          && projeto.getAgenciaFinanciadora().matches("(?i).*" + termo + ".*"))) {
        result.add(projeto);
      }
    });

    return result;
  }

  public ArrayList<ProducaoAcademica> buscaProducaoAcademica(String termo) {
    ArrayList<ProducaoAcademica> result = new ArrayList<ProducaoAcademica>();

    producoesAcademicas.forEach(producaoAcademica -> {
      if (producaoAcademica.getTitulo().matches("(?i).*" + termo + ".*")) {
        result.add(producaoAcademica);
      }
    });

    return result;
  }

  public String criarColaborador(String nome, String email, int tipo) {
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
        return "Tipo informado invalido!";
    }

    this.colaboradores.add(new Colaborador(colaboradores.size() + 1, nome, email, t));

    return "";
  }

  public String criarProjetoResumido(String titulo, int idResponsavel) {
    if (colaboradores.get(idResponsavel - 1).getTipo() == TipoColaborador.Prof) {
      this.projetos.add(new Projeto(projetos.size() + 1, titulo, colaboradores.get(idResponsavel - 1)));
      return "";
    }
    return "O responsavel informado nao e um professor";
  }

  public String criarProjetoCompleto(String titulo, String dataInicio, String dataTermino, String agenciaFinanciadora,
      float valorFinanciado, String objetivo, String descricao, int idResponsavel) {
    if (colaboradores.get(idResponsavel - 1).getTipo() == TipoColaborador.Prof) {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      sdf.setLenient(false);
      Date dI, dT;

      try {
        dI = sdf.parse(dataInicio);
        dT = sdf.parse(dataTermino);
      } catch (Exception e) {
        return "Uma das datas informadas nao e valida";
      }

      this.projetos.add(new Projeto(projetos.size() + 1, titulo, dI, dT, agenciaFinanciadora, valorFinanciado, objetivo,
          descricao, colaboradores.get(idResponsavel - 1)));
      return "";
    }
    return "O responsavel informado nao e um professor";
  }

  public String criarPublicacao(String titulo, int anoPublicacao, String nomeConferencia) {
    this.producoesAcademicas
        .add(new Publicacao(producoesAcademicas.size() + 1, titulo, anoPublicacao, nomeConferencia));
    return "";
  }

  public String criarOrientacao(String titulo, int anoPublicacao, int idOrientador) {
    if (colaboradores.get(idOrientador - 1).getTipo() == TipoColaborador.Prof) {
      this.producoesAcademicas.add(
          new Orientacao(producoesAcademicas.size() + 1, titulo, anoPublicacao, colaboradores.get(idOrientador - 1)));
      return "";
    }
    return "O orientador informado nao e um professor";
  }
}