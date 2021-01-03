# Covid19 Info Mail Sender

<p align="center">
<img src="https://github.com/NrktSLL/covid19-info-mail-sender/blob/master/images/covid19-info-mail-sender.png" alt="Covid19 Info Mail Sender" width="75%" height="75%"/> 
</p>

>This is an Apache ActiveMQ Artemis example with jms and Mail sending example. I wish you healthy days.

>I used [this](https://disease.sh/) API to get daily case information

## Prerequisites

*  Docker **20.10.0** or more recent
*  Docker Compose **1.27.4** or more recent

## Installation
>Firstly to send an e-mail, relevant environments must first be defined for the **.env** file or relevant prod/dev(dev mode runs project embedded. No external ActiveMQ needed) properties file. Otherwise project will give an error;
> * SMTP_HOST
> * SMTP_USERNAME
> * SMTP_PASSWORD

>After making the necessary SMTP definitions;
```sh
docker-compose up -d
```

## Used Dependencies
* Spring Boot Web
* Spring Boot Thymeleaf *(for email template)*
* Spring Boot Data JPA
* Spring Boot Validation
* H2 Database *(runs embedded in any profile and to keep e-mail information)*
* Spring Boot Actuator
* Spring Boot Mail
* Spring Boot Artemis
* Spring Boot Quartz
* Artemis Jms Server *(to run in embedded mode)*
* Lombok
* Dev Tools
* okhttp
* Configuration Processor
* Swagger (Springfox)


## Swagger
> **Access : http://localhost:8080/api/**
>

<img src="https://github.com/NrktSLL/covid19-info-mail-sender/blob/master/images/covid19-info-mail-sender-swagger.png" alt="Covid19 Info Mail Sender" width="100%" height="100%"/> 

## Mail Content Example;

<img src="https://github.com/NrktSLL/covid19-info-mail-sender/blob/master/images/covid19-info-mail-sender-mail-content.png" alt="Covid19 Info Mail Sender" width="50%" height="50%"/> 
