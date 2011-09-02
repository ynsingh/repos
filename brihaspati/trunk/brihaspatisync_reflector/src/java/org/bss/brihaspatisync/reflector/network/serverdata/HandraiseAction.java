package org.bss.brihaspatisync.reflector.network.serverdata;

/**
 * HandraiseAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import org.bss.brihaspatisync.reflector.RegisterToIndexServer;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> modify on 10Feb2009.
 */

public class HandraiseAction {

        private static HandraiseAction handraiseaction=null;

        private void startHandraiseAction(String handraise) {
                try{
                	RegisterToIndexServer.getController().requestToChangeStatus(handraise);
                }catch(Exception e){  System.out.println("Error on HandraiseAction :"+e.getCause()); }
        }

        public synchronized void setValue(String handraise){
                startHandraiseAction(handraise);
        }
}

