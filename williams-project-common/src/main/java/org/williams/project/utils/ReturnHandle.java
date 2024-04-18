package org.williams.project.utils;


/**
 * @author Williams
 * @date 2023-05-10-15:47
 * @Description
 */
@FunctionalInterface
public interface ReturnHandle<T> {

    T execute();

}
