#!/bin/sh

# This program create a database insertable script
# containing one entry per row for UserGroupRole table
# Fist parameter input file containing one line 
# per row which IFS equal to space
# Second parameter output file containing the sql 
# command string

awk '{if (NF == 3) print "Insert into TURBINE_USER_GROUP_ROLE values( "$1","$2","$3");"}' $1 > $2

