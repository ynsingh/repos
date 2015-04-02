UPDATE `erpmsubmodule` SET `ESM_HREF` = '/pico/Administration/PICOHelpAction' WHERE `erpmsubmodule`.`ErpmSubModule_ID` =6;
UPDATE `erpmprogram` SET `ERPMP_HREF` = '/pico/Administration/CurrentProfileAction' WHERE `erpmprogram`.`ERPMP_ID` =19;
nsert into erpmprogram (ERPMP_ID, ERPMP_Display_Name, ERPMP_ESM_ID, ERPMP_Purpose, ERPMP_HREF, ERPMP_Order, ERPMP_SubProgram_ID, EPMP_Env_Variable, ERPMP_Display_Name_Hindi, ERPMP_Display_Name_Urdu) VALUES (44,'PICO Registered User ',1,NULL,'/pico/Administration/ErpmUserList.action',4,36,NULL,' ',' ');
insert into institutionroleprivileges(IUP_ID,IUP_ERPM_ID,IUP_ERPMP_ID,IUP_IUR_ID,IUP_Can_Add,IUP_Can_Delete,IUP_Can_Edit,IUP_Can_View,IUP_IM_ID,IUP_EMPMSM_ID) VALUES (87,1,44,1,1,1,1,1,1,1);

