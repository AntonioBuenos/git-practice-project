create table if not exists users
(
    chat_id       bigint not null
        constraint users_pk
            primary key,
    first_name    varchar,
    last_name     varchar,
    user_name     varchar,
    registered_at timestamp
);

alter table users
    owner to postgres;

create unique index if not exists users_chat_id_uindex
    on users (chat_id);

create table if not exists ads
(
    id bigserial
        constraint ads_pk
            primary key,
    ad varchar
);

alter table ads
    owner to postgres;

create unique index if not exists ads_id_uindex
    on ads (id);

