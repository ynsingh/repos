package babylon;

//  Babylon Chat
//  Copyright (C) 1997-2002 J. Andrew McLaughlin
// 
//  This program is free software; you can redistribute it and/or modify it
//  under the terms of the GNU General Public License as published by the Free
//  Software Foundation; either version 2 of the License, or (at your option)
//  any later version.
// 
//  This program is distributed in the hope that it will be useful, but
//  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
//  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
//  for more details.
//  
//  You should have received a copy of the GNU General Public License along
//  with this program; if not, write to the Free Software Foundation, Inc.,
//  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
//  babylonCommand.java
//

/*
  Here is the list of commands, with brief summary information.  If commands
  are different between protocol versions, the different verions are noted
  with asterisks

  NAME		ARGS
  ----		----
  setproto	(Double) version	
  noop
  ping
  connect       (String) name
  userinfo	*2.0* (Integer) user_id, (String) name, (String) password,
                      (Boolean) encrypted, (String) additional
                *2.1* (Integer) user_id, (String) name, (String) password,
                      (Boolean) encrypted, (String) additional,
                      (String) language
  servermess	(String) message
  disconnect	(Integer) user_id, (String) message
  roomlist	(Short) #rooms, [(String) name, (String) creator,
                (Boolean) private, (Boolean) invited, (Integer) #users,
                (String) username...] ...
  invite	(Integer) from_id, (String) roomname, (Integer) invitee_id
  enterroom	(Integer) from_id, (String) roomname, (Boolean) private,
                (String) password, (Boolean) encrypted
  bootuser      (Integer) from_id, (String) roomname, (Integer) boot_id
  banuser       (Integer) from_id, (String) roomname, (Integer) ban_id
  allowuser     (Integer) from_id, (String) roomname, (Integer) allow_id
  activity      (Integer) from_id, (Short) type, (Integer) #for,
                [(Integer) for_id...]
  chattext 	(Integer) from_id, (Boolean) private, (Short) colour,
                (String) data, (Integer) #for, [(Integer) for_id...]
  line		(Integer) from_id, (Short) colour, (Short) x0, (Short) y0,
                (Short) x1, (Short) y1, (Short) thick, (Integer) #for,
                [(Integer) for_id...]
  erase		(Integer) from_id, (Short) colour, (Short) x0, (Short) y0,
                (Short) x1, (Short) y1, (Short) thick, (Integer) #for,
                [(Integer) for_id...]
  rect		(Integer) from_id, (Short) colour, (Short) x0, (Short) y0,
                (Short) width, (Short) height, (Short) thick, (Boolean) fill,
                (Integer) #for, [(Integer) for_id...]
  oval		(Integer) from_id, (Short) colour, (Short) x0, (Short) y0,
                (Short) width, (Short) height, (Short) thick, (Boolean) fill,
                (Integer) #for, [(Integer) for_id...]
  drawtext	(Integer) from_id, (Short) colour, (Short) x, (Short) y,
                (Short) type, (Short) attr, (Short) size, (String) text,
                (Integer) #for, [(Integer) for_id...]
  drawpicture   (Integer) from_id, (Short) x, (Short) y, (Integer) length,
                (Byte[]) data, (Integer) #for, [(Integer) for_id...]
  clearcanv	(Integer) from_id, (Integer) #for, [(Integer) for_id...]
  pageuser	(Integer) from_id, (Integer) #for, [(Integer) for_id...]
  instantmess   (Integer) from_id, (Integer) for_id, (String) message
  leavemess	(Integer) from_id, (String) for_name, (String) message
  readmess	(Integer) from_id
  storedmess	(Short) number, [(String) sender_name, (String) message]...
  error         (Integer) from_id, (Short) errorcode, (Integer) #for,
                [(Integer) for_id...]
*/


public class babylonCommand
{
    // The list of command type ids
    public static final short SETPROTO    = 1;
    public static final short NOOP        = 2;
    public static final short PING        = 3;
    public static final short CONNECT     = 4;
    public static final short USERINFO    = 5;
    public static final short SERVERMESS  = 6;
    public static final short DISCONNECT  = 7;
    public static final short ROOMLIST    = 8;
    public static final short INVITE      = 9;
    public static final short ENTERROOM   = 10;
    public static final short BOOTUSER    = 11;
    public static final short BANUSER     = 12;
    public static final short ALLOWUSER   = 13;
    public static final short ACTIVITY    = 14;
    public static final short CHATTEXT    = 15;
    public static final short LINE        = 16;
    public static final short RECT        = 17;
    public static final short OVAL        = 18;
    public static final short DRAWTEXT    = 19;
    public static final short DRAWPICTURE = 20;
    public static final short CLEARCANV   = 21;
    public static final short PAGEUSER    = 22;
    public static final short INSTANTMESS = 23;
    public static final short LEAVEMESS   = 24;
    public static final short READMESS    = 25;
    public static final short STOREDMESS  = 26;
    public static final short ERROR       = 27;
    public static final short ERASE       = 28;
    public static final short SAVE        = 29;
    public static final short PLAY        = 30;
    public static final short PLAYER      = 31;

    // Activity subtypes
    public static final short ACTIVITY_TYPING  = 1;
    public static final short ACTIVITY_DRAWING = 2;

    // Error subtypes
    public static final short ERROR_NOPAGE  = 1;
    public static final short ERROR_NOSOUND = 2;
}


