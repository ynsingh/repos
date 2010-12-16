Extract MyJSF.zip 
Copy already downloaded lib folder containing various jars inside myjsf.
Build using ANT 
If ant path is not set
1. Set  JAVA_HOME pointing to JDK folder 
2. Copy all folders to ant/bin , copy this lib folder along side. 
3. Replace existing build.xml with this new one. 
Go to instruction 4.
If ant path is set 
4. Execute ant. Thats all.


Running 
Project include sql script payroll_dev.sql containing structure and few sample data to work with. 
On MySQL server, script file can be executed using source filename command on command line . 

