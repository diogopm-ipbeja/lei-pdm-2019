package pt.ipbeja.chat.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /**
     * Cria uma Date a partir de um ano, mês e dia do mês
     * @param year Ano
     * @param month Mês (Os meses começam em zero. 0 -> Janeiro, 1 -> Fevereiro, etc.)
     * @param dayOfMonth Dia do mês
     * @return Uma Date
     */
    public static Date createDate(int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return c.getTime();
    }

    /**
     * Cria uma representação em long de uma data a partir de um ano, mês e dia do mês
     * @param year Ano
     * @param month Mês (Os meses começam em zero. 0 -> Janeiro, 1 -> Fevereiro, etc.)
     * @param dayOfMonth Dia do mês
     * @return Um long da data
     */
    public static long createLong(int year, int month, int dayOfMonth) {
        Date date = createDate(year, month, dayOfMonth);
        return date.getTime();
    }
}
