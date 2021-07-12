**Product-ms**
----
  Este é um microservice desenvolvido para o desafio da CompassoUOL, onde é possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais disponíveis. Também deve ser possível realizar a busca de produtos filtrando por name, description e price.


### EXECUÇÃO
Para rodar o programa, basta instalar as dependências ```Maven``` e inicia-lo como uma aplicação SPRING.
O banco de dados ```MySQL 8``` está configurado para rodar em um container docker, que foi definido no arquivo  ```stack.yml``` então para subir o container basta ter a docker instalada e rodar o comando:

```docker-compose -f stack.yml up```



### URL

  O serviço, como previamente definido roda na porta :9999 sendo assim ficando com o url base: 
  http://localhost:9999


### Formato

O formato esperado de um produto é o seguinte:

```javascript
  {
    "id": intanger,
    "name": "string",
    "description": "string",
    "price": 59.99
  }
```
Durante a criação e alteração, os campos name, description e price são obrigatórios. Em relação ao campo price o valor deve ser positivo.

Retorno em caso de Not Found:
```javascript
  {
    "id": "No such product found with id:" + id buscado,
    "erro_code": integer
  }
```


### Endpoints

São disponibilizados os seguintes endpoints para operação do catálogo de produtos:


| Verbo HTTP  |  Resource path    |          descrição           |
|-------------|:-----------------:|------------------------------:|
| POST        |  /products        |   Criação de um produto       |
| PUT         |  /products/{id}   |   Atualização de um produto   |
| GET         |  /products/{id}   |   Busca de um produto por ID  |
| GET         |  /products        |   Lista de produtos           |
| GET         |  /products/search |   Lista de produtos filtrados |
| DELETE      |  /products/{id}   |   Deleção de um produto       |


      
#### GET /products

Nesse endpoint a API retorna a lista atual de todos os produtos com HTTP 200. Se não existir produtos, retornar uma lista vazia.

Retorno com produtos:
```javascript
[
  {
    "id": "id produto 1",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  },
  {
    "id": "id produto 2",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
]
```

Retorno vazio:
```javascript
[]
```

#### POST /products

Esse endpoint cria um novo produto na base de dados, ao receber o JSON do produto o mesmo é validado em acordo com as regras da sessão **Formato**, e, caso esteja válido, persistido na base de dados e retornado com o *id* gerado e HTTP 201.

Entrada:
```javascript
  {
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

Retorno:
```javascript
  {
    "id": "id gerado",
    "name": "nome",
    "description": "descriÃ§Ã£o",
    "price": <preco>
  }
```
Em caso de algum erro de validação, a API  retorna um HTTP 400 indicando um Bad Request e uma menssagem com o erro ocorrido:

```javascript
  {
    "message": "message",
    "status": integer
  }
```

#### PUT /products/\{id\}

Esse endpoint atualiza um produto baseado no {id} passado via path param. Antes de alterar, é consultada a base de dados pelo *id*, se o produto não for localizado então é retornado um HTTP 404 ao cliente com o Not Found. Se localizar o produto, então os campos *name, description e price* são atualizados conforme recebidos no body da requisição.

Entrada:
```javascript
  {
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

Retorno:
```javascript
  {
    "id": "id atualizado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

As mesmas regras de validaçãi do POST /products são executadas para garantir consistência, e, se ocorrer erro retornar no mesmo formato:

```javascript
  {
    "message": "message",
    "status": integer
  }
```


#### GET /products/\{id\}

Esse endpoint retorna o product localizado na base de dados com um HTTP 200. Em caso de não localização do produto, a API retorna um HTTP 404 indicando que o recurso não foi localizado e um erro de Not Found mencionado no início.

Retorno:
```javascript
  {
    "id": "id buscado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

#### GET /products/search

Nesse endpoint a API retorna a lista atual de todos os produtos filtrados de acordo com query parameters passados na URL.

Os query parameters aceitos são: q, min_price e max_price.

**Importante: nenhum deles é obrigatório na requisição**

Onde:

| Query param |  Ação de filtro     
|-------------|:---------------------------------------------------------------:|
| q           |   bate o valor contra os campos *name* e *description*           |
| min_price   |   bate o valor ">=" contra o campo *price*                |
| max_price   |   bate o valor "<=" contra o campo *price*                |

**Exemplo: /products/search?min_price=10.5&max_price=50&q=superget**

Retorno com produtos filtrados/buscados:
```javascript
[
  {
    "id": "id produto 1",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  },
  {
    "id": "id produto 2",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
]
```

Retorno vazio:
```javascript
[]
```

#### DELETE /products/\{id\}

Esse endpoint deleta um registro de produto na base de dados. Caso encontre o produto filtrando pelo *id* então deleta e retorna um HTTP 200 com uma mensagem. Se o *id* passado não foi localizado deve retorna um HTTP 404 com o erro Not Fount.

Retorno:
```javascript
  {
    "deleted": true
  }
```
