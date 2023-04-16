create table worker (
id identity primary key,
name varchar(1000) not null check (LENGTH(name)>=2 and LENGTH(name)<1000),
birthday date check (year(birthday)>'1900'),
level varchar(15) not null check (level in('Trainee', 'Junior', 'Middle', 'Senior')),
salary integer check (salary>=100 and salary<=100000)
);

insert into worker(name,birthday, level, salary)
values
('Oksana','1984-10-12','Trainee',500),
('Bohdan','1980-12-02','Trainee',500),
('Ivan','2002-07-12','Junior',1100),
('Andrii','2002-01-25','Junior',1100),
('Oleksandr','2001-08-13','Junior',1100),
('Petro','1999-08-19','Middle',2700),
('Sofia','1997-03-10','Middle',2700),
('Bohdan','1998-12-03','Middle',3800),
('Kateryna','1996-04-28','Middle',3800),
('Ivanna','1991-07-05','Senior',5150),
('Yaroslav','1989-10-23','Senior',5150);


select * from worker;
--Task_3
select name, MAX(salary) from worker where level='Senior' group by name ;
delete from worker;

create table client(
id identity primary key,
name varchar(1000) not null check (length(name)>=2 and length(name)<=1000)
);

insert into client(name)
values
('Ivan Petrov'),
('Oksana Ivanenko'),
('Andrii Kovalenko'),
('Kateryna Mykhailiuk'),
('Bohdan Lysak');

select * from client;
delete from client;

create table project(
id identity primary key,
client_id bigint,
starte_date date,
finish_date date,
foreign key (client_id) references client(id)
);

alter table project alter column client_id set not null;
alter table project add constraint test_month check (datediff(month,starte_date,finish_date) between 1 and 100);

insert into project(client_id, starte_date, finish_date)
values
(select id from client where name='Ivan Petrov','2023-02-01','2030-08-01'),
(select id from client where name='Oksana Ivanenko','2023-12-15','2027-03-15'),
(select id from client where name='Andrii Kovalenko','2023-08-01','2030-10-01'),
(select id from client where name='Kateryna Mykhailiuk','2023-05-15','2029-08-15'),
(select id from client where name='Bohdan Lysak','2023-09-01','2030-10-01'),
(select id from client where name='Ivan Petrov','2023-11-01','2027-03-15'),
(select id from client where name='Oksana Ivanenko','2023-10-15','2027-03-15'),
(select id from client where name='Andrii Kovalenko','2023-03-01','2030-05-01'),
(select id from client where name='Kateryna Mykhailiuk','2023-04-15','2027-03-15'),
(select id from client where name='Bohdan Lysak','2023-11-15','2029-08-15');


select * from project;
delete from project;

create table project_worker (
project_id bigint not null,
worker_id bigint not null,
primary key (project_id,worker_id),
foreign key (project_id) references project(id),
foreign key (worker_id) references worker(id)
);

insert into project_worker(project_id, worker_id)
values
(select id from project where starte_date='2023-02-01', select id from worker where name='Oksana'),
(select id from project where starte_date='2023-02-01', select id from worker where name='Bohdan' and level='Trainee'),
(select id from project where starte_date='2023-02-01', select id from worker where name='Ivan'),

(select id from project where starte_date='2023-12-15', select id from worker where name='Oleksandr'),
(select id from project where starte_date='2023-12-15', select id from worker where name='Petro'),
(select id from project where starte_date='2023-12-15', select id from worker where name='Sofia'),
(select id from project where starte_date='2023-12-15', select id from worker where name='Bohdan' and level='Middle'),

(select id from project where starte_date='2023-08-01', select id from worker where name='Kateryna'),
(select id from project where starte_date='2023-08-01', select id from worker where name='Ivanna'),
(select id from project where starte_date='2023-08-01', select id from worker where name='Yaroslav'),

(select id from project where starte_date='2023-05-15', select id from worker where name='Kateryna'),
(select id from project where starte_date='2023-05-15', select id from worker where name='Petro'),
(select id from project where starte_date='2023-05-15', select id from worker where name='Yaroslav'),

(select id from project where starte_date='2023-09-01', select id from worker where name='Petro'),
(select id from project where starte_date='2023-09-01', select id from worker where name='Yaroslav'),

(select id from project where starte_date='2023-11-01', select id from worker where name='Sofia'),
(select id from project where starte_date='2023-11-01', select id from worker where name='Oleksandr'),
(select id from project where starte_date='2023-11-01', select id from worker where name='Petro'),

(select id from project where starte_date='2023-10-15', select id from worker where name='Oleksandr'),
(select id from project where starte_date='2023-10-15', select id from worker where name='Bohdan' and level='Middle'),
(select id from project where starte_date='2023-10-15', select id from worker where name='Petro'),

(select id from project where starte_date='2023-03-01', select id from worker where name='Petro'),
(select id from project where starte_date='2023-03-01', select id from worker where name='Yaroslav'),

(select id from project where starte_date='2023-04-15', select id from worker where name='Oleksandr'),
(select id from project where starte_date='2023-04-15', select id from worker where name='Bohdan' and level='Middle'),

(select id from project where starte_date='2023-11-15', select id from worker where name='Oleksandr'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Petro'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Sofia'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Bohdan' and level='Middle'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Ivanna');

select * from project_worker;
delete from project_worker;