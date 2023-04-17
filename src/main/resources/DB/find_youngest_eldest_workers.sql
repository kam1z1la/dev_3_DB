--task_6
select name, birthday,
case
 when (datediff(year,birthday,'2023-01-01')<=23) then 'YOUNGEST'
 when (datediff(year,birthday,'2023-01-01')>=40) then 'OLDEST'
  end as type
  from worker
group by birthday

having type in ('YOUNGEST' ,'OLDEST');
--having delete from worker datediff(year,birthday,'2023-01-01') is null;