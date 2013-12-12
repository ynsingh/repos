package org.bss.brihaspatisync.network.video_capture;

/**
 * WindowsCamera.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 **/

import java.awt.image.BufferedImage;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;


/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class WindowsCamera {
	private static String CAP_DEVICE = "dshow://";
	private static final String[] VLC_ARGS = {
	    "--intf", "dummy",              // no interface
	    "--vout", "dummy",              // no video output
	    "--no-audio",                   // no audio decoding
	    "--no-video-title-show",        // do not display title
	    "--no-stats",                   // no stats
	    "--no-sub-autodetect-file",     // no subtitles
	    "--no-snapshot-preview",        // no snapshot previews
	    "--live-caching=300",            // reduce capture lag/latency
	    "--quiet",                      // turn off warnings and info messages
	};

	private MediaPlayerFactory factory = null;
	private MediaPlayer mPlayer = null;
	private int imWidth;
	
	protected WindowsCamera() { }

	protected WindowsCamera(int width) {
		try {
			imWidth = width;	
			if(factory==null) {
				NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),org.bss.brihaspatisync.util.RuntimeDataObject.getController().getVLCServer());
	        	        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
				factory = new MediaPlayerFactory(VLC_ARGS);
				mPlayer = factory.newHeadlessMediaPlayer();
				String cameraName=org.bss.brihaspatisync.util.RuntimeDataObject.getController().getVLCdeviceName();
				String[] options = {
				      ":dshow-vdev="+cameraName,
				      ":dshow-adev=none",// no audio device required
				};
				mPlayer.setAdjustVideo(true);
				mPlayer.playMedia(CAP_DEVICE, options);	
			}
		} catch(Exception e){ System.out.println("Exception in indows media player "+e.getMessage()); }
	} 

	public BufferedImage grab() {
		try {
			BufferedImage im = mPlayer.getSnapshot(imWidth,400);   
			if (im == null) {
      				System.out.println("No snap available ");
	      			return null;
    			}
    			return im;
		} catch(Exception e){ System.out.println("Exception in Windows media player in grab method "+e.getMessage()); }
		return null;
  	}  
	
	public void close() {	
		try {
    			if(mPlayer != null) {
      				mPlayer.release();
	                        mPlayer.stop();
        	                mPlayer=null;
    			}
   			 
	   		if(factory != null) {
      				factory.release();
      				factory = null;
    			}
		} catch(Exception e){ System.out.println("Exception in Windows media player in close method "+e.getMessage()); }
  	}  // end of close()
} // end of VLCCapture class
