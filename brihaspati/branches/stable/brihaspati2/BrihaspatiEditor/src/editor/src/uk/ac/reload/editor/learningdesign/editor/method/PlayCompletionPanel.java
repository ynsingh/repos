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

package uk.ac.reload.editor.learningdesign.editor.method;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.learningdesign.datamodel.method.Play;
import uk.ac.reload.editor.learningdesign.editor.shared.CompletionPanel;


/**
 * Sub-Panel for Play Completion Rules
 * 
 * @author Phillip Beauvoir
 * @version $Id: PlayCompletionPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
*/
public class PlayCompletionPanel
extends CompletionPanel
{
    static final String TITLE = "Play Completion";
    static final String DESCRIPTION = "Information relating to the completion of this Play.";
    
    /**
     * Texts for Radio buttons
     */
    private static String[] radioText = {
            "None",
            "Time Limit",
            "When Last Act Completed",
            "When a property is set"
    };

    /**
     * Constructor
     */
    protected PlayCompletionPanel(boolean isVisible) {
        super(isVisible);
        
        // Title
        getHiderLabelPanel().getTitleLabel().setText("<html>" + TITLE);
        
        // Description
        getHiderLabelPanel().getDescriptionLabel().setText("<html>" + DESCRIPTION);
    }
    
    /**
     * Set the component
     * @param dataComponent
     */
    public void setComponent(DataComponent dataComponent) {
        super.setComponent(dataComponent);
        
        Play play = (Play)dataComponent;
        
        // Feedback panel
        getCompletionFeedbackPanel().setItemModelType(play.getFeedbackDescriptionItemModelType());
        
        // DataElement wrappers
        DataElement whenActCompleted = play.getWhenLastActCompletedDataElement();
        DataElement timeLimit = play.getTimeLimitDataElement();
        DataElement propertyValue = play.getWhenPropertyValueSetDataElement();

        // Set Radio Buttons
        
	    // Time Limit
        if(timeLimit.getElement() != null) {
            selectRadioButton(1);
            getTimeLimitField().setElement(timeLimit);
        }
        // When Last Act Completed
	    else if(whenActCompleted.getElement() != null) {
	        selectRadioButton(2);
	        getTimeLimitField().clear();
        }
        // Property value
        else if(propertyValue.getElement() != null) {
            selectRadioButton(3);
            getTimeLimitField().clear();
        }
        // None
        else {
            selectRadioButton(0);
            getTimeLimitField().clear();
        }
    }

    /**
     * A Radio button was pressed
     * @param button the Button that was pressed
     */
    protected void radioButtonPressed(int button) {
        Play play = (Play)getComponent();
        
        // Firstly, delete the top "complete-play" element
        DataElement completePlay = play.getCompletePlayDataElement();
        completePlay.deleteElement();

        // Now get the DataElements afresh (they need to be renewed each time)
        
        switch(button) {
            // None
            case 0:
                getTimeLimitField().clear();
                break;
                
            // Time Limit
            case 1:
                DataElement timeLimit = play.getTimeLimitDataElement();
                getTimeLimitField().setElement(timeLimit);
                break;

            // When Last Act Completed
            case 2:
                DataElement whenActCompleted = play.getWhenLastActCompletedDataElement();
                whenActCompleted.createElement();
                getTimeLimitField().clear();
                break;
                
            // When Property Value Set
            case 3:
                DataElement propertyValue = play.getWhenPropertyValueSetDataElement();
                getTimeLimitField().clear();
                break;
        }
    }

    /**
     * Over-ride to setup
     */
    protected void setupView(boolean isHidden) {
        super.setupView(isHidden);
        
        // When Property Value Set not implemented yet
        getRadioButtons()[3].setEnabled(false);
        JButton button = new JButton("Define");
        button.setEnabled(false);

		// RadioButton sub-panel
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(new Color(250, 250, 254));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray),
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		getCenterPanel().add(buttonPanel, BorderLayout.CENTER);

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.BOTH;
		
		buttonPanel.add(getRadioButtons()[0], gc);
		
		gc.gridy = 1;
		buttonPanel.add(getRadioButtons()[1], gc);
		
		gc.gridx = 1;
		gc.weightx = 2;
		buttonPanel.add(getTimeLimitField(), gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		buttonPanel.add(getRadioButtons()[2], gc);
		
		gc.gridy = 3;
		gc.insets.top = 5;
		buttonPanel.add(getRadioButtons()[3], gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		buttonPanel.add(button, gc);
    }
    
    /**
     * @return The number of radio buttons
     */
    protected int getNumberRadioButtons() {
        return radioText.length;
    }
    
    /**
     * @param button The Radio button
     * @return The displayed text for a Radio Button
     */
    protected String getRadioButtonText(int button) {
        return radioText[button];
    }

}