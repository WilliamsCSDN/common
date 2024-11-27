package org.williams.project.component.util.converters;

public class StringToDouble implements IConverter<String, Double> {
    @Override
    public Double convertFromAToB(String s, Double defaultValue) {
        if(s==null || s.length()<1) return defaultValue;
        try{
            return Double.parseDouble(s);
        }catch (Exception err){}
        return defaultValue;
    }
}
