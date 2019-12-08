drop schema if exists batch;
create schema batch;

use batch;

drop table if exists person;
create table person
(
    id          bigint unsigned     not null
        primary key,
    name        varchar(100)        not null,
    age         tinyint unsigned    not null,
    gender      tinyint(1) unsigned not null,
    update_date datetime
);

drop table if exists task;
create table task
(
    id               bigint            not null
        primary key,
    task_name        varchar(30)       not null,
    task_status      tinyint default 0 not null,
    task_description varchar(60)       null
);

drop procedure if exists generate;
create
    procedure generate(IN num int)
BEGIN
    DECLARE chars varchar(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    DECLARE name VARCHAR(25) DEFAULT '';
    DECLARE id int UNSIGNED;
    DECLARE len int;
    set id = 1;
    delete from person where 1 = 1;
    WHILE id <= num
        DO
            set len = FLOOR(1 + RAND() * 25);
            set name = '';
            WHILE len > 0
                DO
                    SET name = CONCAT(name, substring(chars, FLOOR(1 + RAND() * 62), 1));
                    SET len = len - 1;
                END WHILE;
            set len = FLOOR(1 + RAND() * 25);
            INSERT into person VALUES (id, name, FLOOR(RAND() * 100), FLOOR(RAND() * 2),null);
            set id = id + 1;
        END WHILE;
END;

call generate(10000);
