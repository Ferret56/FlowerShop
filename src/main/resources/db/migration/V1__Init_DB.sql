
-------------------------------------USERS TABLE------------------------------------
create table if not exists users
(
    "ID" LONG NOT NULL,
    "USERNAME" VARCHAR2(50) NOT NULL,
    "PASSWORD" VARCHAR2(50) NOT NULL,
    "EMAIL" VARCHAR2(50) NOT NULL,
    "PHONE" VARCHAR2(20) NOT NULL,
    "MONEY" DECIMAL NOT NULL,
    "DISCOUNT" INT NOT NULL,
    "ROLE" INT NOT NULL,
    PRIMARY KEY("ID")
)