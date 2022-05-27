# Atividade 6 — Devolução de um Livro

## Necessidades

Um(a) usuário(a) pode devolver um livro que estava na sua posse. Aquela devolução indica que tal cópia(instance) agora está disponível para ser solicitada por outra pessoa.

Para a devolução ocorrer precisamos da identificação do usuário e do empréstimo em questão.

## Restrições

- Apenas o(a) usuário(a) que retirou o livro pode fazer a devolução
- A identificação do empréstimo precisa existir no sistema
- A identificação do(a) usuário(a) precisa existir no sistema
- Um empréstimo não pode ser devolvido duas vezes

## Resultado esperado

- Status 200 sem corpo indicando que a devolução foi feita
- Status 422 caso o usuário devolvendo não seja o que pegou
- Status 400 em caso de falhas de validação padrão
- Status 422 em caso de empréstimo sendo devolvido pela segunda vez
