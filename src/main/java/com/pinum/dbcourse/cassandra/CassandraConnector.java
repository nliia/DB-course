package com.pinum.dbcourse.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.stereotype.Component;

/**
 * @author lnurullina
 */
@Component
public class CassandraConnector {

    private Cluster cluster;

    private Session session;

    public CassandraConnector(){
        connect("127.0.0.1", 9142);
    }
    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
