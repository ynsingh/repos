alter table `admissionentrancedata` add `Branch_Applied_for_Programmes_of_Study_of_Post_Graduate` varchar(255) NOT NULL after `Branch_Applied_for_Programmes_of_Study_of_Post_Graduate_Mpur`;
alter table `admissionentrancedata` add `Age_in_months`varchar(255) NOT NULL after `Age`;
alter table `admissionentrancedata` add `Name_of_the_Institution_StdX` varchar(255) NOT NULL after`Mothers_contact_number`;
alter table `admissionentrancedata` add `Marks_Obtained_StdXII`varchar(255) NOT NULL after `Result_declared_StdXII`; 
alter table `admissionentrancedata` add `Subject_Post_Graduation`varchar(255) NOT NULL after`Year_of_Passing_Appearing_Post_Graduarion`;
alter table `admissionentrancedata` add `Max_Marks_Post_Graduarion`varchar(255) NOT NULL after `Marks_Obtained_Post_Graduarion`;
alter table `admissionentrancedata` add `percentage_of_Marks_Post_Graduarion`varchar(255) NOT NULL after `Max_Marks_Post_Graduarion`;
alter table `admissionentrancedata` add `Name_of_the_Institution_MPhil`varchar(255) NOT NULL after `percentage_of_Marks_Post_Graduarion`;
alter table `admissionentrancedata` add `Year_of_Passing_Appearing_MPhil`varchar(255) NOT NULL after `Board_University_MPhil`;
alter table `admissionentrancedata` add `Subject_Mphil`varchar(255) NOT NULL after `Marks_Obtained_MPhil`;
alter table `admissionentrancedata` add `Max_Marks_MPhil`varchar(255) NOT NULL after `Subject_Mphil`; 
alter table `admissionentrancedata` add `Board_University_Other`varchar(255) NOT NULL after `Name_of_the_Institution_Other`;
alter table `admissionentrancedata` add `Year_of_Passing_Appearing_Other`varchar(255) NOT NULL after `Subject_Other`;
alter table `admissionentrancedata` add `Marks_Obtained_Other`varchar(255) NOT NULL after `Result_declared_Other`;
alter table `admissionentrancedata` add `Result_waiting`varchar(255) NOT NULL after `Marks_Obtained_Other`;
alter table `admissionentrancedata` add `percentage_of_Marks_Other`varchar(255) NOT NULL  after `Max_Marks_Other`;
alter table `admissionentrancedata` add `JEE_Main_Rank`varchar(255) NOT NULL after `JEE_Main_RollNo`;
alter table `admissionentrancedata` add `JEE_Main_State`varchar(255) NOT NULL after `JEE_Main_Score`;

