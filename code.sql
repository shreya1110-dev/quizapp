** code for mysql

** creating databases

create database quizapp;

** user table

use quizapp;
create table credentials (
    username varchar(50),
    password varchar(20)
);

** registration table

create table registration (
    name varchar(30),
    cname varchar(40),
    caddr varchar(50),
    pin integer(6),
    age integer(2),
    dob varchar(20),
    gender varchar(8),
    dept varchar(4),
    contact varchar(11),
    email varchar(50),
    skills varchar(40),
    hobbies varchar(30)
);

** quiz table

create table quiz (
    question varchar(100),
    answer varchar(10),
    options varchar(100)
);

