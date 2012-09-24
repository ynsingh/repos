package chat;


/**
* This class represents a chatter in the chat room.
* For each Chatter object a name, age and login time is required.
*/
public class Chatter
{
	private String name = null;


	private String sex = null;
	private String comment = null;
	private String email = null;
	private long loginTime = -1;
	private long enteredInRoomAt = -1;
	private int age = -1;
        private String position = null;
private String room=null;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
         public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
	
	
	
	public Chatter(String name, String sex, long loginTime,String position,String room)
	{
		this.name = name;
		this.sex = sex;
		this.loginTime = loginTime;
                this.position = position;
                this.room=room;
	}	
	
	
	public String getName()
	{
		return name;
	}

	
	public String getSex()
	{
		return sex;
	}
	
	
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	
	public String getComment()
	{
		return comment;
	}
	
	
	public void setAge(int age)
	{
		this.age=age;
	}
	
	
	public int getAge()
	{
		return age;
	}
	
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	
	public String getEmail()
	{
		return email;
	}
	
	public long getLoginTime()
	{
		return loginTime;
	}

	

	public void setEnteredInRoomAt( long enteredAt)
	{
		this.enteredInRoomAt = enteredAt;
	}
	
	public long getEnteredInRoomAt()
	{
		return enteredInRoomAt;
	}
}