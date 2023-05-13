insert into worker(name,birthday, level, salary)
values
('Oksana','1984-10-12','Trainee',500),
('Bohdan','1980-12-02','Trainee',600),
('Ivan','2002-07-12','Junior',1100),
('Andrii','2002-01-25','Junior',1100),
('Oleksandr','2001-08-13','Junior',1100),
('Petro','1999-08-19','Middle',2700),
('Sofia','1997-03-10','Middle',2700),
('Bohdan','1998-12-03','Middle',3800),
('Kateryna','1996-04-28','Middle',3800),
('Ivanna','1991-07-05','Senior',5150),
('Yaroslav','1989-10-23','Senior',5150);


insert into client(name)
values
('Ivan Petrov'),
('Oksana Ivanenko'),
('Andrii Kovalenko'),
('Kateryna Mykhailiuk'),
('Bohdan Lysak');

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


insert into project_worker(project_id, worker_id)
values
(select id from project where starte_date='2023-02-01', select id from worker where name='Oksana' and level='Trainee'),
(select id from project where starte_date='2023-02-01', select id from worker where name='Bohdan' and level='Trainee'),
(select id from project where starte_date='2023-02-01', select id from worker where name='Ivan'and level='Junior'),

(select id from project where starte_date='2023-12-15', select id from worker where name='Oleksandr' and level='Junior'),
(select id from project where starte_date='2023-12-15', select id from worker where name='Petro' and level='Middle'),
(select id from project where starte_date='2023-12-15', select id from worker where name='Sofia' and level='Middle'),
(select id from project where starte_date='2023-12-15', select id from worker where name='Bohdan' and level='Middle'),

(select id from project where starte_date='2023-08-01', select id from worker where name='Kateryna' and level='Middle'),
(select id from project where starte_date='2023-08-01', select id from worker where name='Ivanna' and level='Senior'),
(select id from project where starte_date='2023-08-01', select id from worker where name='Yaroslav' and level='Senior'),

(select id from project where starte_date='2023-05-15', select id from worker where name='Kateryna' and level='Middle'),
(select id from project where starte_date='2023-05-15', select id from worker where name='Petro' and level='Middle'),
(select id from project where starte_date='2023-05-15', select id from worker where name='Yaroslav' and level='Senior'),

(select id from project where starte_date='2023-09-01', select id from worker where name='Petro' and level='Middle'),
(select id from project where starte_date='2023-09-01', select id from worker where name='Yaroslav'and level='Senior'),

(select id from project where starte_date='2023-11-01', select id from worker where name='Sofia' and level='Middle'),
(select id from project where starte_date='2023-11-01', select id from worker where name='Oleksandr' and level='Junior'),
(select id from project where starte_date='2023-11-01', select id from worker where name='Petro' and level='Middle'),

(select id from project where starte_date='2023-10-15', select id from worker where name='Oleksandr' and level='Junior'),
(select id from project where starte_date='2023-10-15', select id from worker where name='Bohdan' and level='Middle'),
(select id from project where starte_date='2023-10-15', select id from worker where name='Petro' and level='Middle'),

(select id from project where starte_date='2023-03-01', select id from worker where name='Petro' and level='Middle'),
(select id from project where starte_date='2023-03-01', select id from worker where name='Yaroslav' and level='Senior'),

(select id from project where starte_date='2023-04-15', select id from worker where name='Oleksandr' and level='Junior'),
(select id from project where starte_date='2023-04-15', select id from worker where name='Bohdan' and level='Middle'),

(select id from project where starte_date='2023-11-15', select id from worker where name='Oleksandr' and level='Junior'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Petro' and level='Middle'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Sofia' and level='Middle'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Bohdan' and level='Middle'),
(select id from project where starte_date='2023-11-15', select id from worker where name='Ivanna' and level='Senior');