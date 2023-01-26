import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main 
{
  public static void main(String[] args) 
  {
    String transponder = "8734048";
    System.out.println(" The users transponder id is :"+ transponder +"\n");
    String userDate = "2022-01-27T00:00:00.000Z";
    System.out.println(" The date of the tag that was seen was :"+ userDate+"\n");
    boolean found = false;

    String[] startSerialNumber = new String[] { "8733848", "8734048", "8741248", "8748448", "8752048", "8759248",
        "8766448", "8770048", "8777248", "8784448" };
    String[] EndSerialNumber = new String[] { "8734047", "8741247", "8748447", "8752047", "8759247", "8766447", "8770047",
        "8777247", "8784447", "8788047" };
    String[] endDates = new String[] { "2025-01-12T00:00:00.000Z", "2025-01-27T00:00:00.000Z",
        "2025-01-27T00:00:00.000Z", "2025-01-27T00:00:00.000Z", "2025-02-03T00:00:00.000Z", "2025-02-03T00:00:00.000Z",
        "2025-02-03T00:00:00.000Z", "2025-03-06T00:00:00.000Z", "2025-03-06T00:00:00.000Z",
        "2025-03-06T00:00:00.000Z" };

    String[] mountType = new String[] { "Interior - Commercial", "Interior - Passenger", "Interior - Passenger",
        "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger",
        "Interior - Passenger", "Interior - Passenger", "Interior - Passenger" };
    int startSerialIndex = 0;
    int serialIndex = 0;
        while (startSerialIndex < startSerialNumber.length && serialIndex < EndSerialNumber.length) 
    {
      String startSerial = startSerialNumber[startSerialIndex];
      String endDate = endDates[startSerialIndex];
      String mount = mountType[startSerialIndex];
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      LocalDate userPromptedDate = LocalDate.parse(userDate, formatter);
      LocalDate endDateValue = LocalDate.parse(endDate, formatter);
      if (Integer.parseInt(transponder) >= Integer.parseInt(startSerialNumber[startSerialIndex])
          && Integer.parseInt(transponder) < Integer.parseInt(EndSerialNumber[serialIndex])) 
      {
        System.out.println("Transponder: " + transponder + " falls between " + startSerial
            + " and " + EndSerialNumber[serialIndex] + ".\nThe Mount Type is : " + mount + "\nThe Warranty End date is : " + endDate
            + "\nThis was found at Index Value: " + startSerialIndex);
        System.out.println("\nThe Index where is found and items being used it at index : " + startSerialIndex);

        
        // check if there is at least a 2 year difference between the two dates and that
        // the enddate is at least 2 years in the future
        if (userPromptedDate.isBefore(endDateValue) || userPromptedDate.isEqual(endDateValue)) 
        {
				long diffInYears = java.time.Period.between(userPromptedDate, endDateValue).getYears();
				if (diffInYears >= 2) 
        {
					System.out.println("\nTransponder is valid with more than 2 years remaining");
				} else 
        {
					System.out.println("\nTransponder is not valid, less than 2 years remaining");
				}
			} else 
        {
				System.out.println("Transponder is expired.");
			}
        
      System.out.println("The date of the users tag is : " + userPromptedDate);
			System.out.println("\nExpiry date for the given transponder is: " + endDateValue);
			found = true;
			break;
		}
		
    startSerialIndex++;
    serialIndex++;
  }
    if (!found) 
    {
			System.out.println("Transponder not found in the given range.");
		}
  }
}

    
