#!/bin/sh

BRIHASPATI_HOME=/home/brihaspati

cd $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon
$BRIHASPATI_HOME/tdk-2.3_01/bin/shutdown.sh
CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon/babylon.jar
export CLASSPATH
java babylon.babylonServerStop STOP & 
