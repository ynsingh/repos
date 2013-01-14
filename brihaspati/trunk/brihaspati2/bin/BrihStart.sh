#!/bin/sh

BRIHASPATI_HOME=/home/nksingh

cd $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon
$BRIHASPATI_HOME/tdk-2.3_01/bin/startup.sh
CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon/babylon.jar
export CLASSPATH
java -Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -XX:-DisableExplicitGC -XX:MaxGCPauseMillis=500 -XX:GCTimeRatio=99 -XX:+UseParallelGC babylon.babylonServerStart -nographics -chatlogs -usepasswords & 
