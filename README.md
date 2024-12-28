# api-franquicias

Base de datos MySQL
Nombre: api-bd


Docker - Para contenerizar la aplicación y la base de datos.
Docker Compose - Para orquestar los contenedores.
JDK 17 -  ejecución de la aplicación Spring Boot si no usas Docker.
Maven 

Clonar  el repositorio:

git clone https://github.com/SanabriaOscar/api-franquicias.git
cd franquicias-api

Compilar la aplicación con Maven:
mvn clean install

Ejecuta la aplicación:

java -jar target/franquicias-0.0.1-SNAPSHOT.jar