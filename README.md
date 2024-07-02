# CGM_Exercise

## How I built the project

It is split in three levels :
 - dataproviders
 - components
 - controllers
 
To avoid a dependency between the controllers and the usecases, I used the presenter concept. Which means that a usecase does not need to know what the controller needs.

Let's take, for instance, the GET /patient/{id} case. The controller returns a PatientDto, and thanks to the presenter, if we change the PatientDto structure, it will not be necessary to update the associated UseCase class.

## Database

### Patient table

Sql code for the patient table : 

~~~~sql
CREATE TABLE `patient` (
  `id` varchar(36) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `social_security_number` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
~~~~

### Visit table

Sql code for the visit table : 

~~~~sql
CREATE TABLE `visit` (
  `id` varchar(36) NOT NULL,
  `patient_id` varchar(36) NOT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `reason` varchar(15) DEFAULT NULL,
  `family_history` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `VISIT_PATIENT_FK_idx` (`patient_id`),
  CONSTRAINT `VISIT_PATIENT_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) 
~~~~

## What I would have done with more time

### Integration test

Here I only implemented (2 small) unit test that do not really represent reality. With more time, I would have added way more unit tests and integration tests with an embedded db or with a real db which is dedicated to tests.

### Rules validation 

What if we decided to refuse every surname that start by a W ? What if there were some specific rules for patient under 18 ?

Here, there is no dedicated class for rules validation. With more time, I would have used the "io.vavr.control" package that contains Validation library. This is very helpful for rules validation.

### Microservices

Here I chose the monolithic approach. With more time, I would have split the project in two microservices (Patient and Visit).