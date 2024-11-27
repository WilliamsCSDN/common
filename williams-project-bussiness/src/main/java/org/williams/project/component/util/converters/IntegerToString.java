package org.williams.project.component.util.converters;

public class IntegerToString implements IConverter<Integer, String>{
    @Override
    public String convertFromAToB(Integer integer, String defaultValue) {
        if(integer==null) return defaultValue;
        return integer.toString();
    }
}
