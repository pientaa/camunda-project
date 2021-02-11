create table apartment
(
    id             serial not null
        constraint apartment_pkey primary key,
    name           varchar(255),
    rooms          int,
    size           int,
    description    varchar(255),
    available_from timestamp,
    duration       int
);