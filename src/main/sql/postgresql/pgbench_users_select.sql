-- \set id 'c1a9cc69-c04c-4d23-9331-ccd040c25562'
\set id random(1, 100000)
begin;
  select * from users where id = :id;
end;
