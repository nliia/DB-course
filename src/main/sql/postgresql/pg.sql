create extension if not exists pgcrypto;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create extension if not exists "pg_trgm";

-- select uuid_generate_v4();

create table if not exists users (
  id bigserial primary key,
  name varchar,
  surname varchar,
  address varchar
);

create table if not exists signal (
  id varchar primary key,
  signal jsonb
);

-- create index signal_btree_idx
--   on signal
--   using btree (id);

-- create index signal_gin_idx on signal using gin(id gin_trgm_ops);

-- drop index signal_gin_idx

create index signal_text_pattern_idx on signal(id TEXT_PATTERN_OPS);

-- create index signal_hash_index
--   on signal
--   using hash (id);

create index users_hash_index
  on users
  using hash (id);

/*
select count(*)
from users;

show all;

delete from users;

SELECT schemaname, relname, n_live_tup, n_dead_tup, last_autovacuum
FROM pg_stat_all_tables;*/