<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.InputStream"%>
<%
	response.setContentType("text/html");
%>
<%
System.out.println("here");
	// Create a factory for disk-based file items
	FileItemFactory factory = new DiskFileItemFactory();
	// Create a new file upload handler
	ServletFileUpload upload = new ServletFileUpload(factory);
	// Parse the request
	/* FileItem */
	List items = upload.parseRequest(request);

	String recievedFileName = request.getParameter("fileName");

	// Process the uploaded items
	Iterator itr = items.iterator();

	while (itr.hasNext()) {
		FileItem item = (FileItem) itr.next();
		//handling a normal form-field
		if (!item.isFormField()) {//handling file loads
			String fileName = item.getName().toLowerCase();
			String filePath = this.getServletContext().getRealPath("/");
			String fileType = fileName.substring(fileName
					.lastIndexOf("."));

			if (fileType.equalsIgnoreCase(".xls")
					|| fileType.equalsIgnoreCase(".xlsx")) {
				filePath = filePath + request.getParameter("folder");
			} else if (fileType.equalsIgnoreCase(".jpg")
					|| fileType.equalsIgnoreCase(".jpeg")
					|| fileType.equalsIgnoreCase(".png")
					|| fileType.equalsIgnoreCase(".gif")) {
				filePath = filePath + "StudentPhotos/";
			} else if (fileType.equalsIgnoreCase(".zip")) {
				filePath = filePath + request.getParameter("folder");
			}

			File file = new File(filePath);
			file.mkdirs();
			filePath = filePath + recievedFileName;
			byte[] data = item.get();
			FileOutputStream fileOutSt = new FileOutputStream(filePath);
			fileOutSt.write(data);
			fileOutSt.close();
		}
	}
%>