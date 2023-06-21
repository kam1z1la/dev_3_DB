insert into client(name)
values
('Ivan Petrov'),
('Oksana Ivanenko'),
('Andrii Kovalenko'),
('Kateryna Mykhailiuk'),
('Bohdan Lysak'),
('Olga Smirnova'),
('Dmytro Volkov'),
('Natalia Sokolova'),
('Petro Ivanov'),
('Yulia Kovalchuk');

insert into planet(id, name)
values
('MERCURY', 'Меркурій'),
('VENUS', 'Венера'),
('MARS', ' Марс'),
('JUPITER', ' Юпітер'),
('SATURN', 'Сатурн');

insert into ticket (client_id , from_planet_id, to_planet_id)
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'MERCURY'
inner join planet to_p on  to_p.id = 'VENUS'
where c.name = 'Ivan Petrov'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'MERCURY'
inner join planet to_p on  to_p.id = 'MARS'
where c.name = 'Oksana Ivanenko'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'JUPITER'
inner join planet to_p on  to_p.id = 'VENUS'
where c.name = 'Andrii Kovalenko'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'SATURN'
inner join planet to_p on  to_p.id = 'JUPITER'
where c.name = 'Kateryna Mykhailiuk'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'VENUS'
inner join planet to_p on  to_p.id = 'MARS'
where c.name = 'Bohdan Lysak'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'MERCURY'
inner join planet to_p on  to_p.id = 'MARS'
where c.name = 'Dmytro Volkov'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'MERCURY'
inner join planet to_p on  to_p.id = 'MARS'
where c.name = 'Natalia Sokolova'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'SATURN'
inner join planet to_p on  to_p.id = 'MERCURY'
where c.name = 'Petro Ivanov'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'MERCURY'
inner join planet to_p on  to_p.id = 'MARS'
where c.name = 'Yulia Kovalchuk'
union
select c.id, from_p.id , to_p.id
from client c
inner join planet  from_p on  from_p.id = 'MARS'
inner join planet to_p on  to_p.id = 'SATURN'
where c.name = 'Olga Smirnova';