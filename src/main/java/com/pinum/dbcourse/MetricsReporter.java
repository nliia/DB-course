package com.pinum.dbcourse;

import com.codahale.metrics.CsvReporter;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metrics;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author lnurullina
 */
public class MetricsReporter {

    public static void main(String[] args) {
        Cluster cluster = Cluster.builder()
                .build();
        Metrics metrics = cluster.getMetrics();

        CsvReporter csvReporter = CsvReporter.forRegistry(metrics.getRegistry())
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .convertRatesTo(TimeUnit.SECONDS)
                .build(new File("/sql/cassandra/users_insert.sql"));
        csvReporter.start(30, TimeUnit.SECONDS);

    }
}
