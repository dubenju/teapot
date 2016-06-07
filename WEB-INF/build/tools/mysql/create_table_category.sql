分类表

drop table if exists category;

CREATE TABLE category
(
        category_id VARCHAR (3) NOT NULL,
        category_name VARCHAR (60) NOT NULL,
        category_order VARCHAR (3),
        create_date VARCHAR (8) NOT NULL,
        create_time VARCHAR (6) NOT NULL,
        update_date VARCHAR (8) NOT NULL,
        update_time VARCHAR (6) NOT NULL,
        update_user_id VARCHAR (20) NOT NULL,
        update_screen_id VARCHAR (20) NOT NULL,
    PRIMARY KEY(category_id)
) default charset utf8;


insert into category value ('999', '系统管理', '999', '20160607', '202020', '20160607', '202020', 'admin', 'init');
