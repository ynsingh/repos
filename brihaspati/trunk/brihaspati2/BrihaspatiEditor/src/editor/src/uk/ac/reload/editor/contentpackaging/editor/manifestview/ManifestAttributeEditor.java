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

package uk.ac.reload.editor.contentpackaging.editor.manifestview;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import org.jdom.Attribute;
import org.jdom.Element;

import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.gui.AttributeEditor;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaAttribute;


/**
 * The CP Attribute Panel.  This will display a Table for the attributes
 * of an Element.
 *
 * @author Phillip Beauvoir
 * @version $Id: ManifestAttributeEditor.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestAttributeEditor
extends AttributeEditor
{
	
	public ManifestAttributeEditor() {
		super();
	}
	
	/**
	 * @return the Table Cell Editor you'd like to use
	 * Sub-classers can over-ride this for their own editor
	 */
	protected TableCellEditor createTableCellEditor() {
		return new ManifestAttributeCellEditor();
	}
	
	/**
	 * @return the Table Model you'd like to use
	 * Sub-classers can over-ride this for their own models
	 */
	protected AbstractTableModel createTableModel() {
		return new ManifestAttributeTableModel();
	}
	
////////////////////////////////////////////////////////////////////////////////
///////////////////////  Attribute Table Model /////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
	
	class ManifestAttributeTableModel extends AttributeEditor.AttributeTableModel {
		
		/**
		 * The cell has been edited manually
		 * aValue will be a String or a mapping that we need to put into the Attribute
		 */
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// Convert mapping to String ID Value
			if(aValue instanceof ID_Mapping) aValue = ((ID_Mapping)aValue).getIdentifier();
			super.setValueAt(aValue, rowIndex, columnIndex);
		}
		
		/**
		 * @return The Value at the Cell in the Table.
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex) {
				// Convert IDENTIFIERREF attributes to actual Elements
				case 1:
					Attribute att = getAttribute(rowIndex);
					if(att != null) {
						ContentPackage cp = (ContentPackage) getElementBinding().getSchemaDocument();
						Element element = getElementBinding().getElement();
						String elementName = element.getName();
						
						Element ref_element = null;
						
						// IDENTIFIERREF for ITEM & DEPENDENCY
						if(elementName.equals(CP_Core.ITEM) || elementName.equals(CP_Core.DEPENDENCY)) {
							if(att.getName().equals(CP_Core.IDENTIFIERREF)) {
								ref_element = cp.getReferencedElement(element);
							}
						}
						
						// DEFAULT ORGANIZATION
						else if(elementName.equals(CP_Core.ORGANIZATIONS)) {
							if(att.getName().equals(CP_Core.DEFAULT)) {
								ref_element = cp.getDefaultOrganization(element);
							}
						}
						
						// Display a friendly thing
						if(ref_element != null) return cp.getElementDisplayName(ref_element);
					}
					
					return super.getValueAt(rowIndex, columnIndex);
					
				default:
					return super.getValueAt(rowIndex, columnIndex);
			}
		}
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////
///////////////////////  Attribute Cell Editor /////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * A Cell Editor Wrapper that delegates to two default Cell Editors
	 */
	class ManifestAttributeCellEditor extends AttributeEditor.AttributeCellEditor {
		
		public ManifestAttributeCellEditor() {
			super();
		}
		
		/**
		 * Over-ride this so we can return a mapping to something else
		 */
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			String attName = null;
			
			// Try for a Schema Attribute
			SchemaAttribute schemaAtt = getSchemaAttribute(row);
			if(schemaAtt != null) {
				attName = schemaAtt.getName();
			}
			// Nope, so go for the Attribute itself
			else {
				Attribute att = getAttribute(row);
				if(att != null) attName = att.getName();
			}
			
			if(attName != null) {
				ContentPackage cp = (ContentPackage) getElementBinding().getSchemaDocument();
				Element element = getElementBinding().getElement();
				String elementName = element.getName();
				
				Element[] elements = null;
				
				// IDENTIFIERREF for ITEM & DEPENDENCY
				if(elementName.equals(CP_Core.ITEM) || elementName.equals(CP_Core.DEPENDENCY)) {
					if(attName.equals(CP_Core.IDENTIFIERREF)) {
						elements = cp.getReferencedElementsAllowed(element);
					}
				}
				
				// DEFAULT ORGANIZATION
				else if(elementName.equals(CP_Core.ORGANIZATIONS)) {
					if(attName.equals(CP_Core.DEFAULT)) {
						elements = cp.getOrganizationsAllowed(element);
					}
				}
				
				// Found some referenced elements so create some mappings
				if(elements != null && elements.length > 0) {
					ID_Mapping[] id_mappings = new ID_Mapping[elements.length + 1];
					
					// Add an extra "(none)" element
					id_mappings[0] = new ID_Mapping(cp, null);
					
					// Save the id ref of what we are pointing at
					String id_ref = element.getAttributeValue(attName);
					
					// Save selected mapping
					ID_Mapping selected = null;
					
					for(int i = 1; i < id_mappings.length; i++) {
						// Create new mapping
						id_mappings[i] = new ID_Mapping(cp, elements[i - 1]);
						
						// Is this the selected one?
						if(id_ref != null && id_mappings[i].getIdentifier() != null) {
							if(id_ref.equals(id_mappings[i].getIdentifier())) {
								selected = id_mappings[i];
							}
						}
					}
					
					editor = comboEditor;
					comboBox.setItems(id_mappings);
					// makes it non-editable so you cannot enter an invalid String
					comboBox.setEditable(false);
					
					// Set selected
					comboBox.setSelectedItem(selected);
					
					return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
				}
			}
			
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
	}
	
	/**
	 * Convenience Mapping class
	 */
	class ID_Mapping {
		ContentPackage _cp;
		Element _element;
		
		ID_Mapping(ContentPackage cp, Element element) {
			_cp = cp;
			_element = element;
		}
		
		public String getIdentifier() {
			if(_element == null) return "";
			return _element.getAttributeValue(CP_Core.IDENTIFIER);
		}
		
		public String toString() {
			if(_element == null) return "(none)";
			return _cp.getElementDisplayName(_element);
		}
	}
	
}