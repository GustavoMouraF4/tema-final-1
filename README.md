#### **Instalando o docker**

Basta abrir o terminal e executar o seguinte comando para instalar o docker no Ubuntu:
$ sudo apt install docker.io

Caso o seu OS nao seja o Ubuntu, acesse: https://www.docker.com/get-started

#### **Rodando a calculadora**
No diretório da aplicação execute os seguintes comandos:

sudo gradle clean build (para gerar o arquivo .jar da sua aplicação);
sudo docker build -f Dockerfile -t calculator . (para criar a imagem calculator no docker);
docker run --network=host calculator (para iniciar o container na network host com a porta 8080 padrão). 
Após isso basta verificar se o passo funcionou no link: localhost:8080.

#### **Utilizando a calculadora**
Insira os valores em X, Y e Z: localhost:8080/calculator/?num1=X&op=Y&num2=Z

Utilizar as operações a seguir (Sempre em caixa alta): SUM SUB DIV MULT POW

Para acessar o histórico da calculadora, insira a url localhost:8080/calculator/?history