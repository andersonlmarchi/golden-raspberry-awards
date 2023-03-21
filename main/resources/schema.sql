DROP TABLE IF EXISTS AWARDS;

CREATE TABLE AWARDS (
    ID int PRIMARY KEY auto_increment,
    "YEAR" int NOT NULL,
    TITLE varchar(255) NOT NULL,
    STUDIOS varchar(255) NOT NULL,
    PRODUCERS varchar(255) NOT NULL,
    WINNER boolean NOT NULL default false
);
