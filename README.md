# CRIO Backend

**Descrição:**  
O CRIO é um sistema de gerenciamento de agendamento de eventos e publicação de conteúdos. O backend da aplicação é desenvolvido em Laravel e fornece uma API RESTful para gerenciar eventos, usuários, e entidades parceiras, além de permitir a publicação de novidades em um sistema de blog.

**Funcionalidades:**  
- Gerenciamento de Eventos: Cadastro, edição e exclusão de eventos com validação de conflitos de horários.  
- Notificações: Envio de notificações por e-mail sobre eventos futuros.  
- Sistema de Blog: Publicação de novidades e conteúdos.  
- Gerenciamento de Usuários: Controle de acesso e gerenciamento de usuários do sistema.  
- Cadastro de Entidades Parceiras: Registro e gestão de entidades parceiras.

**Tecnologias Utilizadas:**  
- Laravel: Framework PHP para construção da API.  
- MySQL: Banco de dados relacional.  
- Composer: Gerenciador de dependências PHP.

**Pré-requisitos:**  
- PHP >= 8.0  
- Composer  
- MySQL  
- Node.js (opcional, para front-end)

**Instalação:**  
1. Clone o repositório:  
   `git clone https://github.com/seu-usuario/crio-backend.git`  
   `cd crio-backend`
 
2. Instale as dependências:  
   `composer install`
 
3. Configuração do Ambiente:  
   - Renomeie o arquivo `.env.example` para `.env` e configure as credenciais do banco de dados e outras variáveis de ambiente.

4. Gere a chave de aplicação:  
   `php artisan key:generate`
 
5. Migrações do banco de dados:  
   `php artisan migrate`
 
6. Inicie o servidor:  
   `php artisan serve`
 
O backend estará disponível em `http://localhost:8000`.

**Endpoints da API:**  
A API possui os seguintes endpoints:

**Eventos:**  
- `GET /api/eventos`: Lista todos os eventos.  
- `POST /api/eventos`: Cria um novo evento.  
- `GET /api/eventos/{id}`: Exibe um evento específico.  
- `PUT /api/eventos/{id}`: Atualiza um evento existente.  
- `DELETE /api/eventos/{id}`: Remove um evento.

**Usuários:**  
- `POST /api/login`: Realiza o login de um usuário.  
- `GET /api/usuarios`: Lista todos os usuários.  
- `POST /api/usuarios`: Cria um novo usuário.

**Blog:**  
- `GET /api/blog`: Lista as postagens do blog.  
- `POST /api/blog`: Cria uma nova postagem no blog.

**Contribuição:**  
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou relatar problemas.

**Licença:**  
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

**Contato:**  
Para mais informações, entre em contato com seu-email@exemplo.com.
