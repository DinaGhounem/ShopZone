------------------------------------------------this command must be executed as sysdba
create user shopping identified by shopping
grant resource to shopping
grant connect to shopping
------------------------------------------------run this in shopping schema
/*ALTER TABLE SHOPPING.USERINFO
 DROP PRIMARY KEY CASCADE;
DROP TABLE SHOPPING.USERINFO CASCADE CONSTRAINTS;*/

CREATE TABLE SHOPPING.USERINFO
(
  USER_ID       NUMBER,
  FIRST_NAME    VARCHAR2(255 BYTE),
  LAST_NAME     VARCHAR2(255 BYTE),
  EMAIL         VARCHAR2(255 BYTE)              NOT NULL,
  ADDRESS       VARCHAR2(255 BYTE),
  BIRTHDATE     DATE,
  PASSWORD      VARCHAR2(20 BYTE)               NOT NULL,
  JOB           VARCHAR2(255 BYTE),
  CREDIT_LIMIT  NUMBER,
  USER_IMG      VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX SHOPPING.USERINFO_PK ON SHOPPING.USERINFO
(USER_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE SHOPPING.USERINFO ADD (
  CONSTRAINT USERINFO_PK
 PRIMARY KEY
 (USER_ID));

-------------------------------------------------------------------------------------------
--DROP TABLE SHOPPING.USER_INTERESTS CASCADE CONSTRAINTS;

CREATE TABLE SHOPPING.USER_INTERESTS
(
  USER_ID        NUMBER,
  INTEREST_NAME  VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SHOPPING.USER_INTERESTS ADD (
  CONSTRAINT USER_INTERESTS_R01 
 FOREIGN KEY (USER_ID) 
 REFERENCES SHOPPING.USERINFO (USER_ID));
------------------------------------------------------------------------------------------------
/*ALTER TABLE SHOPPING.PRODUCTS_CATEGORY
 DROP PRIMARY KEY CASCADE;
DROP TABLE SHOPPING.PRODUCTS_CATEGORY CASCADE CONSTRAINTS;*/

CREATE TABLE SHOPPING.PRODUCTS_CATEGORY
(
  CATEGORY_ID    NUMBER,
  CATEGORY_NAME  VARCHAR2(255 BYTE)             NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX SHOPPING.PRODUCTS_CATEGORY_PK ON SHOPPING.PRODUCTS_CATEGORY
(CATEGORY_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE SHOPPING.PRODUCTS_CATEGORY ADD (
  CONSTRAINT PRODUCTS_CATEGORY_PK
 PRIMARY KEY
 (CATEGORY_ID));
--------------------------------------------------------------------------------------------------
/*ALTER TABLE SHOPPING.PRODUCTS_INFO
 DROP PRIMARY KEY CASCADE;
DROP TABLE SHOPPING.PRODUCTS_INFO CASCADE CONSTRAINTS;*/

CREATE TABLE SHOPPING.PRODUCTS_INFO
(
  PRODUCT_ID    NUMBER,
  PRODUCT_NAME  VARCHAR2(255 BYTE)              NOT NULL,
  PRICE         NUMBER                          NOT NULL,
  QUANTITY      NUMBER                          NOT NULL,
  DESCRIPTION   VARCHAR2(255 BYTE),
  CATEGORY_ID   NUMBER,
  IMG           VARCHAR2(255 BYTE),
  DELETED_FLG   NUMBER                          DEFAULT 1                     NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX SHOPPING.PRODUCTS_PK ON SHOPPING.PRODUCTS_INFO
(PRODUCT_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE SHOPPING.PRODUCTS_INFO ADD (
  CONSTRAINT PRODUCTS_PK
 PRIMARY KEY
 (PRODUCT_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE SHOPPING.PRODUCTS_INFO ADD (
  CONSTRAINT PRODUCTS_INFO_R01 
 FOREIGN KEY (CATEGORY_ID) 
 REFERENCES SHOPPING.PRODUCTS_CATEGORY (CATEGORY_ID));

---------------------------------------------------------------------------------
--DROP TABLE SHOPPING.SHOPPING_CART CASCADE CONSTRAINTS;

CREATE TABLE SHOPPING.SHOPPING_CART
(
  USER_ID     NUMBER,
  PRODUCT_ID  NUMBER,
  QUANTITY    NUMBER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SHOPPING.SHOPPING_CART ADD (
  CONSTRAINT SHOPPING_CART_R01 
 FOREIGN KEY (USER_ID) 
 REFERENCES SHOPPING.USERINFO (USER_ID),
  CONSTRAINT SHOPPING_CART_R02 
 FOREIGN KEY (PRODUCT_ID) 
 REFERENCES SHOPPING.PRODUCTS_INFO (PRODUCT_ID));
-------------------------------------------------------------------------------------
--DROP TABLE SHOPPING.USER_PRODUCTS CASCADE CONSTRAINTS;

CREATE TABLE SHOPPING.USER_PRODUCTS
(
  USER_ID     NUMBER,
  PRODUCT_ID  NUMBER,
  QUANTITY    NUMBER,
  ORDER_DATE  DATE,
  PRICE       NUMBER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SHOPPING.USER_PRODUCTS ADD (
  CONSTRAINT USER_PRODUCTS_R01 
 FOREIGN KEY (USER_ID) 
 REFERENCES SHOPPING.USERINFO (USER_ID),
  CONSTRAINT USER_PRODCTS_R02 
 FOREIGN KEY (PRODUCT_ID) 
 REFERENCES SHOPPING.PRODUCTS_INFO (PRODUCT_ID));

---------------------------------------------------------------------------------
--DROP TABLE SHOPPING.ADMIN_INFO CASCADE CONSTRAINTS;

CREATE TABLE SHOPPING.ADMIN_INFO
(
  ADMIN_ID    NUMBER,
  EMAIL       VARCHAR2(255 BYTE),
  FIRST_NAME  VARCHAR2(255 BYTE),
  LAST_NAME   VARCHAR2(255 BYTE),
  PASSWORD    VARCHAR2(30 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;
--------------------------------------------------------------------------------------
SET DEFINE OFF;
Insert into ADMIN_INFO
   (ADMIN_ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
 Values
   (1, 'admin@yahoo.com', 'admin', 'admin', '123');
COMMIT;