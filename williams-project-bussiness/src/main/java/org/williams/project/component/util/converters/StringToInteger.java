package org.williams.project.component.util.converters;

public class StringToInteger implements IConverter<String, Integer> {

    @Override
    public Integer convertFromAToB(String a, Integer defaultValue) {
        if(a==null) return defaultValue;
        try {
            return Integer.valueOf(a);
        }catch (NumberFormatException nfe){}
        return defaultValue;
    }
}
