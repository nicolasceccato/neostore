# NeoStore Suppliers

Este projeto é um sistema de gerenciamento de fornecedores que permite aos usuários adicionar, editar, obter e excluir fornecedores por meio de uma aplicação web.



## Como Executar o Projeto

1. **Clonar o Repositório:**
   Clone este repositório em sua máquina local usando o seguinte comando:

   git clone https://github.com/nicolasceccato/neostore

2. **Configurar o Banco de Dados:**
- Certifique-se de ter o banco de dados relacional MySQL instalado e em execução.
- Crie um banco de dados chamado `supplier`.

3. **Configurar o Backend:**
- Certifique-se de ter o Java, Maven e o Apache Tomcat instalados.
- Abra a pasta supplier (neostore/back/supplier) em sua IDE Java favorita (Este tutorial está sendo feito considerando o uso do Intellij IDEA). Com a pasta aberta, clique com o botão direito sobre o arquivo pom.xml e escolha a opção `Edit with Intellij IDEA`.
- Com o projeto aberto no Intellij, atualize as dependências do pom, se necessário.
- Atualize o arquivo `persistence.xml` com seus dados de acesso ao banco de dados.
- Após isso, configure o Apache Tomcat como Local. Nas configurações do Tomcat, na aba Deployment adicione o artefato `supplier:war exploded` e no Application context edite para que fique `/supplier`.
- Com isso já pode-se iniciar a aplicação backend.

4. **Executar o Frontend:**
- Abra o arquivo `index.html`(neostore/front/index.html) em um navegador.
- A interface aberta permitirá interagir com o backend.

## Estrutura do Projeto

O projeto está estruturado em vários pacotes, cada um com um propósito específico:

### Backend

- **`com.neostore.supplier`**: Contém um servlet simples de "Hello World!".
- **`com.neostore.supplier.entities`**: Contém a classe de entidade Supplier que representa um fornecedor.
- **`com.neostore.supplier.controllers`**: Contém os Servlets responsáveis por lidar com as requisições HTTP.
- **`com.neostore.supplier.filter`**: Contém um filtro para lidar com Compartilhamento de Recursos de Origem Cruzada (CORS).
- **`com.neostore.supplier.repositories`**: Contém interface e uma implementação para acessar o banco de dados com JPA.
- **`com.neostore.supplier.services`**: Contém a lógica de negócios para gerenciar fornecedores.
- **`com.neostore.supplier.services.exceptions`**: Contém exceção personalizada para a camada de serviços.

### Frontend

- **`index.html`**: Contém a estrutura da página.
- **`script.js`**: Arquivo JavaScript contendo funções para interagir com o backend e realizar validações.
- **`style.css`**: Arquivo CSS para estilizar a página.

## Decisões e Considerações

### Backend
- O backend é implementado em `Java`.
- `JPA` (Java Persistence API) com `Hibernate` é usado para persistência de dados.
- `MySQL` foi utizado como banco de dados.

### Frontend
- O frontend é uma interface web simples construída usando `HTML`, `CSS`, `Bootstrap` e `JavaScript`.
- JavaScript é usado para enviar requisições HTTP ao backend e receber as respostas.

### Validação de Fornecedor
- As funções `isValidCnpj` e `isValidEmail` são implementadas para validar CNPJ e e-mails inseridos.
- As validações são realizadas no frontend antes de fazer as requisições ao backend.

### Tratamento de Erros
- O backend trata erros, como entrada inválida ou recurso não encontrado.
- O frontend também faz tratamento entradas inválidas e apresenta ao usuário.

## Próximos Passos

- Refatorar o frontend para usar o framework JavaScript AngularJS.
- Implementar listagem de fornecedores paginada.
- Fazer um serviço de importação de fornecedores a partir de um JSON.
 