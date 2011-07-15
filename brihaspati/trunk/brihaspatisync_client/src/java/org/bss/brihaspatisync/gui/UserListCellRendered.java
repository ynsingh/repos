package org.bss.brihaspatisync.gui;

/**
 * UserListCellRendered.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,Kanpur.
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListCellRenderer;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

class UserListCellRendered implements ListCellRenderer {

        protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
                Font theFont = null;
                Color theForeground = null;
                ImageIcon theIcon = null;
                String theText = null;

                JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                if (value instanceof Object[]) {
                        Object values[] = (Object[]) value;
                        theFont = (Font) values[0];
                        theForeground = (Color) values[1];
                        theIcon = (ImageIcon) values[2];
                        theText = (String) values[3];
                } else {
                        theFont = list.getFont();
                        theForeground = list.getForeground();
                        theText = "";
                }
                if (isSelected) {
                        renderer.setForeground(theForeground);
			HandRaiseAction.getController().setSelectedUsername(theText);				
                }
                if (theIcon != null) {
                        renderer.setIcon(theIcon);
                }
                renderer.setText(theText);
                renderer.setFont(theFont);
                return renderer;
        }
}

