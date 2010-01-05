#!/bin/sh

BRIHASPATI_HOME=/home/brihaspati

$BRIHASPATI_HOME/tdk-2.3_01/bin/shutdown.sh
CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon/babylon.jar
export CLASSPATH
cd $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon
java babylon.babylonServerShutdown &
