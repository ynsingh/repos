
package uk.ac.reload.dweezil.menu;

import junit.framework.TestCase;


/**
 * MenuActionTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: MenuActionTest.java,v 1.1 1998/03/25 20:51:23 ynsingh Exp $
 */
public class MenuActionTest extends TestCase {

    
    protected void setUp() throws Exception {
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testMenuAction() {
        MenuAction menuAction = new MenuAction();
        assertNotNull("MenuAction was null", menuAction);
        assertEquals("Text should be empty", "", menuAction.getText());
        assertNull("Icon should be null", menuAction.getMenuIcon());
    }

    public void testMenuAction_String() {
        MenuAction menuAction = new MenuAction("Hello World");
        assertNotNull("MenuAction was null", menuAction);
        assertEquals("Text should be set", "Hello World", menuAction.getText());
        assertNull("Icon should be null", menuAction.getMenuIcon());
    }

    public void testMenuAction_String_String() {
        MenuAction menuAction = new MenuAction("Hello World", "uk/ac/reload/dweezil/resources/east.gif");
        assertNotNull("MenuAction was null", menuAction);
        assertEquals("Text should be set", "Hello World", menuAction.getText());
        assertNotNull("Icon should be set", menuAction.getMenuIcon());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetMnemonic() {
        MenuAction menuAction = new MenuAction("&Hello World");
        assertNotNull("MenuAction was null", menuAction);
        assertEquals("Mnemonic wrong", 'H', menuAction.getMnemonic());
        menuAction.setMnemonic('e');
        assertEquals("Mnemonic wrong", 'e', menuAction.getMnemonic());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetText() {
        MenuAction menuAction = new MenuAction("&Hello World");
        assertNotNull("MenuAction was null", menuAction);
        assertEquals("Mnemonic wrong", "Hello World", menuAction.getText());
        menuAction.setText("H&ello World");
        assertEquals("Mnemonic wrong", "Hello World", menuAction.getText());
        menuAction.setText("Ponk");
        assertEquals("Mnemonic wrong", "Ponk", menuAction.getText());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetButton() {
        MenuAction menuAction = new MenuAction("Hello World");
        assertNotNull("MenuAction was null", menuAction);
        assertNotNull("Button was null", menuAction.getButton());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetMnemonic_Static() {
        assertEquals("Mnemonic wrong", 'H', MenuAction.getMnemonic("&Hello"));
        assertEquals("Mnemonic wrong", 'o', MenuAction.getMnemonic("Hell&o"));
        assertEquals("Mnemonic wrong", 'e', MenuAction.getMnemonic("H&ell&o"));
        assertEquals("Mnemonic wrong", '&', MenuAction.getMnemonic("&&"));
        assertEquals("Mnemonic wrong", 0, MenuAction.getMnemonic("&"));
        assertEquals("Mnemonic wrong", 0, MenuAction.getMnemonic("Hello&"));
        assertEquals("Mnemonic wrong", 0, MenuAction.getMnemonic("Hello"));
        assertEquals("Mnemonic wrong", 0, MenuAction.getMnemonic(""));
        assertEquals("Mnemonic wrong", 0, MenuAction.getMnemonic(null));
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRemoveMnemonicText_Static() {
        assertEquals("Text wrong", "Hello", MenuAction.getRemoveMnemonicText("&Hello"));
        assertEquals("Text wrong", "Hello", MenuAction.getRemoveMnemonicText("Hell&o"));
        assertEquals("Text wrong", "Hell&o", MenuAction.getRemoveMnemonicText("H&ell&o"));
        assertEquals("Text wrong", "&", MenuAction.getRemoveMnemonicText("&&"));
        assertEquals("Text wrong", "", MenuAction.getRemoveMnemonicText("&"));
        assertEquals("Text wrong", "Hello", MenuAction.getRemoveMnemonicText("Hello&"));
        assertEquals("Text wrong", "Hello", MenuAction.getRemoveMnemonicText("Hello"));
        assertEquals("Text wrong", "", MenuAction.getRemoveMnemonicText(""));
        assertEquals("Text wrong", null, MenuAction.getRemoveMnemonicText(null));
    }

    // ---------------------------------------------------------------------------------------------
}
