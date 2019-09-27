-- VIEW BY DATE
SELECT records.rec_fullName AS FullName,records.rec_jail_id, records.rec_crime_id, crime.crime_type AS CrimeType, crime.crime_details,
	jail.jail_no, jail.jail_cell_no, records.rec_bloodGroup, records.rec_fingerPrint,
    records.rec_retineScan, records.rec_dnaInfo, records.rec_date_time,
    facePhoto.fp_front, facePhoto.fp_right, facePhoto.fp_left, rec_date_time
FROM records
INNER JOIN crime 
ON records.rec_crime_id = crime.crime_id
INNER JOIN jail
ON jail.jail_id = records.rec_jail_id
INNER JOIN facePhoto 
ON facePhoto.fp_id = records.rec_face_photos_id
WHERE DATE(rec_date_time) ='2019-09-20'
ORDER BY rec_date_time DESC;

-- VIEW BY NAME SORTED BY DATE
SELECT records.rec_fullName AS FullName,records.rec_jail_id, records.rec_crime_id, crime.crime_type AS CrimeType, crime.crime_details,
	jail.jail_no, jail.jail_cell_no, records.rec_bloodGroup, records.rec_fingerPrint,
    records.rec_retineScan, records.rec_dnaInfo, records.rec_date_time,
    facePhoto.fp_front, facePhoto.fp_right, facePhoto.fp_left, rec_date_time
FROM records
INNER JOIN crime 
ON records.rec_crime_id = crime.crime_id
INNER JOIN jail
ON jail.jail_id = records.rec_jail_id
INNER JOIN facePhoto 
ON facePhoto.fp_id = records.rec_face_photos_id
WHERE records.rec_fullName = "test" 
ORDER BY rec_date_time DESC;

-- VIEW ALL RECORDS SORTED BY NAME AND THEN BY DATE
SELECT records.rec_fullName AS FullName,records.rec_jail_id, records.rec_crime_id, crime.crime_type AS CrimeType, crime.crime_details,
	jail.jail_no, jail.jail_cell_no, records.rec_bloodGroup, records.rec_fingerPrint,
    records.rec_retineScan, records.rec_dnaInfo, records.rec_date_time,
    facePhoto.fp_front, facePhoto.fp_right, facePhoto.fp_left, rec_date_time
FROM records
INNER JOIN crime 
ON records.rec_crime_id = crime.crime_id
INNER JOIN jail
ON jail.jail_id = records.rec_jail_id
INNER JOIN facePhoto 
ON facePhoto.fp_id = records.rec_face_photos_id
ORDER BY rec_fullName DESC ,rec_date_time DESC;

select count(rec_id) from records;