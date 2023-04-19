--task_6
select name, birthday,
case
 when birthday = (SELECT MIN(birthday) FROM worker) then 'YOUNGEST'
 when birthday = (SELECT MAX(birthday) FROM worker) then 'OLDEST'
  end as type
  from worker
group by birthday

having type in ('YOUNGEST' ,'OLDEST');