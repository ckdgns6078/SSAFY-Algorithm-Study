SELECT person_name
FROM (
    SELECT *, SUM(weight) OVER (ORDER BY turn) AS sum
    FROM Queue
) AS subquery
WHERE sum <= 1000
ORDER BY turn desc
LIMIT 1
