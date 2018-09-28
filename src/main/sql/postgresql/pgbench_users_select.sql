-- \set id 'c1a9cc69-c04c-4d23-9331-ccd040c25562'
begin;
  select * from users where id = uuid_generate_v4();
end;


--
-- select md5(random() :: text);
-- select md5(random() :: text), uuid_generate_v4();