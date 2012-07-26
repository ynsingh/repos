package org.bss.brihaspatisync.reflector.network.ref_to_ref;


public class  ContainObject {

	private Post_GetAudio         post_get_audio      =null;
	private Post_Get_InsVideo     post_get_ins_video  =null;
	private Post_GetSharedScreen  post_get_sharescreen=null;
	private Post_Get_StudentVideo post_get_std_video  =null;
	private Post_Get_W_Chat       post_get_w_chat     =null;

	public ContainObject(String sessionid) throws Exception{
		//post_get_audio      =new Post_GetAudio(sessionid);
		//post_get_ins_video  =new Post_Get_InsVideo(sessionid);
		//post_get_sharescreen=new Post_GetSharedScreen(sessionid);
		//post_get_std_video  =new Post_Get_StudentVideo(sessionid);
		post_get_w_chat     =new Post_Get_W_Chat(sessionid);
		start();
	}	
	
	public void start() throws Exception {
		System.out.println("Start thread in all tools ");	
		//post_get_audio.start();	
		//post_get_ins_video.start();	
		//post_get_sharescreen.start();	
		//post_get_std_video.start();	
		post_get_w_chat.start();	
	}

	public void stop() throws Exception {
		System.out.println("Stop thread in all tools ");	
		//post_get_audio.stop();
                //post_get_ins_video.stop();
                //post_get_sharescreen.stop();
                //post_get_std_video.stop();
                post_get_w_chat.stop();
        }

}
