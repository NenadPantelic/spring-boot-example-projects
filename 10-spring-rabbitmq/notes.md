# RabbitMQ

- RabbitMQ is a message broker that originally implements the 
Advance Message Queueing Protocol (AMQP)
  
- AMQP standardizes messaging using Producers (producing the messages),
Broker (holding the messages) and Consumers (consuming those messages)
  
- AMQP standard was designed with the following main characteristics:
    - Security - supports authentication, authorization, LDAP and TLS
      via RMQ plugins
    - Reliability - confirms the message was successfully delivered to the message broker
      and confirms that the message was successfully processed by the consumer
    - Interoperability - the message is transferred as a stream of bytes so any client 
  can operate on it irrespective of the language
  


## RabbitMQ design

1. regular queue
![](images/queue.png)


2. RabbitMQ simplified
   
![](images/rabbitmq.png)

3. RabbitMQ multiple queues (routing key)
   
![](images/rabbitmq-routing.png)
