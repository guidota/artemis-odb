package com.artemis.generator.strategy.components;

import com.artemis.generator.common.IterativeModelStrategy;
import com.artemis.generator.model.artemis.ComponentDescriptor;
import com.artemis.generator.model.type.AccessLevel;
import com.artemis.generator.model.type.FieldDescriptor;
import com.artemis.generator.model.type.ParameterizedTypeImpl;
import com.artemis.generator.model.type.TypeModel;
import com.artemis.generator.util.FieldBuilder;

/**
 * Generates create method for each component type.
 *
 * @author Daan van Yperen
 */
public class ComponentsClassLibraryStrategy extends IterativeModelStrategy {

    @Override
    protected void apply(ComponentDescriptor component, TypeModel model) {
        model.add(componentClassField(component));
    }

    /**
     * T componentName() -> create new entity.
     */
    private FieldDescriptor componentClassField(ComponentDescriptor component) {
        return new FieldBuilder(new ParameterizedTypeImpl(Class.class, component.getComponentType()), component.getName())
                .debugNotes(component.getComponentType().getName())
                .setAccessLevel(AccessLevel.PUBLIC)
                .setStatic(true)
                .setFinal(true)
                .initializer(component.getComponentType().getSimpleName() + ".class")
                .build();
    }
}
