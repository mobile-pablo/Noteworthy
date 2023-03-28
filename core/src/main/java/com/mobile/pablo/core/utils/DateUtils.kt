import android.text.format.DateFormat
import java.util.Date

object DateUtils {

    fun dayMonthYearFormat(date: Date) : String = DateFormat.format(
        "dd-MM-yyyy",
        date
    ).toString()
}