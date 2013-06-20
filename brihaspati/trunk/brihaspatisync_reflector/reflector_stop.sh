#!/bin/sh
CLASSPATH=bin/classes

for i in lib/*
 do
  CLASSPATH=$CLASSPATH:$i
 done
export CLASSPATH  
java -Djavax.net.ssl.trustStore=jnlp/brihaspatisync org.bss.brihaspatisync.reflector.ShutDownReflector 
