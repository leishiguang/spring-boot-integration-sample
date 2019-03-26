-- 进行该配置后，每次启动程序，程序都会运行resources/db/data.sql文件，对数据库的数据操作
insert into city (name, state, country) values ('San Francisco', 'CA', 'US');
insert into hotel(city, name, address, zip) values (1, 'Conrad Treasury Place', 'William & George Streets', '4001')
