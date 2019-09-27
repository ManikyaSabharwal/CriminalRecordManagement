-- DELETE ALL BY NAME
delete from records where rec_fullName = "test";

--  DELETE LAST RECORD BY NAME
select records.rec_id from records where records.rec_fullName = ? order by rec_date_time desc;
delete from records where rec_id = ?;

-- CLEARING UNUSED TABLE DATA
DELETE FROM crime WHERE NOT EXISTS( SELECT * FROM records AS T1 WHERE T1.rec_crime_id = crime.crime_id);
DELETE FROM jail WHERE NOT EXISTS( SELECT * FROM records AS T1 WHERE T1.rec_crime_id = jail.jail_id);
DELETE FROM facePhoto WHERE NOT EXISTS( SELECT * FROM records AS T1 WHERE T1.rec_crime_id = facePhoto.fp_id);