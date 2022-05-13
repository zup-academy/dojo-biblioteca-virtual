## Explicação

Um exemplar é o termo utilizado para referenciar as cópias de um livro que estão disponíveis para empréstimo.

Um exemplar pode ser de circulação livre e restrito. Isso é importante porque restringe o tipo de usuário que pode ter acesso. 

Exemplares de circulação livre podem ser emprestados para qualquer usuário enquanto que exemplares restritos só podem ser pegos por usuários que são pesquisadores. 

## Necessidades

Para cada exemplar, podemos ter sua versão livre de circulação e também a de circulação restrita. É necessário que seja feita um cadastro dos exemplares do livro referenciando o ISBN do livro em si que foi cadastrado. Exemplo: cadastro para o ISBN XPTO do exemplar de circulação livre. 

Um determinado livro pode ter um número ilimitado de exemplares.

## Restrições

* O ISBN do livro que vai ter o exemplar cadastrado é obrigatório
* O ISBN deve existir no sistema
* O tipo de circulação(restrita ou livre) é obrigatória

## Resultado esperado

* Status 201 com header de location com endereco do recurso
* Status 400 caso tenha qualquer falha de validação com as falhas no corpo da resposta
* Caso o ISBN passado não exista, deve ser retornar status 404. 