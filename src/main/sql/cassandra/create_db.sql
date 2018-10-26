
Выполнять в Cassandra CQL

create keyspace db_course with replication = {'class':'SimpleStrategy', 'replication_factor':6};
create table db_course.users (id uuid primary key, name text, surname text, address text);

-- create type db_course.signal (date timestamp, latitude double, longitude double);

create table db_course.signals (id uuid primary key, date timestamp, latitude double, longitude double);

create table db_course.frequency(geo_name text primary key, frequency counter);

create MATERIALIZED VIEW db_course.signals_by_date as SELECT date, latitude, longitude FROM db_course.signals
  WHERE date IS NOT NULL
  primary key(date, id);