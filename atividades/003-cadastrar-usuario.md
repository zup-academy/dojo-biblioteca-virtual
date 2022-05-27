# Atividade 3 — Cadastrar um Usuário

## Necessidades

Os usuários(as) são as pessoas que podem solicitar o empréstimo do exemplar de um livro. A única característica necessária para este usuário é saber se ele do tipo Padrão ou Pesquisador.

## Restrições

- O tipo é obrigatório
- Nome
- Data de nascimento

## Resultado esperado

- Status 201 com header de location com endereco do recurso criado
- Status 400 caso tenha qualquer falha de validação com as falhas no corpo da resposta
