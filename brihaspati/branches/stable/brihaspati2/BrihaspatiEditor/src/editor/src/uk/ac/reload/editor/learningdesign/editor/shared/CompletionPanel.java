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

package uk.ac.reload.editor.learningdesign.editor.shared;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.widgets.DataElementDurationField;


/**
 * Sub-Panel for Completion Rules
 *
 * @author Phillip Beauvoir
 * @version $Id: CompletionPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class CompletionPanel
extends JPanel
{
    /**
     * Currently selected Radio Button - stops re-clicking
     */
    private int _currentButton;

    /**
     * The backing Component
     */
    private DataComponent _dataComponent;

    /**
	 * The hider for the panel
	 */
    private ComponentHiderLabelPanel _hiderPanel;
    
    /**
     * The Panel containing radio buttons and the completion feedback panel
     */
    private JPanel _centerPanel;
    
    /**
     * Radio buttons
     */
    private JRadioButton[] _radioButtons;
    
	/**
	 * Time Limit Field
	 */
    private DataElementDurationField _dfTimeLimit;

	/**
     * Completion Feedback Panel
     */
    private ItemModelTypePanel _completionFeedbackPanel;
    

    /**
     * Constructor
     * @param isHidden Whether the panel is initially hidden or not
     */
    protected CompletionPanel(boolean isHidden) {
        setupView(isHidden);
    }
    
    /**
     * Set the component
     * @param dataComponent The DataComponent
     */
    public void setComponent(DataComponent dataComponent) {
        _dataComponent = dataComponent;
    }

    /**
     * Get the data component
     * @return The DataComponent
     */
    public DataComponent getComponent() {
        return _dataComponent;
    }

    /**
	 * Set up the view
	 * @param isHidden Whether the panel is initially hidden or not
	 */
	protected void setupView(boolean isHidden) {
		setOpaque(false);
        setLayout(new BorderLayout(10, 10));
		
		// Center Panel
		add(getCenterPanel(), BorderLayout.CENTER);

		// ComponentHider Panel to show/hide the sub-panel
		_hiderPanel = new ComponentHiderLabelPanel("", null);
		_hiderPanel.getComponentHiderButton().setComponent(getCenterPanel());
		_hiderPanel.getComponentHiderButton().showComponent(isHidden);
		add(_hiderPanel, BorderLayout.NORTH);
		
		// Completion Feedback Panel
		getCenterPanel().add(getCompletionFeedbackPanel(), BorderLayout.SOUTH);
	}
	
    /**
     * @return The LD_ComponentHiderLabelPanel
     */
    protected ComponentHiderLabelPanel getHiderLabelPanel() {
        return _hiderPanel;
    }
    
    /**
     * @return The Duaration Time Limit Text Field
     */
    protected DataElementDurationField getTimeLimitField() {
        if(_dfTimeLimit == null) {
    		_dfTimeLimit = new DataElementDurationField();
    		_dfTimeLimit.setEditable(false);
        }
        
        return _dfTimeLimit;
    }
    
    /**
     * @return The Radio buttons
     */
    protected JRadioButton[] getRadioButtons() {
        if(_radioButtons == null) {
    		ButtonGroup bg = new ButtonGroup();
    		_radioButtons = new JRadioButton[getNumberRadioButtons()];
    		for(int i = 0; i < getNumberRadioButtons(); i++) {
    		    _radioButtons[i] = createRadioButton(i);
    		    bg.add(_radioButtons[i]);
            }
        }
        
        return _radioButtons;
    }

    /**
     * Create a RadioButton
     * @param num The number of the button.
     * @return The RadioButton
     */
    protected JRadioButton createRadioButton(final int num) {
        JRadioButton rb = new JRadioButton();
        rb.setOpaque(false);
        rb.setText(getRadioButtonText(num));
        rb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(_currentButton != num) {
                    radioButtonPressed(num);
                }
                _currentButton = num;
            }
        });
        
        return rb;
    }

    /**
     * @return The Completion Feedback ItemModelType Panel
     */
    protected ItemModelTypePanel getCompletionFeedbackPanel() {
        if(_completionFeedbackPanel == null) {
    		_completionFeedbackPanel = new ItemModelTypePanel(false);
    		_completionFeedbackPanel.setBorder(new EmptyBorder(10, 10, 0, 0));
        }
        
        return _completionFeedbackPanel;
    }
    
    /**
     * @return The Center panel that contains the Radio buttons and sub widgets
     */
    protected JPanel getCenterPanel() {
        if(_centerPanel == null) {
    		_centerPanel = new JPanel(new BorderLayout());
    		_centerPanel.setOpaque(false);
        }
        
        return _centerPanel;
    }
    
    /**
     * Clean up
     */
    public void cleanup() {
        if(_completionFeedbackPanel != null) {
            _completionFeedbackPanel.cleanup();
        }
        
        _dataComponent = null;
    }
    
    /**
     * Select one of the Radio buttons.  We do it this way so we can keep a track of the
     * currenty selected button.
     * @param button The Button to be selected
     */
    protected void selectRadioButton(int button) {
        _radioButtons[button].setSelected(true);
        _currentButton = button;
    }
    
    /**
     * Get the numnber of Radio Buttons
     * @return The number of radio buttons
     */
    protected abstract int getNumberRadioButtons();
    
    /**
     * Get the text for a Radio Button
     * @param button The Radio button
     * @return The displayed text for a Radio Button
     */
    protected abstract String getRadioButtonText(int button);
    
    
    /**
     * A Radio button was pressed
     * @param button The Button that was pressed
     */
    protected abstract void radioButtonPressed(int button);
}