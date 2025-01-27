--create table users(
--    id int AUTO_INCREMENT primary key,
--    username varchar(50) not null,
--    password varchar(500) not null,
--    enabled BOOLEAN not null,
--    firstname varchar(120),
--    lastname varchar(120),
--    email varchar(120),
--    phno int
--);
--
--create table authorities (
--    id int AUTO_INCREMENT primary key,
--    uid int not null,
--    authority varchar(50) not null,
--    constraint fk_authorities_users foreign key(uid) references users(id)
--);
--
--create unique index ix_auth_username on authorities (uid,authority);


-- Create the users table
create table users (
    id int AUTO_INCREMENT primary key,
    username varchar(50) not null,
    password varchar(500) not null,
    enabled BOOLEAN not null,
    firstname varchar(120),
    lastname varchar(120),
    email varchar(120),
    phno int
);

-- Create the authorities table
create table authorities (
    id int AUTO_INCREMENT primary key,
    uid int not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(uid) references users(id)
);

-- Create a unique index on authorities table for uid and authority
create unique index ix_auth_username on authorities (uid, authority);

-- Create the refresh table (mapping to RefreshToken entity)
create table refresh (
    id int AUTO_INCREMENT primary key,
    token varchar(255) not null,  -- Adjust length as per your requirements
    expiry_date timestamp not null,
    users_id int not null,
    constraint fk_refresh_users foreign key(users_id) references users(id)
);
