package com.pinum.dbcourse.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.pinum.dbcourse.entity.jsonmodels.SignalInfo;
import lombok.val;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.UUID;

public class CassandraDataGenerator {

    private static final Random random = new Random();
    private static final String SIGNAL_INSERT = "insert into db_course.signals(id, signal) values (?, {date:" +
            " ?, latitude: ?, longitude: ?})";
    private static final String INCREATE_EAST_COUNTER = "update db_course.frequency set frequency = frequency + 1 where geo_name = 'EAST'";
    private static final String INCREATE_WEST_COUNTER = "update db_course.frequency set frequency = frequency + 1 where geo_name = 'WEST'";
    //    private ObjectMapper objectMapper;
    private InetSocketAddress address;
    private Cluster cluster;
    private Session session;

    public CassandraDataGenerator() {
//        objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.setDateFormat(new StdDateFormat());
        address = new InetSocketAddress("127.0.0.1", 9042);
        cluster = Cluster.builder()
                .addContactPointsWithPorts(address)
                .addContactPointsWithPorts()
                .withoutJMXReporting()
                .build();
        this.session = cluster.connect();
    }

    public void generateSignals(final int signalsCount) {
//        val preparedStatement = session.prepare(SIGNAL_INSERT);
        int count = 0;
        while (count != signalsCount) {
            int randomLongitude = random.nextInt(361) - 180;
            int randomLatitude = random.nextInt(361) - 180;
            val signalInfo = new SignalInfo(randomLatitude, randomLongitude);
            session.execute(SIGNAL_INSERT, UUID.randomUUID(), signalInfo.getTime(), signalInfo.getLatitude(),
                    signalInfo.getLongitude());
//            val boundStatement = preparedStatement.bind()
//                    .setUUID("id", UUID.randomUUID())
//                    .setString("signal", stringSignalInfo);
//            session.execute(boundStatement);
            count++;
            if (randomLatitude <= 0) {
                session.execute(INCREATE_WEST_COUNTER);
            } else {
                session.execute(INCREATE_EAST_COUNTER);
            }
        }
    }

    public void disconnect() {
        this.cluster.close();
    }
}
