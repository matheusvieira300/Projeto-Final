# Projeto Final - Clinic Time

## Sobre o Projeto
O projeto trata-se de um sistema de Cadastro de Pacientes automatizado que visa simplificar o processo de registro e gerenciamento de pacientes em consultórios médicos. Através deste sistema, os usuários terão acesso a um painel de controle para cadastrar novos pacientes e gerenciar informações relevantes. Com isso, será possível obter uma melhor organização dos registros, facilitar o acesso aos dados e evitar duplicações, proporcionando mais eficiência e praticidade no atendimento aos pacientes.

# Tecnologias Utilizadas

## Back End
- Java - 17
- Spring Boot - MVC
- JPA / Hibernate

## Front End
- HTML
- CSS
- JavaScript
- Thymeleaf
- Bootstrap

# Funcionalidades
## Home
Na página home contém uma descrição do que se trata o sistema Clinic Time
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/adb655b5-d232-4907-857f-98f0ab77f8b5)
## <b>Cadastre-se</b>
A aba cadastre-se seria a aba aonde é possível cadastrar uma nova secretária que teria acesso ao sistema, a secretária seria o usuário com permissão apenas de visualizar/cadastar/editar/excluir Pacientes.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/d5fe2970-428e-4678-9a6c-3564cdcd5bc7)</br>

<b>Registrado:</b>
quando o e-mail da secretária ainda não existe no sistema, ela é cadastrada com sucesso e a mensagem de <b>Registrado Com Sucesso!</b> é exibida.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/d265960d-1b82-498a-a63a-47f0a5e69c57)


<b> Já existente: </b>
quando o e-mail da secretária já existe no sistema a mensagem <b>E-mail já existe</b> é exibida ao tentar cadastrar uma nova secretária.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/76ed204a-94e5-44cc-8473-e3f98c3c788c)

## Login
<b>Login:</b> 
na aba login ao logar com um usuário <b>padrão(Secretária)</b> o sistema irá direcionar para a página index da Secretária. E ao logar com um usuário <b>admin(Doutor)</b> o sistema irá direcionar para a página index do Doutor.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/be1c82dd-1f6d-451e-a0a7-c01102061a6e)

<b>E-mail ou Senha Incorretos:</b>
ao tentar logar com um e-mail ou senha incorreto a mensagem <b>E-mail ou Senha Inválidos</b> é exibida
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/0af3ab99-e411-4e74-b379-469600fbad6d)

## Páginal Inicial Doutor
Na página incial do Doutor é apresentado alguns dados dos Pacientes cadastrados no sistema além de uma barra de pesquisa para pesquisar pelo nome do Paciente, além disso também é exibido um botão de Logout para deslogar o doutor atual do sistema.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/6f999390-bad0-44ef-b646-7afed6fa3aed)

## Cadastrar Paciente
Na página cadastro de pacientes temos alguns campos para preencher referentes ao paciente que iremos cadastrar no sistema.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/0ec6f538-6c56-4137-bd47-400c05d6dfde)

<b>Erro de CPF:</b>
Caso o CPF digitado tenha mais que 11 digítos ao tentar cadastrar será exibido o erro.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/8011601b-cc05-4f3f-b56e-767b5c557b71)

<b>CEP Existente:</b> Ao digitar o número de um CEP existente os campos: Endereço,Bairro,Cidade e Estado são preenchidos automaticamente.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/9b12ccd9-cf89-4839-83bc-10cf34a7fd3c)

<b>CEP Inexistente:</b> 
Ao digitar um CEP inexistente é apresentada uma mensagem de CEP não encontrado.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/52171711-67b0-4b46-bec9-e61bcf89f349)

<b>Efetuar Cadastro:</b>
Ao digitar os dados e clicar em Cadastrar, é exibida uma mensagem para clicar em <b>OK</b> para cadastrar o Paciente e <b>Cancel</b> para Cancelar o cadastro.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/f2a77113-7cde-4415-b01e-1a234efc36de)

## Excluir Paciente
Ao clicar no botão excluir do Paciente Cadastrado é exibida uma mensagem para clicar em OK para exclusão e Cancel para cancelar a Exclusão.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/fdf2f140-dbc2-4df0-af63-83e08f80ac74)

## Editar Paciente
O Editar Paciente possui o mesmo compartamento do Cadastrar Paciente, porém é preenchido os campos com os dados do Paciente a ser alterado para poder alterar todos os dados do Paciente já existente.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/3eae6b00-fd83-4a11-86aa-c7fdf31b48c0)

## Cadastrar Doutor
<b>Cadastro Efetuado:</b> Ao tentar cadastrar um Doutor é verificado se o e-mail já existe cadastrado como Secretária ou Doutor, caso não esteja cadastrado ainda é exibida a mensagem Registrado com Sucesso!
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/5fb2e43f-2f25-4d06-b065-0abbd77a32cc)

<b>E-mail Já Existente:</b> Ao tentar cadastar um Doutor caso o e-mail já foi cadastrado como Secretária ou Doutor a mensagem, E-mail já existe é exibida e não é efetuado o cadastro.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/540e9793-fb93-4ebe-853f-b81fdc7a57d0)

## Secretárias
Na página Secretárias é listado todas as secretárias cadastradas no sistema, além disso temos a barra de pesquisa para pesquisar uma secretária pelo nome e temos os botões de <b>NOVO</b> para cadastrar uma nova secretária, <b>Editar</b> para editar uma secretária e <b>Excluir</b> para Excluir uma secretária.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/b838c35e-7804-43f1-955e-26c21cfc591b)

## Cadastrar Secretária
Na página de Cadastro de Secretária possuimos os campos Nome,E-mail e senha para efetuar o cadastro da secretária, caso o e-mail digitado já exista seja cadastrado como Doutor ou Secretária a mensagem de Erro <b>E-mail já existe</b> é exibida.
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/8f484216-da1c-4346-8567-fdbac7879614)


## Página Inicial Secretária
Ao logar com o usuário de uma Secretária, o usuário terá acesso a lista dos pacientes cadastrados, barra de pesquisa para pesquisar os Pacientes, irá poder Cadastrar um paciente ao clicar no botão <b>NOVO</b> vai poder Editar um paciente ao clicar no botão <b>Editar</b> e excluir um paciente ao clicar no botão <b>Excluir.</b>
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/51f32982-a276-4a0d-a8ca-f22384b5b85b)




## Logout
Ao clicar no botão de Logout é exibida a mensagem de Confirmação para escolher <b>sim</b> para deslogar o usuário atual do sistema, seja um Doutor ou uma Secretária, e <b>Não</b> para não efetuar o Logout do usuário atual.</br>
</br>
<b>Logout Doutor:</b>
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/04e2c5c8-f8e5-426b-a034-b3cbe0a274a8)

<b>Logout Secretária:</b>
![image](https://github.com/matheusvieira300/Projeto-Final/assets/53275513/75de51a1-4bed-41ac-a5e2-e42c5533ffc9)




# Autor
Matheus Gomes Vieira
