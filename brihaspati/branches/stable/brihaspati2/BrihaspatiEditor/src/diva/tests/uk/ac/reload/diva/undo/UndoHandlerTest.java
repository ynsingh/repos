
package uk.ac.reload.diva.undo;

import junit.framework.TestCase;


/**
 * UndoHandlerTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: UndoHandlerTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class UndoHandlerTest extends TestCase {

    /**
     * Flags for listener notifications
     */
    boolean _undoableActionHappened, _undoHappened, _redoHappened;
    
    class TestUndo implements UndoableAction {

        public void undo() {
            _undoHappened = true;
        }

        public void redo() {
            _redoHappened = true;
        }

        public String getName() {
            return "TestUndo";
        }
    }
    
    
    /**
     * Implementation of UndoableAction
     */
    TestUndo testUndo;

    // ---------------------------------------------------------------------------------------------

    protected void setUp() {
        testUndo = new TestUndo();
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testAddUndoableAction() {
        UndoListener undoListener = new UndoListener() {
            public void undoableActionHappened(UndoableAction a) {
                _undoableActionHappened = true;
                assertEquals("UndoableAction was wrong", testUndo, a);
            }
        };
        
        UndoHandler undohandler = new UndoHandler(undoListener);
        undohandler.addUndoableAction(testUndo);
        
        assertEquals("Next Undo action wrong", testUndo, undohandler.nextUndoAction());
        assertNull("Next Redo action should be null", undohandler.nextRedoAction());
        assertTrue("Should have been notified", _undoableActionHappened);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testUndoLastAction() {
        UndoHandler undohandler = new UndoHandler();
        undohandler.addUndoableAction(testUndo);
        undohandler.undoLastAction();
        
        assertNull("Next Undo action wrong", undohandler.nextUndoAction());
        assertEquals("Next Redo action should be set", testUndo, undohandler.nextRedoAction());
        assertTrue("Should have been notified", _undoHappened);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testRedoLastAction() {
        UndoHandler undohandler = new UndoHandler();
        undohandler.addUndoableAction(testUndo);
        undohandler.undoLastAction();
        
        assertNull("Next Undo action wrong", undohandler.nextUndoAction());
        assertEquals("Next Redo action should be set", testUndo, undohandler.nextRedoAction());
        assertTrue("Should have been notified", _undoHappened);
        
        undohandler.redoLastAction();

        assertEquals("Next Undo action wrong", testUndo, undohandler.nextUndoAction());
        assertNull("Next Redo action should be null", undohandler.nextRedoAction());
        assertTrue("Should have been notified", _redoHappened);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testClearAll() {
        UndoHandler undohandler = new UndoHandler();
        undohandler.addUndoableAction(testUndo);
        undohandler.clearAll();

        assertNull("Next Undo action should be null", undohandler.nextUndoAction());
        assertNull("Next Redo action should be null", undohandler.nextRedoAction());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testNextUndoAction() {
        UndoHandler undohandler = new UndoHandler();
        undohandler.addUndoableAction(testUndo);
        assertEquals("Next Undo action wrong", testUndo,undohandler.nextUndoAction());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testNextRedoAction() {
        UndoHandler undohandler = new UndoHandler();
        undohandler.addUndoableAction(testUndo);
        assertEquals("Next Undo action wrong", testUndo,undohandler.nextUndoAction());
        assertNull("Next Redo action should be null", undohandler.nextRedoAction());
        undohandler.undoLastAction();
        assertEquals("Next Redo action should be set", testUndo, undohandler.nextRedoAction());
    }

    // ---------------------------------------------------------------------------------------------
    
}
