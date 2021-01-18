package com.artemis.generator.strategy.components;

import com.artemis.generator.common.BuilderModelStrategy;
import com.artemis.generator.model.artemis.ArtemisModel;
import com.artemis.generator.model.type.TypeModel;

/**
 * Generate basic scaffold for SuperMapper class.
 *
 * @author Daan van Yperen
 */
public class ComponentsBaseStrategy implements BuilderModelStrategy {

    @Override
    public void apply(ArtemisModel artemisModel, TypeModel model) {
        model.name = "C";
        model.packageName = "com.artemis";
    }
}
