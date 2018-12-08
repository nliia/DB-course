package com.pinum.dbcourse.rabbitmq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");

        String queryInsert;
        String genUUID;
        for (int i = 0; i < 456; i++) {
            genUUID = UUID.randomUUID().toString();
            queryInsert = "insert into users (id, name, surname, address) values ('" +
                    genUUID + "', 'SomeName', 'SomeSurname', 'Pushkina street')";
            String queryUpdate = "update users set name = 'UPDATED_NAME' where id = '" + genUUID + "'";
            String querySelect = "select * from users where id = '" + genUUID + "'";
            producer.sendMessage(queryInsert);
            producer.sendMessage(queryUpdate);
            producer.sendMessage(querySelect);
            System.out.println("Message " + queryInsert + " sent.");
        }
    }
}
