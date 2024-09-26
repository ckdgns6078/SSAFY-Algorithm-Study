/*
실패 지점 - %Y-%m-%d으로 데이터 포맷팅
*/
SELECT 
MP.MEMBER_NAME, RR.REVIEW_TEXT, DATE_FORMAT(RR.REVIEW_DATE, "%Y-%m-%d") AS REVIEW_DATE
FROM 
MEMBER_PROFILE MP
JOIN REST_REVIEW RR ON MP.MEMBER_ID = RR.MEMBER_ID
WHERE RR.MEMBER_ID = (
    SELECT MEMBER_ID
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
    ORDER BY COUNT(*) DESC
    LIMIT 1
)
ORDER BY RR.REVIEW_DATE ASC, RR.REVIEW_TEXT ASC
;
