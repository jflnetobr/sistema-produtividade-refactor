package src.model.states;

import src.model.Colaborador;
import src.model.Projeto;
import src.model.Publicacao;

public class Concluido implements State {
  Projeto projeto;

  public Concluido(Projeto projeto) {
    this.projeto = projeto;
  }

  public String getDescricao() {
    return "Em Concluido";
  }

  public String avancaStatus() {
    return "O projeto ja foi concluido";
  }

  public String alocaParticipante(Colaborador participante) {
    return "Nao e possivel alocar colaboradores, pois o projeto nao esta mais em elaboracao";
  }

  public String associaPublicacao(Publicacao publicacao) {
    return "Nao foi possivel associar a publicacao, pois o projeto nao esta em andamento";
  }
}
