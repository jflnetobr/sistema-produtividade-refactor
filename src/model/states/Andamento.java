package src.model.states;

import src.model.*;
import src.util.IntercurrenceException;

public class Andamento implements State {
  Projeto projeto;

  public Andamento(Projeto projeto) {
    this.projeto = projeto;
  }

  public String getDescricao() {
    return "Em Andamento";
  }

  public void avancaStatus() throws IntercurrenceException {
    if (projeto.getPublicacoes().size() > 0) {
      projeto.setStatus(projeto.getConcluido());
    } else {
      throw new IntercurrenceException("Nao existem publicacoes associadas ao projeto");
    }
  }

  public void alocaParticipante(Colaborador participante) throws IntercurrenceException {
    throw new IntercurrenceException("Nao e possivel alocar colaboradores, pois o projeto nao esta mais em elaboracao");
  }

  public void associaPublicacao(Publicacao publicacao) throws IntercurrenceException {
    if (!projeto.getPublicacoes().contains(publicacao)) {
      projeto.adicionaPublicacao(publicacao);
      publicacao.setProjeto(projeto);
    } else {
      throw new IntercurrenceException("A publicacao informada ja esta associada ao Projeto");
    }
  }
}
