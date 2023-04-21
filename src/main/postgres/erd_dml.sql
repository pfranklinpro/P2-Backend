--Create and set schema

SET search_path TO p2;

--Create users data

INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('0', 'username1', 'password1', 'email1@email1.com', '2022-12-13 00:00:00', true, null),
('1', 'username2', 'password2', 'email2@email2.com', '2022-12-13 00:00:00', true, null),
('2', 'username3', 'password3', 'email3@email3.com', '2022-12-13 00:00:00', true, null);

INSERT INTO posts
(post_id, posted, content, img_url, user_id)
VALUES('0', '2022-12-13 00:00:00', 'first post', null, '0'),
('1', '2022-12-13 00:00:00', 'second post', null, '0'),
('2', '2022-12-13 00:00:00', 'third post', null, '1');
