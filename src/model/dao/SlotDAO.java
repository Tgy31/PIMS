package model.dao;
import static tools.Replace.ENTER;
import static tools.Replace.PATTERN;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import model.db.Template;
import model.entity.Inspector;
import model.entity.Slot;
import model.entity.Student;
import model.entity.TimeSlot;
import model.mapping.SlotMapping;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

import tools.DateConvert;

public class SlotDAO {
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
	public boolean save(Slot slot){
		String sql = "INSERT INTO slot"					+ENTER+
							"			(slot_id, " +
							"			 start_date, " 	+
							"			 end_date, " 	+
							"			 student_id, " 	+
							"			 inspector_id)" 			+ENTER+
							"values"							 		+ENTER+
							"			(?,?,?,?,?)";
		try {
			return (template.update(sql, slot.getSlot_id(),
													  slot.getStart_date(),
													  slot.getEnd_date(),
													  slot.getStudent_id(),
													  slot.getInspector_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean addSlotsforStudent(List<TimeSlot> timeSlots, Student student){
		boolean success =false;
		for (TimeSlot timeSlot : timeSlots) {
			String sql = "INSERT INTO slot"	+ENTER+
					"			(start_date, " 	+
					"			 end_date, " 	+
					"			 student_id)" 			+ENTER+
					"values"							 	+ENTER+
					"			(?,?,?)";
			try {
				success = template.update(sql,timeSlot.getStartDate(),
														timeSlot.getEndDate(),
														student.getStudent_id()) == 1;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Save opertaion failed !");
			}
			success = false;
		}
		return success;
	}
	
	public boolean addSlotsforInspector(List<TimeSlot> timeSlots, Inspector inspector){
		boolean success = false;
		for (TimeSlot timeSlot : timeSlots) {
			String sql = "INSERT INTO slot"	+ENTER+
					"			(start_date, " 	+
					"			 end_date, " 	+
					"			 inspector_id)" 			+ENTER+
					"values"							 	+ENTER+
					"			(?,?,?)";
			try {
				success = template.update(sql,timeSlot.getStartDate(),
															timeSlot.getEndDate(),
															inspector.getInspector_id()) == 1;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Save opertaion failed !");
			}
			success = false;
		}
		return success;
	}
	
	public boolean update(Slot slot){
		String sql = "update slot"								+ENTER+
							"set"											+ENTER+
							"			 start_date= ?, "+
							"			 end_date= ?, "+
							"			 student_id= ?, "+
							"			 inspector_id= ? "			+ENTER+
							"where"										+ENTER+
							"			slot_id = ?";
		try {
			return (template.update(sql, slot.getStart_date(),
													  slot.getEnd_date(),
													  slot.getStudent_id(),
													  slot.getInspector_id(),
													  slot.getSlot_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteBySlotID(int ID){
		String sql = "delete from slot where slot_id = '"+ID+"'";
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByStudentID(int ID){
		String sql = "delete from slot where student_id = '"+ID+"'";
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByInspectorID(int ID){
		String sql = "delete from slot where inspector_id = '"+ID+"'";
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public boolean  removeSlotsfromStudentID(int ID){
		String sql = "delete from slot where student_id = '"+ID+"'";
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public boolean  removeSlotsfromInspectorID(int ID){
		String sql = "delete from slot where inspector_id = '"+ID+"'";
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public Slot findBySlotID(int ID){
		String sql = "SELECT  * " + 
							"FROM slot " + 
							"WHERE slot_id= " + "'" + ID + "'";
		List<Slot> slots = null;
		try {
			slots = template.query(sql, new SlotMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return slots.get(0);
	}
	
	public List<Slot> findByStudentID(int ID){
		String sql = "SELECT  * " + 
							"FROM slot " + 
							"WHERE student_id= " + "'" + ID + "'";
		List<Slot> slots = null;
		try {
			slots = template.query(sql, new SlotMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return slots;
	}
	
	public List<Slot> findByInspectorID(int ID){
		String sql = "SELECT  * " + 
							"FROM slot " + 
							"WHERE inspector_id= " + "'" + ID + "'";
		List<Slot> slots = null;
		try {
			slots = template.query(sql, new SlotMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return slots;
	}
	
	public List<Slot> findAll(){
		String sql = "SELECT  * " + 
							"FROM slot ";
		List<Slot> slots = null;
		try {
			slots = template.query(sql, new SlotMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return slots;
	}
	
	/**
	 * abandoned
	 * @param file
	 * @return
	 */
	public boolean importCSV(File file) {
		List<String[]> recordList = null;
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Slot slot = new Slot();
		boolean hasHeaderRecords = true;
		for (int r = 0; r < recordList.size(); r++) {
			String[] records = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try {
				if(records[0].matches(PATTERN)){
					slot.setSlot_id(Integer.valueOf(records[0]));
				}
				slot.setStart_date(DateConvert.ConverFromCSVToDate(records[1]));
				slot.setEnd_date(DateConvert.ConverFromCSVToDate(records[2]));
			} catch (ParseException e) {
				e.printStackTrace();
			}catch( NumberFormatException e){
				e.printStackTrace();
			}
		}
		return save(slot);
	}
}
