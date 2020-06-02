create table roles
(
	id int auto_increment,
	name varchar(50) not null,
	status varchar(25) default 'NOT_ACTIVE' not null,
	constraint roles_pk
		primary key (id)
);

create unique index roles_name_uindex
	on roles (name);
