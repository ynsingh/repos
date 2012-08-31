/*
 * @(#) PhoneField.java
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.gwtext.client.widgets.form.NumberField;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */
public class PhoneField extends FlexTable{
	messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    NumberField officePhone=new NumberField();
	NumberField homePhone=new NumberField();
	NumberField otherPhone=new NumberField();
	NumberField fax=new NumberField();
	
	public PhoneField(){
		officePhone.setValidateOnBlur(true);
		officePhone.setMaxLength(15);
		officePhone.setMinLength(10);
        
		homePhone.setAllowBlank(false);
		homePhone.setValidateOnBlur(true);
		homePhone.setMaxLength(15);
		homePhone.setMinLength(10);
        
		otherPhone.setValidateOnBlur(true);
		otherPhone.setMaxLength(15);
		otherPhone.setMinLength(10);
        
		fax.setValidateOnBlur(true);
		fax.setMaxLength(15);
		fax.setMinLength(10);

		this.setWidget(0, 0, new Label("Home Phone"));
		this.setWidget(0, 2,new Label("     "));
		this.setWidget(0, 3, new Label("Office Phone"));
		this.setWidget(1, 0, new Label("Other Phone"));
		this.setWidget(1, 3, new Label("Fax"));

		this.setWidget(0, 1, homePhone);
		
		this.setWidget(0, 4, officePhone);
		this.setWidget(1, 1, otherPhone);
		this.setWidget(1, 4, fax);
	}
}
