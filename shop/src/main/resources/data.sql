insert into roles(id, name) values (1, 'USER');
insert into roles(id, name) values (2, 'ADMIN');

insert into account(id, name, email, passwd, create_date)
 values (1, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$HtZq0Ey5fTNQmxqeGD.i8OTK3jMZ4/Ts3DWtRGt/qsk1cS3pIA3Zq', now());

insert into user_roles(user_id, role_id) values (1, 1);
insert into user_roles(user_id, role_id) values (1, 2);

