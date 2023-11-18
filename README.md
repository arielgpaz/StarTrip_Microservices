  <h1 align="center">★ StarTrip ★</h1>
  <h3 align="center">★ Que a diversão esteja com você! ★</h3>

---
## Descrição

Este é um projeto back-end para um aplicativo de locação de imóveis para 

## Objetivo

Esse projeto é uma refatoração do projeto inicial Startrip, a fim de criar microsserviços e colocar em prática
o conhecimento de novas tecnologias, como MongoDB e Kafka.

## Tecnologias

- ``Java 17``
- ``Maven``
- ``Spring Boot``
- ``MongoDB``
- ``Kafka``
- ``Lombok``
- ``OpenFeign``
- ``Swagger``
- ``Docker``

## Componentes

- St-Usuarios: Microsserviço responsável pelo cadastro, atualização e consulta de usuários (Donos e hóspedes dos imóveis);
- St-Imoveis: Microsserviço responsável pelo cadastro, consulta e exclusão lógica dos imóveis;
- St-Anuncios: Microsserviço responsável pelo cadastro, consulta e exclusão lógica dos anúncios;
- St-Reservas: Microsserviço responsável pelo cadastro, consulta, pagamento, cancelamento e estorno das reservas;
- St-Pagamentos: Este microsserviço se tornará o responsável pelo pagamento, cancelamento e estorno das reservas, e se comunicará com o St-Reservas por mensagens enviadas pelo Kafka.

## Como usar

Este projeto pode ser usado através do Docker, basta seguir os passos abaixo:

1) Gerar os arquivos *.jar* para cada microsserviço: `mvn clean package`
2) No terminal Docker e estando na pasta raiz do projeto, executar o comando: `docker compose up -d`
3) Abrir o swagger: [St-Usuarios](http://localhost:8080/swagger-ui/), [St-Imoveis](http://localhost:7002/swagger-ui/index.html), [St-Anuncios](http://localhost:7003/swagger-ui/index.html), [St-Reservas](http://localhost:7004/swagger-ui/index.html#/)

## Próximos passos:
1. Criar um retorno padrão para casos de erros através de Errors Handler.
