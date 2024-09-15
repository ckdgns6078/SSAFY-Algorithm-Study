with sub as (
    select 
        q.*, 
        sum(weight) over (order by turn) as cumul
    from 
        Queue q
)
select
    person_name
from
    sub
where
    cumul <= 1000
order by
    cumul desc
limit 1;
