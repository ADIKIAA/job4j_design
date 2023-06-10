create table movie(
	id serial primary key,
	name text,
	director text
);

create table book(
	id serial primary key,
	title text,
	author text
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
	   	  
select name from movie
intersect
select title from book;

select title from book
except
select name from movie;

(select name from movie
union
select title from book)
except
(select name from movie
intersect
select title from book);

	   
	
	   
	   
	   
	   
	   