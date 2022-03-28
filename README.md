# ansible-jenkins-cicd
This repository creates  a cicd pipeline using Jenkins, Ansible, SpringBoot

To run unit tests :
mvn test -Punit-tests

To run Integration tests:
mvn test -Pintegration-tests

To run this springboot application :
mvn spring-boot:run

Once application is running, hit this endpoint to get a response :
http://localhost:8099/v1/user/1