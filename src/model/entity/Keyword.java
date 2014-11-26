package model.entity;

public class Keyword {
	private int  keyword_id;
	private int  module_id;
	private String  keyword_name;

	public Keyword() {
		super();
	}

public Keyword(int keyword_id, int module_id, String keyword_name){
	this.keyword_id=keyword_id;
	this.module_id=module_id;
	this.keyword_name=keyword_name;
}


@Override
public String toString() {
	return "Keyword [keyword_id=" + keyword_id + ", module_id=" + module_id
			+ ", keyword_name=" + keyword_name + "]";
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + keyword_id;
	result = prime * result
			+ ((keyword_name == null) ? 0 : keyword_name.hashCode());
	result = prime * result + module_id;
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Keyword other = (Keyword) obj;
	if (keyword_id != other.keyword_id)
		return false;
	if (keyword_name == null) {
		if (other.keyword_name != null)
			return false;
	} else if (!keyword_name.equals(other.keyword_name))
		return false;
	if (module_id != other.module_id)
		return false;
	return true;
}


public int getKeyword_id() {
	return keyword_id;
}


public void setKeyword_id(int keyword_id) {
	this.keyword_id = keyword_id;
}


public int getModule_id() {
	return module_id;
}


public void setModule_id(int module_id) {
	this.module_id = module_id;
}


public String getKeyword_name() {
	return keyword_name;
}


public void setKeyword_name(String keyword_name) {
	this.keyword_name = keyword_name;
}


}