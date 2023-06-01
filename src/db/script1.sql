create table carModels(
    id serial primary key,
    model int,
    yearOfRelease date,
    engine_id int references engines(id)
);

create table engines(
    id serial primary key,
    model int,
    yearOfRealease date
);

insert into carModels(model, yearOfRelease, engine_id) values (3456, 10/02/1979, 1);
insert into engines(model, yearOfRealease) values (456, 05/04/1967)

select * from carModels where id in (select engine_id from engines);
select * from engines;



create table banks(
    id serial primary key,
    name varchar(255)
);

create table clients(
    id serial primary key,
    name varchar(255)
);

create table banks_clients(
    id serilal primary key,
    bank_id int references banks(id),
    client_id int references clients(id)
);

insert into banks(name) values ('Green Bank');
insert into banks(name) values ('Black Bank');

insert into clients(name) values ('Ivan');
insert into clients(name) values ('Sveta');

insert into banks_clients(bank_id, client_id) values (1, 1);
insert into banks_clients(bank_id, client_id) values (1, 2);
insert into banks_clients(bank_id, client_id) values (2, 1);
insert into banks_clients(bank_id, client_id) values (2, 2);



create table licenses(
    id serial primary key,
    driver_id int references drivers(id) unique,
    numberseria int
);

create table drivers(
    id serial primary key,
    name varchar(255),
    passport int
);

insert into licenses(driver_id, numberseria) values (1, 123645);
insert into drivers(name, passport) values ('Ivan', 6523645);