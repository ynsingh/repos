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

package uk.ac.reload.editor.scorm.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.layout.XYConstraints;
import uk.ac.reload.dweezil.gui.layout.XYLayout;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.editor.gui.ElementAttributePanel;
import uk.ac.reload.editor.gui.ElementEditor;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.contentpackaging.SCORM12_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;

/**
 * The SCORM Editor Dialog used in the CP Editor to edit SCORM Elements
 * It edits SCORM Elements from an Item node of a CP
 *
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_EditorDialog.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SCORM12_EditorDialog
extends JDialog
implements IIcons
{
	
	/**
	 * The Content Package to which this Item Element belongs
	 */
    private ContentPackage _contentPackage;
	
	/**
	 * The parent "item" element containing the SCORM Elements
	 */
    private Element _itemElement;
	
	/**
	 * The schemaElement
	 */
    private SchemaElement _schema_itemElement;
	
	/**
	 * A list box to display the list of Items
	 */
    private JList _itemListBox;
	
	/**
	 * Prerequisites Editor
	 */
    private ElementAttributePanel _prereqEditor;
	
	/**
	 * Dialog Width
	 */
    private int WIDTH = 780;
	
	/**
	 * Dialog Height
	 */
    private int HEIGHT = 555;
	
	/**
	 * Constructor
	 */
	public SCORM12_EditorDialog(ContentPackage contentPackage, Element itemElement, SchemaElement schema_itemElement) {
		super(EditorFrame.getInstance(), Messages.getString("uk.ac.reload.editor.contentpackaging.scorm12.SCORM12_EditorDialog.0"), true); //$NON-NLS-1$
		
		_itemElement = itemElement;
		_contentPackage = contentPackage;
		_schema_itemElement = schema_itemElement;
		
		setResizable(false);
		
		// Trap window closing event for our Window listener
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Add a listener to Close our window down on exit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		// Main Panel
		JPanel mainPanel = createMainPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		// Button Panel
		JPanel buttonPanel = createButtonPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		// Dialog Size
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(EditorFrame.getInstance());
	}
	
	
	/**
	 * @return the Main Panel
	 */
	protected JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new XYLayout());
		
		int x = 5, y = 5;
		
		// Prerequisites Panel
		JPanel prereqPanel = createPrerequisitesPanel();
		mainPanel.add(prereqPanel, new XYConstraints(x, y, WIDTH - 15, 250));
		
		y += 265;
		
		// Other Panel
		JPanel otherPanel = createSCORMElementsPanel();
		mainPanel.add(otherPanel, new XYConstraints(x, y, WIDTH - 15, 220));
		
		return mainPanel;
	}
	
	/**
	 * @return the Prerequisites element editing Panel
	 */
	protected JPanel createPrerequisitesPanel() {
		JPanel panel = new JPanel(new XYLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		SchemaController schemaController = _contentPackage.getSchemaController();
		
		SchemaElement prereqSchemaElement = _schema_itemElement.getChild(SCORM12_Core.PREREQUISITES,
				CP_EditorHandler.ADLCP_NAMESPACE_12);
		
		if(prereqSchemaElement != null) {
			int x = 5, y = 0;
			
			// Friendly name
			String name = schemaController.getElementFriendlyName(prereqSchemaElement.getXMLPath());
			JLabel nameLabel = new JLabel();
			nameLabel.setForeground(Color.BLUE);
			nameLabel.setFont(DweezilUIManager.boldFont12);
			nameLabel.setText(name);
			panel.add(nameLabel, new XYConstraints(x, y, 100, 25));
			
			y += 35;
			
			// Element binding
			Element element = _itemElement.getChild(prereqSchemaElement.getName(), prereqSchemaElement.getNamespace());
			ElementBinding eb = new ElementBinding(element, prereqSchemaElement, _contentPackage);
			// No element yet so set parent instead
			if(element == null) {
			    eb.setParentElement(_itemElement);
			}
			
			// Element Editor
			_prereqEditor = new ElementAttributePanel();
			_prereqEditor.setElementBinding(eb);
			panel.add(_prereqEditor, new XYConstraints(x, y, 340, 200));
			
			// Add Button
			JButton addButton = new JButton(DweezilUIManager.getIcon(ICON_LEFT));
			panel.add(addButton, new XYConstraints(345, 120, 0, 0));
			addButton.addActionListener(new AddClick(_prereqEditor));
			
			// Add Item Listbox
			_itemListBox = new JList(new ItemListModel());
			_itemListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			_itemListBox.addMouseListener(new AddClick(_prereqEditor));
			panel.add(new JScrollPane(_itemListBox), new XYConstraints(405, y, 340, 200));
		}
		
		return panel;
	}
	
	/**
	 * @return the editing Panel for the other SCORM Elements
	 */
	protected JPanel createSCORMElementsPanel() {
		JPanel panel = new JPanel(new XYLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		int x = 5, y = 0;
		
		// Max time allowed
		SchemaElement maxtimeallowedSchemaElement = _schema_itemElement.getChild(SCORM12_Core.MAXTIMEALLOWED,
				CP_EditorHandler.ADLCP_NAMESPACE_12);
		addElement(maxtimeallowedSchemaElement, panel, x, y, 120, 25);
		
		y += 60;
		
		// Time Limit Action
		SchemaElement schemaElement = _schema_itemElement.getChild(SCORM12_Core.TIMELIMITACTION,
				CP_EditorHandler.ADLCP_NAMESPACE_12);
		addElement(schemaElement, panel, x, y, 320, 100);
		
		x = 360; y = 0;
		
		// Data from LMS
		schemaElement = _schema_itemElement.getChild(SCORM12_Core.DATAFROMLMS,
				CP_EditorHandler.ADLCP_NAMESPACE_12);
		addElement(schemaElement, panel, x, y, 200, 25);
		
		y += 60;
		
		// Mastery Score
		schemaElement = _schema_itemElement.getChild(SCORM12_Core.MASTERYSCORE,
				CP_EditorHandler.ADLCP_NAMESPACE_12);
		addElement(schemaElement, panel, x, y, 200, 25);
		
		return panel;
	}
	
	
	/**
	 * Add an Element to the Panel
	 */
	protected void addElement(SchemaElement schemaElement, JPanel panel, int x, int y, int width, int height) {
		if(schemaElement != null) {
			SchemaController schemaController = _contentPackage.getSchemaController();
			
			// Friendly name
			String name = schemaController.getElementFriendlyName(schemaElement.getXMLPath());
			
			// Name label
			JLabel nameLabel = new JLabel();
			nameLabel.setForeground(Color.BLUE);
			nameLabel.setFont(DweezilUIManager.boldFont12);
			nameLabel.setText(name);
			panel.add(nameLabel, new XYConstraints(x, y, 100, 25));
			
			y += 30;
			
			// Element binding
			Element element = _itemElement.getChild(schemaElement.getName(), schemaElement.getNamespace());
			ElementBinding eb = new ElementBinding(element, schemaElement, _contentPackage);
			// No element yet so set parent instead
			if(element == null) {
			    eb.setParentElement(_itemElement);
			}
			
			// Element Editor
			ElementEditor elementEditor = new ElementEditor();
			elementEditor.setElementBinding(eb);
			panel.add(elementEditor, new XYConstraints(x, y, width, height));
		}
	}
	
	/**
	 * @return the Button Panel
	 */
	protected JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		// OK Button
		JButton btnOK = new JButton(Messages.getString("uk.ac.reload.editor.contentpackaging.scorm12.SCORM12_EditorDialog.1")); //$NON-NLS-1$
		btnOK.addActionListener(new OKClick());
		getRootPane().setDefaultButton(btnOK);
		buttonPanel.add(btnOK);
		
		return buttonPanel;
	}
	
	/**
	 * Show the Dialog
	 */
	public void show() {
		// Do this later because a modal dialog will block
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_prereqEditor.getSplitpane().setDividerLocation(0.7);
			}
		});
		super.show();
	}
	
	/**
	 * User pressed OK
	 */
	protected void finish() {
		
		// Tell listeners
		//_contentPackage.changedElement(this, _itemElement);
		
		// Finished
		dispose();
	}
	
	/**
	 * Clean up
	 */
	public void dispose() {
		try {
			// Do cleanup stuff here
			super.dispose();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * OK handler
	 */
	private class OKClick extends AbstractAction {
		
		public void actionPerformed(ActionEvent e) {
			finish();
		}
	}
	
	/**
	 * Add handler
	 */
	private class AddClick extends AbstractAction implements MouseListener {
		ElementAttributePanel _elementEditor;
		
		public AddClick(ElementAttributePanel elementEditor) {
			_elementEditor = elementEditor;
		}
		
		public void actionPerformed(ActionEvent e) {
			addText();
		}
		
		private void addText() {
			// Insert Identifier Text
			Object object = _itemListBox.getSelectedValue();
			if(object instanceof ID_Mapping) {
				String id = ((ID_Mapping)object).getIdentifier();
				if(id != null) {
					_elementEditor.getElementEditor().insertText(id);
					_elementEditor.getElementEditor().setFocus();
				}
			}
		}
		
		/**
		 * Double-clicks
		 */
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) addText();
		}
		
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Convenience mapping class
	 */
	class ID_Mapping {
		Element _element;
		
		ID_Mapping(Element element) {
			_element = element;
		}
		
		public String getIdentifier() {
			return _element.getAttributeValue(CP_Core.IDENTIFIER);
		}
		
		public String toString() {
			return _contentPackage.getElementDisplayName(_element) + "   (" + getIdentifier() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	/**
	 * List Model
	 */
	class ItemListModel extends AbstractListModel {
		Vector v = new Vector();
		
		public ItemListModel() {
			// Fill the list with Items that can be Pre-requisites
			Element manifestElement = _contentPackage.getParentManifestElement(_itemElement);
			if(manifestElement != null) {
				Element[] items = _contentPackage.getElementsInManifest(manifestElement, CP_Core.ITEM, manifestElement.getNamespace());
				for(int i = 0; i < items.length; i++) {
					// Don't include the Item that we are
					if(!items[i].equals(_itemElement)) {
						ID_Mapping mapping = new ID_Mapping(items[i]);
						v.add(mapping);
					}
				}
			}
		}
		
		public int getSize() {
			return v.size();
		}
		
		public Object getElementAt(int index) {
			return v.elementAt(index);
		}
	}
	
}