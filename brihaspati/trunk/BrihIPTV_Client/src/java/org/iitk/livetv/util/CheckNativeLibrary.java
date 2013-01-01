package org.iitk.livetv.util;

/**
 * CheckNativeLibrary.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.io.File;
import java.io.FilenameFilter;
import com.sun.jna.Platform;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import org.iitk.livetv.http.HttpCommManager;
import org.iitk.livetv.util.UnPackNativeLibs;
import org.iitk.livetv.util.ClientObject;
import org.iitk.livetv.util.HttpsUtil;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 25Dec2012 
 */


public class CheckNativeLibrary{

	private static CheckNativeLibrary cnl=null;
	private ClientObject client_obj=ClientObject.getController();


	public static CheckNativeLibrary getController(){
		if(cnl==null){
			cnl=new CheckNativeLibrary();
		}
		return cnl;
	}

	
	public boolean getNativeLibrary(){
		if (Platform.isLinux()) {
			if(Platform.is64Bit()){
				File dir = new File("/usr/lib64/");
      				FilenameFilter filter = new FilenameFilter() {
         				public boolean accept(File dir, String name) {
            					return name.startsWith("libvlc");
        				}
      				};
      				String[] children = dir.list(filter);
				/*for (int i=0; i< children.length; i++) {
            				String filename = children[i];
            				System.out.println(children.length+"======"+filename);
         			}*/
      				if (children.length>0) {
					NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"/usr/lib64/" );
		                        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);	
					return true;
				}else{
					downloadNativeLibrary();
					return true;
				}		
			}else{
				File dir = new File("/usr/lib/");
                                FilenameFilter filter = new FilenameFilter() {
                                        public boolean accept(File dir, String name) {
                                                return name.startsWith("libvlc");
                                        }
                                };
                                String[] children = dir.list(filter);
                                if (children.length>0) {
                                        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"/usr/lib/" );
                                        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
                                        return true;
                                }else{
                                        downloadNativeLibrary();
                                        return true;

				}
			}
		}else if(Platform.isWindows()) {
			if((new File("C:/Program Files/VideoLAN/VLC/").exists())){
				NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC/");

    				Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
				return true;
			}else{
				downloadNativeLibrary();
                              	return true;
			}
		}
		return false;
	}
	
	public void downloadNativeLibrary(){

                 try {
                        String pathLibs = System.getProperty("user.dir") + System.getProperty("file.separator") + "vlc_lib" + System.getProperty("file.separator");

                        if (Platform.isLinux()) {
                                if((!(new File(pathLibs+"libvlc-Linux.jar").exists()))||(!(new File(pathLibs+"pluginsvlc-Linux.jar").exists()))){
                                        (new File(pathLibs)).mkdir();
                                        HttpsUtil.getController().downloadFile(client_obj.getIndexServerName()+"/plugin/libvlc-Linux/64/"+"libvlc-Linux.jar", pathLibs + "libvlc-Linux.jar");
                                        HttpsUtil.getController().downloadFile(client_obj.getIndexServerName()+"/plugin/libvlc-Linux/64/"+"pluginsvlc-Linux.jar", pathLibs + "pluginsvlc-Linux.jar");
                                        UnPackNativeLibs.getController().unpackJarFile(pathLibs+"libvlc-Linux.jar",pathLibs);
                                        UnPackNativeLibs.getController().unpackJarFile(pathLibs+"pluginsvlc-Linux.jar", pathLibs);
                                }
				System.setProperty("VLC_PLUGIN_PATH", pathLibs);
                        	System.setProperty("LD_LIBRARY_PATH",pathLibs);

                        	try {
                                	System.load(pathLibs+"libvlccore.so.4");
                               	 	System.load(pathLibs+"libvlccore.so.4.0.3");
                                	System.load(pathLibs+"libvlc.so.5");
                                	System.load(pathLibs+"libvlc.so.5.2.1");
                        	} catch (UnsatisfiedLinkError e) {
                                	System.err.println("Native code library failed to load.\n" + e);
                        	}

                        }

                        if(Platform.isWindows()){
                                 if((!(new File(pathLibs+"libvlc-Win.jar").exists()))||(!(new File(pathLibs+"pluginsvlc-Win.jar").exists()))){
                                        (new File(pathLibs)).mkdir();
                                        HttpsUtil.getController().downloadFile(client_obj.getIndexServerName()+"/plugin/libvlc-Win/"+"libvlc-Win.jar", pathLibs + "libvlc-Linux.jar");
                                        HttpsUtil.getController().downloadFile(client_obj.getIndexServerName()+"/plugin/libvlc-Win/"+"pluginsvlc-Win.jar", pathLibs + "pluginsvlc-Linux.jar");
                                        UnPackNativeLibs.getController().unpackJarFile(pathLibs+"libvlc-Linux.jar",pathLibs);
                                        UnPackNativeLibs.getController().unpackJarFile(pathLibs+"pluginsvlc-Linux.jar", pathLibs);
                                }
                        }
                        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), pathLibs);
                        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

                } catch (Exception e) {System.out.println("Error in loading native library : "+e);}
        }
}
