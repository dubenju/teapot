文件存储

drop table if exists blob_file;

CREATE TABLE blob_file
(
        blob_file_id VARCHAR (10) NOT NULL,
        blob_file_name VARCHAR (60) NOT NULL,
        blob_file_type VARCHAR (10),
        blob_file_size decimal (11),
        blob_file_data LongBlob,
        create_date VARCHAR (8) NOT NULL,
        create_time VARCHAR (6) NOT NULL,
        update_date VARCHAR (8) NOT NULL,
        update_time VARCHAR (6) NOT NULL,
        update_user_id VARCHAR (20) NOT NULL,
        update_screen_id VARCHAR (20) NOT NULL,
    PRIMARY KEY(blob_file_id)
) default charset utf8;


insert into blob_file value ('000000001', '测试文件', 'mp3', '0', NULL, '20160607', '202020', '20160607', '202020', 'admin', 'init');

