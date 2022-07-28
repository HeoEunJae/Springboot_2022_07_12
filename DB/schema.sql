DROP DATABASE
IF EXISTS sbb;

CREATE DATABASE sbb;

CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    viewcnt INT NOT NULL,
    author_id INT NOT NULL,
    modify_date DATETIME NOT NULL
);

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 1',
content = '질문내용 1',
author_id = 1,
modify_date = NOW();

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 2',
content = '질문내용 2',
author_id = 2,
modify_date = NOW();

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 3',
content = '질문내용 3',
author_id = 3,
modify_date = NOW();

CREATE TABLE Answer (
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    create_date DATETIME NOT NULL,
    question_id INT UNSIGNED NOT NULL,
    content TEXT NOT NULL,
    reply_like BOOLEAN,
    author_id INT NOT NULL,
    modify_date DATETIME NOT NULL
);

INSERT INTO Answer SET
create_date = NOW(),
content = '답변내용 1',
question_id = 1,
reply_like = FALSE,
author_id = 1,
modify_date = NOW();


INSERT INTO Answer SET
create_date = NOW(),
content = '답변내용 2',
question_id = 2,
reply_like = FALSE,
author_id = 2,
modify_date = NOW();

INSERT INTO Answer SET
create_date = NOW(),
content = '답변내용 3',
question_id = 3,
reply_like = FALSE,
author_id = 3,
modify_date = NOW();

CREATE TABLE `user`(
id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
username TEXT UNIQUE NOT NULL,
`password` TEXT NOT NULL,
email TEXT UNIQUE NOT NULL
);

INSERT INTO `user` SET
id = 1,
username ='유저1',
`password` ="1234",
email ="test@test.com";

INSERT INTO `user` SET
id = 2,
username ="유저2",
`password` ="1234",
email ="test2@test.com";

INSERT INTO `user` SET
id = 3,
username ="유저3",
`password` ="1234",
email ="test3@test.com";

SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM `user`;