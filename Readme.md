# Meteogroup Kata


## Getting started

The following commands are to be run from the command line unless otherwise specified. Obviously the goals can be run from within your IDE of choice supporting maven...

### run tests

`mvn test`

### run the service 

`mvn spring-boot:run` - and check out your very own intimate [localhost](http://127.0.0.1:8080) if no error occurred.

### deployment

the next step creates a docker image based on the *java* image and salutes you with a whopping 650+ MB.

`mvn clean package docker:build`

This will get the freshly docker deployed container up and running:

`docker run -p 8080:8080 --name=mgtest mgtest`

In case you are running your docker containers inside **docker-machine** host or **boot2docker** open [this link](http://192.168.99.100:8080)

