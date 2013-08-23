#!/bin/sh
# BrihStartRoot.sh
# Modified on 23 Aug 2013 1853hrs IST.  
# This is used by /etc/rc.local to start the Brihaspati at boot time.
# Please don't use this as Brihaspati to start the system.
# Instead use BrihStart.sh

JAVA_HOME=/usr/java/jdk1.6.0_02
PATH=/usr/java/jdk1.6.0_02/bin:$PATH
export JAVA_HOME PATH

BRIHASPATI_HOME=/home/brihaspati 
#ANT_HOME=$BRIHASPATI_HOME/apache-ant-1.6.1
#PATH=$BRIHASPATI_HOME/apache-ant-1.6.1/bin:$PATH
APACHE_DIR=apache-tomcat-7.0.42

#SUDO_HOME=/usr/bin
cd $BRIHASPATI_HOME/$APACHE_DIR/webapps/brihaspati2/babylon

$BRIHASPATI_HOME/$APACHE_DIR/bin/startup.sh

CLASSPATH=$CLASSPATH:$BRIHASPATI_HOME/$APACHE_DIR/webapps/brihaspati2/babylon/babylon.jar

export CLASSPATH
java babylon.babylonServerStart -nographics -chatlogs -usepasswords &
#$SUDO_HOME/sudo -u ynsingh $BRIHASPATI_HOME/tdk-2.3_01/webapps/brihaspati2/bin/brandgang.sh &

#export PATH JAVA_HOME ANT_HOME BRIHASPATI_HOME SUDO_HOME CLASSPATH


