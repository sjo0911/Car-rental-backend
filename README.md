# Car Rental Backend
A backend for my Car Rental project. 
Created in Java Spring boot with techniques as Eureka Discovery Server and Spring Boot Gateway.
Have started to configure a deployment.yaml so i can run it in a Kubernetes cluster on Okteto and will continue 
with that project in a couple of weeks.

## Essentials

![Microservice architecture](Bild1.png?raw=true "Car rental microservices")

## Usage

Since deployment.yaml is'nt finished yet the easiest way is to start each of the microservices with:
```bash
mvn spring-boot:run
```

The microservices will show some errors until Eureka Discovery Server has been started.

## License

No license!

## Author

Simon Jonasson