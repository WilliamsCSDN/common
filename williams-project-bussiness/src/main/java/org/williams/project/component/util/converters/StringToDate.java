package org.williams.project.component.util.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StringToDate implements IConverter<String, Date> {
    private String pattern;
    private Locale locale;
    private Integer dateStyle;
    private Integer timeStyle;
    private Calendar calendar;
    private Boolean lenient;
    private TimeZone timeZone;

    private Boolean usedForDate;
    private Boolean usedForTime;
    private Boolean usedForDateTime;


    private DateFormat getDateInstance(){
        return DateFormat.getDateInstance(
                dateStyle==null?DateFormat.DEFAULT:dateStyle,
                locale==null?Locale.getDefault(Locale.Category.FORMAT):locale
        );
    }

    private DateFormat getTimeInstance(){
        return DateFormat.getTimeInstance(timeStyle==null?DateFormat.DEFAULT:timeStyle,
                locale==null?Locale.getDefault(Locale.Category.FORMAT):locale);
    }

    private DateFormat getDateTimeInstance(){
        return DateFormat.getDateTimeInstance(
                dateStyle==null?DateFormat.DEFAULT:dateStyle,
                timeStyle==null?DateFormat.DEFAULT:timeStyle,
                locale==null?Locale.getDefault(Locale.Category.FORMAT):locale);
    }

    private DateFormat getInstance(){
        DateFormat dateFormat = null;
        if(pattern!=null){
            dateFormat = new SimpleDateFormat(pattern, locale==null?Locale.getDefault(Locale.Category.FORMAT):locale);
        }
        if(Boolean.TRUE.equals(usedForDate)){
            dateFormat = getDateInstance();
        }else if(Boolean.TRUE.equals(usedForTime)){
            dateFormat =  getTimeInstance();
        }else if(Boolean.TRUE.equals(usedForDateTime)){
            dateFormat = getDateTimeInstance();
        }else{
            dateFormat = getDateInstance();
        }
        if(calendar!=null) {
            dateFormat.setCalendar(calendar);
        }
        if(lenient!=null) {
            dateFormat.setLenient(lenient);
        }
        if(timeZone!=null) {
            dateFormat.setTimeZone(timeZone);
        }
        return dateFormat;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    @Override
    public Date convertFromAToB(String s, Date defaultValue) {
        DateFormat dateFormat = getInstance();
        try {
            return dateFormat.parse(s);
        }catch (Exception err){ }
        return defaultValue;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public Locale getLocale() {
        return locale;
    }

    public StringToDate setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public Integer getDateStyle() {
        return dateStyle;
    }

    public StringToDate setDateStyle(Integer dateStyle) {
        this.dateStyle = dateStyle;
        return this;
    }

    public Boolean getUsedForDate() {
        return usedForDate;
    }

    public StringToDate setUsedForDate(Boolean usedForDate) {
        this.usedForDate = usedForDate;
        return this;
    }

    public Boolean getUsedForTime() {
        return usedForTime;
    }

    public StringToDate setUsedForTime(Boolean usedForTime) {
        this.usedForTime = usedForTime;
        return this;
    }

    public Boolean getUsedForDateTime() {
        return usedForDateTime;
    }

    public StringToDate setUsedForDateTime(Boolean usedForDateTime) {
        this.usedForDateTime = usedForDateTime;
        return this;
    }

    public String getPattern() {
        return pattern;
    }

    public StringToDate setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public Integer getTimeStyle() {
        return timeStyle;
    }

    public StringToDate setTimeStyle(Integer timeStyle) {
        this.timeStyle = timeStyle;
        return this;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public StringToDate setCalendar(Calendar calendar) {
        this.calendar = calendar;
        return this;
    }

    public Boolean getLenient() {
        return lenient;
    }

    public StringToDate setLenient(Boolean lenient) {
        this.lenient = lenient;
        return this;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public StringToDate setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }
}
