--Task_4
select name, count(project.client_id) as max_project from client
join project on client.id = project.client_id
group by name
having count(project.client_id) =
 (
 select MAX(max_project)
 from (
 select count(client_id) as max_project
 from project group by client_id )
 );