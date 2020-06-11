create table articles_tags
(
	article_id int not null,
	tag_id int not null,
	constraint articles_tags_pk
		primary key (article_id, tag_id),
	constraint articles_tags___fk_article_id
		foreign key (article_id) references articles (id)
			on update cascade on delete cascade,
	constraint articles_tags___fk_tag_id
		foreign key (tag_id) references tags (id)
			on update cascade on delete cascade
);
