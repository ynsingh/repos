#!/bin/sh
# BrihStartRoot.sh
# Modify 18-11-2005
# This is used by /etc/rc.local to start the Brihaspati at boot time.
# Please don't use this as Brihaspati to start the system.
# Instead use BrihStart.sh

JAVA_HOME=/usr/java/jdk1.5.0_02
PATH=/usr/java/jdk1.5.0_02/bin:$PATH

ANT_HOME=~/apache-ant-1.6.1
PATH=~/apache-ant-1.6.1/bin:$PATH
BRIHASPATI_HOME=/home/brihaspati
SUDO_HOME=/usr/bin
export PATH JAVA_HOME ANT_HOME BRIHASPATI_HOME SUDO_HOME
cd $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon

$SUDO_HOME/sudo -u brihaspati $BRIHASPATI_HOME/tdk-2.3_01/bin/startup.sh

CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/babylon/babylon.jar
export CLASSPATH
$SUDO_HOME/sudo -u brihaspati java babylon.babylonServer -nographics -chatlogs -usepasswords &
#$SUDO_HOME/sudo -u brihaspati $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/bin/brandgang.sh &

