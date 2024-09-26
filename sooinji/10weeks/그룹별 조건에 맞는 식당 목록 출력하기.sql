select member_name, review_text, DATE_FORMAT(review_date, '%Y-%m-%d') AS review_date
from
(select m.member_id, member_name, review_text, review_date
from member_profile as m inner join rest_review as r on m.member_id = r.member_id
where m.member_id = (
SELECT member_id
FROM (
    SELECT COUNT(*) AS cnt, member_id
    FROM REST_REVIEW 
    GROUP BY member_id
    ORDER BY cnt DESC 
        LIMIT 1
) AS sub1)) AS sub2
order by review_date, review_text
