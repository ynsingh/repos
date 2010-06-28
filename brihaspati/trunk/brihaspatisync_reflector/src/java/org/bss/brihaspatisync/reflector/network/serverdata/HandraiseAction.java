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

        private Vector vector=new Vector();

        private static HandraiseAction handraiseaction=null;

        /**
         * Controller for this calss
         */

        public static HandraiseAction getController() {
                if(handraiseaction == null)
                        handraiseaction=new HandraiseAction();
                return handraiseaction;
        }

        private void startHandraiseAction() {
                try{
			if(vector.size()>0) {
                                RegisterToIndexServer.getController().requestToChangeStatus(vector.get(0).toString());
                                vector.remove(0);
                        }
                }catch(Exception e){  /*JOptionPane.showMessageDialog(null,"Error on HandraiseAction :"+e.getCause()); */ }
        }

        public void setValue(String str){
                vector.add(str);
                startHandraiseAction();
        }
}

