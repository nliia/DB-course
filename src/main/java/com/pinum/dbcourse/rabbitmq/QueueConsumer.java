package com.pinum.dbcourse.rabbitmq;


import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    Connection connection;

    public QueueConsumer(String endPointName) throws IOException, ClassNotFoundException, SQLException {
        super(endPointName);
//        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/db_course", "postgres", "postgres");
    }

    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) {
        String request = (String) SerializationUtils.deserialize(body);
        try {
            connection.prepareStatement(request).execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.close();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());

            }
        }

        System.out.println("Request: " + request + " received.");
    }

    public void handleCancel(String consumerTag) {
    }

    public void handleCancelOk(String consumerTag) {
    }

    public void handleRecoverOk(String consumerTag) {
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }
}