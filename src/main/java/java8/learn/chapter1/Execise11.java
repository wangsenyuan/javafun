package java8.learn.chapter1;

/**
 * Created by senyuanwang on 15/4/19.
 */
public class Execise11 {

    public static void main(String[] args) {
        X x = new X();
        x.h();
    }

}

interface I {
    default void f() {
        System.out.println("I#f()");
    }

    void g();
}


interface J {
    default void f() {
        System.out.println("J#f();");
    }

    default void g() {
        System.out.println("J#g()");
    }

    default void h() {
        System.out.println("J#h()");
    }
}

class X extends S implements I, J {

    @Override
    public void f() {
        System.out.println("X#f()");
    }

    @Override
    public void g() {
        System.out.println("X#g()");
    }
}

class S {
    public void h() {
        System.out.println("S#h()");
    }
}