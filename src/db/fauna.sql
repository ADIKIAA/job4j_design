create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name,avg_age, discovery_date) values ('blackfish', 30000, '1856-02-01');
insert into fauna(name,avg_age, discovery_date) values ('fishwhite', 15000, null);
insert into fauna(name,avg_age, discovery_date) values ('karp', 17000, '1845-09-03');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
