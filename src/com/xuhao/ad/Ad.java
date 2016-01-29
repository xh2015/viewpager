package com.xuhao.ad;

public class Ad {
	private int icon;
	private String desc;

	public Ad(int icon, String desc) {
		super();
		this.icon = icon;
		this.desc = desc;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
