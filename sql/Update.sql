-- SELECT JAIL ID DEPENDING ON DETAILS
SELECT 
    rec_jail_id
FROM
    records
        INNER JOIN
    jail ON records.rec_jail_id = jail.jail_id
WHERE
    records.rec_fullName = 'test'
        AND jail.jail_no = 100
        AND jail.jail_cell_no = 200;

-- UPDATE FOUND JAIL DETAILS
UPDATE jail 
SET 
    jail_cell_no = 100,
    jail_no = 200
WHERE
    jail_id = 1;
    
-- TRANSFER(UPDATE) ALL FROM ONE JAIL TO ANOTHER
UPDATE jail 
SET 
    jail_no = 10
WHERE
    jail_no = 100;