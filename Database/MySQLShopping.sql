----------------------------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS shopping;
USE shopping;
-- --------------------------------------------------------------------------------------
CREATE TABLE userinfo(
  user_id int(10) unsigned NOT NULL auto_increment,
  first_name varchar(255) NOT NULL default '0',
  last_name varchar(255) NOT NULL default '0',
  EMAIL       varchar(255)  NOT NULL default '0',
  ADDRESS       varchar(255 ),
  BIRTHDATE     DATE,
  PASSWORD      varchar(20 ) NOT NULL,
  JOB           varchar(255 ),
  CREDIT_LIMIT  decimal(15),
  USER_IMG      varchar(255 ),
  PRIMARY KEY  (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- ------------------------------------------------------------------------------------
CREATE TABLE USER_INTERESTS
(
  USER_ID        int(10) unsigned NOT NULL,
  INTEREST_NAME  varchar(255) NOT NULL,
  PRIMARY KEY  (USER_ID,INTEREST_NAME),
  KEY fk_user_interests (user_id),
  CONSTRAINT fk_user_int FOREIGN KEY (user_id) REFERENCES userinfo (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
-------------------------------------------------------------------------------------------
CREATE TABLE PRODUCTS_CATEGORY
(
  CATEGORY_ID    int(10) unsigned NOT NULL auto_increment,
  CATEGORY_NAME  varchar(255) NOT NULL,
  primary key(CATEGORY_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `shopping`.`products_category` 
ADD INDEX `Fk_Cat_indx` (`CATEGORY_ID` ASC);
-------------------------------------------------------------------------------------
CREATE TABLE SHOPPING.PRODUCTS_INFO
(
  PRODUCT_ID    int(10) unsigned NOT NULL auto_increment,
  PRODUCT_NAME  varchar(255) NOT NULL,
  PRICE         decimal(10) not NULL,
  QUANTITY      int(10)     NOT NULL,
  DESCRIPTION   varchar(255),
  CATEGORY_ID   int(10) unsigned not null,
  IMG           varchar(255) ,
  DELETED_FLG   int DEFAULT 1  NOT NULL,
  primary key(PRODUCT_ID),
  KEY fk_product_category (CATEGORY_ID),
  CONSTRAINT fk_product_cat FOREIGN KEY (CATEGORY_ID) REFERENCES PRODUCTS_CATEGORY (CATEGORY_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
---------------------------------------------------------------------------------------------------------------
CREATE TABLE SHOPPING.SHOPPING_CART
(
  USER_ID     int(10) unsigned NOT NULL,
  PRODUCT_ID  int(10) unsigned NOT NULL,
  QUANTITY    int(10)     NOT NULL,
  primary key(USER_ID,PRODUCT_ID),
  CONSTRAINT SHOPPING_CART_R01  FOREIGN KEY (USER_ID)  REFERENCES SHOPPING.USERINFO (USER_ID),
  CONSTRAINT SHOPPING_CART_R02  FOREIGN KEY (PRODUCT_ID) REFERENCES SHOPPING.PRODUCTS_INFO (PRODUCT_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
--------------------------------------------------------------------------------------------------------------------------
CREATE TABLE SHOPPING.USER_PRODUCTS
(
  USER_ID     int(10) unsigned NOT NULL,
  PRODUCT_ID  int(10) unsigned NOT NULL,
  QUANTITY    int(10)     NOT NULL,
  ORDER_DATE  DATE,
  PRICE       decimal(10) not NULL,
  primary key(USER_ID,PRODUCT_ID),
  CONSTRAINT USER_PRODUCTS_R01   FOREIGN KEY (USER_ID)  REFERENCES SHOPPING.USERINFO (USER_ID),
  CONSTRAINT USER_PRODUCTS_R02  FOREIGN KEY (PRODUCT_ID) REFERENCES SHOPPING.PRODUCTS_INFO (PRODUCT_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
----------------------------------------------------------------------------------------------------------------
CREATE TABLE SHOPPING.ADMIN_INFO
(
  ADMIN_ID    int(10) unsigned NOT NULL auto_increment,
  EMAIL       varchar(255) NOT NULL,
  FIRST_NAME  varchar(255) NOT NULL,
  LAST_NAME   varchar(255) NOT NULL,
  PASSWORD    varchar(255) NOT NULL,
  primary key(ADMIN_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;