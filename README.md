# PrimeNumbers

## This repository contains two services:
  - First one "Producer" created a sequnce of numbers, sends them to the second one "Consumer" and saves them into a csv* file.
  - Second one "Consumer" receives the sequnce of numbers from "Producer" service and finds all prime numbers from the list then saves all prime numbers into a csv* file.

## What you need:
 1) Java 17
 2) Maven
  - *Optional*
 1) Docker
 2)  Postman 

 ## How to run the services
 1) Clone the repo
 2) Run `mvn clean install` in project root 
 3) I've used IntelliJ IDEA as a IDE so if you use it as well it will auto-detect Spring configurations and you can run them both straight away.
 4) If you want to run the services into Docker you can run `docker-compose up --build` in project root


 * CSV files are located in project root. Producer's csv file is named *random_numbers.csv* and consumer's file is named *prime_numbers.csv*
