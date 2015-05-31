package org.alpha.mop.interceptors.classes

/**
 * Created by Sheldon on 31/5/15.
 */

/*
* This call extends the WithClosure which has  a closure property defined with name "print"
* In this class, no interceptors are defined like : invokeMethod or methodMissing
* */
class WithMethodOnly extends WithClosure{

    def testMethod() {
        "Method Calling ... "
    }
}
