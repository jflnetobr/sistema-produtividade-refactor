package src.model.states;

import src.model.Colaborador;
import src.model.Publicacao;
import src.util.IntercurrenceException;

public interface State {
  public String getDescricao();

  public void avancaStatus() throws IntercurrenceException;

  public void alocaParticipante(Colaborador participante) throws IntercurrenceException;

  public void associaPublicacao(Publicacao publicacao) throws IntercurrenceException;
}
