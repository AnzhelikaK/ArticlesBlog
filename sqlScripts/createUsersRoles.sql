create table users_roles
(
	role_id int null,
	user_id int null,
	constraint users_roles_fk1
		foreign key (role_id) references roles (id),
	constraint users_roles_fk2
		foreign key (user_id) references users (id)
);

alter table users_roles modify role_id int not null;

alter table users_roles
	add constraint users_roles_pk
		primary key (role_id, user_id);
