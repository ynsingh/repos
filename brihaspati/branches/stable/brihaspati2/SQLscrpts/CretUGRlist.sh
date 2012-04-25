#!/bin/sh

# The program generate the table usergroup role from 
# these files User table, Group table and Student 
# list per course
# The file is run form same directory containing student list

# First parameter is  group file name
# Second parameter is the user file name
# Third parameter is output file containing userid,
# groupid and roleid one per line

for i in `/bin/ls *.txt` ; do
     crs=${i%.txt} 
     cid=`grep -i $crs $1| cut -f 2 -d "|" ` ;
	exec 0<>$i;
	while read un ; do
         uid=`grep $un $2| cut -f 2` 
         echo "$uid $cid 3" >> $3
	 echo
done
done

