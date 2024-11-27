package org.williams.project.component.util.converters;

public class StringToLong implements IConverter<String, Long> {
    @Override
    public Long convertFromAToB(String s, Long defaultValue) {
        if(s==null || s.length()<1){
            return defaultValue;
        }
        try{
            return Long.valueOf(s);
        }catch (Exception err){}
        return defaultValue;
    }
}
