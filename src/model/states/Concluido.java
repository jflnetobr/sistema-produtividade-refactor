package src.model.states;

import src.model.Colaborador;
import src.model.Projeto;
import src.model.Publicacao;
import src.util.IntercurrenceException;

public class Concluido implements State {
  Projeto projeto;

  public Concluido(Projeto projeto) {
    this.projeto = projeto;
  }

  public String getDescricao() {
    return "Em Concluido";
  }

  public void avancaStatus() throws IntercurrenceException {
    throw new IntercurrenceException("O projeto ja foi concluido");
  }

  public void alocaParticipante(Colaborador participante) throws IntercurrenceException {
    throw new IntercurrenceException("Nao e possivel alocar colaboradores, pois o projeto nao esta mais em elaboracao");
  }

  public void associaPublicacao(Publicacao publicacao) throws IntercurrenceException {
    throw new IntercurrenceException("Nao foi possivel associar a publicacao, pois o projeto nao esta em andamento");
  }
}
