import java.io.File;

public class EmployeeRecords{
	
	static Employee currentEmployee;
	private static RandomFile application = new RandomFile();
	
	// find byte start in file for first active record
		public static Employee firstRecord(File file, long currentByteStart) {
			// if any active record in file look for first record
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for first record
				currentByteStart = application.getFirst();
				// assign current Employee to first record in file
				currentEmployee = application.readRecords(currentByteStart);
				application.closeReadFile();// close file for reading
				// if first record is inactive look for next record
				if (currentEmployee.getEmployeeId() == 0) {
					currentEmployee = nextRecord(file, currentByteStart);// look for next record
				} // end if
				return currentEmployee;
		}// end firstRecord

		// find byte start in file for previous active record
		public static Employee previousRecord(File file, long currentByteStart) {
			// if any active record in file look for first record
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for previous record
				currentByteStart = application.getPrevious(currentByteStart);
				// assign current Employee to previous record in file
				currentEmployee = application.readRecords(currentByteStart);
				// loop to previous record until Employee is active - ID is not 0
				while (currentEmployee.getEmployeeId() == 0) {
					// get byte start in file for previous record
					currentByteStart = application.getPrevious(currentByteStart);
					// assign current Employee to previous record in file
					currentEmployee = application.readRecords(currentByteStart);
				} // end while
				application.closeReadFile();// close file for reading
				return currentEmployee;
		}// end previousRecord

		// find byte start in file for next active record
		public static Employee nextRecord(File file, long currentByteStart) {
			// if any active record in file look for first record
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for next record
				currentByteStart = application.getNext(currentByteStart);
				// assign current Employee to record in file
				currentEmployee = application.readRecords(currentByteStart);
				// loop to previous next until Employee is active - ID is not 0
				while (currentEmployee.getEmployeeId() == 0) {
					// get byte start in file for next record
					currentByteStart = application.getNext(currentByteStart);
					// assign current Employee to next record in file
					currentEmployee = application.readRecords(currentByteStart);
				} // end while
				application.closeReadFile();// close file for reading
				return currentEmployee;
		}// end nextRecord

		// find byte start in file for last active record
		public static Employee lastRecord(File file, long currentByteStart) {
			// if any active record in file look for first record
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for last record
				currentByteStart = application.getLast();
				// assign current Employee to first record in file
				currentEmployee = application.readRecords(currentByteStart);
				application.closeReadFile();// close file for reading
				// if last record is inactive look for previous record
				if (currentEmployee.getEmployeeId() == 0) {
					currentEmployee = previousRecord(file, currentByteStart);// look for previous record
				}
				return currentEmployee;
				
		}// end lastRecord


}
