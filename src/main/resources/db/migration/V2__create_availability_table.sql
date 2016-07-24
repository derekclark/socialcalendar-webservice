create table AVAILABILITY (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    start_date DATETIME,
    end_date DATETIME,
    owner_email varchar(255),
    owner_name varchar(255),
    status varchar(255),
    title varchar(255)
);

