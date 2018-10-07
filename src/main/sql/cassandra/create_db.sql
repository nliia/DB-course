
Выполнять в Cassandra CQL

create keyspace db_course with replication = {'class':'SimpleStrategy', 'replication_factor':6};
create table db_course.users (id uuid primary key, name text, surname text, address text);

create type db_course.signal (date timestamp, latitude double, longitude double);

create table db_course.signals (
  id text primary key,
  signal map<UUID, FROZEN<signal>>
);