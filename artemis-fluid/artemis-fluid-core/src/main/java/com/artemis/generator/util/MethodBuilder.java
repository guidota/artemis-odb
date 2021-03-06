package com.artemis.generator.util;

import com.artemis.generator.model.artemis.ComponentDescriptor;
import com.artemis.generator.model.type.AccessLevel;
import com.artemis.generator.model.type.MethodDescriptor;
import com.artemis.generator.model.type.ParameterDescriptor;

import java.lang.reflect.Type;

/**
 * Helper builder for MethodDescriptor.
 *
 * @author Daan van Yperen
 */
public class MethodBuilder {

    private final MethodDescriptor method;

    public MethodBuilder(Type returnType, String methodName) {
        method = new MethodDescriptor(returnType, methodName);
    }

    /**
     * Prep method.
     */
    public MethodDescriptor build()
    {
        return method;
    }

    public MethodBuilder returnFluid() {
        method.addStatement("return this");
        return this;
    }

    public MethodBuilder component(ComponentDescriptor component, String suffix) {
        method.addStatement(component.getName() + " component = " + "mappers.m" + component.getName() + suffix);
        return this;
    }

    public MethodBuilder mapper(ComponentDescriptor component, String suffix) {
        mapper("", component, suffix);
        return this;
    }

    public MethodBuilder mapper(String prefix, ComponentDescriptor component, String suffix) {
        method.addStatement(prefix + "mappers.m"+component.getName() + suffix);
        return this;
    }

    public MethodBuilder markOrRemove(String condition, String entityId, ComponentDescriptor component) {
        method.addStatement("if (" + condition + ") this._changeRegistry.mark(" + entityId + ", " + "mappers.m"+component.getName() + ".get(entityId))");
        method.addStatement("if (!" + condition + ") this._changeRegistry.remove(" + entityId + ", " + component.getName() + ".class)");
        return this;
    }

    public MethodBuilder remove(String entityId, ComponentDescriptor component) {
        method.addStatement("this._changeRegistry.remove(" + entityId + ", " + component.getName() + ".class)");
        return this;
    }

    public MethodBuilder mark(String entityId, String component) {
        method.addStatement("this._changeRegistry.mark(" + entityId + ", " + component + ")");
        return this;
    }

    public MethodBuilder parameter(Type type, String name) {
        method.addParameter(new ParameterDescriptor(type, name));
        return this;
    }

    public MethodBuilder setStatic(boolean value) {
        method.setStatic(value);
        return this;
    }

    /** Add body, excluding outer brackets. */
    public MethodBuilder body(String value) {
        String[] split = value.split("\n");
        for (String statement : split) {
            method.addStatement(statement);
        }
        return this;
    }

    public MethodBuilder statement(String statement) {
        method.addStatement(statement);
        return this;
    }

    public MethodBuilder accessLevel(AccessLevel level) {
        method.setAccessLevel(level);
        return this;
    }

    public MethodBuilder debugNotes(String message) {
        method.setDebugNotes(message);
        return this;
    }

    public MethodBuilder varArgs(boolean varargs) {
        method.setVarargs(varargs);
        return this;
    }

    public MethodBuilder javaDoc(String javadoc) {
        method.setJavadoc(javadoc);
        return this;
    }
}
