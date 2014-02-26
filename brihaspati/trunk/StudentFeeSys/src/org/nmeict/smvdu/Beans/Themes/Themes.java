package org.nmeict.smvdu.Beans.Themes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="themes")
@SessionScoped

/**
 * @author Shaista Bano
 */

public class Themes {

	private String theme = "cruze";
	private boolean flag = false;
	//private String defaultTheme = "blitzer";
	private String defaultTheme = "bluesky";
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getDefaultTheme() {
		return defaultTheme;
	}

	public void setDefaultTheme(String defaultTheme) {
		this.defaultTheme = defaultTheme;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
}
