package ru.dmitriy.rabbit;

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;

public class RabbitStarter {

    public static void main(String[] args) {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().build();
        EmbeddedRabbitMq rabbitMq = new EmbeddedRabbitMq(config);
        rabbitMq.start();

    }

}
