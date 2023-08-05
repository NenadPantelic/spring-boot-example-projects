Kafka je distribuiran sistem za prenos eventova. Kafka radi u cluster-u (vise node-ova) tako da podrzava repliciranje i particionisanje topic-a kroz vise node-ova. Jednom kada se
poruka procita, moguce je da ostane u queue satima, danima, godinama ili zauvek (kod RabbitMQ, cim se procita poruka, skida se sa queue-a), a poruke mogu biti i beskonacno velike teorijski.

Broker (masina ili VM) koji vrti Kafka proces (prima zahteve i obradjuje ih).

# Kafka - distributed stream processing system.

- Developed by Linkedin, written in Scala and Java.
- Kafka components:

  - Producer
  - Broker (server listening at 9092)
  - Consumer

- It works in a cluster (supports multi-node config), so it also supports replication and partinioning of the topic through multiple nodes. Once the message is read, it is possible for it to stay in queue for many hours, days, years or even forever.

- It is a distributed system (multi-zone, multi-region), it supports many brokers.
- Producer and Broker are connected via TCP (two-way connection)
- Topic - collection of events (replicated and partitioned)
- Broker - a server component (machine or VM) that runs Kafka process (takes requests and process it)
- Use cases: messaging (message broker), data syncing


![](kafka-system.png)