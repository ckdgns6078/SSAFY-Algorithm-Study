select person_name
from (SELECT @sum := @sum+weight as total, person_name 
    FROM (select weight, person_name from queue order by turn) a
    join (select @sum := 0) b) c
where total <= 1000
order by total desc
limit 0,1
