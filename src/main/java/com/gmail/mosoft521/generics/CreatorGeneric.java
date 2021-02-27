// generics/CreatorGeneric.java
package com.gmail.mosoft521.generics;

abstract class GenericWithCreate<T> {
    final T element;

    //GenericWithCreate 包含 element 字段，并通过无参构造函数强制其初始化，该构造函数又调用抽象的 create() 方法。这种创建方式可以在子类中定义，同时建立 T 的类型。
    GenericWithCreate() {
        element = create();
    }

    abstract T create();//模板方法设计模式
}

class X {
}

class XCreator extends GenericWithCreate<X> {
    @Override
    X create() {
        return new X();
    }

    void f() {
        System.out.println(
                element.getClass().getSimpleName());
    }
}

public class CreatorGeneric {
    public static void main(String[] args) {
        XCreator xc = new XCreator();
        xc.f();
    }
}
/* Output:
X
*/
