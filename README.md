# AwesomePizzaManager

## Project Overview

Come pizzeria "Awesome Pizza" voglio creare il mio nuovo portale per gestire gli ordini dei miei clienti. Il portale non
richiede la registrazione dell'utente per poter ordinare le sue pizze. Il pizzaiolo vede la coda degli ordini e li può
prendere in carico uno alla volta. Quando la pizza è pronta, il pizzaiolo passa all'ordine successivo. L'utente riceve
il suo codice d'ordine e può seguire lo stato dell'ordine fino all'evasione.
Come team, procediamo allo sviluppo per iterazioni. Decidiamo che nella prima iterazione non sarà disponibile
un'interfaccia grafica, ma verranno create delle API al fine di ordinare le pizze e aggiornarne lo stato. Decidiamo di
utilizzare il framework Spring e Java (versione a tua scelta).

Decidiamo di progettare anche i test di unità sul codice oggetto di sviluppo.

## Build Instructions

To build the project, run:

```sh
mvn clean install
```

To build the Docker image:

```sh
mvn clean install -Pdocker
```

## Running the Project

To run the project, start the required services using the `docker-componse.support.yml` file located in the `support`
directory.
Then, you can run the project either with Java or using Docker (`docker-componse.pizza.yml`).

## API Documentation (Swagger)

The Swagger documentation is available without authentication at:

```
http://localhost:9000/pizza-manager/swagger-ui/index.html
```
## Workflow

1. Customer Order Flow

Create an order: The customer submits a pizza order.

POST /api/v1/customer/createOrder

Get order details: The customer retrieves the order status.

GET /api/v1/customer/getOrder/{orderId}

Pay for the order: The customer marks the order as paid.

PUT /api/v1/customer/payOrder/{orderId}

2. Pizza Maker Flow

Retrieve a new order: The pizza maker fetches the next order in the queue.

GET /api/v1/pizza-man/getNewOrder/

Update order status: The pizza maker progresses the order status.



