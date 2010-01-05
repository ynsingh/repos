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

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import uk.ac.reload.dweezil.gui.FlatCheckBox;
import uk.ac.reload.dweezil.gui.treetable.DweezilTreeTable;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_TreeRenderer;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Conference;


/**
 * ConferenceRoleTable
 * 
 * @author Phillip Beauvoir
 * @version $Id: ConferenceRoleTable.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ConferenceRoleTable
extends DweezilTreeTable
{
    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * The Table Model
     */
    private ConferenceRoleTableModel _tableModel;

    
    /**
     * @param treeTableModel
     */
    public ConferenceRoleTable() {
        super();
        
        _tableModel = new ConferenceRoleTableModel(this);
        setTreeTableModel(_tableModel);
        
		// Set up the Table
		getTableHeader().setReorderingAllowed(false);
		
		// Columns
		TableColumnModel tcm = getTableHeader().getColumnModel();
		tcm.getColumn(0).setPreferredWidth(150);
		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Make sure we have a renderer for the Tree
		getTree().setCellRenderer(new Editor_TreeRenderer());
		
		// Renderer for Table
		setDefaultRenderer(Boolean.class, new BooleanRenderer());
		
		// Editor for Table
		setDefaultEditor(Boolean.class, new BooleanEditor());

		// Don't show root node
		getTree().setRootVisible(false);
		
		// Stop double-clicks
		getTree().setToggleClickCount(0);
    }

    /**
     * Set the Conference
     * @param conference The Conference
     */
    public void setConference(Conference conference) {
        // Lazily set the Data Model first time
        if(getDataModel() == null) {
            setDataModel(conference.getDataModel());
        }
        
        _tableModel.setConference(conference);
        
        // Need to update
        repaint();
    }
    
    /**
     * Set the DataModel
     * @param dataModel
     */
    public void setDataModel(DataModel dataModel) {
        _dataModel = dataModel;
    }
    
    /**
     * @return The DataModel
     */
    public DataModel getDataModel() {
        return _dataModel;
    }
    
    /**
     * Clean up
     */
    public void cleanup() {
        _tableModel.cleanup();
        
        // This is important!
        _dataModel = null;
    }
    
    // =============================================================================================
    
    /**
     * Editor for check boxes
     */
    static class BooleanEditor
    extends DefaultCellEditor {

        public BooleanEditor() {
            super(new FlatCheckBox());
            FlatCheckBox checkBox = (FlatCheckBox)getComponent();
    	    checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                														int row, int column) {
            
            if(value == null) {
                return null;
            }
            else {
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
        }
    }
    
    
    /**
     * Renderer for check boxes
     */
    static class BooleanRenderer
    implements TableCellRenderer {
        FlatCheckBox checkbox;
        JLabel label;
        
        public BooleanRenderer() {
            checkbox = new FlatCheckBox() {
                {
                    setHorizontalAlignment(JLabel.CENTER);
                    setBorder(new LineBorder(Color.GRAY));
                }
                
            	protected void setUI() {
            		setOpaque(false);
            		setBorderPaintedFlat(true);
            	}
            };
            
            label = new JLabel();
        }
        
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component component;
            
            if(value == null) {
                component = label;
            }
            else {
                component = checkbox;
                checkbox.setSelected((value != null && ((Boolean)value).booleanValue()));
            }
            
            if(isSelected) {
                component.setForeground(table.getSelectionForeground());
                component.setBackground(table.getSelectionBackground());
            }
            else {
                component.setForeground(table.getForeground());
                component.setBackground(table.getBackground());
            }

            return component;
        }
    }
}
