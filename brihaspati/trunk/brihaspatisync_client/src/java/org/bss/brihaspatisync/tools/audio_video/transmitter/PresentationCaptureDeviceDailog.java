package org.bss.brihaspatisync.tools.audio_video.transmitter;

/**
 *  PresentationCaptureDeviceDailog.java
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.DataSource;
import org.bss.brihaspatisync.util.Language;

public class PresentationCaptureDeviceDailog extends Dialog implements ActionListener, ItemListener {

  	boolean configurationChanged = false;
  	Vector devices;
  	Vector audioDevices;
  	Vector audioFormats;
  	Choice audioDeviceCombo;
  	Choice audioFormatCombo;

  	protected PresentationCaptureDeviceDailog(Frame parent, String title, boolean mode) {
    		super(parent, title, mode);
    		init();
  	}

  	private void init() {
    		setSize(450, 130);
    		Panel p = new Panel();
    		p.setLayout(null);

		Label l1 = new Label(Language.getController().getLangValue("CaptureDeviceDialog.AudioDevice"));
    		Label l3 = new Label(Language.getController().getLangValue("CaptureDeviceDialog.AudioFormat"));
    		audioDeviceCombo = new Choice();
    		audioFormatCombo = new Choice();

		Button OKbutton = new Button(Language.getController().getLangValue("CaptureDeviceDialog.OKBttn"));
        	OKbutton.setActionCommand("OK");
        	Button cancelButton = new Button(Language.getController().getLangValue("CaptureDeviceDialog.CancelBttn"));
        	cancelButton.setActionCommand("Cancel");
    		
		
		p.add(l1);
    		l1.setBounds(5, 5, 100, 20);
    		p.add(audioDeviceCombo);
    		audioDeviceCombo.setBounds(115, 5, 300, 20);
    		p.add(l3);
		l3.setBounds(5, 30, 100,20);
    		p.add(audioFormatCombo);
    		audioFormatCombo.setBounds(115, 30, 300,20);
    		p.add(OKbutton);
    		OKbutton.setBounds(280, 60, 60, 25);
    		p.add(cancelButton);
    		cancelButton.setBounds(355, 60, 60, 25);
			
		add(p, "Center");
    		audioDeviceCombo.addItemListener(this);
    		OKbutton.addActionListener(this);
    		cancelButton.addActionListener(this);
		
		devices = CaptureDeviceManager.getDeviceList ( null );
    		CaptureDeviceInfo cdi;
    		if(devices!=null && devices.size()>0) {
      			int deviceCount = devices.size();
      			audioDevices = new Vector();
			Format[] formats;
      			for ( int i = 0;  i < deviceCount;  i++ ) {
        			cdi = (CaptureDeviceInfo) devices.elementAt ( i );
        			formats = cdi.getFormats();
        			for( int j=0;  j<formats.length; j++ ) {
          				if ( formats[j] instanceof AudioFormat ) {
            					audioDevices.addElement(cdi);
            					break;
          				}
        			}
      			}
			for (int i=0; i<audioDevices.size(); i++) {
        			cdi  = (CaptureDeviceInfo) audioDevices.elementAt(i);
        			audioDeviceCombo.addItem(cdi.getName());
      			}
      			displayAudioFormats();
		} else { }
  	}

  	void displayAudioFormats() {
    		CaptureDeviceInfo cdi;
    		audioFormatCombo.removeAll();
		
		int i = audioDeviceCombo.getSelectedIndex();
		if (i!=-1) {
      			cdi = (CaptureDeviceInfo) audioDevices.elementAt(i);
      			if (cdi!=null) {
        			Format[] formats = cdi.getFormats();
        			audioFormats = new Vector();
        			for (int j=0; j<formats.length; j++) {
          				audioFormatCombo.add(formats[j].toString());
          				audioFormats.addElement(formats[j]);
        			}
      			}
    		}
  	}

  	public CaptureDeviceInfo getAudioDevice() {
    		CaptureDeviceInfo cdi = null;
    		if (audioDeviceCombo!=null) {
      			int i = audioDeviceCombo.getSelectedIndex();
      			cdi = (CaptureDeviceInfo) audioDevices.elementAt(i);
    		}
    		return cdi;
  	}

	public Format getAudioFormat() {
    		Format format = null;
    		if(audioFormatCombo!=null) {
      			int i = audioFormatCombo.getSelectedIndex();
      			format = (Format) audioFormats.elementAt(i);
    		}
    		return format;
  	}

  	public boolean hasConfigurationChanged() {
    		return configurationChanged;
  	}

  	public void actionPerformed(ActionEvent ae) {
    		String command = ae.getActionCommand().toString();
    		if (command.equals("OK")) {
      			configurationChanged = true;
    		}
    		dispose();
  	}

  	public void itemStateChanged(ItemEvent ie) {
    		System.out.println(ie.getSource().toString());
    		if (ie.getSource().equals(audioDeviceCombo))
      			displayAudioFormats();
  	}
}

