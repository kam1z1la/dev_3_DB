--Task_3
select name, MAX(salary) from worker where level='Senior' group by name ;