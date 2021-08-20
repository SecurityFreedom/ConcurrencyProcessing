DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS COUPON CASCADE;
DROP TABLE IF EXISTS CATEGORY CASCADE;
DROP TABLE IF EXISTS COUPON_STATE CASCADE;
DROP TABLE IF EXISTS ITEM CASCADE;
DROP TABLE IF EXISTS ORDERS CASCADE;

CREATE TABLE USERS
(
    pk       bigint NOT NULL AUTO_INCREMENT,
    id       varchar(64),
    name     varchar(64),
    password varchar(64),
    email    varchar(64),
    PRIMARY KEY (pk)
);

CREATE TABLE CATEGORY
(
    pk   bigint NOT NULL AUTO_INCREMENT,
    name varchar(64),
    PRIMARY KEY (pk)
);

CREATE TABLE COUPON
(
    pk       bigint NOT NULL AUTO_INCREMENT,
    category bigint,
    discount bigint,
    name     varchar(64),
    PRIMARY KEY (pk),
    FOREIGN KEY (category) REFERENCES CATEGORY(pk)
);

CREATE TABLE COUPON_STATE
(
    pk       bigint NOT NULL AUTO_INCREMENT,
    coupon   bigint,
    user     bigint,
    quantity bigint,
    PRIMARY KEY (pk),
    FOREIGN KEY (coupon) REFERENCES COUPON(pk),
    FOREIGN KEY (user) REFERENCES USERS(pk)
);

CREATE TABLE ITEM
(
    pk       bigint NOT NULL AUTO_INCREMENT,
    category bigint,
    quantity bigint,
    price    bigint,
    PRIMARY KEY (pk),
    FOREIGN KEY (category) REFERENCES CATEGORY(pk)
);

CREATE TABLE ORDERS
(
    pk     bigint NOT NULL AUTO_INCREMENT,
    user   bigint,
    item   bigint,
    coupon bigint,
    PRIMARY KEY (pk),
    FOREIGN KEY (user) REFERENCES USERS(pk),
    FOREIGN KEY (item) REFERENCES ITEM(pk),
    FOREIGN KEY (coupon) REFERENCES COUPON(pk)
);