create extension if not exists pgcrypto;

create table users (
  id UUID primary key,
  name varchar,
  surname varchar,
  address varchar
);

create table signal (
  id varchar primary key,
  signal jsonb
);

create index signal_hash_index
  on signal
  using hash (id);