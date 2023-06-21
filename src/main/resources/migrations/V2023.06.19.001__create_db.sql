create table if not exists client (
id identity,
name varchar(200),
primary key (id)
);

alter table client  add constraint name_length check (length(name) >= 3 and length(name) <= 200);

create table if not exists planet (
id varchar(20),
name varchar(500),
primary key (id),
check (id = upper(id)),
check (length(name) >= 1 and length(name) <= 500)
);

create table if not exists ticket (
id identity,
created_at time with time zone default CURRENT_TIMESTAMP at  time zone 'UTC',
client_id int not null,
from_planet_id  varchar(500) not null,
to_planet_id  varchar(500) not null,
primary key (id),
foreign key (client_id) references  client(id)
     on delete cascade
     on update cascade,
foreign key (from_planet_id) references  planet(id)
     on delete cascade
     on update cascade,
foreign key (to_planet_id) references  planet(id)
     on delete cascade
     on update cascade
);