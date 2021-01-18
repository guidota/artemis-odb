package com.artemis.weaver;


import com.artemis.Weaver;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

class ProfileAnnotationRemoverWeaver extends ClassVisitor implements Opcodes {

	public ProfileAnnotationRemoverWeaver(ClassVisitor cv) {
		super(ASM8, cv);
	}
	
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return (Weaver.PROFILER_ANNOTATION.equals(desc))
			? null
			: super.visitAnnotation(desc, visible);
	}
}
