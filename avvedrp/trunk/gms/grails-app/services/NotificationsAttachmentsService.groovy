class NotificationsAttachmentsService 
{
	public List getNotificationsAttachmentsByNotification(def params)
	{
		def notificationsAttachmentsList
		println "++++++++++++++params.id+++++++++" + params.id
		println "++++++++++++++params.documentType+++++++++" + params.documentType
		if(params.documentType=='Notification')
		{
/*	19-11-2010
 * 	notificationsAttachmentsList=NotificationsAttachments
				.findAll("from NotificationsAttachments NA where NA.notification="
						+params.id + " and NA.attachmentType.documentType='"+params.documentType+"'");
		*/
		notificationsAttachmentsList=NotificationsAttachments
		.findAll("from NotificationsAttachments NA where NA.notification="
				+params.id + " and NA.attachmentType.documentType='"+params.documentType+"'");

		println "attachments="+notificationsAttachmentsList
		}
		else
		{
			notificationsAttachmentsList=NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.proposal="
					+params.id + " and NA.attachmentType.documentType='"+params.documentType+"'");
			println "attachments="+notificationsAttachmentsList
		}
		return notificationsAttachmentsList
	}
	public Integer getNotificationsAttachmentsInstance(def downloadedfile,
			def params)
	{
		Integer notificationId = null;
		println "++++++++++++++params.attachmentType.id+++++++++" + params.attachmentType.id
		def notificationsAttachments = NotificationsAttachments
										.find("from NotificationsAttachments NA where NA.attachmentType.id=" 
											+ params.attachmentType.id
											+ " and NA.attachmentPath = '"
											+ downloadedfile.getOriginalFilename() +"'");
		println "++++++++++++++notificationsAttachments+++++++++" + notificationsAttachments

		if(notificationsAttachments)
			notificationId= notificationsAttachments.id;
		return notificationId
		
	}
	public List getAttachmentType(def params)
	{
		def attachmentsList=AttachmentType
								.findAll("from AttachmentType  AT where  AT.documentType='"+params.documentType+"'");
		return attachmentsList;
	}
	public List getNotificationAttachmentsByNotificationId(def type,def notificationId)
	{
		def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.attachmentType.documentType='"+type+"' and NA.notification="+notificationId)
		return notificationsAttachmentsInstance;
	}
}
