package special.planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalendarUtil {
    public static final int dated = 0;
    public static final int within2Days = 1;
    public static final int within3OrMore = 2;

    private CalendarUtil(){}
    public static int eventDateType(String eventDate) {
        // Parse the event date string to LocalDate
        LocalDate parsedEventDate = LocalDate.parse(eventDate);

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the difference in days between current date and event date
        long daysDifference = ChronoUnit.DAYS.between(currentDate, parsedEventDate);

        // Determine the type of event date
        if (daysDifference <= 0) {
            // Event is today
            return dated;
        } else if (daysDifference <= 2) {
            // Event is within 2 days
            return within2Days;
        } else {
            // Event is more than 2 days away
            return within3OrMore;
        }
    }
    public static String getDateAfterDays(int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate newDate = currentDate.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return newDate.format(formatter);
    }



}
