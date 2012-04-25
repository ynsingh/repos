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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.SchemaDocument;

/**
 * Source Viewer that displays the XML source of a Reload/JDOM Document.<br>
 * 
 * @author Phillip Beauvoir
 * @version $Id: DocumentSourceViewerPanel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class DocumentSourceViewerPanel
extends JPanel
implements XMLDocumentListener
{
	
	/**
	 * SchemaDocument
	 */
    private SchemaDocument _doc;
	
	/**
	 * JTextArea where source is displayed
	 */
    private JTextArea _textArea;
	
	/**
	 * Default Constructor
	 */
	public DocumentSourceViewerPanel(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		_textArea = new JTextArea();
		_textArea.setEditable(false);
		_textArea.setLineWrap(true);
		_textArea.setWrapStyleWord(true);
		_textArea.setCursor(DweezilUIManager.TEXT_CURSOR);
		_textArea.setFont(DweezilUIManager.plainFont12);
		
		add(new JScrollPane(_textArea), BorderLayout.CENTER);
	}
	
	/**
	 * Set the document
	 * @param doc
	 */
	public void setDocument(SchemaDocument doc){
		_doc = doc;
		_doc.addXMLDocumentListener(this);
		refreshView();
	}
	
    /**
     * Clean up
     */
    public void destroy() {
    	_doc.removeXMLDocumentListener(this);
    }

    /**
	 * Converts JDom document to string for outputting of XML
	 * @param document
	 * @return the String
	 */
	private String getSource(Document document) {
		XMLOutputter outputter = new XMLOutputter("   ", true);
		// This gets rid of junk characters
		outputter.setTextNormalize(true);
		return outputter.outputString(document);
	}
	
	/**
	 * Refresh the Text Area
	 */
	public synchronized void refreshView() {
		// Put this on a thread and it ensures that the JTextArea doesn't
		// always scroll to the bottom.
		// Plus it all happens smoothly.  This has to be synchronized, though.
		Thread thread = new Thread() {
			public void run() {
				Document doc = _doc.getDocument();
				String source = getSource(doc);
				_textArea.setText(source);
			}
		};
		thread.start();
	}

	public void elementAdded(XMLDocumentListenerEvent event) {
		refreshView();
	}
	
	public void elementRemoved(XMLDocumentListenerEvent event) {
		refreshView();
	}
	
	public void elementChanged(XMLDocumentListenerEvent event) {
		refreshView();
	}
	
	public void documentSaved(XMLDocument doc) {}
	
}
