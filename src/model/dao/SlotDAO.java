package model.dao;
import static tools.Replace.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import model.db.Template;
import model.entity.Slot;
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
				slot.setAvailbility(Boolean.valueOf(records[3]));
			} catch (ParseException e) {
				e.printStackTrace();
			}catch( NumberFormatException e){
				e.printStackTrace();
			}
		}
		return save(slot);
	}
}
