CREATE TABLE IF NOT EXISTS USERS (
  UserId INT PRIMARY KEY auto_increment,
  Username VARCHAR(20) UNIQUE,
  Ppassword VARCHAR,
  Salt VARCHAR,
  FirstName VARCHAR(50),
  LastName VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    CredentialId INT PRIMARY KEY auto_increment,
    Username VARCHAR (30),
    Password VARCHAR,
    Userkey VARCHAR,
    Url VARCHAR(100),
    UserId INT,
    foreign key (UserId) references USERS(UserId)
);

CREATE TABLE IF NOT EXISTS FILES (
    FileId INT PRIMARY KEY auto_increment,
    FileName VARCHAR,
    TypeContent VARCHAR,
    FileCapacity VARCHAR,
    UserId INT,
    FileInfo VARCHAR,
    foreign key (UserId) references USERS(UserId)
);

CREATE TABLE IF NOT EXISTS NOTES (
    NoteId INT PRIMARY KEY auto_increment,
    Title VARCHAR(20),
    Description VARCHAR (1000),
    UserId INT,
    foreign key (UserId) references USERS(UserId)
);




