create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
	
create trigger tax_statement 
	after insert on products
	referencing new table  as inserted
	for each statement
	execute procedure tax_statement();

create or replace function tax_statement()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.15
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row
	before insert
	on products
	for each row
	execute procedure tax_row();
	
create or replace function tax_row()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.15
		where id = new.id;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';


create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger info_price
	after insert
	on products
	for each row
	execute procedure info_price();
	
create or replace function info_price()
	returns trigger as
$$
	BEGIN
		insert into history_of_price
		(name, price, date) values
		(new.name, new.price, current_date);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';


