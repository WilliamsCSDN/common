package org.williams.project.component.util.converters;

public class StringToFloat implements IConverter<String, Float> {
    @Override
    public Float convertFromAToB(String s, Float defaultValue) {
        if(s==null || s.length()<1){
            return defaultValue;
        }
        try{
            return Float.parseFloat(s);
        }catch (Exception err){
        }
        return defaultValue;
    }
}
