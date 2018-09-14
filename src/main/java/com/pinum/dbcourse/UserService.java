package com.pinum.dbcourse;

import com.github.javafaker.Faker;
import com.pinum.dbcourse.dao.SignalPostgresDao;
import com.pinum.dbcourse.dao.UserPostgresDao;
import com.pinum.dbcourse.entity.Signal;
import com.pinum.dbcourse.entity.User;
import com.pinum.dbcourse.entity.jsonmodels.SignalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private Faker faker;

    @Autowired
    private UserPostgresDao userPostgresDao;

    @Autowired
    private SignalPostgresDao signalPostgresDao;

    public User generateUser() {
        return new User(faker.name().firstName(), faker.name().lastName(), faker.address().fullAddress());
    }

    public List<Signal> generateSignals(Duration durationInThePast, int numUsers) {
        int k = 0;
        List<User> users = userPostgresDao.findAll();
        List<Signal> signals = new ArrayList<>();
        while (k < numUsers && users.size() > 0) {
            User remove = users.remove(faker.random().nextInt(users.size()));
            Instant now = Instant.now();
            long seconds = durationInThePast.getSeconds();
            Instant rndTime = now.minus(faker.random().nextLong(seconds), ChronoUnit.SECONDS);
            SignalInfo signalInfo = new SignalInfo(faker.random().nextDouble() * 180 - 90,
                    faker.random().nextDouble() * 360 - 180, Date.from(rndTime));
            Signal signal = new Signal(remove.getId(), signalInfo);
            signalPostgresDao.save(signal);
            signals.add(signal);
            k++;
        }
        return signals;
    }
}