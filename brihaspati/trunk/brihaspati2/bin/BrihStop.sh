#!/bin/sh

BRIHASPATI_HOME=/home/shaista

$BRIHASPATI_HOME/New_tdk-2.3_01/bin/shutdown.sh
CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/New_tdk-2.3_01/webapps/brihaspati2/babylon/babylon.jar

#CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME
export CLASSPATH
##cd $BRIHASPATI_HOME/New_tdk-2.3_01/webapps/brihaspati2/babylon
java babylon.babylonServerStop STOP & 
