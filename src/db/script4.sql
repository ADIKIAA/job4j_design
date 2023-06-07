create table departments (
	id serial primary key,
	name text
);

create table employees (
	id serial primary key,
	name text,
	salary int,
	department_id int references departments(id)
);

insert into departments(name) 
values ('IT');
insert into departments(name) 
values ('Sales');
insert into departments(name) 
values ('Support');
insert into departments(name)
values ('Building');

insert into employees(name, salary, department_id)
values ('Oleg', 100, 1);
insert into employees(name, salary, department_id)
values ('Ivan', 90, 1);
insert into employees(name, salary, department_id)
values ('Mariya', 110, 2);
insert into employees(name, salary, department_id)
values ('Mark', 75, 2);
insert into employees(name, salary, department_id)
values ('Max', 110, 3);
insert into employees(name, salary, department_id)
values ('John', 90, 3);
insert into employees(name, salary, department_id)
values ('Masha', 120, 2);

select d.name, count(e.name) from departments d left outer join employees e
on (d.id = e.department_id)
group by d.name
having count(e.name) = 0;

select d.id, d.name, e.id, e.name, e.salary, e.department_id 
from departments d left outer join employees e
on (d.id = e.department_id);

select d.id, d.name, e.id, e.name, e.salary, e.department_id 
from employees e right outer join departments d
on (d.id = e.department_id);

create table teens (
	id serial primary key,
	name text,
	gender boolean
);

insert into teens(name, gender) values ('Olya', false);
insert into teens(name, gender) values ('Oleg', true);
insert into teens(name, gender) values ('Masha', false);
insert into teens(name, gender) values ('Max', true);
insert into teens(name, gender) values ('Artem', true);
insert into teens(name, gender) values ('Anna', false);

select * from teens t1 cross join (select * from teens) t2
where t1.gender != t2.gender and t1.id >= t2.id;



