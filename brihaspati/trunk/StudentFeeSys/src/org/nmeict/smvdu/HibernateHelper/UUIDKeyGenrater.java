package org.nmeict.smvdu.HibernateHelper;
import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.*;

public class UUIDKeyGenrater implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
			UUID u = UUID.randomUUID();
			setKeyValue(u.toString());
		return u;
	}

	private String keyValue;

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		
		this.keyValue = keyValue;
	}
	
}
