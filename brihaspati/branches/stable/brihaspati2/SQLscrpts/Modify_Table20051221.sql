use brihaspati;

ALTER TABLE USER_CONFIGURATION ALTER QUESTION_ID SET DEFAULT 0;
#---------------------------------------------------------------------------
# Hint questions for forget password module
#---------------------------------------------------------------------------
INSERT INTO HINT_QUESTION VALUES (1,"What is your pet's name?");
INSERT INTO HINT_QUESTION VALUES (2,"What was the name of your first school?");
INSERT INTO HINT_QUESTION VALUES (3,"Who was your child hero?");
INSERT INTO HINT_QUESTION VALUES (4,"What is your favorite past time?");
INSERT INTO HINT_QUESTION VALUES (5,"What is your all time favorite sports team?");
INSERT INTO HINT_QUESTION VALUES (6,"What is your father's middle name?");
INSERT INTO HINT_QUESTION VALUES (7,"What is your high school mascot?");
INSERT INTO HINT_QUESTION VALUES (8,"What was your first bike or car?");
INSERT INTO HINT_QUESTION VALUES (9,"Where did you first meet your girlfriend/boyfriend?");
INSERT INTO HINT_QUESTION VALUES (10,"What is your favorite food?");
