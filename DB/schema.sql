DROP DATABASE
IF EXISTS sbb;

CREATE DATABASE sbb;

CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL
);

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 1',
content = '질문내용 1';

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 2',
content = '질문내용 2';

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 3',
content = '질문내용 3';

CREATE TABLE Answer (
id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
create_date DATETIME NOT NULL,
question_id INT UNSIGNED NOT NULL,
content TEXT NOT NULL,
reply_like BOOLEAN
);

INSERT INTO Answer SET
create_date = NOW(),
content = '답변내용 1',
question_id = 1,
reply_like = FALSE;

INSERT INTO Answer SET
create_date = NOW(),
content = '답변내용 2',
question_id = 2,
reply_like = FALSE;

INSERT INTO Answer SET
create_date = NOW(),
content = '답변내용 3',
question_id = 3,
reply_like = FALSE;

ALTER TABLE Question ADD viewcnt INT NOT NULL;

CREATE TABLE `user`(
id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
username TEXT UNIQUE NOT NULL,
PASSWORD TEXT NOT NULL,
email TEXT UNIQUE NOT NULL
);

SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM `user`;