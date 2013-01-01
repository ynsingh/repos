
# -----------------------------------------------------------------------
# USER
# -----------------------------------------------------------------------
drop table if exists USER;

CREATE TABLE USER
(
		            USER_ID INTEGER NOT NULL,
		            FULL_NAME VARCHAR (255),
		            USERNAME VARCHAR (255),
		            PASSWORD VARCHAR (255),
		            EMAIL_ID VARCHAR (100),
		            DATE_OF_BIRTH DATETIME,
		            PHONE_NUMBER VARCHAR (255),
		            ADDRESS VARCHAR (255),
		            POSTCODE INTEGER,
		            CITY VARCHAR (255),
		            STATE VARCHAR (255),
		            COUNTRY VARCHAR (255),
		            QUOTA DECIMAL default 100 NOT NULL,
		            CREATED_ON TIMESTAMP default '20120120',
		            LAST_LOGINE TIMESTAMP default '20120120',
    PRIMARY KEY(USER_ID)
);

# -----------------------------------------------------------------------
# ROLE
# -----------------------------------------------------------------------
drop table if exists ROLE;

CREATE TABLE ROLE
(
		            ROLE_ID INTEGER NOT NULL,
		            ROLE_NAME VARCHAR (99) NOT NULL,
    PRIMARY KEY(ROLE_ID),
    UNIQUE (ROLE_NAME)
);

# -----------------------------------------------------------------------
# USER_ROLE
# -----------------------------------------------------------------------
drop table if exists USER_ROLE;

CREATE TABLE USER_ROLE
(
		            USER_ID INTEGER NOT NULL,
		            ROLE_ID INTEGER NOT NULL,
    PRIMARY KEY(USER_ID,ROLE_ID),
    FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID)
    ,
    FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ROLE_ID)
    
);

# -----------------------------------------------------------------------
# CATEGORY
# -----------------------------------------------------------------------
drop table if exists CATEGORY;

CREATE TABLE CATEGORY
(
		            CATEGORY_ID INTEGER NOT NULL,
		            CATEGORY_NAME VARCHAR (99) NOT NULL,
		            CATEGORY_DESC VARCHAR (255),
    PRIMARY KEY(CATEGORY_ID),
    UNIQUE (CATEGORY_NAME)
);

# -----------------------------------------------------------------------
# CHANNEL
# -----------------------------------------------------------------------
drop table if exists CHANNEL;

CREATE TABLE CHANNEL
(
		            CHANNEL_ID INTEGER NOT NULL,
		            CHANNEL_NAME VARCHAR (100) NOT NULL,
		            CHANNEL_DESC VARCHAR (255),
		            CHANNEL_IP_ADDRESS VARCHAR (25) NOT NULL,
		            CHANNEL_PORT INTEGER (10) NOT NULL,
		            CHANNEL_ICON VARCHAR (255),
    PRIMARY KEY(CHANNEL_ID),
    UNIQUE (CHANNEL_NAME)
);
  
  
  
  
  
