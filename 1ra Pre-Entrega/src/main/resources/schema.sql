CREATE DATABASE sells;
use sells;

CREATE TABLE clients(
    id int primary key auto_increment,
    name varchar(75),
    lastname varchar(75),
    dni varchar(11)
);

CREATE TABLE invoices(
    id int primary key auto_increment,
    clients_id int,
    create_ad datetime,
    total double,

    CONSTRAINT fk_clients_id FOREIGN KEY (clients_id) REFERENCES clients(id)
);

CREATE TABLE invoices_details(
    id int primary key  auto_increment,
    invoices_id int,
    amount int,
    products_id int,
    price double,

    CONSTRAINT fk_invoices_id FOREIGN KEY (invoices_id) REFERENCES invoices(id),
    CONSTRAINT fk_products_id FOREIGN KEY (products_id) REFERENCES products(id)
);

CREATE TABLE products(
    id int primary key auto_increment,
    description varchar(150),
    code varchar(50),
    stock int,
    price decimal(10,2)
);