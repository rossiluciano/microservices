drop table ACCOUNT if exists;
create table ACCOUNT (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50) not null, BALANCE decimal(8,2), unique(NUMBER));
ALTER TABLE ACCOUNT ALTER COLUMN BALANCE SET DEFAULT 0.0;