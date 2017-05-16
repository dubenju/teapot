序列号表

drop table if exists serial_number;

CREATE TABLE serial_number
(
        key1 VARCHAR (10) NOT NULL,
        key2 VARCHAR (10) NOT NULL,
        key3 VARCHAR (10),
        current_value decimal (16) NOT NULL,
        step_value decimal (16) NOT NULL,
        max_value decimal (16) NOT NULL,
        create_date VARCHAR (8) NOT NULL,
        create_time VARCHAR (6) NOT NULL,
        update_date VARCHAR (8) NOT NULL,
        update_time VARCHAR (6) NOT NULL,
        update_user_id VARCHAR (20) NOT NULL,
        update_screen_id VARCHAR (20) NOT NULL,
    PRIMARY KEY(key1, key2, key3)
) default charset utf8;


insert into serial_number value ('system', 'category', 'id', '0', '1', '998', '20160608', '060606', '20160608', '060606', 'admin', 'init');
insert into serial_number value ('business', 'user', 'id', '0', '1', '99999999999', '20170507', '060606', '20170507', '060606', 'admin', 'init');
