create database criminalRecordDB;
use criminalRecordDB;
drop database criminalRecordDB;

create table jail(
	jail_no int primary key not null,
    cell_no int
);

create table crime (
	crime_id int not null primary key,
    crime_type varchar(50) not null ,
    crime_details varchar(150)
);

create table facePhoto(
	fp_id int(5) primary key,
	fp_front varchar(1024) not null,
    fp_right varchar(1024) not null,
	fp_left varchar(1024) not null
);

create table records(
	rec_id int primary key not null auto_increment,
	rec_crime_id int not null,
    rec_jail_no int ,
    rec_face_photos_id int not null,
    rec_bloodGroup varchar(10) not null,
    rec_fingerPrint varchar(1024) not null,
    rec_retineScan varchar(1024) not null,
    rec_dnaInfo varchar(1024) not null,
    foreign key(rec_crime_id) references crime (crime_id),
    foreign key(rec_jail_no) references jail (jail_no),
    foreign key(rec_face_photos_id) references facePhoto(fp_id)
);

