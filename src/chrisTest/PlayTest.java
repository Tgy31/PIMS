package chrisTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class PlayTest {

	

	
	public static void tell(String s) {
		
		System.out.println("\n=====================================\n" + s + "\n");
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		
		
		//Setting up database connection
		tell("Setting up database connection");
		DatabaseConnection dbc = new DatabaseConnection();
		
		//Connecting to database - will need need exception handling when done for real
		dbc.connectToDatabase();
		
		//Simulated database using HashMap
		Database db = new Database(dbc);
		
		
		tell("Creating two coordinators: Shan He and Julie Heathcote");
		//Setting up two coordinators
		Coordinator shan = new Coordinator("Shan","He", "454545", db);
		Coordinator julie = new Coordinator("Julie","Heathcote", "3254364",db);
		tell(shan.toString());
		tell(julie.toString());
		
		tell("Setting up new module 'masters2014'");
		//Setting up a module with two coordinators:
		Module masters2014 = new Module("26582", "MSc Conversion Final Term Project", "2013/14", db, shan, julie);
		tell(masters2014.toString());
		
		tell("Setting the dates of the first inspection week");
		
		Date start1 = new DateSetter(1, 6, 2014);
		Date end1 = new DateSetter(7,6,2014);
		masters2014.setFirstInspectionStartDate(start1);
		masters2014.setFirstInspectionEndDate(end1);
		tell(masters2014.toString());
		
		tell("Setting the dates of the second inspection week");
		
		Date start2 = new DateSetter(24,8,2014);
		Date end2 = new DateSetter(31,8,2014);
		masters2014.setSecondInspectionStartDate(start2);
		masters2014.setSecondInspectionEndDate(end2);
		tell(masters2014.toString());

		tell("Setting default capacity to 12 students (unweighted)");
		masters2014.setDefaultCapacity(12);
		
		tell("Printing out details of module from database");

		System.out.println(DatabaseRetrieveMethods.retrieveModuleByIDSQL(masters2014.getModule_id(),  db));
		System.out.println(DatabaseRetrieveMethods.retrieveCoordinatorByIDSQL(shan.getStaffID(),db));
		System.out.println(DatabaseRetrieveMethods.retrieveCoordinatorByIDSQL(julie.getStaffID(),db));


		tell("Setting up keywords");

		db.keywords.add("AI");
		db.keywords.add("Image Analysis");
		db.keywords.add("Machine Learning");
		db.keywords.add("Medical Imaging");
		db.keywords.add("Cryptography");
		db.keywords.add("HCI");
		db.keywords.add("Neural Networks");
		db.keywords.add("Algorithms");
		db.keywords.add("Graph Theory");
		db.keywords.add("NLP");
		db.keywords.add("Databases");
		db.keywords.add("Functional Programming");
		db.keywords.add("Haskell");
		db.keywords.add("Operating Systems");
		db.keywords.add("Kernel");
		db.keywords.add("Linux");
		db.keywords.add("Research");
		db.keywords.add("Software Engineering");
		db.keywords.add("Mobile");
		db.keywords.add("App");
		db.keywords.add("Smart Phone");
		db.keywords.add("Visualisation");
		db.keywords.add("Data Analysis");		
		db.keywords.add("Security");	
		db.keywords.add("Internet");		
		db.keywords.add("Web");		
		db.keywords.add("System Architecture");	
		db.keywords.add("Robotics");			
		db.keywords.add("Video Game");			
		db.keywords.add("Simulation");			
		db.keywords.add("Emulation");			
		
		tell(db.printKeywords());
		
		tell("Setting up inspectors and students");

		//setting up inspectors
		Inspector iain = new Inspector("54321","Dr","Iain","Styles", "i.styles@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector ata = new Inspector("12345","Dr","Ata","Kaban", "a.kaban@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector shan2 = new Inspector("24368"," ","Shan","He", "s.he@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector martin = new Inspector("34435"," ","Martin","Escardo", "m.escardo@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector dan = new Inspector("37734"," ","Dan","Ghica", "d.ghica@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector russell = new Inspector("57489","Professor","Russell","Beale", "r.beale@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector manfred = new Inspector("14795","Manfred","Kerber","Beale", "m.kerber@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector dave = new Inspector("34255","Dr","David","Parker", "d.parker@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		Inspector paul = new Inspector("36474","Dr","Paul","Levy", "p.levy@bham.ac.uk", DatabaseRetrieveMethods.getSomeKeywords(db), db);
		String[] ids = {"54321","12345","24368","34435","37734","57489", "14795","34255","36474"};

		
		tell("Setting each inspector's capacity to match module default");
		
		iain.setCapacity(masters2014.getDefaultCapacity());
		ata.setCapacity(masters2014.getDefaultCapacity());
		shan2.setCapacity(masters2014.getDefaultCapacity());
		martin.setCapacity(masters2014.getDefaultCapacity());
		dan.setCapacity(masters2014.getDefaultCapacity());
		russell.setCapacity(masters2014.getDefaultCapacity());
		manfred.setCapacity(masters2014.getDefaultCapacity());
		dave.setCapacity(masters2014.getDefaultCapacity());
		paul.setCapacity(masters2014.getDefaultCapacity());
		
		//Setting up students for 54321
		Student chris 			= new Student("1375158", "Christopher", "Wright", masters2014, "cxw358@bham.ac.uk", "54321", "Quantifying Angiogenesis", db);
		Student abood 			= new Student("1348785", "Abdulaziz", "Alobaid", masters2014,"axa365@bham.ac.uk", "54321", "Steganography", db);
		Student pieter  		= new Student("1340667","Pieter",	"Stephanus",  masters2014,"pieter@bham.ac.uk", "54321", "Mirco", db);
		Student zhenpeng  		= new Student("1397837","Zhenpeng", "Guo", masters2014,"zhenpeng@bham.ac.uk", "54321", "TBC", db);
		Student rebecca  		= new Student("1320388","Rebecca",	"Devney", masters2014,"rebecca@bham.ac.uk",	"54321", "App to demonstrate materials behaviour", db);
		Student xiao  			= new Student("1320210","Xiao", "Wang",	 masters2014,"xiao@bham.ac.uk", "54321", "From Excel Spreadsheet to Hadoop", db);
		Student xuyao  			= new Student("1330896","Xuyao", "Zhang",	 masters2014,"xuyao@bham.ac.uk","54321", "Low-grain measurement of energy consumption", db);
		Student rowan 			= new Student("1358905","Rowan", "Stringer", masters2014,"rowan@bham.ac.uk",	"54321", "Realistic animations of dynamic natural phenomena", db);
		Student zeng 			= new Student("1381133","Zeng","Chen",  masters2014,"zeng@bham.ac.uk","12345", "Coursework submission system", db);
		Student lang 			= new Student("1401196","Lang", "Le", masters2014,"lang@bham.ac.uk",	"12345", "TBC", db);
		Student lijie 			= new Student("1371686", "Lijie", "Hu", masters2014,"lijie@bham.ac.uk", "12345", "Memory and social media", db);
		Student thiha 			= new Student("1373354","Thiha", "Win-Pe", masters2014,"thiha@bham.ac.uk","12345", "social aspects of HCI", db);
		Student paulius			= new Student("1388605","Paulius", "Balasevicius", masters2014,"paulius@bham.ac.uk",	"24368", "Physical interaction with an agent in Android", db);
		Student yuechuan		= new Student("1340775","Yuechuan", "Zhang", masters2014,"yuechuan@bham.ac.uk","24368", "Three Kingdom Card Game", db);
		Student yalong			= new Student("1270160","Yalong", "Cui", masters2014,"yalong@bham.ac.uk",	"24368", "Mobile shoping assistant", db);
		Student rui				= new Student("1339333","Rui", "Liu", masters2014,"rui@bham.ac.uk",	"24368", "Online testing system", db);
		Student simon			= new Student("1378818","Simon", "Dicken", masters2014,"simon@bham.ac.uk","24368", "TBC", db);
		Student mohammad		= new Student("1359924","Mohammad", "Shafsad", masters2014,"mohammad@bham.ac.uk",	"24368", "Text mining", db);
		Student agha			= new Student("1382493","Agha Ali", "Khan", masters2014,"agha@bham.ac.uk","37734", "TBA", db);
		Student thomas			= new Student("547753","Thomas", "Reeve", masters2014,"thomas@bham.ac.uk","37734", "Developing Intelligent Agents for Robocode", db);;
		Student richard			= new Student("1383036","Richard", "Slade", masters2014,"richard@bham.ac.uk",	"37734", "Game Development using Computational Intelligence", db);
		Student christopher1	= new Student("1075888","Christopher", "Datta-Smith", masters2014,"christopher1@bham.ac.uk",	"37734", "TBC", db);
		Student christopher2	= new Student("1401637","Christopher", "Wardell", masters2014,"christopher2@bham.ac.uk","37734", "BlueBrush Dental Workout", db);
		Student rui2			= new Student("1373415","Rui", "Sun",masters2014, "rui@bham.ac.uk","37734", "Evolutionary Computation for Software Effort Estimation", db);
		Student khalid			= new Student("900107","Khalid", "Ahmad", masters2014,"khalid@bham.ac.uk","37734", "Khattak	Mixtures of spatiotemporal models", db);
		Student ying			= new Student("1363550","Ying", "He", masters2014,"ying@bham.ac.uk",	"37734", "Document analysis through LSA", db);
		Student chenfei			= new Student("1321735","Chenfei", "Zhao", masters2014,"chenfei@bham.ac.uk","57489", "Graph-based databases and anomally detection", db);
		Student kanat			= new Student("1355219","Kanat", "Tulbassiyev", masters2014,"kanat@bham.ac.uk","57489", "security  via bigdata", db);
		Student yuchang			= new Student("1378088","Yuchang", "Lu", masters2014,"yuchang@bham.ac.uk","57489", "TBC", db);
		Student benjamin		= new Student("1102677","Benjamin", "Crispin", masters2014,"benjamin@bham.ac.uk","57489", "tbc", db);
		Student iordanis		= new Student("1382922","Iordanis", "Petkakis", masters2014,"iordanis@bham.ac.uk","14795", "Intrusion Detection", db);
		Student	tim				= new Student("1400917","Robbins", "Intrusion", masters2014,"tim@bham.ac.uk","14795",  "detection for alarm monitoring networks", db);
		Student ye				= new Student("1239458","Ye", "Tian", masters2014,"ye@bham.ac.uk","14795", "Eike Ritter: intrusion detection or Hayo", db);
		Student hendra			= new Student("1397955","Hendra", "Rudiansyah", masters2014,"hendra@bham.ac.uk","14795", "IT services", db);
		Student yevheniy		= new Student("1379981","Yevheniy", "Vlasenko", masters2014,"yevheniy@bham.ac.uk","14795", "Monitoring mobile devices to identify malware infections", db);
		Student samuel			= new Student("876796","Samuel", "Farmer", masters2014,"samuel@bham.ac.uk","14795", "Android visualisation of matter", db);
		Student reshma			= new Student("1402651","Reshma", "Shawar", masters2014,"reshma@bham.ac.uk","14795", "Financial Management Tool", db);
		Student yichuan			= new Student("1328994","Yichuan", "Bu", masters2014,"yichuan@bham.ac.uk","14795", "System for booking meals", db);
		Student wenTing			= new Student("1363200","Wen-Ting", "Li", masters2014,"wenTing@bham.ac.uk","14795", "Street market real-time system", db);
		Student hetong			= new Student("1384131","Hetong", "Ma", masters2014,"hetong@bham.ac.uk","34255", "Hospital management system", db);
		Student james		 	= new Student("1393168","James", "Yu", masters2014,"james@bham.ac.uk","34255", "Implementing call-by-push-value", db);
		Student siyu			= new Student("1383304","Siyu", "Huang", masters2014,"siyu@bham.ac.uk","36474", "Marking tools", db);
		Student george			= new Student("1016820","George", "Kiff", masters2014,"george@bham.ac.uk","36474", "Volker Sorge Game playing", db);
		db.printInspectors();
		db.printStudents();
		
		
		
		tell("Printing inspectors' caseload size and remaining capactiy after supervisions taken into account:");
		
		System.out.println(iain.getDisplayName() + " Remaining capacity: " +iain.getRemainingCapacity() + " Size of caseload: " + iain.getCaseloadSize());
		System.out.println(ata.getDisplayName() + " remaining capacity: " +ata.getRemainingCapacity() + ". Size of caseload: " + ata.getCaseloadSize());
		System.out.println(shan2.getDisplayName() + " remaining capacity: " +shan2.getRemainingCapacity() + ". Size of caseload: " + shan2.getCaseloadSize());
		System.out.println(martin.getDisplayName() + " remaining capacity: " +martin.getRemainingCapacity() + ". Size of caseload: " + martin.getCaseloadSize());
		System.out.println(dan.getDisplayName() + " remaining capacity: " +dan.getRemainingCapacity() + ". Size of caseload: " + dan.getCaseloadSize());
		System.out.println(russell.getDisplayName() + " remaining capacity: " +russell.getRemainingCapacity() + ". Size of caseload: " + russell.getCaseloadSize());
		System.out.println(manfred.getDisplayName() + " remaining capacity: " +manfred.getRemainingCapacity() + ". Size of caseload: " + manfred.getCaseloadSize());
		System.out.println(dave.getDisplayName() + " remaining capacity: " +dave.getRemainingCapacity() + ". Size of caseload: " + dave.getCaseloadSize());
		System.out.println(paul.getDisplayName() + " remaining capacity: " +paul.getRemainingCapacity() + ". Size of caseload: " + paul.getCaseloadSize());
		
		
		
		//Arranging first inspection week
		tell("Arranging first inspection schedule");
		

		masters2014.setFirstInspectionSchedule();
		
		tell("Setting availability for first inspection schedule. 9 - 12 on 2014-06-04 not available.");
		
		Schedule schedule1 = masters2014.getFirstInspectionSchedule();
		
		TimeSlot[][] slots = schedule1.getSlots();
		
		slots[3][0].setAvailability(false);
		slots[3][1].setAvailability(false);
		slots[3][2].setAvailability(false);
		slots[3][3].setAvailability(false);
		slots[3][4].setAvailability(false);
		slots[3][5].setAvailability(false);
		
		System.out.println(masters2014.getFirstInspectionSchedule().toString());

		tell("Setting availability for first inspection schedule. 12:00 - 13:00 every day not available.");

		
		masters2014.setSecondInspectionSchedule();
		Schedule schedule2 = masters2014.getSecondInspectionSchedule();
		TimeSlot[][] slots2 = schedule2.getSlots();
		
		slots2[0][7].setAvailability(false);
		slots2[0][8].setAvailability(false);
		slots2[1][7].setAvailability(false);
		slots2[1][8].setAvailability(false);
		slots2[2][7].setAvailability(false);
		slots2[2][8].setAvailability(false);
		slots2[3][7].setAvailability(false);
		slots2[3][8].setAvailability(false);
		slots2[4][7].setAvailability(false);
		slots2[4][8].setAvailability(false);
		slots2[5][7].setAvailability(false);
		slots2[5][8].setAvailability(false);
		slots2[6][7].setAvailability(false);
		slots2[6][8].setAvailability(false);		
		slots2[7][7].setAvailability(false);
		slots2[7][8].setAvailability(false);
		
		System.out.println(masters2014.getSecondInspectionSchedule().toString());
		
		tell("Ata Kaban Setting inspector availability for inspection 1. Not available on 06/06/2014 or 06/07/2014.");
		ArrayList<TimeSlot> ataKabanUnavailable = new ArrayList<TimeSlot>();

		for (int i = 5; i <= 6; i++) {
			for (int j = 0; j < slots[i].length; j++) {
				
				ataKabanUnavailable.add(slots[i][j]);

			}
		}
		
		
		
		
		ata.setUnavailableForSchedule(ataKabanUnavailable);
		
		//randomly setting inspectors as unavailable for approximately 30% of timeslots in schedule 1
		tell("randomly setting inspectors as unavailable for approximately 30% of timeslots in schedule 1");
		
		schedule1.setRandomAvailability(iain);
		schedule1.setRandomAvailability(ata);
		schedule1.setRandomAvailability(shan2);
		schedule1.setRandomAvailability(martin);
		schedule1.setRandomAvailability(dan);
		schedule1.setRandomAvailability(russell);
		schedule1.setRandomAvailability(manfred);
		schedule1.setRandomAvailability(dave);
		schedule1.setRandomAvailability(paul);
		
		/*
		tell("Printing each inspector's availability for schedule 1");
		
		System.out.println(iain.printAvailabilityForSchedule(schedule1));
		System.out.println(ata.printAvailabilityForSchedule(schedule1));		
		System.out.println(shan2.printAvailabilityForSchedule(schedule1));		
		System.out.println(martin.printAvailabilityForSchedule(schedule1));		
		System.out.println(dan.printAvailabilityForSchedule(schedule1));		
		System.out.println(russell.printAvailabilityForSchedule(schedule1));		
		System.out.println(manfred.printAvailabilityForSchedule(schedule1));		
		System.out.println(dave.printAvailabilityForSchedule(schedule1));		
		System.out.println(paul.printAvailabilityForSchedule(schedule1));		
		*/
		

		
		//tell("Printing caseloads:");
		
		//db.printCaseloads();
		//db.printModules();
		
		
		
		System.out.println(chris.getDisplayName() + "'s project keywords: " + chris.getProject().getKeywords());
		System.out.println("=================================================================\n");
		
		chris.findBestMatchForKeywords();
			
			
		
		
		
		
		

		
		
		
		/*
		//Inspector(String id, String t, String fn, String ln, String e, ArrayList<String> kw)
	
		


		*/
	}

}
