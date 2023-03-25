SELECT id AS "NAME", DATEDIFF (month, start_date, finish_date) AS "MONTH_COUNT"
FROM project
WHERE DATEDIFF (month, start_date, finish_date) IN (
SELECT DATEDIFF (month, start_date, finish_date) 
FROM project
ORDER BY DATEDIFF (month, start_date, finish_date) DESC
LIMIT 1
);