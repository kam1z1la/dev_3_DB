--Task_3
select name, MAX(salary) as max_salary from worker where level='Senior' group by name ;