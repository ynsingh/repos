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

package uk.ac.reload.editor.gui.widgets;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;


/**
 * An adapter class that listens to JTextComponent Document changes and notifies the XML DOM.<p>
 * <p>
 * This class is associated with a JTextComponent and its Text Document.  It is further bound to a JDOM
 * element or attribute or xml path. Once an edit event occurs on the JTextComponent's Document model, a change
 * event is fired to the LearningDesign object notifying that the element or attribute has been changed.
 * If the element does not exist, at the first edit event it will be created.
 *
 * @author Phillip Beauvoir
 * @version $Id: DocumentListenerAdapter.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class DocumentListenerAdapter
implements DocumentListener
{
	
    /**
	 * The bound, possibly uncreated yet, JDOM Element.
	 */
	private DataElement _dataElement;
	
	/**
	 * The Attribute's name we may be bound to.  If null, then not bound to an Attribute
	 */
	private String _attName;

	/**
	 * The Attribute's namespace we may be bound to.  If null, then not bound to an Attribute
	 */
	private Namespace _ns;
	
	/**
	 * The SchemaAttribute we may be bound to.  If null, then not bound to an Attribute
	 */
	private SchemaAttribute _schemaAttribute;

	/**
     * The Text Component we listen to
     */
    private JTextComponent _textComponent;
    
	/**
     * Flag for text change notifications
     */
    private boolean doNotify = true;
    

	/**
	 * Constructor
	 * @param textComponent The Text Component to listen to.
	 */
	public DocumentListenerAdapter(JTextComponent textComponent) {
	    _textComponent = textComponent;
	    _textComponent.getDocument().addDocumentListener(this);
	}
	
	/**
	 * Set the Attribute to be edited.
	 * @param dataElement The bound JDOM parent Element of the Attribute to be edited
	 * @param attName The name of the Attribute to be edited
	 * @param ns The Namespace of the attribute.  If null the then no namespace.
	 */
	public void setAttribute(DataElement dataElement, String attName, Namespace ns) {
	    _dataElement = dataElement;
	    _attName = attName;
	    _ns = (ns == null) ? Namespace.NO_NAMESPACE : ns;
	    
	    String text = "";
	    Element element = _dataElement.getElement();
	    if(element != null) {
		    Attribute att = element.getAttribute(attName, _ns);
		    if(att != null) {
		        text = att.getValue();
		    }
	    }

	    doNotify = false;
	    _textComponent.setText(text);	
        doNotify = true;
	}

	/**
	 * Set the element to be bound and edited.
	 * This method is used when we know that the Element exists.
	 * @param dataElement The bound JDOM Element
	 */
	public void setElement(DataElement dataElement) {
	    _dataElement = dataElement;
	    _attName = null;
	    
	    doNotify = false;
	    Element element = _dataElement.getElement();
	    String text = (element == null) ? "" : element.getText();
	    _textComponent.setText(text);	
        doNotify = true;
	}
	
	/**
	 * @return The SchemaAttribute for the Attribute the we may be bound to.
	 */
	private SchemaAttribute getSchemaAttribute(Attribute att) {
	    if(_schemaAttribute == null) {
            XMLPath xmlPath = XMLPath.getXMLPathForAttribute(att);
            _schemaAttribute = (SchemaAttribute)_dataElement.getDataModel()
            							.getSchemaDocument()
            							.getSchemaController()
            							.getSchemaNode(xmlPath);
	    }
	    return _schemaAttribute;
	}
	
    // ========================= Document Listener Events ====================================
	
    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
     */
    public void changedUpdate(DocumentEvent e) {
        notifyTextChange();
    }

    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
     */
    public void insertUpdate(DocumentEvent e) {
        notifyTextChange();
    }

    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
     */
    public void removeUpdate(DocumentEvent e) {
        notifyTextChange();
    }

    /**
     * The Text was changed
     */
    protected void notifyTextChange() {
        if(doNotify) {
            Element element = _dataElement.getElement();
            
            // Create element if need be
            if(element == null) {
                element = _dataElement.createElement();
            }
            
            if(element != null) {
                String text = _textComponent.getText();
                
    	        // An Attribute
    	        if(_attName != null) {
    	            Attribute att = element.getAttribute(_attName, _ns);
    	            
	                // If text is the empty String then remove the attribute if it is optional
    	            // according to the Schema.
    	            if("".equals(text) && att != null) {
  	                    String use = getSchemaAttribute(att).getUse();
  	                    if("optional".equals(use)) {
  	                        element.removeAttribute(att);
  	                    }
  	                    else {
  	                        element.setAttribute(_attName, "", _ns);
  	                    }
    	            }
    	            else {
    	                element.setAttribute(_attName, text, _ns);
    	            }
    	        }
    	        
    	        // An Element
    	        else {
    	            element.setText(text);
    	        }
    	        
    	        // Notify listeners
    	        _dataElement.getDataModel()
    	        		  .getSchemaDocument()
    	        		  .changedElement(this, element);
    	    }
        }
    }
}