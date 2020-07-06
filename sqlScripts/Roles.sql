create table roles
(
	id int auto_increment,
	name varchar(50) not null,
	constraint roles_pk
		primary key (id)
);

create unique index roles_name_uindex
	on roles (name);

____________full in table

INSERT INTO `artblog`.`roles` (`name`) VALUES ('ADMIN');
INSERT INTO `artblog`.`roles` (`name`) VALUES ('USER');
INSERT INTO `artblog`.`roles` (`name`) VALUES ('PUBLISHER');
INSERT INTO `artblog`.`roles` (`name`) VALUES ('EDITOR');


