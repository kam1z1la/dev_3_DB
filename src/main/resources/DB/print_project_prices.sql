--Task_7 very hard
select project.id, datediff(month,starte_date,finish_date) * sum(worker.salary) as price
from project
JOIN project_worker ON project.id = project_worker.project_id
JOIN worker ON  project_worker.worker_id = worker.id
group by project.id
order by price desc;