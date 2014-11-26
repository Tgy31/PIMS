package model.entity;

public class StudentKeyword {
	private int student_keyword_id;
	private int keyword_id;
	private int student_id;
	
	public StudentKeyword() {
		super();
	}
	public StudentKeyword(int student_keyword_id, int keyword_id, int student_id) {
		super();
		this.student_keyword_id = student_keyword_id;
		this.keyword_id = keyword_id;
		this.student_id = student_id;
	}
	public int getStudent_keyword_id() {
		return student_keyword_id;
	}
	public void setStudent_keyword_id(int student_keyword_id) {
		this.student_keyword_id = student_keyword_id;
	}
	public int getKeyword_id() {
		return keyword_id;
	}
	public void setKeyword_id(int keyword_id) {
		this.keyword_id = keyword_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + keyword_id;
		result = prime * result + student_id;
		result = prime * result + student_keyword_id;
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
		StudentKeyword other = (StudentKeyword) obj;
		if (keyword_id != other.keyword_id)
			return false;
		if (student_id != other.student_id)
			return false;
		if (student_keyword_id != other.student_keyword_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StudentKeyword [student_keyword_id=" + student_keyword_id
				+ ", keyword_id=" + keyword_id + ", student_id=" + student_id
				+ "]";
	}
	
}
