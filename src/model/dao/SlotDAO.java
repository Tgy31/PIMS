package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Course;
import model.entity.Slot;
import model.mapping.CourseMapping;
import model.mapping.SlotMapping;

public class SlotDAO {
	private Template template = new Template();
	
	public boolean save(Slot slot){
		String sql = "INSERT INTO slot"					+ENTER+
							"			(slot_id, " +
							"			 start_date, " 	+
							"			 end_date, " 	+
							"			 avalibility)" 				+ENTER+
							"values"							 		+ENTER+
							"			(?,?,?,?)";
		try {
			return (template.update(sql, slot.getSlot_id(),
													  slot.getStart_date(),
													  slot.getEnd_date(),
													  slot.getAvailbility()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Slot slot){
		String sql = "update slot"								+ENTER+
							"set"													+ENTER+
							"			 start_date= ?, "+
							"			 end_date= ?, "+
							"			 avalibility= ?"		+ENTER+
							"where"											+ENTER+
							"			slot_id = ?";
		try {
			return (template.update(sql, slot.getStart_date(),
													  slot.getEnd_date(),
													  slot.getAvailbility(),
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
	
	public boolean deleteBySlotD(int ID){
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
	
	public List<Slot> findByAll(){
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
	
}
