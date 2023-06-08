create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price)
values ('product_1', 'producer_1', 15, 150.0);
insert into products(name, producer, count, price)
values ('product_2', 'producer_2', 20, 100.0);
insert into products(name, producer, count, price)
values ('product_3', 'producer_3', 35, 220.0);


create or replace procedure delete_row(d_id int)
language 'plpgsql'
as $$
	BEGIN
	delete from products where id = d_id;
	END
$$;

call delete_row(1);


create or replace function f_take_from_row(f_id int, f_count int)
returns int
language 'plpgsql'
as
$$
	declare
		result int;
		begin 
			update products set count = count - f_count where id = f_id;
			select into result count from products where id = f_id;
			if count <= 0 from products where id = f_id then
				delete from products where id = f_id;
				select into result 0;
			end if;
		return result;
		end;
$$;

select f_take_from_row(1, 10);
select f_take_from_row(3, 35);







