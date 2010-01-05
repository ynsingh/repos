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

package uk.ac.reload.editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.gui.ComboBoxTipRenderer;
import uk.ac.reload.dweezil.gui.DweezilComboBox;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.vocab.VocabularyList;

/**
 * An Element Attribute Editor.  This will display a Table for the attributes
 * of an Element.
 *
 * @author Phillip Beauvoir
 * @version $Id: AttributeEditor.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class AttributeEditor
extends JPanel
implements XMLDocumentListener {

    /**
	 * The table to display the Attributes
	 */
    private JTable table;
	
	/**
	 * The Table Model
	 */
    private AbstractTableModel tableModel;
	
	/**
	 * The current ElementBinding
	 */
    private ElementBinding _elementBinding;
	
	
	/**
	 * Constructor
	 */
	public AttributeEditor() {
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);
		
		setBorder(null);
		
		// Set up the Table
		tableModel = createTableModel();
		table = new JTable(tableModel);
		
		// Single Selection
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Column width
		//table.getColumnModel().getColumn(0).setMaxWidth(120);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		
		// No re-ordering
		table.getTableHeader().setReorderingAllowed(false);
		
		// Set the default cell editor for any type object
		table.setDefaultEditor(Object.class, createTableCellEditor());
		
		// Set the default cell renderer for any type object
		AttributeCellRenderer cellRenderer = new AttributeCellRenderer();
		table.setDefaultRenderer(Object.class, cellRenderer);
		
		JScrollPane sp = new JScrollPane(table);
		add(sp, BorderLayout.CENTER);
	}
	
	/**
	 * Set The Panel to display the data for the Element
	 * @param elementBinding The ElementBinding class containing Element,
	 * SchemaElement and Document
	 */
	public void setElementBinding(ElementBinding elementBinding) {
		// If we are in mid table-edit stop the editing before updating new data
		if(table.isEditing()) {
		    table.getCellEditor().stopCellEditing();
		}
		
		// Remove old Listener
		if(_elementBinding != null) {
		    _elementBinding.getSchemaDocument().removeXMLDocumentListener(this);
		}
		
		_elementBinding = elementBinding;
		
		// Add Listener
		_elementBinding.getSchemaDocument().addXMLDocumentListener(this);
		
		// Tell the Model
		tableModel.fireTableDataChanged();
	}
	
    /**
     * @return Returns the ElementBinding.
     */
    public ElementBinding getElementBinding() {
        return _elementBinding;
    }

    /**
	 * Clean up
	 */
	public void cleanup() {
		if(_elementBinding != null) {
		    _elementBinding.getSchemaDocument().removeXMLDocumentListener(this);
		}
	}
	
	/**
	 * @return the Table Cell Editor you'd like to use
	 * Sub-classers can over-ride this for their own editor
	 */
	protected TableCellEditor createTableCellEditor() {
		return new AttributeCellEditor();
	}
	
	/**
	 * @return the Table Model you'd like to use
	 * Sub-classers can over-ride this for their own models
	 */
	protected AbstractTableModel createTableModel() {
		return new AttributeTableModel();
	}
	
	
	// =============================================================================
	// ===================== These events are received FROM the Document ===========
	// =============================================================================
	
	public void elementChanged(XMLDocumentListenerEvent event) {
		// Don't listen to events that we have fired
		if(event.getSource() == this) return;
		
		// If it's us update
		Element element = _elementBinding.getElement();
		if(element != null) {
			Element changed_element = event.getElement();
			if(element == changed_element) {
				repaint();
				//tableModel.fireTableDataChanged();
			}
		}
	}
	
	public void elementRemoved(XMLDocumentListenerEvent event) {}
	public void elementAdded(XMLDocumentListenerEvent event) {}
	public void documentSaved(XMLDocument doc) {}
	
	
	// =============================================================================
	// ==================== Convenience methods on Attributes ======================
	// =============================================================================
	
	/**
	 * Get the JDOM Attribute at attIndex or null if there isn't one.
	 * @return The JDOM Attribute which may be null if there isn't one
	 */
	public Attribute getAttribute(int attIndex) {
		Attribute att = null;
		
		// Try and get the details from the Schema
		SchemaAttribute schemaAttribute = getSchemaAttribute(attIndex);
		if(schemaAttribute != null && _elementBinding.getElement() != null) {
			String attName = schemaAttribute.getName();
			// Another Namespace
			if(schemaAttribute.isExternalNamespace()) {
				Namespace ns = schemaAttribute.getNamespace();
				att = _elementBinding.getElement().getAttribute(attName, ns);
			}
			// Same Namespace
			else {
				att = _elementBinding.getElement().getAttribute(attName);
			}
		}
		
		// No SchemaAttribute, so try to get it from the Element
		else if(_elementBinding.getElement() != null) {
			java.util.List list = _elementBinding.getElement().getAttributes();
			if(attIndex < list.size()) att = (Attribute)list.get(attIndex);
		}
		
		return att;
	}
	
	/**
	 * Get the Friendly Name of the Attribute
	 * @return The Friendly Name of the Attribute or the Attribute name if there isn't one
	 */
	public String getAttributeFriendlyName(int attIndex) {
		String fname = ""; //$NON-NLS-1$
		
		// Get it from the Schema Controller if we can
		SchemaAttribute schemaAttribute = getSchemaAttribute(attIndex);
		if(schemaAttribute != null) {
			fname = _elementBinding.getSchemaController().getElementFriendlyName(schemaAttribute.getXMLPath());
			if(fname == null) fname = schemaAttribute.getName();
		}
		// No SchemaAttribute
		else {
			Attribute att = getAttribute(attIndex);
			if(att != null) {
				fname = _elementBinding.getSchemaController().getElementFriendlyName(XMLPath.getXMLPathForAttribute(att));
				if(fname == null) fname = att.getName();
			}
		}
		
		return fname;
	}
	
	/**
	 * Get the ToolTip for the Attribute
	 * @return The ToolTip for the Attribute or null
	 */
	public String getAttributeToolTip(int attIndex) {
		String tip = null;
		
		// Get it from the Schema Controller if we can
		SchemaAttribute schemaAttribute = getSchemaAttribute(attIndex);
		if(schemaAttribute != null) {
			tip = _elementBinding.getSchemaController().getElementTip(schemaAttribute.getXMLPath());
		}
		// No SchemaAttribute
		else {
			Attribute att = getAttribute(attIndex);
			if(att != null) {
				tip = _elementBinding.getSchemaController().getElementTip(XMLPath.getXMLPathForAttribute(att));
			}
		}
		
		return tip;
	}
	
	/**
	 * Return the Vocabulary for this Attribute
	 * @return The Vocabulary for this Attribute or null if there isn't one
	 */
	public VocabularyList getAttributeVocabulary(int attIndex) {
		VocabularyList vocab = null;
		SchemaAttribute schemaAttribute = getSchemaAttribute(attIndex);
		if(schemaAttribute != null) {
			vocab = _elementBinding.getSchemaController().getVocabularyList(schemaAttribute);
		}
		return vocab;
	}
	
	/**
	 * Get the SchemaAttribute
	 * @return The associated SchemaAttribute or null if there isn't one
	 */
	public SchemaAttribute getSchemaAttribute(int attIndex) {
		return _elementBinding.getSchemaElement() == null ? null : _elementBinding.getSchemaElement().getSchemaAttributeAtIndex(attIndex);
	}


	
	// =============================================================================
	// ===================== AttributeTableModel ===================================
	// =============================================================================

	/**
	 * TableModel for the Attribute Table
	 */
	protected class AttributeTableModel extends AbstractTableModel {
	    /**
	     * The Column Names
	     */
	    String[] cNames = {
	            Messages.getString("uk.ac.reload.editor.gui.AttributeEditor.0"), //$NON-NLS-1$
	            Messages.getString("uk.ac.reload.editor.gui.AttributeEditor.1") //$NON-NLS-1$
	    };
	    
	    /**
	     * The cell has been edited manually
	     * aValue will be a String that we need to put into the Attribute
	     */
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	        if(aValue == null || !(aValue instanceof String)) return;
	        
	        // Only column 1 allowed
	        if(columnIndex == 1) {
	            SchemaDocument doc = _elementBinding.getSchemaDocument();
	            
	            Element element = _elementBinding.getElement();
	            
	            // Element is null so try to create one
	            if(element == null) element = _elementBinding.createElement(this);
	            
	            // Oops
	            if(element == null) return;
	            
	            Attribute att = getAttribute(rowIndex);
	            
	            String oldValue = (att == null) ? null : att.getValue();
	            
	            // A new value
	            if(!aValue.equals(oldValue)) {
	                // A blank value means we can remove the attribute
	                if(aValue.equals("")) { //$NON-NLS-1$
	                    if(att != null) {
	                        element.removeAttribute(att);
	                        doc.changedElement(AttributeEditor.this, element);
	                    }
	                    return;
	                }
	                
	                // If we don't have an Attribute, create one
	                if(att == null) {
	                    SchemaAttribute schemaAttribute = getSchemaAttribute(rowIndex);
	                    if(schemaAttribute != null) {
	                        att = doc.addAttribute(AttributeEditor.this, element, schemaAttribute);
	                    }
	                }
	                
	                if(att != null) {
	                    att.setValue((String) aValue);
	                    doc.changedElement(AttributeEditor.this, element);
	                }
	            }
	        }
	    }
	    
	    /**
	     * @return The Value at the Cell in the Table.
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex) {
	            case 0:
	                // Column 0 displays the Friendly Name of the Attibute
	                return getAttributeFriendlyName(rowIndex);
	            case 1:
	                // Column 1 displays the Attribute Value from the JDOM Document
	                Attribute att = getAttribute(rowIndex);
	                return att == null ? null : att.getValue();
	            default:
	                return ""; //$NON-NLS-1$
	        }
	    }
	    
	    /**
	     * Get the Column count
	     * @return The Column count
	     */
	    public int getColumnCount() {
	        return cNames.length;
	    }
	    
	    /**
	     * Get the Column Name
	     * @param columnIndex
	     * @return
	     */
	    public String getColumnName(int columnIndex) {
	        return cNames[columnIndex];
	    }
	    
	    /**
	     * Return the amount of rows needed to display the number of Attributes
	     * as specified in the SchemaModel.
	     * @return The number of rows.
	     */
	    public int getRowCount() {
	        if(_elementBinding != null) {
	            // We have a Schema Element
	            if(_elementBinding.getSchemaElement() != null) {
	                SchemaAttribute[] atts = _elementBinding.getSchemaElement().getSchemaAttributes();
	                return atts.length;
	            }
	            // We don't have a Schema Element
	            else if(_elementBinding.getElement() != null) {
	                java.util.List list = _elementBinding.getElement().getAttributes();
	                return list.size();
	            }
	        }
	        return 0;
	    }
	    
	    /**
	     * Is this cell editable? It is if it's in column 1
	     */
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return columnIndex == 1;
	    }
	}
	
	// =============================================================================
	// ======================== Attribute Cell Editor ==============================
	// =============================================================================
	
	/**
	 * A Cell Editor Wrapper that delegates to two default Cell Editors,
	 * a JTextField and a DweezilComboBox
	 */
	protected class AttributeCellEditor extends AbstractCellEditor implements TableCellEditor {
	    protected DweezilComboBox comboBox;
	    protected JTextField textField;
	    protected DefaultCellEditor editor;
	    protected DefaultCellEditor textEditor;
	    protected DefaultCellEditor comboEditor;
	    
	    public AttributeCellEditor() {
	        // TextField
	        textField = new JTextField();
	        textField.setBorder(null);
	        textEditor = new DefaultCellEditor(textField);
	        
	        // Combobox
	        comboBox = new DweezilComboBox(true);
	        comboBox.setBorder(null);
	        // This sets a better font for Metal L&F
	        comboBox.setFont(textField.getFont());
	        // Show tooltips
	        comboBox.setRenderer(new ComboBoxTipRenderer());
	        comboEditor = new DefaultCellEditor(comboBox);
	    }
	    
	    public Object getCellEditorValue() {
	        return editor.getCellEditorValue();
	    }
	    
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        // If we have a Vocabulary for this Attribute, populate the Combo Box
	        VocabularyList vocab = getAttributeVocabulary(row);
	        if(vocab != null) {
	            editor = comboEditor;
	            comboBox.setItems(vocab.getList());
	            comboBox.setEditable(true);
	        }
	        // Otherwise use a text Field
	        else {
	            editor = textEditor;
	        }
	        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
	    }
	}
	
	/**
	 * Extend the Table Cell Renderer so we can show a tooltip
	 */
	protected class AttributeCellRenderer extends DefaultTableCellRenderer {
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	     */
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	            boolean hasFocus, int row, int column) {
	        String tip = getAttributeToolTip(row);
	        setToolTipText(tip);
	        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    }
	    
	}
}
