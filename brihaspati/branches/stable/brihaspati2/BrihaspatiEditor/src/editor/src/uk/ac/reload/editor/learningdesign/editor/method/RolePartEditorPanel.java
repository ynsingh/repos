/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *  Project Management Contact:
 *
 *  Oleg Liber
 *  Bolton Institute of Higher Education
 *  Deane Road
 *  Bolton BL3 5AB
 *  UK
 *
 *  e-mail:   o.liber@bolton.ac.uk
 *
 *
 *  Technical Contact:
 *
 *  Phillip Beauvoir
 *  e-mail:   p.beauvoir@bolton.ac.uk
 *
 *  Web:      http://www.reload.ac.uk
 *
 */

package uk.ac.reload.editor.learningdesign.editor.method;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.reload.dweezil.gui.ComponentHiderButton;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.IdentifierLabelTextField;
import uk.ac.reload.editor.gui.TitledEditorPanel;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;

/**
 * Role Part Editor Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: RolePartEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RolePartEditorPanel
extends TitledEditorPanel
{
	/**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;
	
	/**
	 * Labels
	 */
	private JLabel _actLabel, _roleLabel;
	
    /**
     * Identifier TextField
     */
    private IdentifierLabelTextField _tfIdentifier;

    /**
     * Role tree
     */
    private RolePartRoleSelectorTree _roleTree;
	
	/**
	 * Activity Tree
	 */
	private RolePartActivitySelectorTree _activityTree;
	
    /**
	 * Default Constructor
	 */
	public RolePartEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set sub-panels
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);

	    RolePart rolePart = (RolePart)dataComponent;
	    
	    // Wrap the RolePart Element
	    DataElement rolePartElement = dataComponent.getDataElement();
	    
	    // Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(rolePartElement);
	    }
	    
	    // Role Tree
	    _roleTree.setRolePartComponent(rolePart);
	    
	    // Other Tree
	    _activityTree.setRolePartComponent(rolePart);
	}

	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

	    RelativeLayoutManager layoutManager = getRelativeLayoutManager();
	    
	    int LEFTX = 10;
	    
		// Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier = new IdentifierLabelTextField();
	    	LabelComponentPanel panelIdentifier = new LabelComponentPanel("Identifier:", _tfIdentifier, 0.3, 0);
			layoutManager.addFromLeftToRightEdges(panelIdentifier, "panelIdentifier", TOPPANEL_ID,
			        RelativeLayoutManager.BOTTOM, 10, 10);
	    }

	    ExpanderLabel label1 = new ExpanderLabel("Role:");
		label1.setFont(label1.getFont().deriveFont(Font.BOLD, 12));
		if(SHOW_IDENTIFIER) {
		    layoutManager.addFromLeftEdge(label1, "label1", "panelIdentifier",
		            RelativeLayoutManager.BOTTOM, 15, LEFTX + 16);
		}
		else {
		    layoutManager.addFromLeftEdge(label1, "label1", TOPPANEL_ID,
		            RelativeLayoutManager.BOTTOM, 10, LEFTX + 16);
		}
		
		_roleLabel = new JLabel("(not set)");
		_roleLabel.setFont(_roleLabel.getFont().deriveFont(Font.BOLD, 12));
		layoutManager.addFromLeftToRightEdges(_roleLabel, "_roleLabel", "label1",
				RelativeLayoutManager.TOP, 0, LEFTX + 110);

		_roleTree = new RolePartRoleSelectorTree(_roleLabel);
		JScrollPane sp1 = new JScrollPane(_roleTree);
		sp1.setPreferredSize(new Dimension(0, 200));
		sp1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JPanel dummy1 = new JPanel(new BorderLayout());
		dummy1.add(sp1);
		layoutManager.addFromLeftToRightEdges(dummy1, "_roleTree", "_roleLabel",
				RelativeLayoutManager.BOTTOM, 10, LEFTX);
		
		ComponentHiderButton hider1 = new ComponentHiderButton(dummy1);
		hider1.showComponent(false);
		hider1.setToolTipText("Click to expand");
		layoutManager.addFromLeftEdge(hider1, "hider1", "label1", RelativeLayoutManager.TOP, 2, LEFTX);
		label1.setComponentHiderButton(hider1);
		
		ExpanderLabel label2 = new ExpanderLabel("Activity:");
		label2.setFont(label2.getFont().deriveFont(Font.BOLD, 12));
		layoutManager.addFromLeftEdge(label2, "label2", "_roleTree",
				RelativeLayoutManager.BOTTOM, 10, LEFTX + 16);
		
		_actLabel = new JLabel("(not set)");
		_actLabel.setFont(_actLabel.getFont().deriveFont(Font.BOLD, 12));
		layoutManager.addFromLeftToRightEdges(_actLabel, "_actLabel", "label2",
				RelativeLayoutManager.TOP, 0, LEFTX + 110);
		
		_activityTree = new RolePartActivitySelectorTree(_actLabel);
		JScrollPane sp2 = new JScrollPane(_activityTree);
		sp2.setPreferredSize(new Dimension(0, 200));
		sp2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JPanel dummy2 = new JPanel(new BorderLayout());
		dummy2.add(sp2);
		layoutManager.addFromLeftToRightEdges(dummy2, "_activityTree", "_actLabel",
				RelativeLayoutManager.BOTTOM, 10, LEFTX);
		
		ComponentHiderButton hider2 = new ComponentHiderButton(dummy2);
		hider2.showComponent(false);
		hider2.setToolTipText("Click to expand");
		layoutManager.addFromLeftEdge(hider2, "hider2", "label2", RelativeLayoutManager.TOP, 2, LEFTX);
		label2.setComponentHiderButton(hider2);
	}
	
	/**
	 * A ExpanderLabel Label
	 */
	class ExpanderLabel extends JLabel {
	    
	    public ExpanderLabel(String title) {
	        setText(title);
	        //setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
	        setFont(getFont().deriveFont(Font.BOLD, 12));
	        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        setToolTipText("Click to expand");
	    }
	    
	    public void setComponentHiderButton(final ComponentHiderButton hiderButton) {
	        addMouseListener(new MouseAdapter() {
	            public void mouseReleased(MouseEvent e) {
	                hiderButton.showComponent(hiderButton.isHidden());
	            }
	        });
	    }
	}
	
    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_roleTree != null) {
            _roleTree.cleanup();
        }
        
        if(_activityTree != null) {
            _activityTree.cleanup();
        }
    }

}
