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

package uk.ac.reload.editor.gui.widgets;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.types.DurationType;

/**
 * A Field Widget used to display a xsd Duration type and bound to a DataElement.
 * <p><p>
 * Specifies an amount of time: the duration of an event in relative terms (e.g. the duration given the start
 * datetime of the run of a unit-of-learning.
 * The format - also used in the W3C XML schema specification - is: PnYnMnDTnHnMnS where:<p><p>
 * P is the designator that must always be present.<p>
 * n is a variable where an integer is filled in.<p>
 * nY represents the number of years.<p>
 * nM represents the number of month.<p>
 * nD represents the number of days.<p>
 * T is the date/time separator which must always be present when representing time.<p>
 * nH is the number of hours.<p>
 * nM is the number of minutes.<p>
 * nS is the number of seconds.<p><p>
 * Example: P2Y0M1DT20H10M55S. Meaning that the duration is: 2 years and 0 month and 1 day and 20 hours
 * and 10 minutes and 55 seconds. Limited forms of lexical production are also allowed: For example, a duration of
 * 40 minutes is expressed as PT40M; a duration of 30 days is P30D.
 * <p><p>
 *
 * @author Phillip Beauvoir
 * @version $Id: DataElementDurationField.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class DataElementDurationField
extends JPanel
{
	
    /**
     * Text fields
     */
    private DurationNumberField _years, _months, _days, _hours, _mins, _secs;
    
	/**
     * Flag for text change notifications
     */
    private boolean doNotify = true;

    /**
	 * The bound, possibly uncreated yet, JDOM Element.
	 */
	private DataElement _dataElement;
	
	/**
	 * The Duration Type
	 */
	private DurationType _durationType;
	

	/**
	 * Default Constructor
	 */
	public DataElementDurationField() {
	    setup();
	}
	
	/**
	 * Setup the components
	 */
	protected void setup() {
	    setOpaque(false);
	    
	    SGLayout layout = new SGLayout(2, 6, 10, 0);
	    setLayout(layout);
	    
	    _years = new DurationNumberField("y");
	    _months = new DurationNumberField("m");
	    _days = new DurationNumberField("d");
	    _hours = new DurationNumberField("h");
	    _mins = new DurationNumberField("mm");
	    _secs = new DurationNumberField("s");

	    add(createLabel("Yrs"));
	    add(createLabel("Mons"));
	    add(createLabel("Days"));
	    add(createLabel("Hrs"));
	    add(createLabel("Mins"));
	    add(createLabel("Secs"));

	    add(_years);
	    add(_months);
	    add(_days);
	    add(_hours);
	    add(_mins);
	    add(_secs);
	}
	
	/**
	 * Create a Label
	 * @param text Text to display for a field
	 * @return The JLabel
	 */
	protected JLabel createLabel(String text) {
	    JLabel l = new JLabel();
	    l.setText(text);
	    l.setHorizontalAlignment(SwingConstants.CENTER);
	    return l; 
	}

	/**
	 * Clear all the fields and set the DataElement to null
	 */
	public void clear() {
	    _dataElement = null;
	    _durationType = null;
	    
	    doNotify = false;
        _years.setText("");
        _months.setText("");
        _days.setText("");
        _hours.setText("");
        _mins.setText("");
        _secs.setText("");
        doNotify = true;
        
        setEditable(false);
	}
	
	/**
     * Set the Text Fields' editable state
     * @param editable
     */
    public void setEditable(boolean editable) {
        _years.setEditable(editable);
        _months.setEditable(editable);
        _days.setEditable(editable);
        _hours.setEditable(editable);
        _mins.setEditable(editable);
        _secs.setEditable(editable);
    }

    /**
     * Set the bound DataElement
     * @param dataElement
     */
    public void setElement(DataElement dataElement) {
        _dataElement = dataElement;
        _durationType = new DurationType();
        
	    // Is there an element with text?
	    Element element = _dataElement.getElement();
	    if(element != null) {
	        _durationType.setDurationString(element.getText());
	    }
        
	    doNotify = false;
        _years.setText(Integer.toString(_durationType.getYears()));
        _months.setText(Integer.toString(_durationType.getMonths()));
        _days.setText(Integer.toString(_durationType.getDays()));
        _hours.setText(Integer.toString(_durationType.getHours()));
        _mins.setText(Integer.toString(_durationType.getMinutes()));
        _secs.setText(Integer.toString(_durationType.getSeconds()));
        doNotify = true;
	    
	    setEditable(true);
    }
    
    // =====================================================================================
    // ============================= Number field class ====================================
    // =====================================================================================
    
    /**
     * Text Number field
     */
    class DurationNumberField
    extends NumberFieldWidget
    implements DocumentListener {
        
        private String _field;
        
        /**
         * Default constructor
         * @param field The field type, can be y, m, d, h, mm, s
         */
        public DurationNumberField(String field) {
            super(4);
            _field = field;
            getDocument().addDocumentListener(this);
        }
        
    	/**
    	 * Set the UI - over-ride if needed
    	 */
    	protected void setUI() {
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
            if(doNotify && _dataElement != null) {
                // Create element if need be
                Element element = _dataElement.getElement();
                if(element == null) {
                    element = _dataElement.createElement();
                }
                
                if(element != null) {
                    int val;
                    
                    try {
                        val = Integer.parseInt(getText());
                    }
                    catch(NumberFormatException ex) {
                        val = 0;
                    }

                    if("y".equals(_field)) {
                        _durationType.setYears(val);
                    }
                    else if("m".equals(_field)) {
                        _durationType.setMonths(val);
                    }
                    else if("d".equals(_field)) {
                        _durationType.setDays(val);
                    }
                    else if("h".equals(_field)) {
                        _durationType.setHours(val);
                    }
                    else if("mm".equals(_field)) {
                        _durationType.setMinutes(val);
                    }
                    else if("s".equals(_field)) {
                        _durationType.setSeconds(val);
                    }
                    
                    String duration = _durationType.getDurationString();
                    if(duration == null) {
                        element.setText("P0Y");  // Some value is better than none
                    }
                    else {
                        element.setText(duration);
                    }
                    
                    // Notify listeners
        	        _dataElement.getDataModel()
        	        		  .getSchemaDocument()
        	        		  .changedElement(this, element);
                }
            }
        }
    } // end class

}

