ALTER TABLE MEMBER
MODIFY (
    USERPWD NOT NULL,
    PHONE NOT NULL
);

COMMIT;

UPDATE MEMBER
SET ADMIN = 'Y'
WHERE USERID = 'admin';

commit;