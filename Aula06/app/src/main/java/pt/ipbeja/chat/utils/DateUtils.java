package pt.ipbeja.chat.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    // Definimos o formato da String para a data (https://developer.android.com/reference/java/text/SimpleDateFormat)
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private DateUtils() {}

    /**
     * Transforma um objecto Date numa String "dd/MM/yyyy HH:mm"
     * @param date A data
     * @return Uma String legível pelo utilizador com a data e hora
     */
    public static String formatDate(Date date) {
        return formatter.format(date);
    }

    /**
     * Transforma um long numa String "dd/MM/yyyy HH:mm"
     * @param date A data
     * @return Uma String legível pelo utilizador com a data e hora
     */
    public static String formatDate(long date) {
        return formatDate(new Date(date));
    }
}
