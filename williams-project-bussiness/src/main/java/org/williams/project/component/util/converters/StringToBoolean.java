package org.williams.project.component.util.converters;

public class StringToBoolean implements IConverter<String, Boolean> {
    @Override
    public Boolean convertFromAToB(String s, Boolean defaultValue) {
        if(s==null || s.length()<1) return defaultValue;
        try {
            return Boolean.valueOf(s);
        }catch (Exception e){ }
        return defaultValue;
    }
}
