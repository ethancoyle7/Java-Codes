import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a transponder value: ");
        String transponder = input.nextLine();
        System.out.print("Enter a date in the format YYYY-MM-DDT00:00:00.000Z: ");
        String userDate = input.nextLine();
        boolean found = false;

        String[] startSerialNumber = new String[] { "8733848", "8734048", "8741248", "8748448", "8752048", "8759248", "8766448", "8770048", "8777248", "8784448" };
        String[] serialNumbers = new String[] { "8734047", "8741247", "8748447", "8752047", "8759247", "8766447", "8770047", "8777247", "8784447", "8788047" };
        String[] endDates = new String[] {"2025-01-12T00:00:00.000Z","2025-01-27T00:00:00.000Z","2025-01-27T00:00:00.000Z","2025-01-27T00:00:00.000Z","2025-02-03T00:00:00.000Z","2025-02-03T00:00:00.000Z","2025-02-03T00:00:00.000Z","2025-03-06T00:00:00.000Z","2025-03-06T00:00:00.000Z","2025-03-06T00:00:00.000Z" };
		
        String[] mountType = new String[] { "Interior - Commercial", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger", "Interior - Passenger" };
        int startSerialIndex = 0;
        int serialIndex = 0;

        while (startSerialIndex < startSerialNumber.length && serialIndex < serialNumbers.length) {
            String startSerial = startSerialNumber[startSerialIndex];
            String endDate = endDates[startSerialIndex];
            String mount = mountType[startSerialIndex];
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDate userPromptedDate = LocalDate.parse(userDate, formatter);
            LocalDate endDateValue = LocalDate.parse(endDate, formatter);
            if (Integer.parseInt(transponder) >= Integer.parseInt(startSerialNumber[startSerialIndex]) && Integer.parseInt(transponder) < Integer.parseInt(serialNumbers[serialIndex])) {
                System.out.println("Transponder: " + transponder + " falls between " + startSerialNumber[startSerialIndex] + " and " + serialNumbers[serialIndex] + ". Mount Type: " + mount + " End date: " + endDate + " Index Value: " + startSerialIndex);
                System.out.println("startSerialNumber index: " + startSerialIndex + " serialNumbers index: " + serialIndex + " endDates index: " + startSerialIndex + " mountType index: " + startSerialIndex);
                found = true;
                // check if there is at least a 2 year difference between the two dates and that the enddate is at least 2 years in the future
                if(endDateValue.isAfter(userPromptedDate.plusYears(2)) && endDateValue.isAfter(LocalDate.now().plusYears(2))){
                    System.out.println("Transponder end date is at least 2 years in the future and has at least 2 year difference with user prompted date");
                }
                else{
                    System.out.println("Transponder end date does not meet the requirement of being at least 2 years in the future and having at least 2 year difference with user prompted date");
                }
                break;
            }
            startSerialIndex++;
            serialIndex++;
        }

        if (!found) {
            System.out.println("Transponder value not found in arrays.");
        } else {
            System.out.println("Transponder value found in arrays");
        }
    }
}
