Create table inventory(
id int primary key,
make varchar(20),
model varchar(30),
trim varchar(20),
year int,
color varchar(15),
cost int
);


Create table auction(
id int ,
offer int,
offered_by varchar(15)
);


insert into inventory values(1, 'HONDA', 'ACCORD', 'EXL', 2012,'BLACK', 25000);
insert into inventory values(2, 'BMW', 'X5', '3.0', 2011,'SILVER', 45000);
insert into inventory values(3, 'HONDA', 'ACCORD', 'LX', 2012,'WHITE', 20000);
insert into inventory values(4, 'HONDA', 'CRV', 'LX', 2010,'BLACK', 19000);
insert into inventory values(5, 'TOYOTA', 'CARMY', 'EX', 2011,'WHITE', 22000);

insert into inventory values(6, 'HONDA', 'ACCORD', 'EXL', 2011,'SILVER', 24000);
insert into inventory values(7, 'BMW', 'X3', '3.0', 2012,'SILVER', 35000);
insert into inventory values(8, 'HONDA', 'ACCORD', 'LX', 2011,'BLACK', 19000);
insert into inventory values(9, 'BMW', 'X3', '3.0', 2011,'SILVER', 34000);


insert into auction VALUES('1','26000','Tom');
insert into auction VALUES('1','23000','MAX' ); 
insert into auction VALUES('2','48000','Bob' );
insert into auction VALUES('2','44000','Tom' );
insert into auction VALUES('2','40000','Liz' );
insert into auction VALUES('3','19000','Ryan'); 
insert into auction VALUES('7','26000','Ryan');
insert into auction VALUES('7','29000','Max' );
insert into auction VALUES('7','33000','Liz' );
insert into auction VALUES('7','36000','Tom' ); 