// generics/ArrayMaker.java
package com.gmail.mosoft521.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);//对于在泛型中创建数组，使用 Array.newInstance() 是推荐的方式。
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringMaker.create(5);
        System.out.println(Arrays.toString(stringArray));
        stringArray[0] = "hello";
        stringArray[stringArray.length - 1] = "world";
        System.out.println(Arrays.toString(stringArray));
    }
}
/* Output:
[null, null, null, null, null]
[hello, null, null, null, world]
*/
