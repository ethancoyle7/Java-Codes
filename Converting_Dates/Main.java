import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String[] endDates = new String[] { "1/12/2025", "1/27/2025", "1/27/2025", "1/27/2025", "2/3/2025", "2/3/2025", "2/3/2025", "3/6/2025", "3/6/2025", "3/6/2025" };
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        for (int i = 0; i < endDates.length; i++) {
            try {
                Date date = dateFormat.parse(endDates[i]);
                endDates[i] = "\"" + targetFormat.format(date) + "\"";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String endDatesString = String.join(",", endDates);
        System.out.println("EndDates array: " + endDatesString);
    }
}
