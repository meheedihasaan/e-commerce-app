create table if not exists categories (
    id UUID not null primary key,
    name varchar(255) not null,
    description text
);

create table if not exists products (
    id UUID not null primary key,
    name varchar(255) not null,
    description text,
    quantity integer not null,
    price numeric(32, 2) not null,
    category_id UUID
    constraint fk1udkslidekslskdef references categories
)