CREATE DATABASE IF NOT EXISTS SocialMedia_db;

use SocialMedia_db;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;

CREATE TABLE users (
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY
	, firstName VARCHAR(20) NOT NULL
	, lastName VARCHAR(25) NOT NULL
	, userName VARCHAR(35) NOT NULL
	, email VARCHAR(35) NOT NULL
	, city VARCHAR(20)
	, state VARCHAR(2) NOT NULL
	, phoneNumber VARCHAR(10)
	, password VARCHAR(35) NOT NULL
);

CREATE TABLE posts (
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY
	, userid int(11) NOT NULL
	, post VARCHAR(2000)
	, createdDate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
	, dateModified DATETIME
	, FOREIGN KEY(userid) REFERENCES users(id)

);

CREATE TABLE comments (
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY
	, userid int(11) NOT NULL
	, postid int(11) NOT NULL
	, comment VARCHAR(8000)
	, createdDate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
	, dateModified DATETIME
	, FOREIGN KEY(userid) REFERENCES users(id)
	, FOREIGN KEY(postid) REFERENCES posts(id)


);
