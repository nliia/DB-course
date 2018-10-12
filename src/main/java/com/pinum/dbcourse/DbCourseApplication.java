package com.pinum.dbcourse;

import com.pinum.dbcourse.cassandra.CassandraDataGenerator;
import lombok.val;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbCourseApplication {
    public static void main(String[] args) {
        val dataGenerator = new CassandraDataGenerator();
        dataGenerator.generateSignals(1000);
        dataGenerator.disconnect();
    }
}
