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

package uk.ac.reload.editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.editor.menu.MainMenu;

/**
 * A Notes Panel for general Notes
 *
 * @author Phillip Beauvoir
 * @version $Id: NotesPanel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class NotesPanel extends JPanel 
implements DocumentListener
{
	
    /**
     * Handles Save and SaveAs Events
     */
    private ProxySaveHandler _saveHandler, _saveAsHandler;

    /**
	 * The scrollpane
	 */
    private JScrollPane _scrollPane;
	
	/**
	 * The main textPane
	 */
    private JTextPane _textPane;
	
	/**
	 * The main label
	 */
    private JLabel _label;
	
	/**
	 * The hider panel for the text pane
	 */
    private ComponentHiderLabelPanel _hiderPanel;
    
    /**
     * The backing File
     */
    private File _file;
    
	/**
     * Flag for text change notifications
     */
    private boolean _doNotify = true;
    
    /**
     * Dirty flag
     */
    private boolean _isDirty;


    /**
	 * Constructor
	 * @param labelText the text to display
	 * @param decriptionText Any descriptive text (can be null)
	 */
	public NotesPanel(String labelText, String decriptionText) {
		setLayout(new BorderLayout());
	    setOpaque(false);
		
		// Text Pane
	    _textPane = new JTextPane();
		_textPane.setFont(_textPane.getFont().deriveFont(12f));
		
		// Add Document Listener
		_textPane.getDocument().addDocumentListener(this);
		
		// Put in ScrollPane
		_scrollPane = new JScrollPane(_textPane);
		_scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// Set a default size to stop the text pane resizing itself
		_scrollPane.setPreferredSize(new Dimension(100, 100));

		// Sub-Panel needed for ComponentHider to work
		JPanel subPanel = new JPanel(new BorderLayout());
		subPanel.setOpaque(false);
		subPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		subPanel.add(_scrollPane);
		
		// ComponentHider Panel
		_hiderPanel = new ComponentHiderLabelPanel(labelText, decriptionText);
		_hiderPanel.getComponentHiderButton().setComponent(subPanel);

		// Hider sub-Panel
		add(_hiderPanel, BorderLayout.NORTH);
		
		// Text Pane Sub-Panel
		add(subPanel, BorderLayout.CENTER);

		// Proxy Save Handlers
        _saveHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSave);
        _saveAsHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSaveAs);
	}
	
	/**
	 * Show or hide the text pane
	 * @param show
	 */
	public void showTextPane(boolean show) {
	    _hiderPanel.getComponentHiderButton().showComponent(show);
	}
	
	/**
	 * Set the Font of the TextPane
	 * @param font
	 */
	public void setTextFont(Font font) {
	    _textPane.setFont(font);
	}
	
	/**
	 * Over-ride this to set the size of the TextPane
	 * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
	 */
	public void setPreferredSize(Dimension d) {
		_scrollPane.setPreferredSize(d);
	}
	
	/** 
	 * Clean up
	 */
	public void cleanup() {
	    // Don't use _saveHandler.clear() because the menu might be set enabled by another component
	    _saveHandler.removeListener();
	    _saveAsHandler.removeListener();
	}
	
	/**
	 * @return Returns the textPane.
	 */
	public JTextPane getTextPane() {
		return _textPane;
	}
	
	/**
	 * Load the backing File for the text
	 * @param file The backing file
	 * @throws IOException
	 */
	public void loadFile(File file) throws IOException {
	    _file = file;
	    
	    if(_file != null  && _file.exists()) {
	        Reader in = new FileReader(_file);
	        char[] buff = new char[4096];
	        Document doc = _textPane.getDocument();
	        int nch;
	        _doNotify = false;
	        while((nch = in.read(buff, 0, buff.length)) != -1) {
	            try {
                    doc.insertString(doc.getLength(), new String(buff, 0, nch), null);
                }
                catch(BadLocationException ex) {
                    ex.printStackTrace();
                }
	        }
		    _doNotify = true;

		    in.close();
		    
		    _saveHandler.addListener();
		    _saveAsHandler.addListener();
	    }
	}
	
	/**
	 * Save the Document to File
	 * @return True if everything OK
	 */
	public boolean saveDocument() {
	    if(_file != null && _isDirty) {
	        try {
	            // Ensure parent folder exists
	            _file.getParentFile().mkdirs();
	            // Write
                Writer out = new FileWriter(_file);
                out.write(_textPane.getText());
                out.flush();
                out.close();
            }
            catch(IOException ex) {
                ex.printStackTrace();
                return false;
            }
	    }
	    
	    _saveHandler.setEnabled(false);
	    _isDirty = false;
	    
	    return true;
	}
	
    //==============================================================================
    //======================PROXY MENU HANDLERS=====================================
    //==============================================================================


    /**
     * Deals with Save Menu Events from the Main "Save" and "Save As" Menu
     */
    class ProxySaveHandler extends ProxyAction {
        public ProxySaveHandler(MenuAction menuAction) {
            super(menuAction);
        }

        public void menuActionPerformed(DweezilMenuEvent event) {
            if(event.getSource() == MainMenu.getSharedInstance().actionSave) {
                saveDocument();
            }
            
            if(event.getSource() == MainMenu.getSharedInstance().actionSaveAs) {
                // Do something...
            }
        }
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
        if(_doNotify) {
            _isDirty = true;
            _saveHandler.setEnabled(true);
        }
    }
}
