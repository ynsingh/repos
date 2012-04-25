#!/bin/sh

# This program generate the course files containing 
# registered students username only, one per line

# First parameter is input directory of courselist received 
# from DOAA office of register students one file per course.
# Second parameter is output directory name which have one 
# file per course containing Registered Student user
# name one per line and output directory must be exist

for i  in  `/bin/ls $1` ; do
`/bin/cut -f 1 -d ";" $1/$i > $2/$i` ;
done


