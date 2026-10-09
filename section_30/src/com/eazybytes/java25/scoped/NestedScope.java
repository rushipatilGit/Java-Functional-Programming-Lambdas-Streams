package com.eazybytes.java25.scoped;

public class NestedScope {

    private static final ScopedValue<String> LEVEL = ScopedValue.newInstance();

    public void outerMethod() {
        ScopedValue.where(LEVEL, "Level 1").run(() -> {
            System.out.println(LEVEL.get());  // Prints "Level 1"
            innerMethod();
            System.out.println(LEVEL.get());  // Still prints "Level 1"
        });
    }

    public void innerMethod() {
        System.out.println(LEVEL.get());  // Prints "Level 1"

        ScopedValue.where(LEVEL, "Level 2").run(() -> {
            System.out.println(LEVEL.get());  // Prints "Level 2"
            deepMethod();
        });

        System.out.println(LEVEL.get());  // Back to "Level 1"
    }

    public void deepMethod() {
        System.out.println(LEVEL.get());  // Prints "Level 2"
    }

    void main() {
        new NestedScope().outerMethod();
    }
}
