-- 进行该配置后，每次启动程序，程序都会运行resources/db/schema.sql文件，对数据库的结构进行操作。

drop table if exists city;
drop table if exists hotel;

create table city (id int primary key auto_increment, name varchar, state varchar, country varchar);
create table hotel (city int, name varchar, address varchar, zip varchar);
