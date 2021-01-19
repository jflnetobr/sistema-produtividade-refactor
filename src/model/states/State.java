package src.model.states;

import src.model.Colaborador;
import src.model.Publicacao;

public interface State {
  public String getDescricao();

  public String avancaStatus();

  public String alocaParticipante(Colaborador participante);

  public String associaPublicacao(Publicacao publicacao);
}
