SELECT
    'ELDEST' AS TYPE,
    name,
    birthday
FROM worker
WHERE birthday= (SELECT MIN(birthday)  FROM worker)
UNION
SELECT
    'YOUNGEST' AS TYPE,
    name,
    birthday
FROM worker
WHERE birthday = (SELECT MAX(birthday) FROM worker)
ORDER BY TYPE DESC;