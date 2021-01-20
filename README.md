
# Sistema de Gestão de Produtividade Acadêmica

Projeto da disciplina de Projeto de Software - Curso de Engenharia de Computação (UFAL)

## Como executar

Estando na raiz do projeto, basta executar o comando:

      javac src/Main.java && java src/Main

## Padrões de projeto aplicados

Foram implementados 4 padrões de projeto, a saber:

 - Chain constructors
 - State
 - Strategy
 - Extract Method

Os padrões foram implementados em:

### Chain Constructors

#### Commit: https://github.com/jflnetobr/sistema-produtividade-refactor/commit/f1cbcd92bf0b1acfbfff82bc9fe2b2c488725c4c

- Classe Projeto 

### State
#### Commits: https://github.com/jflnetobr/sistema-produtividade-refactor/commit/112d659b96a0fe83a925c44de92f79100fed9e5e e https://github.com/jflnetobr/sistema-produtividade-refactor/commit/38b0fd071207f571e1f33684a7c677f611c0993e

- Classe Projeto 

Refletiu em:

 - Criação de arquivos no diretório states (https://github.com/jflnetobr/sistema-produtividade-refactor/tree/main/src/model/states)
 - Exclusão do Enum de status
 - Classe LaboratorioView
 - Classe ManuPrincipal
 - Classe ProjetoView

### Strategy
#### Commit: https://github.com/jflnetobr/sistema-produtividade-refactor/commit/ce0141100e974cde5bd6ab929bff90cb78140ea7

- Classe LaboratorioView

Refletiu em:

 - Criação de arquivos no diretório reports (https://github.com/jflnetobr/sistema-produtividade-refactor/tree/main/src/view/reports)
 - Classe ColaboradorView
 - Classe ProducaoAcademicaView
 - Classe ProjetoView

### Extract Method
#### Commits: https://github.com/jflnetobr/sistema-produtividade-refactor/commit/a495f8883313d351d4a6d35179b0a760a674019e e https://github.com/jflnetobr/sistema-produtividade-refactor/commit/8f7f1950b0355b4e14e48ea3db0286760d6b85a3

**Emissão de relatório:**
Implementado em:

- Classe LaboratorioView

Refletiu em:

 - Classe ColaboradorView
 - Classe ProducaoAcademicaView
 - ClasseProjetoView
 
**Formatação e parse de data:**
Implementado em:

- Classe Util 

Refletiu em:

 - Classe Laboratorio
 - Classe Projeto
 - Classe ColaboradorView
 - Classe ProjetoView

## Tratamento de exceções
#### Commits: https://github.com/jflnetobr/sistema-produtividade-refactor/commit/46ab76cfced26548adc561e0294b93b8808461c8, https://github.com/jflnetobr/sistema-produtividade-refactor/commit/b7af2d1d7d6c4aeb6f60406e3458579908681f14 e https://github.com/jflnetobr/sistema-produtividade-refactor/commit/13f0db839aa76092e5571db09b801156640dfe9f

 - Implementação de classe de exceção própria (IntercurrenceException) para intercorrências, como por exemplo, id não presente no sistema, colaborador não é do tipo necessário, entre outros. Anteriormente esse tipo de situação era tratado retornarndo uma String com o erro e considerando que quando fosse retornado "" não tinham ocorrido erros. Com a criação da exceção o código ficou muito mais limpo e melhor de entender.
 - Tratamento de exceções no uso de ParseInt e outros.

Classe de Exceção implementada:
 - IntercurrenceException (https://github.com/jflnetobr/sistema-produtividade-refactor/blob/main/src/util/IntercurrenceException.java)
 
Classes afetadas:

 - Classe Laboratorio
 - Classe Orientacao
 - Classe Projeto
 - Classe Publicacao
 - Classe Andamento
 - Classe Concluido
 - Classe Elaboracao
 - Classe State
 - Classe ColaboradorView
 - Classe ProducaoAcademicaView
 - Classe ProjetoView
 
## Decisões tomadas no sistema

Alguns aspectos da modelagem não ficaram explícitos na especificação, de forma que foram tomadas as seguintes decisões durante a modelagem:

 - Sobre o laboratório
 
 1. O sistema é voltado para um laboratório apenas ou vários?

	**Como a ideia inicial não é que o sistema rode em rede nem nada do tipo, se imaginou que cada laboratório vai ter sua própria instância rodando. Portanto, o sistema foi projetado para apenas um laboratório, objetivando uma implementação e uso mais simples, mas é possível adequar para atender N laboratórios com facilidade, cada um com seu administrador. Serão informados no primeiro acesso ao sistema:**
	- **Nome do laboratório;**
	- **Instituição de que faz parte.**

- Sobre o administrador

2. Deve existir apenas um administrador por laboratório, mais de um por laboratório, ou, ainda, apenas um administrador para todo o sistema?

	**Assim como o laboratório, existirá apenas um administrador para todo o sistema, que é cadastrado no primeiro acesso junto com as informações do laboratório. Será informado no cadastro o nome do Administrador;**

- Sobre o colaborador

3. Que atributos um colaborador deve ter?

	**Para o colaborador são solicitadas as seguintes informações, de acordo com a segunda parte da especificação:**
	- **Nome;**
	- **E-mail.**

- Sobre as orientações

4. Orientações são uma classe a parte ou elas e as publicações são herdeiras da classe “Produção Acadêmica” com os atributos de publicação?

	**As classes Publicacao e Orientacao herdarão de ProducaoAcademica. Esta última terá título e ano de publicação.**

5. Quantos alunos podem ser orientados (um ou vários) e quantos professores podem participar de uma orientação (um ou vários)?

	**Considerei que um professor pode orientar um ou mais alunos em cada orientação.**

6. Se a orientação for uma entidade a parte, que atributos deve ter? Apenas orientador(es) e orientado(s)? Orientações estão relacionadas com projetos, assim como as Produções?

	**Considerei que apenas a classe Publicacao está relacionada com os projetos.**
	**Para uma orientação, além de título, ano de publicação e projeto, herdados, existirão também:**
	
	- **Orientador (colaborador do tipo professor);**
	- **Orientados (colaboradores do tipo aluno, que só podem estar em uma orientação).**

