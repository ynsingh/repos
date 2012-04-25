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

package uk.ac.reload.editor.datamodel;

import java.util.Vector;

import uk.ac.reload.moonunit.SchemaDocument;



/**
 * 2nd level DataModel
 *
 * @author Phillip Beauvoir
 * @version $Id: DataModel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public abstract class DataModel {
    
    /**
     * The XML SchemaDocument
     */
    private SchemaDocument _schemaDocument;
    
    
    /**
     * Constructor
     * @param schemaDocument The XML SchemaDocument
     */
    public DataModel(SchemaDocument schemaDocument) {
        _schemaDocument = schemaDocument;
    }
    
    /**
     * @return The The XML SchemaDocument
     */
    public SchemaDocument getSchemaDocument() {
        return _schemaDocument;
    }
    
    /**
     * Destroy this object
     */
    public void destroy() {
        _schemaDocument.destroy();
        _schemaDocument = null;
        
        _listeners.clear();
        _listeners = null;
    }
    
    //========================== FIRE LISTENER EVENTS  ==========================
    
    /**
     * Create one now, we will always need one
     */
    private Vector _listeners = new Vector();
    
    
    /**
     * Add a IDataModelListener
     * @param listener The IDataModelListener
     */
    public synchronized void addIDataModelListener(IDataModelListener listener) {
        if(!_listeners.contains(listener)) {
            _listeners.addElement(listener);
        }
    }
    
    /**
     * Remove a IDataModelListener
     * @param listener The IDataModelListener
     */
    public synchronized void removeIDataModelListener(IDataModelListener listener) {
        _listeners.removeElement(listener);
    }
    
    /**
     * Tell our listeners that we have added a new Component
     */
    public void fireDataComponentAdded(DataComponent component) {
        for(int i = _listeners.size() - 1; i >= 0; i--) {
            IDataModelListener listener = (IDataModelListener)_listeners.elementAt(i);
            listener.componentAdded(component);
        }
    }
    
    /**
     * Tell our listeners that we have deleted a Component
     */
    public void fireDataComponentRemoved(DataComponent component) {
        for(int i = _listeners.size() - 1; i >= 0; i--) {
            IDataModelListener listener = (IDataModelListener)_listeners.elementAt(i);
            listener.componentRemoved(component);
        }
    }
    
    /**
     * Tell our listeners that we have moved a Component
     */
    public void fireDataComponentMoved(DataComponent component) {
        for(int i = _listeners.size() - 1; i >= 0; i--) {
            IDataModelListener listener = (IDataModelListener)_listeners.elementAt(i);
            listener.componentMoved(component);
        }
    }

    /**
     * Tell our listeners that we have changed a Component
     */
    public void fireDataComponentChanged(DataComponent component) {
        for(int i = _listeners.size() - 1; i >= 0; i--) {
            IDataModelListener listener = (IDataModelListener)_listeners.elementAt(i);
            listener.componentChanged(component);
        }
    }
}
