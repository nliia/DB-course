package com.pinum.dbcourse.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.pinum.dbcourse.entity.Signal;
import com.pinum.dbcourse.entity.jsonmodels.SignalInfo;
import lombok.val;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.UUID;

public class CassandraDataGenerator {

    private static final Random random = new Random();
    private static final String SIGNAL_INSERT = "insert into db_course.signals(id, signal) values (?, ?)";
    private InetSocketAddress address;
    private Cluster cluster;
    private Session session;

    public CassandraDataGenerator() {
        this.address = new InetSocketAddress("127.0.0.1", 9042);
        this.cluster = Cluster.builder()
                .addContactPointsWithPorts(address)
                .addContactPointsWithPorts()
                .withoutJMXReporting()
                .build();
        this.session = cluster.connect();
    }

    public void generateSignals(final int signalsCount) {
        val preparedStatement = session.prepare(SIGNAL_INSERT);
        int count = 0;
        while (count != signalsCount) {
            int longitude = random.nextInt(361) - 180;
            int latitude = random.nextInt(361) - 180;
            val signalInfo = new SignalInfo(latitude, longitude);
            val signal = new Signal(UUID.randomUUID(), signalInfo);
            val boundStatement = preparedStatement.bind()
                    .setUUID("id", UUID.randomUUID())
//                    .setString("signal", "{"+UUID)
            session.execute(boundStatement);
            count++;
        }
    }
}
