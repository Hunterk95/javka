drop table if exists proj_table;
drop table if exists user_table;
drop table if exists task_table;

create table task_table
(
    task_id serial not null
        constraint task_table_pk
            primary key,
    task_body text
);

alter table task_table owner to "user";

create unique index task_table_task_id_uindex
    on task_table (task_id);

create table user_table
(
    user_id serial not null
        constraint user_table_pk
            primary key,
    task integer
        constraint users_task_fk
            references task_table
);

alter table user_table owner to "user";

create unique index user_table_user_id_uindex
    on user_table (user_id);

create table proj_table
(
    proj_id serial not null
        constraint proj_table_pk
            primary key,
    "user" integer
        constraint proj_user_fk
            references user_table
);

alter table proj_table owner to "user";

create unique index proj_table_proj_id_uindex
    on proj_table (proj_id);

