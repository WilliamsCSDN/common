package org.williams.project.component.util.converters;

public interface IConverter<A, B> {
    public B convertFromAToB(A a, B defaultValue);
}
