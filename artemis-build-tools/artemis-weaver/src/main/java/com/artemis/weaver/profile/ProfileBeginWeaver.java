package com.artemis.weaver.profile;

import com.artemis.meta.ClassMetadata;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

class ProfileBeginWeaver extends AdviceAdapter implements Opcodes {
	private ClassMetadata info;
	
	ProfileBeginWeaver(MethodVisitor methodVisitor, ClassMetadata info, int access, String name, String desc) {
		super(ASM8, methodVisitor, access, name, desc);
		this.info = info;
	}
	
	@Override
	protected void onMethodEnter() {
		String systemName = info.type.getInternalName();
		String profiler = info.profilerClass.getInternalName();
		String profileDescriptor = info.profilerClass.getDescriptor();

		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, systemName, "$profiler", profileDescriptor);
		mv.visitMethodInsn(INVOKEVIRTUAL, profiler, "start", "()V", false);
	}
}
