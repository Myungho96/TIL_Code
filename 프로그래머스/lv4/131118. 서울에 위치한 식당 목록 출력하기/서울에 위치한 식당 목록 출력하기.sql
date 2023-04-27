-- 코드를 입력하세요
SELECT RR.REST_ID,REST_NAME,FOOD_TYPE,FAVORITES,ADDRESS,ROUND(AVG(REVIEW_SCORE),2) SCORE

FROM REST_INFO RI JOIN REST_REVIEW RR ON RI.REST_ID = RR.REST_ID 

WHERE SUBSTRING(ADDRESS,1,2)="서울"
GROUP BY REST_NAME
ORDER BY AVG(REVIEW_SCORE) DESC, FAVORITES DESC