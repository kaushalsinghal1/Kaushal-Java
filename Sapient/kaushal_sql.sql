-- usecase1
select * from inventory order by MAKE;


---usecase2 to show all inventory records sorted by Make (Alphabetic order) and Cost (Highest cost at top)

select * from inventory order by MAKE, COST desc;

-- usecase 3 to show all HONDA cars

select * from INVENTORY where MAKE='HONDA';


--4 to show all HONDA cars before 2011 year

select * from INVENTORY where MAKE='HONDA' and YEAR <2011;

--5 to show all HONDA or BMW cars 2011 or beyond

select * from INVENTORY where MAKE in ('HONDA','BMW') and YEAR>= 2011 order by MAKE;


--6 to show total cost of all Honda cars
select sum(COST) from INVENTORY where MAKE='HONDA';

-- 7 to show total cost of cars grouped by MAKE and YEAR

select MAKE, YEAR, sum(COST) from INVENTORY group by MAKE, YEAR;

--8 SQL to show all car makes with count of cars for each make
select MAKE,COUNT(*) as cars from INVENTORY group by MAKE;

--9 SQL to show all cars makes and year with counts of car for each MAKE and YEAR

select MAKE, YEAR, COUNT(*) as cars from INVENTORY group by MAKE, YEAR;

--10 SQL to show all cars having more than 1 car for same make and year e.g. 2 Honda 2011

select MAKE, YEAR, cars from (select MAKE, YEAR, COUNT(*) as cars from INVENTORY group by MAKE, YEAR) as tab where cars >1 ;

--11 SQL to show cars for having max number of items by make
select * from INVENTORY where make IN 
( select make from (select MAKE,  COUNT (1) as cars from INVENTORY  where ROWNUM=1 group by MAKE order by cars desc) as tab1);

--12 SQL to show cars with cost between $22000 and $30000
select * from INVENTORY where COST BETWEEN 22000 and 30000;

--13 SQL to find most expensive car

SELECT * FROM INVENTORY where COST = (SELECT max(COST) from INVENTORY);

--14 SQL to find 2nd most expensive car

SELECT * FROM (SELECT * FROM INVENTORY where cost < (SELECT max(COST) from INVENTORY) order by cost desc) WHERE ROWNUM =1;

-- 15 SQL to find most expensive and least expensive cars

SELECT * FROM INVENTORY WHERE COST = ( SELECT MAX(COST) FROM INVENTORY)
UNION
SELECT * FROM INVENTORY WHERE COST = ( SELECT MIN(COST) FROM INVENTORY);

--16 SQL to find who put max number of bids

select OFFERED_BY, count (1) as NO_BIDS from AUCTION group by OFFERED_BY 
having count (1) >= (Select max(bids) from ( select count (1) as bids from AUCTION group by OFFERED_BY) );

-- 17 SQL to find who won max number of cars in auction
with WINNING_BIDS as (
SELECT ID, MAX(OFFER) as MAX_OFFER from AUCTION GROUP BY ID)

SELECT OFFERED_BY, a.ID, a.OFFER from AUCTION a, WINNING_BIDS WHERE a.ID= WINNING_BIDS.ID AND OFFER= WINNING_BIDS.MAX_OFFER;

--18 SQL to show which car had max number of bids
select i.ID, i. MAKE FROM INVENTORY i, (
select ID, count (1) as NO_BIDS from AUCTION group by ID 
having count (1) >= (Select max(bids) from ( select count (1) as bids from AUCTION group by ID) )
) o where i.ID= o.ID;

--19 SQL to show which cars did not have any bids

select ID,  MAKE FROM INVENTORY where ID NOT IN (SELECT distinct ID from AUCTION);

--20 SQL to show all cars with their corresponding bids
select i.ID, i.MAKE,i.MODEL, a.OFFERED_BY FROM INVENTORY i LEFT OUTER JOIN AUCTION a ON i.ID= a.ID;

--21 SQL to show all cars with their max bid
with WINNING_BIDS as (
SELECT ID, MAX(OFFER) as MAX_OFFER from AUCTION GROUP BY ID)

SELECT  i.ID,i.MAKE,i.MODEL, w.MAX_OFFER from INVENTORY i LEFT OUTER JOIN WINNING_BIDS w 
 ON  i.ID= w.ID;

--22 SQL to calculate Profit or loss for each car 

with WINNING_BIDS as (
SELECT ID, MAX(OFFER) as MAX_OFFER from AUCTION GROUP BY ID)

SELECT  i.ID,i.MAKE,i.MODEL, w.MAX_OFFER, (w.MAX_OFFER - i.COST) as profit  from INVENTORY i LEFT OUTER JOIN WINNING_BIDS w 
 ON  i.ID= w.ID;
 
 --23 Provide SQL to show AVERAGE bid for each car

with WINNING_BIDS as (
SELECT ID, AVG(OFFER) as AVG_OFFER from AUCTION GROUP BY ID)

SELECT  i.ID,i.MAKE, i.MODEL, w.AVG_OFFER from INVENTORY i LEFT OUTER JOIN WINNING_BIDS w 
 ON  i.ID= w.ID;

