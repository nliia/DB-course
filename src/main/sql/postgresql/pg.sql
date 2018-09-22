create extension if not exists pgcrypto;

create table if not exists users (
  id UUID primary key default gen_random_uuid(),
  name varchar,
  surname varchar,
  address varchar
);

create table if not exists signal (
  id varchar primary key,
  signal jsonb
);

create index signal_hash_index
  on signal
  using hash (id);
