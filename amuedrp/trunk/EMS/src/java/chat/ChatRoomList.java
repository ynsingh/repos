package chat;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/**
* This class is used to manipulate and store ChatRoom objects.
* It provides methods to store and retrieve ChatRoom objects
* in this <code>ChatRoomList</code>.

*/
public class ChatRoomList
{
	/**
	* Stores all the ChatRoom objects
	*/
	private Map roomList;
	/**
	*/
	public ChatRoomList()
	{
		roomList = new HashMap();
	}
	
	public synchronized void addRoom(ChatRoom room)
	{
		roomList.put(room.getName(),room);
	}
	
	
	public synchronized void removeRoom(String name)
	{
		roomList.remove(name);
	}
	
	
	public ChatRoom getRoom(String name)
	{
		return (ChatRoom) roomList.get(name);
	}
	
	public ChatRoom getRoomOfChatter(String name)
	{
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			boolean chatterexists = rooms[i].chatterExists(name);
			if (chatterexists)
			{
				return rooms[i];
			}
		}
		return null;
	}
	
	
	public Set getRoomList()
	{
		return roomList.entrySet();
	}
	
	/** returns an array containing all ChatRoom objects
	
	*/
	public ChatRoom[] getRoomListArray()
	{
		ChatRoom[] roomListArray = new ChatRoom[roomList.size()];
		Set roomlist = getRoomList();
		Iterator roomlistit = roomlist.iterator();
		int i = 0;
		while(roomlistit.hasNext())
		{
			Map.Entry me = (Map.Entry)roomlistit.next();
			String key = (String) me.getKey();
			roomListArray[i] = (ChatRoom)me.getValue();
			i++;
		}
		return roomListArray;
	}
	
	
	public boolean chatterExists(String nickname)
	{
		boolean chatterexists = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			chatterexists = rooms[i].chatterExists(nickname);
			if (chatterexists)
			{
				break;
			}
		}
		return chatterexists;
	}
}