
create table tags
(
    id   int auto_increment
        primary key,
    name varchar(255) not null,
    constraint tags_name_uindex
        unique (name)
);
