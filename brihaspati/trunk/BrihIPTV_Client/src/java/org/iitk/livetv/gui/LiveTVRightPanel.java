package org.iitk.livetv.gui;

/**
 * LiveTVRightPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Vector;
import java.util.Date;


import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;

import org.iitk.livetv.util.UnPackNativeLibs;
import org.iitk.livetv.util.JavaGetUrl;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 05Sep2012
 */

public class LiveTVRightPanel extends JPanel implements ActionListener, MouseListener{

	private static LiveTVRightPanel rightPanel=null;
	private JPanel mainPanel=null;
	private JPanel northPanel=null;
	private JPanel playerPanel=null;
	private JPanel controlsPanel=null;
	private Canvas canvas;
  	private MediaPlayerFactory factory;
  	private EmbeddedMediaPlayer mediaPlayer;
  	private CanvasVideoSurface videoSurface;


	public static LiveTVRightPanel getController(){
		if(rightPanel==null){
			rightPanel=new LiveTVRightPanel();
		}
		return rightPanel;
	}

	public LiveTVRightPanel(){
	}

	public JPanel createGUI(){
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		northPanel=new JPanel();

		canvas = new Canvas();
    	canvas.setBackground(Color.black);
    	factory = new MediaPlayerFactory("--no-video-title-show", "--no-plugins-cache", "--no-snapshot-preview");
    	mediaPlayer = factory.newEmbeddedMediaPlayer();

    	String[] standardMediaOptions ={"video-filter=logo", "logo-file=vlcj-logo.png", "logo-opacity=25"};
    	mediaPlayer.setStandardMediaOptions(standardMediaOptions);

    	videoSurface = factory.newVideoSurface(canvas);

    	mediaPlayer.setVideoSurface(videoSurface);
		mainPanel.add(canvas, BorderLayout.CENTER);
		controlsPanel = new PlayerControlsPanel(mediaPlayer);
		mainPanel.add(controlsPanel,BorderLayout.SOUTH);

		return mainPanel;
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void mouseClicked(MouseEvent ev) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e){}
}
