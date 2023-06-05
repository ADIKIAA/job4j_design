create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('TV', 36.700);
insert into devices(name, price) values ('PC', 45.790);
insert into devices(name, price) values ('Phone', 4.400);
insert into devices(name, price) values ('Table', 2.300);

insert into people(name) values ('Ivan');
insert into people(name) values ('Sasha');
insert into people(name) values ('Nick');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (4, 1);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (3, 3);

select avg(price) from devices;

select p.name, avg(price) 
from people p join devices_people dp 
on (p.id = dp.people_id)
join devices d 
on (dp.device_id = d.id)
group by p.name;

select p.name, avg(price)
from people p join devices_people dp
on (p.id = dp.people_id)
join devices d 
on (dp.device_id = d.id)
group by p.name
having avg(price) > 5;