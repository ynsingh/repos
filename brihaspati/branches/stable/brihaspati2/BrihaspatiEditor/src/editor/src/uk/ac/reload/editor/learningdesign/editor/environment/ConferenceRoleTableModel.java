/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

package uk.ac.reload.editor.learningdesign.editor.environment;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.treetable.TreeTableModel;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Conference;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.editor.roles.RoleTreeModel;


/**
 * Conference Role TableModel
 * 
 * @author Phillip Beauvoir
 * @version $Id: ConferenceRoleTableModel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ConferenceRoleTableModel
extends RoleTreeModel
implements TreeTableModel
{
    
    /**
     * The owning tabletree
     */
    private ConferenceRoleTable _table;
    
    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * The Conference
     */
    private Conference _conference;
    
    
    /**
     * The Class types for the Columns - this is important because the tree
     * won't display properly without it.
     */
    private static Class[] cTypes = { 
            TreeTableModel.class,
            Boolean.class,
            Boolean.class,
            Boolean.class,
            Boolean.class
    };

    /**
     * The Column Names
     */
    private static String[] cNames = {
            "Role",  
            "Participant",
            "Observer",
            "Manager",
            "Moderator"
    };
    

    /**
     * Default Constructor
     */
    public ConferenceRoleTableModel(ConferenceRoleTable table) {
        super((DweezilTree)table.getTree());
        
        _table = table;
    }
    
    /**
     * Set the Conference
     * @param conference The Conference
     */
    public void setConference(Conference conference) {
        // Lazily set the Data Model first time
        if(getDataModel() == null) {
            setDataModel(conference.getDataModel());
        	((DweezilTree)getTree()).expandTree(true);
        }
        
        _conference = conference;
    }
    
    /**
     * Get the Child count in the Model
     * @param parent the parent node
     * @return the Number of children of parent
     */
    public int getChildCount(Object parent) {
        Editor_TreeNode node = (Editor_TreeNode) parent;
        return node.getChildCount();
    }

    /**
     * Get the child node at index point
     * @param parent The parent node
     * @param index The index of the child
     * @return The Child node at the index point
     */
    public Object getChild(Object parent, int index) {
        Editor_TreeNode node = (Editor_TreeNode) parent;
        return node.getChildAt(index);
    }

    /**
     * Set the value of a node
     * @param aValue The value to set
     * @param node The node whose value will be set
     * @param column The column of the node
     */
    public void setValueAt(Object aValue, Object node, int column) {
        Editor_TreeNode roleNode = (Editor_TreeNode)node;
        LD_Component ldComponent = (LD_Component)roleNode.getUserObject();
        
        if(ldComponent instanceof LD_Grouping) {
            return;
        }
        
        Role role = (Role)ldComponent;
        
        switch(column) {
            // Participant
            case 1:
                if(_conference.isParticipant(role) && !_conference.isLastParticipant()) {
                    _conference.removeParticipant(role);
                }
                else {
                    _conference.addParticipant(role);
                }
                _table.repaint();
                break;
            
            // Observer                
            case 2:
                if(_conference.isObserver(role)) {
                    _conference.removeObserver(role);
                }
                else {
                    _conference.addObserver(role);
                }
                _table.repaint();
                break;
            
            // Manager
            case 3:
                if(_conference.isManager(role)) {
                    _conference.removeManager(role);
                }
                else {
                    _conference.addManager(role);
                }
                _table.repaint();
                break;
            
            // Moderator
            case 4:
                if(_conference.isModerator(role)) {
                    _conference.removeModerator(role);
                }
                else {
                    _conference.addModerator(role);
                }
                _table.repaint();
                break;
            
            default:
                break;
        }
    }

    /**
     * Get the value for a given node and column.
     * This asks for the value at column 1 or greater, column 0 is the JTree
     * @param node The Node to get the value for
     * @param column The column
     * @return The appropriate value.
     */
    public Object getValueAt(Object node, int column) {
        Editor_TreeNode roleNode = (Editor_TreeNode)node;
        LD_Component ldComponent = (LD_Component)roleNode.getUserObject();
        
        if(ldComponent instanceof LD_Grouping) {
            return null;
        }
        
        Role role = (Role)ldComponent;
        
        switch(column) {
            // Participant
            case 1:
                return new Boolean(_conference.isParticipant(role));
                
            // Observer
            case 2:
                return new Boolean(_conference.isObserver(role));
                
            // Manager
            case 3:
                return new Boolean(_conference.isManager(role));
                
            // Moderator
            case 4:
                return new Boolean(_conference.isModerator(role));
                
            default:
                return "";
        }
    }

    /**
     * Determine whether a particular cell is editable.
     * @param node The node we are asking.
     * @param column The column to query
     * @return true if the cell is editable, false otherwise
     */
    public boolean isCellEditable(Object node, int column) {
        return true;
    }

    /**
     * Return the type of class for each column.
     * Column 0 has to be of type TreeTableModel.class
     * @param column The column to query
     * @return The class associated with each column
     */
    public Class getColumnClass(int column) {
        return cTypes[column];
    }

    /**
     * Get the number of columns in our model.
     * @return The number of columns in our model.
     */
    public int getColumnCount() {
        return cNames.length;
    }

    /**
     * Get the name for a particular column.
     * @param column The column to ask.
     * @return The name of the column.
     */
    public String getColumnName(int column) {
        return cNames[column];
    }
    
}
