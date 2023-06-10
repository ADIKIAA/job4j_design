create table customers(
	id serial primary key,
	first_name text,
	last_name text,
	age int,
	country text
);

insert into customers(first_name, last_name, age, country) 
values ('Ivan', 'Ivanov', 20, 'Russia');
insert into customers(first_name, last_name, age, country) 
values ('Olya', 'Yusypova', 26, 'US');
insert into customers(first_name, last_name, age, country) 
values ('Tom', 'Black', 33, 'Poland');
insert into customers(first_name, last_name, age, country) 
values ('Jack', 'White', 27, 'Germany');
insert into customers(first_name, last_name, age, country) 
values ('Bob', 'White', 20, 'Germany');

select * from customers where age = (select min(age) from customers);

create table orders(
	id serial primary key,
	amount int,
	customer_id int references customers(id)
);

insert into orders(amount, customer_id) 
values (30, 1);
insert into orders(amount, customer_id) 
values (321, 1);
insert into orders(amount, customer_id) 
values (289, 2);
insert into orders(amount, customer_id) 
values (4390, 2);
insert into orders(amount, customer_id) 
values (349, 5);
insert into orders(amount, customer_id) 
values (3999, 5);

select * from customers where customers.id not in (select customer_id from orders);
