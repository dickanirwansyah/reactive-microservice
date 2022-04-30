/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  dickanirwansyah
 * Created: Apr 27, 2022
 */

create table user(
    id bigint auto_increment,
    name varchar(255),
    balance int,
    primary key (id)
);

create table user_transaction(
    id bigint auto_increment,
    user_id bigint,
    amount int,
    transaction_date timestamp,
    primary key (id)
);

insert into user(name, balance) values ('muhammad dicka nirwansyah', 90000000),
('muhammad joni', 8900000),('irma khairunnisa', 89800000);