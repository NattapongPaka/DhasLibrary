package app.dhaslibrary.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Dev on 4/1/2560.
 */

public class TimeUtil {
    private static TimeUtil instance;

    public static TimeUtil getInstance() {
        if (instance == null) {
            instance = new TimeUtil();
        }
        return instance;
    }

    public String TimeStampConverter(final String inputFormat, String inputTimeStamp, final String outputFormat) throws ParseException {
        return new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(inputFormat).parse(inputTimeStamp));
    }

    public String getTimeOnly(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        return date.format(currentLocalTime);
    }

    public String getDateThai(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm", Locale.getDefault());
        date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        return date.format(currentLocalTime);
    }

    public String getDateOnlyNow(){
        int _offset = getCurrentTimezoneOffset();
        Calendar c = Calendar.getInstance();
        int YEAR = c.get(Calendar.YEAR);
        int MONTH = c.get(Calendar.MONTH);
        int DATE = c.get(Calendar.DATE);
        String value = YEAR + "-" + _getFullFormat(Integer.toString(MONTH + 1)) + "-" + _getFullFormat(Integer.toString(DATE));
        if (_offset == -420) {
            return value;
        } else {
            return genTimeZoneThai(value);
        }
    }

    public String getDateTimeNow() {
        int _offset = getCurrentTimezoneOffset();
        Calendar c = Calendar.getInstance();
        int YEAR = c.get(Calendar.YEAR);
        int MONTH = c.get(Calendar.MONTH);
        int DATE = c.get(Calendar.DATE);
        int HOUR_OF_DAY = c.get(Calendar.HOUR_OF_DAY);
        int MINUTE = c.get(Calendar.MINUTE);
        int SECOND = c.get(Calendar.SECOND);
        String value = YEAR + "-" + _getFullFormat(Integer.toString(MONTH + 1)) + "-" + _getFullFormat(Integer.toString(DATE)) + " " + _getFullFormat(Integer.toString(HOUR_OF_DAY)) + ":" + _getFullFormat(Integer.toString(MINUTE)) + ":" + _getFullFormat(Integer.toString(SECOND));
        if (_offset == -420) {
            return value;
        } else {
            return genTimeZoneThai(value);
        }
    }

    private int getCurrentTimezoneOffset() {
        GregorianCalendar cal = new GregorianCalendar();
        return -(cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / 60000;
    }

    private String _getFullFormat(String data) {
        if (data.length() < 2) {
            data = '0' + data;
        }
        return data;
    }

    private String genTimeZoneThai(String datetime) {
        int _offset = getCurrentTimezoneOffset();
        if (_offset == -420)
            return datetime;
        String[] d = datetime.split(" ");
        String[] date = d[0].split("-");
        String[] time = d[1].split(":");
        if (_offset >= 0) {
            int minutes = 420 + _offset;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
            cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
            cal.set(Calendar.DATE, Integer.parseInt(date[2]));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            cal.set(Calendar.SECOND, Integer.parseInt(time[2]));
            cal.add(Calendar.MINUTE, minutes);
            String _d = _getFullFormat(Integer.toString(cal.get(Calendar.DATE)));
            String _m = _getFullFormat(Integer.toString((cal.get(Calendar.MONTH) + 1)));
            String _y = Integer.toString(cal.get(Calendar.YEAR));
            String _h = _getFullFormat(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
            String _m2 = _getFullFormat(Integer.toString(cal.get(Calendar.MINUTE)));
            String _s = _getFullFormat(Integer.toString(cal.get(Calendar.SECOND)));
            return _y + "-" + _m + "-" + _d + " " + _h + ":" + _m2 + ":" + _s;
        } else if (_offset < 0) {
            if (_offset > -420) {
                int minutes = 420 - (_offset * -1);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
                cal.set(Calendar.DATE, Integer.parseInt(date[2]));
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                cal.set(Calendar.SECOND, Integer.parseInt(time[2]));
                cal.add(Calendar.MINUTE, minutes);
                String _d = _getFullFormat(Integer.toString(cal.get(Calendar.DATE)));
                String _m = _getFullFormat(Integer.toString((cal.get(Calendar.MONTH) + 1)));
                String _y = Integer.toString(cal.get(Calendar.YEAR));
                String _h = _getFullFormat(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
                String _m2 = _getFullFormat(Integer.toString(cal.get(Calendar.MINUTE)));
                String _s = _getFullFormat(Integer.toString(cal.get(Calendar.SECOND)));
                return _y + "-" + _m + "-" + _d + " " + _h + ":" + _m2 + ":" + _s;
            } else {
                int minutes = (_offset * -1) - 420;
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
                cal.set(Calendar.DATE, Integer.parseInt(date[2]));
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                cal.set(Calendar.SECOND, Integer.parseInt(time[2]));
                cal.add(Calendar.MINUTE, -minutes);
                String _d = _getFullFormat(Integer.toString(cal.get(Calendar.DATE)));
                String _m = _getFullFormat(Integer.toString((cal.get(Calendar.MONTH) + 1)));
                String _y = Integer.toString(cal.get(Calendar.YEAR));
                String _h = _getFullFormat(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
                String _m2 = _getFullFormat(Integer.toString(cal.get(Calendar.MINUTE)));
                String _s = _getFullFormat(Integer.toString(cal.get(Calendar.SECOND)));
                return _y + "-" + _m + "-" + _d + " " + _h + ":" + _m2 + ":" + _s;
            }
        }
        return datetime;
    }

}
