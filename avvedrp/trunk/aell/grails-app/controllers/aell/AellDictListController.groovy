
package aell
import grails.converters.JSON;
class AellDictListController {

    def index = { }
    //find the word meaning from databse
    def dictSearch={
        	def meaning
        	def dictListInstance
        	def dictImageInstance
        	def dictVoiceInstance
        	def image
        	def voice
        	def dictTypeMasterInstance
        	def array=[]
        	if(params.t){
        		 dictListInstance=AellDictList.findAll("from AellDictList DL where DL.word='"+params.t+"'")
        		if(dictListInstance)
        		{
        			for(i in dictListInstance.aellDictTypeMaster.id){
        		    	dictTypeMasterInstance=AellDictTypeMaster.find("from AellDictTypeMaster where id='"+i+"'")
        		    	array.add(dictTypeMasterInstance.type)
        		    	}
        		meaning=dictListInstance.definition;
        		dictImageInstance=AellDictImage.find("from AellDictImage where aellDictList='"+dictListInstance.id[0]+"'")
            	if(dictImageInstance){
            		image="click to view image";
            		
            		}
            		else
            		{
            			image=""
            		}
        		dictVoiceInstance=AellDictVoice.find("from AellDictVoice where aellDictList='"+dictListInstance.id[0]+"'")
            	if(dictVoiceInstance){
            		voice="click to view voice";
            		}
            		else
            		{
            			voice=""
            		}
        		}
        		else
        		{  
        			meaning="No definition found.";
        			image=""
        			voice=""
        			array=""
        					
        		}
        	}  else
        	{
        		meaning="select any text";
        		image=""
        			voice=""
        				array=""
        	}	
        	//return the word meaning from database
        	def result = ["textMeaning" : meaning,"image":image,"voice":voice,"type":array]
        	render result as JSON
        }
    //searching and returing  image from the database
        def dictImageSearch={
        	def dictListInstance=AellDictList.find("from AellDictList where word='"+params.t+"' ")
    		def dictImageInstance=AellDictImage.find("from AellDictImage where aellDictList='"+dictListInstance.id+"'")
    		if(dictImageInstance){
    		def result = ["ImageLink" : dictImageInstance.imageLink]
        	render result as JSON
    		}
        }
    //searching and returing voice from the database
        def dictVoiceSearch={
        	def dictListInstance=AellDictList.find("from AellDictList where word='"+params.t+"' ")
    		def dictVoiceInstance=AellDictVoice.find("from AellDictVoice where aellDictList='"+dictListInstance.id+"'")
    		if(dictVoiceInstance){
    		def result = ["VoiceLink" : dictVoiceInstance.voiceLink]
        	render result as JSON
    		}

        }
}
