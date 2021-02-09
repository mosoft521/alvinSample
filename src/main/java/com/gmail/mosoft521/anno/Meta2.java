package com.gmail.mosoft521.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//@What(desc = "An annotation test class")
//@MyAnno(str = "Meta2", val = 99)
@What()
@MyAnno()
public class Meta2 {
    @What(desc = "An annotation test method")
    @MyAnno(str = "Testing", val = 100)
    public static void myMeth() {
        Meta2 ob = new Meta2();

        Annotation annos[] = ob.getClass().getAnnotations();
        System.out.println("All annotations for Meta2:");
        for (Annotation a : annos)
            System.out.println(a);
        System.out.println();

        try {
            Method m = ob.getClass().getMethod("myMeth");
            annos = m.getAnnotations();
            System.out.println("All annotations for myMeth:");
            for (Annotation a : annos)
                System.out.println(a);
            System.out.println();
        } catch (NoSuchMethodException nsme) {
            System.out.println("myMeth not found.");
        }
    }

    public static void main(String[] args) {
        myMeth();
    }
}
