# Virtual threads

Este exemplo mostra o uso da tecnologia Virtual threads lançada em Preview no Java 19 (https://www.infoq.com/news/2022/09/java19-released/)

## Como executar?

O Arquivo VirtualThreads contém exemplo de uso comparando o uso de Threads comuns e Virtual Threads. Para comparar basta trocar a instância do ExecutorService:

```java
ExecutorService executor = Executors.newFixedThreadPool(400); // Com pool de Threds
ExecutorService executorVirtual = Executors.newVirtualThreadPerTaskExecutor(); // Com Virtual Threads
```
Para mais detalhes, não deixe de ver o vídeo explicativo: https://youtu.be/6zr4h7a7ALA