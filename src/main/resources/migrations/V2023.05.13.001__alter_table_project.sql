SET REFERENTIAL_INTEGRITY FALSE;
alter table project drop constraint if exists project_FK;
alter table project add constraint project_FK foreign key (client_id) references client(id) on delete cascade;
SET REFERENTIAL_INTEGRITY TRUE;