package com.artemis.generator.util;

import com.google.common.collect.Lists;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Predicate;

import static org.reflections.ReflectionUtils.*;

/**
 * @author Daan van Yperen
 */
public abstract class ExtendedTypeReflection {

    private static final Map<Type, Set<Field>> allPublicFields = new HashMap<>();
    private static final Map<Type, Set<Method>> allPublicMethods = new HashMap<>();
    private static final Map<Type, List<Annotation>> allAnnotations = new HashMap<>();

    /**
     * Get if component is a flag component.
     *
     * @return {@code true} is simple flag, {@code false} if it is a data container.
     */
    public static boolean isFlagComponent(Class<?> type) {
        return getAllPublicFields(type).isEmpty() &&
                getAllPublicMethods(type).isEmpty();
    }

    /**
     * Get all public fields of this type, cached.
     * <p>
     * Excludes static.
     */
    @SuppressWarnings("unchecked")
    public static Set<Field> getAllPublicFields(Class<?> type) {
        Set<Field> result = allPublicFields.get(type);
        if (result == null) {
            result = getAllFields(type, withModifier(Modifier.PUBLIC), withoutModifier(Modifier.STATIC)::test);
            allPublicFields.put(type, result);
        }
        return result;
    }

    /**
     * Get all public annotations of type, throughout the hierarchy!
     * Ordered from superclass to subclass.
     */

    public static List<Annotation> getAllAnnotations(Class<?> type) {
        List<Annotation> result = allAnnotations.get(type);
        if (result == null) {
            result = getAllAnnotationsList(type);
            allAnnotations.put(type, result);
        }
        return result;
    }

    /** Returns all annotations on hierarchy. Ignores Object and interfaces. */
    @SuppressWarnings("unchecked")
    public static List<Annotation> getAllAnnotationsList(Class<?> type) {
        ArrayList<Annotation> result = new ArrayList<>(4);
        for (Class<?> t : getHierarchy(type)) {
            result.addAll(getAnnotations(t));
        }
        return result;
    }

    /** Return class hierarchy, except object. */
    private static List<Class<?>> getHierarchy(Class<?> type) {
        ArrayList<Class<?>> results = new ArrayList<>();
        while (type != Object.class && !type.isInterface()) {
            results.add(type);
            type = type.getSuperclass();
        }
        return Lists.reverse(results);
    }


    /**
     * Get all public methods of type, cached.
     * <p>
     * Excludes static, abstract.
     */
    @SuppressWarnings("unchecked")
    public static Set<Method> getAllPublicMethods(Class<?> type) {
        Set<Method> result = allPublicMethods.get(type);
        if (result == null) {
            result = getAllMethods(type, withModifier(Modifier.PUBLIC), withoutModifier(Modifier.ABSTRACT)::test, withoutModifier(Modifier.STATIC)::test, withoutModifier(Modifier.VOLATILE)::test);
            allPublicMethods.put(type, result);
        }
        return result;
    }

    public static <T extends Member> Predicate<T> withoutModifier(final int mod) {
        return input -> input != null && (input.getModifiers() & mod) == 0;
    }

}
