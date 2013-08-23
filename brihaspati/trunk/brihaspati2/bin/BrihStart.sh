#!/bin/sh

BRIHASPATI_HOME=/home/brihaspati
APACHE_DIR=apache-tomcat-7.0.42
cd $BRIHASPATI_HOME/$APACHE_DIR/webapps/brihaspati2/babylon
$BRIHASPATI_HOME/$APACHE_DIR/bin/startup.sh
CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/$APACHE_DIR/webapps/brihaspati2/babylon/babylon.jar
export CLASSPATH
java -Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -XX:-DisableExplicitGC -XX:MaxGCPauseMillis=500 -XX:GCTimeRatio=99 -XX:+UseParallelGC babylon.babylonServerStart -nographics -chatlogs -usepasswords & 
