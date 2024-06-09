# ZooKeeper Sandbox

This repository contains a basic example of Apache's ZooKeeper usage.
Made as a part of Distributed Systems course at the AGH University of Science and Technology.

**Language**: Kotlin
<br>**UI**: Jetpack Compose

## Configuration

Before running, please make sure that you have read and configured
ZooKeeper server according to [these](scripts/README.md) instructions.

### Environment variables

| Name          | Description                                                               | Default          |
|---------------|---------------------------------------------------------------------------|------------------|
| `ZK_HOSTNAME` | Server's ip and port                                                      | `127.0.0.1:2181` |
| `ZK_EXT_APP`  | An executable that will be launched/closed on node `/a` creation/deletion | `glxgears`       |

## Usage

1. Start server
2. Execute
   ```bash
   ./gradlew run
   ```
3. Now execute some commands, e.g. `create /a`, `delete /a`, `create /a/b` etc

## Original task

Stworzyć aplikację w środowisku Zookeeper (Java, …) która wykorzystując mechanizm
obserwatorów (watches) umożliwia następujące funkcjonalności:

- Jeśli tworzony jest znode o nazwie "a" uruchamiana jest zewnętrzna aplikacja
  graficzna (dowolna, określona w linii poleceń),
- Jeśli jest kasowany "a" aplikacja zewnętrzna jest zatrzymywana,
- Każde dodanie potomka do "a" powoduje wyświetlenie graficznej informacji na ekranie
  o aktualnej ilości potomków.

- Dodatkowo aplikacja powinna mieć możliwość wyświetlenia całej struktury drzewa "a".
- Stworzona aplikacja powinna działać w środowisku "Replicated ZooKeeper".
- ZooKeeper 3.8.4 API - https://zookeeper.apache.org/doc/r3.8.4/apidocs/zookeeper-server/index.html