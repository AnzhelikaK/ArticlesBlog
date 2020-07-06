create table comments
(
	id int auto_increment,
	message text null,
	post_id int not null,
	author_id int not null,
	created_at datetime default current_timestamp not null,
	constraint comments_pk
		primary key (id),
	constraint comments___fk_author
		foreign key (author_id) references users (id),
	constraint comments___fk_post
		foreign key (post_id) references articles (id)
);

