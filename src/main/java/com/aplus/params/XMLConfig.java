package com.aplus.params;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XMLConfig implements Serializable{

	private String name;//节点属性名称

	private String value;//节点属性值

	private boolean enabled;//此节点是否启用

	private List<Map<String,String>> lists = new ArrayList<Map<String,String>>();//保存子节点

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Map<String, String>> getLists() {
		return lists;
	}

	public void setLists(List<Map<String, String>> lists) {
		this.lists = lists;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
