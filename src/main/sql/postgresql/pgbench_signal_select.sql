\set id random(1, 100000)
begin;
  select * from signal where id like ''|| :id || ':%';
end;
