package com.company;

public class Main {

    public static void main(String[] args) {
        MyGUIForm app = new MyGUIForm();
        app.setVisible(true);
        CallUnitTest test = new CallUnitTest();
        test.CallUnitTest();
    }
}
