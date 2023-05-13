create table if not exists worker (
id identity primary key,
name varchar(1000) not null check (LENGTH(name)>=2 and LENGTH(name)<1000),
birthday date check (year(birthday)>'1900'),
level varchar(15) not null check (level in('Trainee', 'Junior', 'Middle', 'Senior')),
salary integer check (salary>=100 and salary<=100000)
);


create table if not exists client (
id identity primary key,
name varchar(1000) not null check (length(name)>=2 and length(name)<=1000)
);


create table if not exists project (
id identity primary key,
client_id bigint,
starte_date date,
finish_date date,
foreign key (client_id) references client(id) on update cascade on delete cascade
);

alter table project alter column client_id set not null;
alter table project add constraint test_month check (datediff(month,starte_date,finish_date) between 1 and 100);

create table if not exists project_worker (
project_id bigint not null,
worker_id bigint not null,
primary key (project_id,worker_id),
foreign key (project_id) references project(id) on update cascade on delete cascade,
foreign key (worker_id) references worker(id) on update cascade on delete cascade
);