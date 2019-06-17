# build
./mvnw clean install

# run
./mvnw spring-boot:run

# check

`curl -X GET http://localhost:8080/contact/1/lastapp`

`curl -X GET http://localhost:8080/contact/2/lastapp`

`curl -X GET http://localhost:8080/contact/3/lastapp`
