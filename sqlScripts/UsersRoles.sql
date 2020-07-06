create table users_roles
(
    role_id int not null,
    user_id int not null,
    primary key (role_id, user_id),
    constraint users_roles_fk1
        foreign key (role_id) references roles (id)
            on update cascade on delete cascade,
    constraint users_roles_fk2
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
);