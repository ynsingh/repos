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

import junit.framework.TestCase;




/**
 * DataComponent Tests
 * 
 * @author Phillip Beauvoir
 * @version $Id: DataComponentTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class DataComponentTest
extends TestCase {
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetParent() {
        DataComponent dc = new ConcreteDataComponent();
        DataComponent dcChild = new ConcreteDataComponent();
        dc.addChild(dcChild);
        assertEquals("Parent is wrong", dc, dcChild.getParent());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetPreviousSibling() {
        DataComponent dc = new ConcreteDataComponent();
        DataComponent dcChild1 = new ConcreteDataComponent();
        DataComponent dcChild2 = new ConcreteDataComponent();
        DataComponent dcChild3 = new ConcreteDataComponent();
        dc.addChild(dcChild1);
        dc.addChild(dcChild2);
        dc.addChild(dcChild3);
        assertEquals("Sibling is wrong", dcChild2, dcChild3.getPreviousSibling());
        assertEquals("Sibling is wrong", dcChild1, dcChild2.getPreviousSibling());
        assertNull("Sibling is wrong", dcChild1.getPreviousSibling());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetNextSibling() {
        DataComponent dc = new ConcreteDataComponent();
        DataComponent dcChild1 = new ConcreteDataComponent();
        DataComponent dcChild2 = new ConcreteDataComponent();
        DataComponent dcChild3 = new ConcreteDataComponent();
        dc.addChild(dcChild1);
        dc.addChild(dcChild2);
        dc.addChild(dcChild3);
        assertEquals("Sibling is wrong", dcChild2, dcChild1.getNextSibling());
        assertEquals("Sibling is wrong", dcChild3, dcChild2.getNextSibling());
        assertNull("Sibling is wrong", dcChild3.getNextSibling());
    }

   
    /**
     * Concrete sub-class for testing
     */
    class ConcreteDataComponent
    extends DataComponent {
        public ConcreteDataComponent() {
        }
    }
}
