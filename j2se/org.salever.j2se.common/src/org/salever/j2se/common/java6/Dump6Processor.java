package org.salever.j2se.common.java6;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

// Source version
@SupportedSourceVersion(SourceVersion.RELEASE_6)
// Process all annotations
@SupportedAnnotationTypes("*")
// No options support
// Empty set when not annotated with @SupportedOptions
public class Dump6Processor extends AbstractProcessor {
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			for (TypeElement element : annotations) {
				System.out.println(element.getQualifiedName() + "("
						+ element.getNestingKind() + ")");
			}
		}
		return false; // No annotations claimed
	}
}