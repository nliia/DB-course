package com.pinum.dbcourse;

import com.pinum.dbcourse.dao.SignalPostgresDao;
import com.pinum.dbcourse.dao.UserPostgresDao;
import com.pinum.dbcourse.entity.Signal;
import com.pinum.dbcourse.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserPostgresDao userPostgresDao;

    @Autowired
    private SignalPostgresDao signalPostgresDao;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
//        JdbcTestUtils.deleteFromTables(userPostgresDao.getJdbc(), "users");
        User user = userService.generateUser();
        Long save = userPostgresDao.save(user);
        System.out.println("Saved user: " + user);
    }

    @Test
    public void testBatchInsert() {
        List<User> users = IntStream.range(0, 1000000).mapToObj(value -> userService.generateUser()).collect(Collectors.toList());
        userPostgresDao.batchSave(users);
    }

    @Test
    public void testSaveSignal() {
        userService.generateSignals(Duration.ofMinutes(30), 100);
    }

    @Test
    public void testFindAllSignals() {
        List<Signal> all = signalPostgresDao.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testUsersInsertFor60Seconds() {
//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        final int[] i = {0};
        ExecutorService executorService = Executors.newCachedThreadPool();
        Instant start = Instant.now();
        System.out.println(LocalTime.from(start.atZone(ZoneId.of("GMT+3"))));
        while ((Instant.now().minus(60, ChronoUnit.SECONDS)).isBefore(start)) {

            User user = userService.generateUser();
            userPostgresDao.save(user);
            /*executorService.submit(() -> {
                User user = userService.generateUser();
                user.setAddress(Thread.currentThread().getName());
                UUID save = userPostgresDao.save(user);

//                i[0]++;
//                atomicInteger.getAndIncrement();
            });*/
        }
        Instant end = Instant.now();
        System.out.println(LocalTime.from(end.atZone(ZoneId.of("GMT+3"))));
//        System.out.println(i[0]);
//        System.out.println(atomicInteger.toString());
    }
}
