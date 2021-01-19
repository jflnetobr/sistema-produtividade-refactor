package src.model.states;

import src.model.*;

public class Andamento implements State {
  Projeto projeto;

  public Andamento(Projeto projeto) {
    this.projeto = projeto;
  }

  public String getDescricao() {
    return "Em Andamento";
  }

  public String avancaStatus() {
    if (projeto.getPublicacoes().size() > 0) {
      projeto.setStatus(projeto.getConcluido());
      return "";
    }
    return "Nao existem publicacoes associadas ao projeto";
  }

  public String alocaParticipante(Colaborador participante) {
    return "Nao e possivel alocar colaboradores, pois o projeto nao esta mais em elaboracao";
  }

  public String associaPublicacao(Publicacao publicacao) {
    if (!projeto.getPublicacoes().contains(publicacao)) {
      projeto.adicionaPublicacao(publicacao);
      publicacao.setProjeto(projeto);
      return "";
    }
    return "A publicacao informada ja esta associada ao Projeto";
  }
}
