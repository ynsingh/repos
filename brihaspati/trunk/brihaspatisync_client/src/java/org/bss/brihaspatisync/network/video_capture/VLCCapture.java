package org.bss.brihaspatisync.network.video_capture;

/**
 * VLCCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 **/

import java.awt.image.BufferedImage;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;


/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class VLCCapture {
	private static String CAP_DEVICE = null;
	private static final String[] VLC_ARGS = {
	    "--intf", "dummy",              // no interface
	    "--vout", "dummy",              // no video output
	    "--no-audio",                   // no audio decoding
	    "--no-video-title-show",        // do not display title
	    "--no-stats",                   // no stats
	    "--no-sub-autodetect-file",     // no subtitles
	    "--no-snapshot-preview",        // no snapshot previews
	    "--live-caching=50",            // reduce capture lag/latency
	    "--quiet",                      // turn off warnings and info messages
	};

	private MediaPlayerFactory factory = null;
	private MediaPlayer mPlayer = null;
	private String cameraName="USB2.0 Camera";
	private int imWidth;
	
	protected VLCCapture() { }

	protected VLCCapture(int width, int height) {
		factory = new MediaPlayerFactory(VLC_ARGS);
		mPlayer = factory.newHeadlessMediaPlayer();
		imWidth = width;
		CAP_DEVICE="v4l2:///dev/video0";//cap_device;
		String[] options = {
		      ":dshow-vdev=" + cameraName,
		      ":dshow-size=" + width+"x"+height,
		      ":dshow-adev=none",// no audio device required
		};
		mPlayer.startMedia(CAP_DEVICE, options);	
		
	} 

	public BufferedImage grab() {
		BufferedImage im = mPlayer.getSnapshot(imWidth, 0);   
		if ((im == null) || (im.getWidth() == 0)) {
      			System.out.println("No snap available");
      			return null;
    		}
    		return im;
  	}  
	
	public void close() {
		System.out.println("Closing grabber for \"" + cameraName + "\" ...");
    		if(mPlayer != null) {
      			mPlayer.release();
      			mPlayer = null;
    		}
    
   		if(factory != null) {
      			factory.release();
      			factory = null;
    		}
  	}  // end of close()
} // end of VLCCapture class
