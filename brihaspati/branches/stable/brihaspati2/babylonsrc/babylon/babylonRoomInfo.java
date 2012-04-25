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
//  babylonRoomInfo.java
//

import java.util.*;
import babylon.*;


public class babylonRoomInfo
{
    // This class is just a data structure to hold information about a
    // chat room.  It needs its own source file because it's used by both
    // the babylonRoomsDialog class and the babylonClient class

    String name;
    String creatorName;
    boolean priv;
    boolean invited;
    boolean roomOwner;
    Vector userNames;

    babylonRoomInfo()
    {
	name = "";
	creatorName = "";
	priv = false;
	invited = false;
	roomOwner = false;
	userNames = new Vector();
    }
}
