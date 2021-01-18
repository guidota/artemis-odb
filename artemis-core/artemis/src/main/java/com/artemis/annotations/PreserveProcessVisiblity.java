package com.artemis.annotations;

import com.artemis.systems.EntityProcessingSystem;

import java.lang.annotation.*;

/**
 * When optimizing an {@link EntityProcessingSystem}, don't reduce the visibility
 * of {@link EntityProcessingSystem#process()}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface PreserveProcessVisiblity {}
