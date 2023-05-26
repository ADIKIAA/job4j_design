create table employees(
	id serial primary key,
	name text,
	position text,
	salary int
);

insert into employees(name, position, salary) values('Ivan', 'Engineer', '40000');

update employees set position = 'Designer';

delete from employees;
