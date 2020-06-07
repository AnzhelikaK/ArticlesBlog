create table articles
(
	id int auto_increment,
	title varchar(255) default 'title' null,
	text text null,
	status varchar(255) null,
	author int null,
	created datetime default current_timestamp null,
	updated datetime default current_timestamp null,
	constraint articles_pk
		primary key (id)
);

alter table articles
	add constraint articles_users_id_fk
		foreign key (author) references users (id);



