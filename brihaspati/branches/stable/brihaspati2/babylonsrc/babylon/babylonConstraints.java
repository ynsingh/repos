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
//  babylonConstraints.java
//

import java.awt.*;
import babylon.*;


public class babylonConstraints
    extends GridBagConstraints
{
    // The GridBagConstraints in Java versions prior to 1.2 doesn't contain
    // this very useful constructor:

    public babylonConstraints(int gridx, int gridy, int gridwidth,
			      int gridheight, double weightx, double weighty,
			      int anchor, int fill, Insets insets,
			      int ipadx, int ipady)
    {
	super();

	this.gridx = gridx;
	this.gridy = gridy;
	this.gridwidth = gridwidth;
	this.gridheight = gridheight;
	this.weightx = weightx;
	this.weighty = weighty;
	this.anchor = anchor;
	this.fill = fill;
	this.insets = insets;
	this.ipadx = ipadx;
	this.ipady = ipady;
    }
}
