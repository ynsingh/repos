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

package uk.ac.reload.editor.learningdesign.editor.method;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;
import uk.ac.reload.editor.learningdesign.datamodel.method.Play;

/**
 * Dialog window to select Plays
 *
 * @author Phillip Beauvoir
 * @version $Id: MethodPlaySelectorDialog.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MethodPlaySelectorDialog
extends JDialog
implements IIcons
{
    
    /**
     * Method
     */
    private Method _method;
    
    /**
     * Selector tree
     */
    private MethodPlaySelectorTree _selectorTree;
	
	/**
	 * Dialog Width
	 */
    private int WIDTH = 480;
	
	/**
	 * Dialog Height
	 */
    private int HEIGHT = 355;
    
	
	/**
	 * Constructor
	 */
	public MethodPlaySelectorDialog() {
		super(EditorFrame.getInstance(), "Select Plays", true);
		
		setResizable(true);
		
		// Trap window closing event for our Window listener
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Add a listener to Close our window down on exit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		// Tree
		_selectorTree = new MethodPlaySelectorTree();
		JScrollPane sp = new JScrollPane(_selectorTree);
		getContentPane().add(sp, BorderLayout.CENTER);
		
		// Button Panel
		JPanel buttonPanel = createButtonPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		// Dialog Size
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(EditorFrame.getInstance());
	}
	
    /**
     * @return The Selector tree
     */
    public MethodPlaySelectorTree getSelectorTree() {
        return _selectorTree;
    }
    
    /**
     * Set the Method Component 
     * @param method Method Component 
     */
	public void setMethod(Method method) {
	    _method = method;
	    
	    _selectorTree.setMethod(method);
		_selectorTree.expandTree(true);
	}
	
	/**
	 * @return the Button Panel
	 */
	protected JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		// OK Button
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new OKClick());
		getRootPane().setDefaultButton(btnOK);
		buttonPanel.add(btnOK);
		
		// Cancel Button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelClick());
		buttonPanel.add(btnCancel);

		return buttonPanel;
	}
	
	/**
	 * User pressed OK
	 */
	protected void finish() {
	    LD_Component[] components = _selectorTree.getSelectedComponents();
	    for(int i = 0; i < components.length; i++) {
	        _method.addPlayCompleted((Play)components[i]);
        }
	    
	    dispose();
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
	    _selectorTree.cleanup();
	    _method = null;
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
	 * Cancel handler
	 */
	private class CancelClick extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	}
}