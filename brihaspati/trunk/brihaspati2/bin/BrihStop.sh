#!/bin/sh

BRIHASPATI_HOME=/home/brihaspati
APACHE_DIR=apache-tomcat-7.0.42
cd $BRIHASPATI_HOME/$APACHE_DIR/webapps/brihaspati2/babylon
$BRIHASPATI_HOME/$APACHE_DIR/bin/shutdown.sh
CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/$APACHE_DIR/webapps/brihaspati2/babylon/babylon.jar
export CLASSPATH
java babylon.babylonServerStop STOP & 
