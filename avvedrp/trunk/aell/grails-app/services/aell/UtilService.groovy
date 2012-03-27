/**
 * This service is for all the utilities
 * Sample invocation code for these methods are in UtilController.groovy
 */

package aell

import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest

class UtilService {

    boolean transactional = true

    def serviceMethod() {

    }
	
	/**
	 * Upload images to a temp directory
	 * @param request
	 * @return
	 */
	String uploadImage( HttpServletRequest  request, String webRootDir) {
		def resp
		try {
			def uimg = request.getFile('image')
			if(!uimg.empty){
				def userDir = new File(webRootDir, "/images/temp/")
				userDir.mkdir();
	 			uimg.transferTo( new File( userDir, uimg.originalFilename))
				resp = '/temp/' + uimg.originalFilename.toString()
			} else {
				resp = "empty file specified"
			}
		} catch (Exception e ) {
			resp = "Exception occured in uploading the file"
			e.printStackTrace()
		}
		return resp
	}

	/**
	 * This function moves images from temp storage to persistent storage
	 * @param tempUrl
	 * @return : the url of the moved image
	 */
	Boolean saveImageFromTempURL(String tURL, String webRootDir, String ctxPath){
		File destFile
		try {
			def imgUrl = new URL(tURL)
			def srcFile = new File (webRootDir + java.util.regex.Pattern.compile(ctxPath).matcher(imgUrl.getPath()).replaceFirst("/"))
			destFile = new File (webRootDir + "/images/" + srcFile.getName())
			srcFile.renameTo(destFile)
		} catch (Exception e ){
			e.printStackTrace()
		}
		return destFile.lastModified() == 0L ? false : true
	}	
	
	String saveImage(String tURI, String webRootDir, String ctxPath){
		def resp
		try {
//			def imgUrl = new URL(tURL)
			def imgRelativePath = "uploads/quiz/crop/"
                        def srcFile = new File(webRootDir + imgRelativePath + tURI)
//			new File (webRootDir + java.util.regex.Pattern.compile(ctxPath).matcher(imgUrl.getPath()).replaceFirst("/"))
			File destFile = new File (webRootDir + imgRelativePath + srcFile.getName())
			srcFile.renameTo(destFile)
			resp = (destFile.lastModified() == 0L ) ? "failure in saving image" : srcFile.getName()
		} catch (Exception e ){
			e.printStackTrace()
		}
		return resp
	}	
	/**
	 * reduces the size of all images in the images directory
	 * @return
	 */
	Boolean reduceImageSize (){
		
	}
	
	/**
	 * This function generates sequence numbers primarily for db inserts
	 * @param name: name of the sequence
	 * @return
	 */
	long getSequence(String name){
		def tempObj
		try{
			tempObj = AvlSequence.findByName(name)
			if (tempObj)
				tempObj.val += 1L
			else
				tempObj = new AvlSequence(name:name, val:1)
			tempObj.save()
		} catch(Exception e){
			e.printStackTrace()
		}
		return tempObj.val
	}
}
