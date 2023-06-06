create table car_bodies (
	id serial primary key,
	name text
);

create table car_engines (
	id serial primary key,
	name text
);

create table car_transmissions (
	id serial primary key,
	name text
);

create table cars (
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Седан');
insert into car_bodies(name) values ('Универсал');
insert into car_bodies(name) values ('Купе');	
insert into car_bodies(name) values ('Хэтчбек');

insert into car_engines(name) values ('V2');
insert into car_engines(name) values ('V4');
insert into car_engines(name) values ('V6');
insert into car_engines(name) values ('V8');
insert into car_engines(name) values ('V12');

insert into car_transmissions(name) values ('МКПП');
insert into car_transmissions(name) values ('АКПП');
insert into car_transmissions(name) values ('РКПП');

insert into cars(name, body_id, engine_id, transmission_id)
values ('Tigo', 1, 1, null);
insert into cars(name, body_id, engine_id, transmission_id)
values ('Sorento', null, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id)
values ('Focus', 2, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('Prado', 2, null, 2);
insert into cars(name, body_id, engine_id, transmission_id)
values ('L200', 3, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('L200', 3, 2, 1);
									
select c.id, c.name, cb.name, ce.name, ct.name from cars c 
left outer join car_bodies cb on (cb.id = c.body_id)
left outer join car_engines ce on (ce.id = c.engine_id)
left outer join car_transmissions ct on (ct.id = c.transmission_id);

select cb.name from cars c right outer join car_bodies cb on (cb.id = c.body_id)
where c.body_id is null;

select ce.name from cars c right outer join car_engines ce on (ce.id = c.engine_id)
where c.engine_id is null;

select ct.name from cars c right outer join car_transmissions ct on (ct.id = c.transmission_id)
where c.transmission_id is null;





