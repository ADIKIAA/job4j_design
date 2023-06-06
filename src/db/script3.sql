create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Колбаса');
insert into type(name) values ('Мороженное');
insert into type(name) values ('Творог');

insert into product(name, type_id, expired_date, price)
values ('Сыр плавленный', 1, '01/08/23', 345.78);
insert into product(name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, '14/08/23', 425.68);
insert into product(name, type_id, expired_date, price)
values ('Сыр косичка', 1, '03/07/23', 235.13);

insert into product(name, type_id, expired_date, price)
values ('Молоко 2.5%', 2, '25.12.22', 73.03);
insert into product(name, type_id, expired_date, price)
values ('Молоко 3.2%', 2, '12/06/23', 94.67);

insert into product(name, type_id, expired_date, price)
values ('Колбаса докторская', 3, '01.12.22', 278.71);
insert into product(name, type_id, expired_date, price)
values ('Колбаса копченая', 3, '13.12.22', 425.68);

insert into product(name, type_id, expired_date, price)
values ('Мороженное шоколадное', 4, '23/10/23', 67.12);
insert into product(name, type_id, expired_date, price)
values ('Мороженное пломбир', 4, '09/10/23', 59.90);

insert into product(name, type_id, expired_date, price)
values ('Творог 5%', 5, '26/06/23', 103.03);
insert into product(name, type_id, expired_date, price)
values ('Творог 9%', 5, '29/06/23', 144.67);

select * from type t join product p on (p.type_id = t.id)
where t.name = 'Сыр';

select * from product where name like '%Мороженное%';

select * from product where expired_date < current_date;

select * from product
where price = (select max(price) from product);

select t.name, count(*) from type t join product p on (t.id = p.type_id) 
group by t.name;

select * from product p join type t on (t.id = p.type_id) 
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name, count(*) from product p join type t on (t.id = p.type_id)
group by t.name
having count(*) < 10;

select t.name, p.name, p.price, p.expired_date 
from product p join type t on (t.id = p.type_id);

