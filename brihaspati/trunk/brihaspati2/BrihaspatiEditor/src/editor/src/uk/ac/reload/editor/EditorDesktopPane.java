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

package uk.ac.reload.editor;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.AbstractList;

import javax.swing.JDesktopPane;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.dweezil.dnd.DNDUtils;

/**
 * The Editor Frame Desktop Panel.
 * This can handle Native Drag and Drop
 *
 * @author Phillip Beauvoir
 * @version $Id: EditorDesktopPane.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class EditorDesktopPane
extends JDesktopPane
implements DropTargetListener
{
	
	public EditorDesktopPane() {
		// Drag Target
		new DropTarget(this, this);
	}
	
	
	/**
	 * Attempt to open the files in the Editor
	 * @param files
	 */
	protected void openFiles(File[] files) {
		for(int i = 0; i < files.length; i++) {
			String ext = FileUtils.getFileExtension(files[i]);
			if(ext.equals("zip") || ext.equals("xml")) {
			    if(files[i].exists()) {
			        EditorHandler.getSharedInstance().openFile(files[i]);
			    }
			}
		}
	}
	
	/**
	 * We just Dropped some files
	 */
	public void drop(DropTargetDropEvent event) {
		// Get what we dropped
		Transferable transferable = event.getTransferable();
		
		// If we are the correct drag object
		if(transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
			// Accept the Drop
			event.acceptDrop(DNDUtils.getCorrectDropContext(event));
			
			// Get the User Object and do something
			try {
				Object userObject = transferable.getTransferData(DataFlavor.javaFileListFlavor);
				if(userObject instanceof AbstractList) {
					AbstractList list = (AbstractList)userObject;
					Object[] files = list.toArray();
					openFiles((File[])files);
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
			event.getDropTargetContext().dropComplete(true);
		}
		
		// Else we are not the right object
		else {
		    event.rejectDrop();
		}
	}
	
	/**
	 * We accept Native Drops
	 */
	public boolean isDropOK(DropTargetDragEvent event) {
		// Only support javaFileListFlavor
		DataFlavor[] flavors = event.getCurrentDataFlavors();
		for(int i = 0; i < flavors.length; i++) {
			if(flavors[i].equals(DataFlavor.javaFileListFlavor)) {
				return true;
			}
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	public void dragExit(DropTargetEvent event) {
	    
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragEnter(DropTargetDragEvent event) {
		if(isDropOK(event)) {
		    event.acceptDrag(DNDUtils.getCorrectDropContext(event));
		}
		else {
		    event.rejectDrag();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragOver(DropTargetDragEvent event) {
		if(isDropOK(event)) {
		    event.acceptDrag(DNDUtils.getCorrectDropContext(event));
		}
		else {
		    event.rejectDrag();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dropActionChanged(DropTargetDragEvent event) {
		if(isDropOK(event)) {
		    event.acceptDrag(DNDUtils.getCorrectDropContext(event));
		}
		else {
		    event.rejectDrag();
		}
	}
	
}