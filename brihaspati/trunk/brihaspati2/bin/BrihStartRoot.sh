#!/bin/sh
# BrihStartRoot.sh
# Modify 18-11-2005
# This is used by /etc/rc.local to start the Brihaspati at boot time.
# Please don't use this as Brihaspati to start the system.
# Instead use BrihStart.sh

JAVA_HOME=/usr/java/jdk1.6.0_02
PATH=/usr/java/jdk1.6.0_02/bin:$PATH
export JAVA_HOME PATH

BRIHASPATI_HOME=/home/brihaspati 
#ANT_HOME=$BRIHASPATI_HOME/apache-ant-1.6.1
#PATH=$BRIHASPATI_HOME/apache-ant-1.6.1/bin:$PATH

#SUDO_HOME=/usr/bin
cd $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon

$BRIHASPATI_HOME/tdk-2.3_01/bin/startup.sh

CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon/babylon.jar

export CLASSPATH
java babylon.babylonServer -nographics -chatlogs -usepasswords &
#$SUDO_HOME/sudo -u ynsingh $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/bin/brandgang.sh &

#export PATH JAVA_HOME ANT_HOME BRIHASPATI_HOME SUDO_HOME CLASSPATH


