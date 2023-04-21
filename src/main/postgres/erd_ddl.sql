--Create and set schema

DROP SCHEMA IF EXISTS p2 CASCADE;
CREATE SCHEMA IF NOT EXISTS p2;
SET search_path TO p2;

--Create tables

CREATE table user_roles (
	role_id VARCHAR(255) primary key,
	role VARCHAR(255) unique not null
);

CREATE table users (
	user_id VARCHAR(255) primary key,
	username VARCHAR(255) unique not null,
	password VARCHAR(255) not null,
	email VARCHAR(255) unique not null,
	registered TIMESTAMP not null,
	is_active BOOLEAN not null,
	role_id VARCHAR(255) REFERENCES user_roles(role_id) --not null
);

CREATE table user_profiles (
	profile_id VARCHAR(255) primary key,
	display_name VARCHAR(255) not null,
	location VARCHAR(255),
	birth_date DATE not null,
	occupation VARCHAR(255),
	bio VARCHAR(255),
	profile_pic_url VARCHAR(255),
	user_id VARCHAR(255) unique not null REFERENCES users(user_id)
);

CREATE table following_relationships (
	relationship_id VARCHAR(255) primary key,
	followed TIMESTAMP not null,
	user_id VARCHAR(255) not null REFERENCES users(user_id),
	following_id VARCHAR(255) not null REFERENCES users(user_id)
);


CREATE table posts (
	post_id VARCHAR(255) primary key,
	posted TIMESTAMP not null,
	content VARCHAR(255) not null,
	img_url VARCHAR(255),
	user_id VARCHAR(255) not null REFERENCES users(user_id)
);

CREATE table hashtags (
	hashtag_id VARCHAR(255) primary key,
	hashtag VARCHAR(255) not null,
	post_id VARCHAR(255) not null REFERENCES posts(post_id)
);

CREATE table likes (
	like_id VARCHAR(255) primary key,
	user_id VARCHAR(255) not null REFERENCES users(user_id),
	post_id VARCHAR(255) not null REFERENCES posts(post_id)
);

CREATE table replies (
	reply_id VARCHAR(255) primary key,
	reply VARCHAR(255) not null,
	replied TIMESTAMP not null,
	img_url VARCHAR(255),
	user_id VARCHAR(255) not null REFERENCES users(user_id),
	post_id VARCHAR(255) not null REFERENCES posts(post_id)
);

CREATE table reposts (
	repost_id VARCHAR(255) primary key,
	reposted TIMESTAMP not null,
	user_id VARCHAR(255) not null REFERENCES users(user_id),
	post_id VARCHAR(255) not null REFERENCES posts(post_id)
);
