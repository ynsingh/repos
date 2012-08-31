/*
 * @(#) AddressField.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.client.Shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.data.Record;
/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class AddressField extends FlexTable {
	messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    public TextField line1 = new TextField();
    public TextField line2 = new TextField();
    public ComboBox city = new ComboBox();
    public ComboBox state = new ComboBox();
    public NumberField pincode = new NumberField();

	public AddressField() {
		this("");
	}
	
	public AddressField(String title) {
		final OA_ComboBoxes stateObj = new OA_ComboBoxes();
		line1.setMaxLength(255);
		line1.setAllowBlank(false);
		line1.setValidateOnBlur(true);
		
		line2.setMaxLength(255);
		
		stateObj.onModuleLoad();
		state=stateObj.statesCB;
		state.setAllowBlank(false);
		state.setEditable(false);
		state.setForceSelection(true);
		state.setValidateOnBlur(true);
		state.setWidth(188);
		state.addListener(new ComboBoxListenerAdapter() {			
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {					
				city.clearValue();
				stateObj.onStateChange(state.getRawValue());
								
			}
			
		});
		city=stateObj.cityCB;
		city.setAllowBlank(false);
		city.setValidateOnBlur(true);
		city.setEditable(false);
		city.setForceSelection(true);
		city.setWidth(188);
		//city.setMaxLength(100);
		
		pincode.setAllowBlank(false);
		pincode.setValidateOnBlur(true);
		pincode.setMinLength(6);
		pincode.setMaxLength(6);
		pincode.setDecimalPrecision(0);
		
		
		//this.setWidget(0,0, new Label(title));
		this.setWidget(2,0, new Label(cons.line1()));
		this.setWidget(3,0, new Label(cons.line2()));
		this.setWidget(2,2, new Label(cons.state()));
		this.setWidget(2,4, new Label(cons.city()));
		this.setWidget(2,6, new Label(cons.pincode()));
		
		this.setWidget(2,1, line1);
		this.setWidget(3,1, line2);
		this.setWidget(2,3, state);
		this.setWidget(2,5, city);
		this.setWidget(2,7, pincode);
	}
	
	public static DialogBox alertWidget(final String header, final String content) {
        final DialogBox box = new DialogBox();
        final VerticalPanel panel = new VerticalPanel();
        box.setText(header);
        panel.add(new Label(content));
        final Button buttonClose = new Button("Close",new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                box.hide();
            }
        });
        // few empty labels to make widget larger
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
        panel.add(buttonClose);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_RIGHT);
        box.add(panel);
        return box;
    }

}
