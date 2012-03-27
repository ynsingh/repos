package aell

import java.util.Date;

class AvlLogDetails 
{
    Integer userId
	Integer sessionCount
	String subjectId
	String topicId
	String experimentId
	String contentTypeId
	Date accessTime
	//Date endTime
	Integer Duration
	String status
	static mapping =
	{
		version false
	}
    static constraints =
	{
		//endTime nullable:true
    }
}
