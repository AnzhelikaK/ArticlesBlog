create table users
(
	id int auto_increment,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	password varchar(255) not null,
	email varchar(100) not null,
	constraint users_pk
		primary key (id)
);

create unique index users_email_uindex
	on users (email);
	
alter table users
	add created_at datetime default current_timestamp not null;

alter table users
	add status varchar(25) default 'NOT_ACTIVE' null;

