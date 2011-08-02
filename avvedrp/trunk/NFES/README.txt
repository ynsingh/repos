
NATIONAL FACULTY EXPERTISE SYSTEM
         Version 1.3
===================================


Installation Steps
--------------------

1.	Apply the patch file

2.	Copy images to the images folder (NFES\images)

3.	Copy the folder NFES_Documents to the root folder (NFES)

4.	Copy database to the database folder (NFES\database)


System Requirements & Installation:
------------------------------------
System requirements and installation instructions are located  at /NFES_Documents/NFES_Installation_Document.txt





Change Admin's Email.
------------------------------
Execute the following sql script in the nfes database after editing the admin's email.

UPDATE `users` SET email='admin@gmail.com' WHERE username='admin';

