package com.eazybytes.java25.scoped;

public class SimpleExample {

    private static final ScopedValue<String> USER_NAME = ScopedValue.newInstance();
    private static final ScopedValue<String> TRANSACTION_ID = ScopedValue.newInstance();

    public void doWork() {
        ScopedValue.where(USER_NAME, "Alice").where(TRANSACTION_ID, "12345")
                .run(() -> {
                    processTask();
                });
        if(USER_NAME.isBound()) {
            String user = USER_NAME.get();  // Returns "Alice"
            IO.println("Testing the scope for the user: " + user);
        }
        if (TRANSACTION_ID.isBound()) {
            String transactionID = TRANSACTION_ID.get();  // Returns "12345"
            IO.println("Testing the scope for the transactionID: " + transactionID);
        }
    }

    private void processTask() {
        if(USER_NAME.isBound()) {
            String user = USER_NAME.get();  // Returns "Alice"
            IO.println("Processing for the user: " + user);
        }
        if (TRANSACTION_ID.isBound()) {
            String transactionID = TRANSACTION_ID.get();  // Returns "12345"
            IO.println("Processing for the transactionID: " + transactionID);
        }
        callAnotherMethod();
    }

    private void callAnotherMethod() {
        String user = USER_NAME.get();  // Returns "Alice"
        IO.println("Still Processing for the user: " + user);
        String transactionID = TRANSACTION_ID.get();  // Returns "12345"
        IO.println("Still Processing for the transactionID: " + transactionID);
    }

    public static void main(String[] args) {
        new SimpleExample().doWork();
    }

}
