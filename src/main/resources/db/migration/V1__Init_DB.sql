
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

);
------------------------------------FLOWERS TABLE-------------------------------------
create table if not exists flowers(
    "ID" LONG NOT NULL,
    "NAME" VARCHAR2(50) NOT NULL,
    "PRICE" DECIMAL NOT NULL,
    "AMOUNT" INT NOT NULL,
    PRIMARY KEY("ID")
);
------------------------------------ORDER TABLE----------------------------------

create table if not exists user_orders(
                  "ID" Long NOT NULL,
                  "USER_ID" Long REFERENCES users("ID"),
                  "CREATE_DATE" Date,
                  "CLOSE_DATE" Date,
                  "COST" Decimal NOT NULL,
                  "STATUS" VARCHAR(20),
                  PRIMARY KEY("ID")
);

-----------------------------------ORDER_ITEM TABLE-------------------------------
create table if not exists order_item(
          "ID" Long NOT NULL,
          "ORDER_ID" Long REFERENCES user_orders("ID"),
          "FLOWER_ID" Long REFERENCES flowers("ID"),
          "AMOUNT" Int NOT NULL,
          PRIMARY KEY("ID")
);