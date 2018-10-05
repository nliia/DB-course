\set id random(1, 100000)
begin;
insert into signal (id, signal)
values (:id || ':' || now(), random() :: text :: jsonb);
end;
