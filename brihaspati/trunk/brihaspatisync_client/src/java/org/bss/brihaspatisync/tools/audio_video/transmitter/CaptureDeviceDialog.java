package org.bss.brihaspatisync.tools.audio_video.transmitter;

/**
 * CaptureDeviceDialog.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.awt.Button;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.Dialog;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

import java.util.Vector;
import javax.media.Format;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;

import javax.media.format.VideoFormat;
import javax.media.format. AudioFormat;

import javax.media.protocol.DataSource;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>      
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class CaptureDeviceDialog extends Dialog implements ActionListener, ItemListener {

	boolean configurationChanged = false;
  	Vector devices;
  	Vector audioDevices;
  	Vector videoDevices;
  	Vector audioFormats;
  	Vector videoFormats;
  	Choice audioDeviceCombo;
  	Choice videoDeviceCombo;
  	Choice audioFormatCombo;
  	Choice videoFormatCombo;

	private Log log=Log.getController();


  	public CaptureDeviceDialog(Frame parent, String title, boolean mode) {
    	super(parent, title, mode);
    	init();
  	}

  	private void init() {
    	setSize(450, 180);
    	Panel p = new Panel();
    	p.setLayout(null);

    	Label l1 = new Label("Audio Device(s)");
    	Label l2 = new Label("Video Device(s)");
    	Label l3 = new Label("Audio Format(s)");
    	Label l4 = new Label("Video Format(s)");
    	audioDeviceCombo = new Choice();
    	videoDeviceCombo = new Choice();
    	audioFormatCombo = new Choice();
    	videoFormatCombo = new Choice();

    	Button OKbutton = new Button("OK");
    	Button cancelButton = new Button("Cancel");


    	p.add(l1);
    	l1.setBounds(5, 5, 100, 20);
    	p.add(audioDeviceCombo);
    	audioDeviceCombo.setBounds(115, 5, 300, 20);
    	p.add(l3);
    	l3.setBounds(5, 30, 100,20);
    	p.add(audioFormatCombo);
    	audioFormatCombo.setBounds(115, 30, 300,20);
    	p.add(l2);
    	l2.setBounds(5, 55, 100, 20);
    	p.add(videoDeviceCombo);
    	videoDeviceCombo.setBounds(115, 55, 300, 20);
    	p.add(l4);
    	l4.setBounds(5, 80, 100, 20);
    	p.add(videoFormatCombo);
    	videoFormatCombo.setBounds(115, 80, 300, 20);
    	p.add(OKbutton);
    	OKbutton.setBounds(280, 115, 60, 25);
    	p.add(cancelButton);
    	cancelButton.setBounds(355, 115, 60, 25);

    	add(p, "Center");
    	audioDeviceCombo.addItemListener(this);
    	videoDeviceCombo.addItemListener(this);
    	OKbutton.addActionListener(this);
    	cancelButton.addActionListener(this);

    	//get all the capture devices
    	devices = CaptureDeviceManager.getDeviceList ( null );
    	CaptureDeviceInfo cdi;
    	if (devices!=null && devices.size()>0) {
      		int deviceCount = devices.size();
      		audioDevices = new Vector();
      		videoDevices = new Vector();

      		Format[] formats;
      		for ( int i = 0;  i < deviceCount;  i++ ) {
        		cdi = (CaptureDeviceInfo) devices.elementAt ( i );
        		formats = cdi.getFormats();
        		for ( int j=0;  j<formats.length; j++ ) {
          			if ( formats[j] instanceof AudioFormat ) {
            			audioDevices.addElement(cdi);
            			break;
          			}
          			else if (formats[j] instanceof VideoFormat ) {
            			videoDevices.addElement(cdi);
            			break;
          			}
        		}
      		}

      		//populate the choices for audio
      		for (int i=0; i<audioDevices.size(); i++) {
        		cdi  = (CaptureDeviceInfo) audioDevices.elementAt(i);
        		audioDeviceCombo.addItem(cdi.getName());
      		}

      		//populate the choices for video
      		for (int i=0; i<videoDevices.size(); i++) {
        		cdi  = (CaptureDeviceInfo) videoDevices.elementAt(i);
        		videoDeviceCombo.addItem(cdi.getName());
      		}

      		displayAudioFormats();
      		displayVideoFormats();

    	} // end if devices!=null && devices.size>0
    	else {
      	//no devices found or something bad happened.
    	}
  	}

  	void displayAudioFormats() {
    	//get audio formats of the selected audio device and repopulate the audio format combo
    	CaptureDeviceInfo cdi;
    	audioFormatCombo.removeAll();

    	int i = audioDeviceCombo.getSelectedIndex();
    	//i = -1 --> no selected index

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

  	void displayVideoFormats() {
    	//get audio formats of the selected audio device and repopulate the audio format combo
    	CaptureDeviceInfo cdi;
    	videoFormatCombo.removeAll();

    	int i = videoDeviceCombo.getSelectedIndex();
    	//i = -1 --> no selected index

    	if (i!=-1) {
      		cdi = (CaptureDeviceInfo) videoDevices.elementAt(i);
      		if (cdi!=null) {
        		Format[] formats = cdi.getFormats();
        		videoFormats = new Vector();
        		for (int j=0; j<formats.length; j++) {
          			videoFormatCombo.add(formats[j].toString());
          			videoFormats.addElement(formats[j]);
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

  	public CaptureDeviceInfo getVideoDevice() {
    	CaptureDeviceInfo cdi = null;
    	if (videoDeviceCombo!=null) {
      		int i = videoDeviceCombo.getSelectedIndex();
      		cdi = (CaptureDeviceInfo) videoDevices.elementAt(i);
    	}
    	return cdi;
  	}

  	public Format getAudioFormat() {
    	Format format = null;
    	if (audioFormatCombo!=null) {
      		int i = audioFormatCombo.getSelectedIndex();
      		format = (Format) audioFormats.elementAt(i);
    	}
    	return format;
  	}

  	public Format getVideoFormat() {
    	Format format = null;
    	if (videoFormatCombo!=null) {
      		int i = videoFormatCombo.getSelectedIndex();
      		format = (Format) videoFormats.elementAt(i);
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
    	log.setLog(ie.getSource().toString());
    	if (ie.getSource().equals(audioDeviceCombo))
      		displayAudioFormats();
    	else
    	  	displayVideoFormats();
	
  		}
}//end fo class
