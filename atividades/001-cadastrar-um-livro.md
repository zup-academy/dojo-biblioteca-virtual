# Atividade 1 — Cadastrar um Livro

## Necessidades

Um livro precisa ser cadastrado com título, preço e respectivo ISBN.

## Restrições

- Título é obrigatório
- Preço é obrigatório
- ISBN é obrigatório
- ISBN não pode ser duplicado

## Resultado Esperado

- Status 201 com header de location com endereco do recurso
- Status 400 caso tenha qualquer falha de validação com as falhas no corpo da resposta
