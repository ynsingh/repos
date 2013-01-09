package org.iitk.livetv.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;


/**
 * This class was autogenerated by Torque on:
 *
 * [Sat Dec 22 02:32:10 IST 2012]
 *
 * You should not use this class directly.  It should not even be
 * extended all references should be to Channel
 */
public abstract class BaseChannel extends BaseObject
{
    /** The Peer class */
    private static final ChannelPeer peer =
        new ChannelPeer();

        
    /** The value for the channelId field */
    private int channelId;
      
    /** The value for the channelName field */
    private String channelName;
      
    /** The value for the channelDesc field */
    private String channelDesc;
      
    /** The value for the channelIpAddress field */
    private String channelIpAddress;
      
    /** The value for the channelPort field */
    private int channelPort;
      
    /** The value for the channelIcon field */
    private String channelIcon;
  
    
    /**
     * Get the ChannelId
     *
     * @return int
     */
    public int getChannelId()
    {
        return channelId;
    }

                        
    /**
     * Set the value of ChannelId
     *
     * @param v new value
     */
    public void setChannelId(int v) 
    {
    
                  if (this.channelId != v)
              {
            this.channelId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ChannelName
     *
     * @return String
     */
    public String getChannelName()
    {
        return channelName;
    }

                        
    /**
     * Set the value of ChannelName
     *
     * @param v new value
     */
    public void setChannelName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.channelName, v))
              {
            this.channelName = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ChannelDesc
     *
     * @return String
     */
    public String getChannelDesc()
    {
        return channelDesc;
    }

                        
    /**
     * Set the value of ChannelDesc
     *
     * @param v new value
     */
    public void setChannelDesc(String v) 
    {
    
                  if (!ObjectUtils.equals(this.channelDesc, v))
              {
            this.channelDesc = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ChannelIpAddress
     *
     * @return String
     */
    public String getChannelIpAddress()
    {
        return channelIpAddress;
    }

                        
    /**
     * Set the value of ChannelIpAddress
     *
     * @param v new value
     */
    public void setChannelIpAddress(String v) 
    {
    
                  if (!ObjectUtils.equals(this.channelIpAddress, v))
              {
            this.channelIpAddress = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ChannelPort
     *
     * @return int
     */
    public int getChannelPort()
    {
        return channelPort;
    }

                        
    /**
     * Set the value of ChannelPort
     *
     * @param v new value
     */
    public void setChannelPort(int v) 
    {
    
                  if (this.channelPort != v)
              {
            this.channelPort = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ChannelIcon
     *
     * @return String
     */
    public String getChannelIcon()
    {
        return channelIcon;
    }

                        
    /**
     * Set the value of ChannelIcon
     *
     * @param v new value
     */
    public void setChannelIcon(String v) 
    {
    
                  if (!ObjectUtils.equals(this.channelIcon, v))
              {
            this.channelIcon = v;
            setModified(true);
        }
    
          
              }
  
         
                
     
    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
          save(ChannelPeer.getMapBuilder()
                .getDatabaseMap().getName());
      }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
       * Note: this code is here because the method body is
     * auto-generated conditionally and therefore needs to be
     * in this file instead of in the super class, BaseObject.
       *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
          try
        {
            con = Transaction.begin(dbName);
            save(con);
            Transaction.commit(con);
        }
        catch(TorqueException e)
        {
            Transaction.safeRollback(con);
            throw e;
        }
      }

      /** flag to prevent endless save loop, if this object is referenced
        by another object which falls in this transaction. */
    private boolean alreadyInSave = false;
      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
          if (!alreadyInSave)
        {
            alreadyInSave = true;


  
            // If this object has been modified, then save it to the database.
            if (isModified())
            {
                if (isNew())
                {
                    ChannelPeer.doInsert((Channel) this, con);
                    setNew(false);
                }
                else
                {
                    ChannelPeer.doUpdate((Channel) this, con);
                }
            }

                      alreadyInSave = false;
        }
      }


                    
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  channelId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setChannelId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setChannelId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getChannelId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       * It then fills all the association collections and sets the
     * related objects to isNew=true.
       */
      public Channel copy() throws TorqueException
    {
        return copyInto(new Channel());
    }
  
    protected Channel copyInto(Channel copyObj) throws TorqueException
    {
          copyObj.setChannelId(channelId);
          copyObj.setChannelName(channelName);
          copyObj.setChannelDesc(channelDesc);
          copyObj.setChannelIpAddress(channelIpAddress);
          copyObj.setChannelPort(channelPort);
          copyObj.setChannelIcon(channelIcon);
  
                    copyObj.setChannelId(0);
                                          
        
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ChannelPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Channel:\n");
        str.append("ChannelId = ")
           .append(getChannelId())
           .append("\n");
        str.append("ChannelName = ")
           .append(getChannelName())
           .append("\n");
        str.append("ChannelDesc = ")
           .append(getChannelDesc())
           .append("\n");
        str.append("ChannelIpAddress = ")
           .append(getChannelIpAddress())
           .append("\n");
        str.append("ChannelPort = ")
           .append(getChannelPort())
           .append("\n");
        str.append("ChannelIcon = ")
           .append(getChannelIcon())
           .append("\n");
        return(str.toString());
    }
}