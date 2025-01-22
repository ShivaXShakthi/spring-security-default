create table users(
    id int AUTO_INCREMENT primary key,
    username varchar(50) not null,
    password varchar(500) not null,
    enabled BOOLEAN not null,
    firstname varchar(120),
    lastname varchar(120),
    email varchar(120),
    phno int
);

create table authorities (
    id int AUTO_INCREMENT primary key,
    uid int not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(uid) references users(id)
);

create unique index ix_auth_username on authorities (uid,authority);
