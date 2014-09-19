#!/bin/sh
CLASSPATH=$CLASSPATH:/home/brihaspati/brihaspati_sync/webapps/brihaspatisync_reflector/jnlp/commons-io-1.1.jar
CLASSPATH=$CLASSPATH:/home/brihaspati/brihaspati_sync/webapps/brihaspatisync_reflector/jnlp/HttpClient.jar
CLASSPATH=$CLASSPATH:/home/brihaspati/brihaspati_sync/webapps/brihaspatisync_reflector/jnlp/syncreflector.jar
export CLASSPATH  
java -Djavax.net.ssl.trustStore=jnlp/brihaspatisync -Xincgc org.bss.brihaspatisync.reflector.Reflector start <option &
