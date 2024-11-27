package org.williams.project.component.util.converters;

public class StringToShort implements IConverter<String, Short> {
    @Override
    public Short convertFromAToB(String s, Short defaultValue) {
        if(s==null||s.length()<1){
            return defaultValue;
        }
        try{
            return Short.parseShort(s);
        }catch (Exception err){}
        return defaultValue;
    }
}
