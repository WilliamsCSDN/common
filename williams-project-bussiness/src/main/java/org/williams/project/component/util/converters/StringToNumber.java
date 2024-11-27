package org.williams.project.component.util.converters;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class StringToNumber implements IConverter<String, Number> {
    private Locale locale;
    private String localizedPattern;
    private String pattern;
    private RoundingMode roundingMode;
    private String positivePrefix;
    private String positiveSuffix;
    private String negativePrefix;
    private String negativeSuffix;

    private Integer multiplier;
    private Integer maximumIntegerDigits;
    private Integer minimumIntegerDigits;
    private Integer maximumFractionDigits;
    private Integer minimumFractionDigits;
    private DecimalFormatSymbols decimalFormatSymbols;
    private Currency currency;
    private Boolean decimalSeparatorAlwaysShown;
    private Integer groupingSize;
    private Boolean groupingUsed;
    private Boolean parseBigDecimal;
    private Boolean parseIntegerOnly;

    private Boolean usedForCurrency;
    private Boolean usedForPercent;
    private Boolean usedForNumber;

    private NumberFormat getForNumber(){
        if(locale==null){
            return NumberFormat.getNumberInstance();
        }else{
            return NumberFormat.getNumberInstance(locale);
        }
    }

    private NumberFormat getForCurrency(){
        if(locale==null){
            return NumberFormat.getCurrencyInstance();
        }else{
            return NumberFormat.getCurrencyInstance(locale);
        }
    }

    private NumberFormat getForPercent(){
        if(locale==null){
            return NumberFormat.getPercentInstance();
        }else{
            return NumberFormat.getPercentInstance(locale);
        }
    }

    public NumberFormat getInstance(){
        if(Boolean.TRUE.equals(usedForNumber)){
            return getForNumber();
        }else if(Boolean.TRUE.equals(usedForCurrency)){
            return getForCurrency();
        }else if(Boolean.TRUE.equals(usedForPercent)){
            return getForPercent();
        }else{
            return getForNumber();
        }
    }

    @Override
    public Number convertFromAToB(String s, Number defaultValue) {
        if(s==null||s.length()<1) return defaultValue;
        NumberFormat numberFormat= getInstance();
        try{
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            if(localizedPattern!=null){
                decimalFormat.applyLocalizedPattern(localizedPattern);
            }else if(pattern!=null) {
                decimalFormat.applyPattern(pattern);
            }
            if(roundingMode!=null) {
                decimalFormat.setRoundingMode(roundingMode);
            }
            if(positivePrefix!=null) {
                decimalFormat.setPositivePrefix(positivePrefix);
            }
            if(positiveSuffix!=null){
                decimalFormat.setPositiveSuffix(positiveSuffix);
            }
            if(negativeSuffix!=null){
                decimalFormat.setNegativeSuffix(negativeSuffix);
            }
            if(negativePrefix!=null){
                decimalFormat.setNegativePrefix(negativePrefix);
            }
            if(multiplier!=null){
                decimalFormat.setMultiplier(multiplier);
            }
            if(maximumIntegerDigits!=null) {
                decimalFormat.setMaximumIntegerDigits(maximumIntegerDigits);
            }
            if(minimumIntegerDigits!=null){
                decimalFormat.setMinimumIntegerDigits(minimumIntegerDigits);
            }
            if(maximumFractionDigits!=null){
                decimalFormat.setMaximumFractionDigits(maximumFractionDigits);
            }
            if(minimumFractionDigits!=null){
                decimalFormat.setMinimumFractionDigits(minimumFractionDigits);
            }
            if(decimalFormatSymbols!=null) {
                decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            }
            if(currency!=null) {
                decimalFormat.setCurrency(currency);
            }
            if(decimalSeparatorAlwaysShown!=null) {
                decimalFormat.setDecimalSeparatorAlwaysShown(decimalSeparatorAlwaysShown);
            }
            if(groupingSize!=null) {
                decimalFormat.setGroupingSize(groupingSize);
            }
            if(groupingUsed!=null) {
                decimalFormat.setGroupingUsed(groupingUsed);
            }
            if(parseBigDecimal!=null) {
                decimalFormat.setParseBigDecimal(parseBigDecimal);
            }
            if(parseIntegerOnly!=null) {
                decimalFormat.setParseIntegerOnly(parseIntegerOnly);
            }

            return decimalFormat.parse(s);
        }catch (Exception err){}
        return defaultValue;
    }


    ////////////////////////////////////////////////////////////////////////////////
    ////


    public Boolean getUsedForCurrency() {
        return usedForCurrency;
    }

    public StringToNumber setUsedForCurrency(Boolean usedForCurrency) {
        this.usedForCurrency = usedForCurrency;
        return this;
    }

    public Boolean getUsedForPercent() {
        return usedForPercent;
    }

    public StringToNumber setUsedForPercent(Boolean usedForPercent) {
        this.usedForPercent = usedForPercent;
        return this;
    }

    public Boolean getUsedForNumber() {
        return usedForNumber;
    }

    public StringToNumber setUsedForNumber(Boolean usedForNumber) {
        this.usedForNumber = usedForNumber;
        return this;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public StringToNumber setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public StringToNumber setMaximumIntegerDigits(Integer maximumIntegerDigits) {
        this.maximumIntegerDigits = maximumIntegerDigits;
        return this;
    }

    public StringToNumber setMinimumIntegerDigits(Integer minimumIntegerDigits) {
        this.minimumIntegerDigits = minimumIntegerDigits;
        return this;
    }

    public StringToNumber setMaximumFractionDigits(Integer maximumFractionDigits) {
        this.maximumFractionDigits = maximumFractionDigits;
        return this;
    }

    public StringToNumber setMinimumFractionDigits(Integer minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits;
        return this;
    }

    public String getLocalizedPattern() {
        return localizedPattern;
    }

    public StringToNumber setLocalizedPattern(String localizedPattern) {
        this.localizedPattern = localizedPattern;
        return this;
    }

    public Locale getLocale() {
        return locale;
    }

    public StringToNumber setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public String getPattern() {
        return pattern;
    }

    public StringToNumber setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public RoundingMode getRoundingMode() {
        return roundingMode;
    }

    public StringToNumber setRoundingMode(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
        return this;
    }

    public String getPositivePrefix() {
        return positivePrefix;
    }

    public StringToNumber setPositivePrefix(String positivePrefix) {
        this.positivePrefix = positivePrefix;
        return this;
    }

    public String getPositiveSuffix() {
        return positiveSuffix;
    }

    public StringToNumber setPositiveSuffix(String positiveSuffix) {
        this.positiveSuffix = positiveSuffix;
        return this;
    }

    public String getNegativePrefix() {
        return negativePrefix;
    }

    public StringToNumber setNegativePrefix(String negativePrefix) {
        this.negativePrefix = negativePrefix;
        return this;
    }

    public String getNegativeSuffix() {
        return negativeSuffix;
    }

    public StringToNumber setNegativeSuffix(String negativeSuffix) {
        this.negativeSuffix = negativeSuffix;
        return this;
    }

    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return decimalFormatSymbols;
    }

    public StringToNumber setDecimalFormatSymbols(DecimalFormatSymbols decimalFormatSymbols) {
        this.decimalFormatSymbols = decimalFormatSymbols;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public StringToNumber setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Boolean getDecimalSeparatorAlwaysShown() {
        return decimalSeparatorAlwaysShown;
    }

    public StringToNumber setDecimalSeparatorAlwaysShown(Boolean decimalSeparatorAlwaysShown) {
        this.decimalSeparatorAlwaysShown = decimalSeparatorAlwaysShown;
        return this;
    }

    public Integer getGroupingSize() {
        return groupingSize;
    }

    public StringToNumber setGroupingSize(Integer groupingSize) {
        this.groupingSize = groupingSize;
        return this;
    }

    public Boolean getGroupingUsed() {
        return groupingUsed;
    }

    public StringToNumber setGroupingUsed(Boolean groupingUsed) {
        this.groupingUsed = groupingUsed;
        return this;
    }

    public Boolean getParseBigDecimal() {
        return parseBigDecimal;
    }

    public StringToNumber setParseBigDecimal(Boolean parseBigDecimal) {
        this.parseBigDecimal = parseBigDecimal;
        return this;
    }

    public Boolean getParseIntegerOnly() {
        return parseIntegerOnly;
    }

    public StringToNumber setParseIntegerOnly(Boolean parseIntegerOnly) {
        this.parseIntegerOnly = parseIntegerOnly;
        return this;
    }

    public int getMaximumIntegerDigits() {
        return maximumIntegerDigits;
    }

    public StringToNumber setMaximumIntegerDigits(int maximumIntegerDigits) {
        this.maximumIntegerDigits = maximumIntegerDigits;
        return this;
    }

    public int getMinimumIntegerDigits() {
        return minimumIntegerDigits;
    }

    public StringToNumber setMinimumIntegerDigits(int minimumIntegerDigits) {
        this.minimumIntegerDigits = minimumIntegerDigits;
        return this;
    }

    public int getMaximumFractionDigits() {
        return maximumFractionDigits;
    }

    public StringToNumber setMaximumFractionDigits(int maximumFractionDigits) {
        this.maximumFractionDigits = maximumFractionDigits;
        return this;
    }

    public int getMinimumFractionDigits() {
        return minimumFractionDigits;
    }

    public StringToNumber setMinimumFractionDigits(int minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits;
        return this;
    }
}
