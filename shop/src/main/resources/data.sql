insert into roles(id, name) values (1, 'USER');
insert into roles(id, name) values (2, 'ADMIN');

insert into account(id, name, email, passwd, create_date)
 values (1, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$HtZq0Ey5fTNQmxqeGD.i8OTK3jMZ4/Ts3DWtRGt/qsk1cS3pIA3Zq', now());

insert into user_roles(user_id, role_id) values (1, 1);
insert into user_roles(user_id, role_id) values (1, 2);

insert into item (id, name, price, create_date) values (1, 'item1', 1000, now());
insert into item (id, name, price, create_date) values (2, 'item2', 1500, now());
insert into item (id, name, price, create_date) values (3, 'item3', 2500, now());
insert into item (id, name, price, create_date) values (4, 'item4', 3500, now());
insert into item (id, name, price, create_date) values (5, 'item5', 4500, now());
insert into item (id, name, price, create_date) values (6, 'item6', 5500, now());

