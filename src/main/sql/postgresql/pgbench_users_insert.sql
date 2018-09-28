begin;
insert into users (name, surname, address)
select md5(random() :: text), md5(random() :: text), md5(random() :: text);
end;