SELECT project.id AS "NAME" , SUM ((DATEDIFF (month,start_date,finish_date)*salary) ) AS "PRICE"
FROM project
JOIN project_worker ON project.id=project_worker.project_id
JOIN worker ON project_worker.worker_id=worker.id
GROUP BY project.id
ORDER BY PRICE DESC;