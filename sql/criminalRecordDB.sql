create database criminalRecordDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'java'@'localhost' IDENTIFIED BY 'password';
use criminalrecorddb;

create table jail(
	jail_id int primary key not null auto_increment,
	jail_no int not null,
    jail_cell_no int
);

create table crime (
	crime_id int not null primary key auto_increment,
    crime_type varchar(50) not null ,
    crime_details varchar(150)
);

create table facePhoto(
	fp_id int primary key auto_increment,
	fp_front varchar(1024) not null,
    fp_right varchar(1024) not null,
	fp_left varchar(1024) not null
);

create table records(
	rec_id int primary key not null auto_increment,
    rec_fullName varchar(100) not null,
	rec_crime_id int not null,
    rec_jail_id int ,
    rec_face_photos_id int not null,
    rec_bloodGroup varchar(10) not null,
    rec_fingerPrint varchar(1024) not null,
    rec_retineScan varchar(1024) not null,
    rec_dnaInfo varchar(1024) not null,
    rec_date_time datetime not null,
    foreign key(rec_crime_id) references crime (crime_id),
    foreign key(rec_jail_id) references jail (jail_id),
    foreign key(rec_face_photos_id) references facePhoto(fp_id)
);

 CREATE TABLE `users` (
  `us_id` int unsigned NOT NULL AUTO_INCREMENT,
  `us_userName` varchar(255) DEFAULT NULL,
  `us_pswd` blob,
  PRIMARY KEY (`us_id`),
  UNIQUE KEY `us_userName` (`us_userName`)
);






INSERT INTO `users` (`us_userName`, `us_pswd`) VALUES ('admin', AES_ENCRYPT('admin', 'secret'));
SELECT CAST(AES_DECRYPT(us_pswd, 'secret') as char(255)) as  `pswd_decrypt` FROM `users` WHERE `us_userName` = 'admin';

#crime insert
insert into crime(crime_type, crime_details) value("murder", "Jane's murder"); 
#SEARCH CRIME BY TYPE AND GET CRIME_ID
select crime_id from crime where crime_type = "murder";

#iNSERT JAIL
insert into jail(jail_no,  jail_cell_no) values(1, 2);

#SEARCH JAIL_NO AND GET JAIL_ID;
select jail_id from jail where jail_no = 1;

#INSERT FACE PHOTOS
insert into facePhoto(fp_front, fp_right, fp_left) values (
	"https://pbs.twimg.com/profile_images/864104988146114560/MSWTWwno.jpg", 
	"https://as1.ftcdn.net/jpg/01/68/45/18/500_F_168451812_e8LjFNB56zq5OKagYTopryS7edhiaL24.jpg",
	"https://www.learnopencv.com/wp-content/uploads/2016/09/head-pose-example.jpg"
);
#SELECT FACE_PHOTO ID
select fp_id from facePhoto where fp_front="https://pbs.twimg.com/profile_images/864104988146114560/MSWTWwno.jpg";

-- select (records.rec_fullName, crime.crime_type)
#INSERT INTO RECORDS 
insert into records(rec_fullName, rec_crime_id, rec_jail_id, rec_face_photos_id, rec_bloodGroup, rec_fingerPrint, rec_retineScan, rec_dnaInfo, rec_date_time) 
	values ("John DOE", 1, 1, 1, "AB+", "fingerPrint", "retinaScan", "dnaInfo", NOW()) ;
insert into records(rec_fullName, rec_crime_id, rec_jail_id, rec_face_photos_id, rec_bloodGroup, rec_fingerPrint, rec_retineScan, rec_dnaInfo, rec_date_time) 
	values ("Alex George", 1, 1, 1, "AB+", "fingerPrint", "retinaScan", "dnaInfo", NOW()) ;
insert into records(rec_fullName, rec_crime_id, rec_jail_id, rec_face_photos_id, rec_bloodGroup, rec_fingerPrint, rec_retineScan, rec_dnaInfo, rec_date_time) 
	values ("test", 1, 1, 1, "AB+", "fingerPrint", "retinaScan", "dnaInfo", NOW()) ;

