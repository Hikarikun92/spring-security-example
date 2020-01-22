create table "user" (
  id serial not null primary key,
  username varchar(255) not null unique
);

create table user_credentials (
  user_id int not null primary key references "user",
  password varchar(255) not null
);

create table user_roles (
  user_id int not null references "user",
  roles varchar(255) not null
);