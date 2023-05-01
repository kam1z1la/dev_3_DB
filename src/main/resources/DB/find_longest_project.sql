select id, datediff(month,starte_date,finish_date) as month_count from project
group by id
having datediff(month,starte_date,finish_date) =
 select max(month_count) from project;