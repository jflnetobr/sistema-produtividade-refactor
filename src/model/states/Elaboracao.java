package src.model.states;

import src.model.*;
import src.model.enums.TipoColaborador;

public class Elaboracao implements State {
  Projeto projeto;

  public Elaboracao(Projeto projeto) {
    this.projeto = projeto;
  }

  public String getDescricao() {
    return "Em Elaboracao";
  }

  public String avancaStatus() {
    if (projeto.isCompleto()) {
      for (int i = 0; i < projeto.getColaboradores().size(); i++) {
        if (projeto.getColaboradores().get(i).getTipo() == TipoColaborador.G) {
          if (projeto.getColaboradores().get(i).getProjetos().stream()
              .filter(projeto -> projeto.getStatus() == projeto.getAndamento()).count() >= 2) {
            return "Um ou mais Graduandos da equipe esta(ao) alocado(s) em dois projetos em andamento";
          }
        }
      }
      projeto.setStatus(projeto.getAndamento());
      return "";
    }
    return "As informacoes basicas do projeto nao estao presentes";
  }

  public String alocaParticipante(Colaborador participante) {
    if (!projeto.getColaboradores().contains(participante)) {
      projeto.adicionaColaborador(participante);
      participante.adicionaProjeto(projeto);
      return "";
    }
    return "O colaborador informado ja esta alocado no projeto";
  }

  public String associaPublicacao(Publicacao publicacao) {
    return "Nao foi possivel associar a publicacao, pois o projeto nao esta em andamento";
  }
}
