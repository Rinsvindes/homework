package ru.homework.loanmaster.util;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

public class TermUtil {
    private final static PeriodFormatter FORMATTER = PeriodFormat.getDefault();

    public static Integer getTermByRate(Integer term, TermRate termRate) {
        if (termRate == null) return term;

        switch (termRate) {
            case MINUTE:
                return Period.minutes(term).getSeconds();
            case HOUR:
                return Period.hours(term).getSeconds();
            case DAY:
                return Period.days(term).getSeconds();
            case MONTH:
                return Period.months(term).getSeconds();
            case YEAR:
                return Period.years(term).getSeconds();
            default:
                return term;
        }
    }

    public static String getTermBySeconds(Integer term) {
        return FORMATTER.print(Period.seconds(term));
    }
}
