create table drivers(
	id serial primary key, 
	name text
);

create table licenses(
	id serial primary key, 
	number int,
	driver_id int references drivers(id)
);

insert into drivers(name) values ('Ivan');
insert into drivers(name) values ('Artem');
insert into drivers(name) values ('Sveta');

insert into licenses(number, driver_id) values (111, 1);
insert into licenses(number, driver_id) values (222, 2);
insert into licenses(number, driver_id) values (333, 3);

select * from drivers as d
join licenses as l on d.id = l.driver_id;

select d.name Имя, l.number Номер 
from drivers d join licenses l on d.id = l.driver_id;

select  d.id "ID", d.name Имя, l.number Номер
from drivers d join licenses l on d.id = l.driver_id;