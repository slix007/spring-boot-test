CREATE SEQUENCE S_CONTACT;
CREATE SEQUENCE S_APPLICATION;

CREATE TABLE CONTACT
(
    CONTACT_ID   BIGINT IDENTITY PRIMARY KEY,
    CONTACT_NAME VARCHAR(30)
);

CREATE TABLE APPLICATION
(
    APPLICATION_ID BIGINT IDENTITY PRIMARY KEY,
    PRODUCT_NAME   VARCHAR(50),
    DT_CREATED     DATETIME,
    CONTACT_ID     BIGINT REFERENCES CONTACT (CONTACT_ID),

);


