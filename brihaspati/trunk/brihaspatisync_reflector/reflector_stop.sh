#!/bin/sh
CLASSPATH=bin/classes

for i in lib/*
 do
  CLASSPATH=$CLASSPATH:$i
 done
export CLASSPATH  
java org.bss.brihaspatisync.reflector.ShutDownReflector & 
