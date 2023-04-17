--Task_5
select name, datediff(month,starte_date,finish_date) as month_count from project
group by name
having datediff(month,starte_date,finish_date) =
 select max(month_count) from project;