package com.pinum.dbcourse;

import com.codahale.metrics.ConsoleReporter;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metrics;
import com.datastax.driver.core.Session;
import lombok.val;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lnurullina
 */
public class MetricsReporter {

    public static void main(String[] args) {
        Random random = new Random();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9042);
        Cluster cluster = Cluster.builder()
                .addContactPointsWithPorts(address)
                .addContactPointsWithPorts()
                .withoutJMXReporting()
                .build();
        Session session = cluster.connect();
        Metrics metrics = cluster.getMetrics();

        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metrics.getRegistry())
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .convertRatesTo(TimeUnit.SECONDS)
                .build();

        consoleReporter.start(70, TimeUnit.SECONDS);
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() < startTime + 30000) {

//            session.execute("insert into db_course.users (id, name, surname, address) values (" + UUID.randomUUID() + ", 'name', 'surname', 'address')");
//        session.execute("select * from db_course.users where id=now()");

            session.execute("insert into db_course.signals (id, signal) values " +
                    "(" + UUID.randomUUID() + ", {" + UUID.randomUUID() + ": {" +
                    "date: dateof(now()) ," +
                    "latitude: " + (random.nextInt(361) - 180) + "," +
                    "longitude: " + (random.nextInt(361) - 180) +
                    "}})");

        }
    }
}
