# Atividade 4 — Solicitar Empréstimo de um Livro

## Explicação

O exemplar de um livro pode ser pedido para empréstimo por um(a) usuário(a). Esse pedido tem algumas características importantes:

- [X] Dependendo do tipo de usuário, não é necessário definir o prazo explicitamente
- [X] Agora, independente do tipo de usuário, todo empréstimo tem tempo máximo de entrega limitado a no máximo 60 dias do momento
- [X] Só pesquisadores(as) podem pedir empréstimos sem definir prazo de entrega explícito. Neste caso o sistema deve definir 60 dias como prazo
- [X] Um usuário(a) padrão(regular) só pode ter até 5 empréstimos ao mesmo temp
- [X] Um usuário(a) pesquisador pode ter quantos empréstimos quiser ao mesmo tempo
- [X] Só pesquisadores podem pedir empréstimos de exemplares restritos

Um outro detalhe importante. A API do sistema deve receber um pedido de empréstimo para um determinado livro, deixando que o sistema busque pelo exemplar disponível daquele determinado livro.

## Necessidades

Precisamos implementar o fluxo completo de pedido de empréstimo de um livro. Quando solicitamos o empréstimo, se o usuário for do tipo padrão, é necessário informar o tempo em dias do empréstimo.

Reforçando que a entrada para um pedido de empréstimo é o identificador do livro e do usuário. O sistema que deve buscar pelo exemplar adequado.

## Restrições

- [X] Apenas pesquisadores(as) podem pedir por empréstimos sem prazo para entrega
- [X] Todo pedido de empréstimo, quando *não* tem o tempo de empréstimo explicitado, tem o limite de 60 dias a partir do momento do pedido
- [X] Se o usuário é padrão existe a obrigação de definir o tempo de empréstimo
- [X] Se não tiver exemplar disponível daquele livro no momento do pedido, o empréstimo não pode ser liberado
- [X] Um(a) usuário(a) padrão(regular) só pode ter no máximo 5 empréstimos ao mesmo tempo
- [X] Só pesquisadores podem receber exemplares restritos

## Resultado esperado

- Status 201 com o header localtion com endereco do empréstimo
- Status 400 caso tenha qualquer falha de validação com as falhas no corpo da resposta

## Exercício importante de imaginação

Aqui temos o fluxo mais complexo do desafio. São múltiplos desafios e sua habilidade em utilizar a Orientação a Objetos e o framework escolhido a seu favor é fundamental.

Antes de começar, invista um tempo para imaginar a solução. Faça um rascunho, no próprio código, do fluxo que você imagina. Essa capacidade de imaginar o que você vai fazer, facilita muito a implementação em si.
