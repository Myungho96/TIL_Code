-- 코드를 입력하세요
SELECT date_format(SALES_DATE,"%Y-%m-%d") as SALES_DATE,PRODUCT_ID,USER_ID,SALES_AMOUNT
FROM ONLINE_SALE
WHERE date_format(SALES_DATE,"%Y%m")="202203"
union
SELECT date_format(SALES_DATE,"%Y-%m-%d") as SALES_DATE,PRODUCT_ID,NULL AS USER_ID,SALES_AMOUNT
FROM OFFLINE_SALE
WHERE date_format(SALES_DATE,"%Y%m")="202203"
order by SALES_DATE,PRODUCT_ID,USER_ID