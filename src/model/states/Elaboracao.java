package src.model.states;

import src.model.*;
import src.model.enums.TipoColaborador;
import src.util.IntercurrenceException;

public class Elaboracao implements State {
  Projeto projeto;

  public Elaboracao(Projeto projeto) {
    this.projeto = projeto;
  }

  public String getDescricao() {
    return "Em Elaboracao";
  }

  public void avancaStatus() throws IntercurrenceException {
    if (projeto.isCompleto()) {
      for (int i = 0; i < projeto.getColaboradores().size(); i++) {
        if (projeto.getColaboradores().get(i).getTipo() == TipoColaborador.G) {
          if (projeto.getColaboradores().get(i).getProjetos().stream()
              .filter(projeto -> projeto.getStatus() == projeto.getAndamento()).count() >= 2) {
            throw new IntercurrenceException(
                "Um ou mais Graduandos da equipe esta(ao) alocado(s) em dois projetos em andamento");
          }
        }
      }
      projeto.setStatus(projeto.getAndamento());
    } else {
      throw new IntercurrenceException("As informacoes basicas do projeto nao estao presentes");
    }
  }

  public void alocaParticipante(Colaborador participante) throws IntercurrenceException {
    if (!projeto.getColaboradores().contains(participante)) {
      projeto.adicionaColaborador(participante);
      participante.adicionaProjeto(projeto);
    } else {
      throw new IntercurrenceException("O colaborador informado ja esta alocado no projeto");
    }
  }

  public void associaPublicacao(Publicacao publicacao) throws IntercurrenceException {
    throw new IntercurrenceException("Nao foi possivel associar a publicacao, pois o projeto nao esta em andamento");
  }
}
