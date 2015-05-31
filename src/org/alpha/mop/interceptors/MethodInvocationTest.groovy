package org.alpha.mop.interceptors

import org.alpha.mop.interceptors.classes.*
import org.junit.Test;

/**
 * Created by Sheldon on 31/5/15.
 */

class MethodInvocationTest extends GroovyTestCase{

    @Test
    void testCanery() {
        assert true, true
    }

    //Test if only closure exist with same name as method called.
    @Test
    void testMethodVsClosureWhenOnlyClosureExit() {
        def closureCall = new WithClosure()
        // it should call closure
        assertEquals 'Closure Calling ... ', closureCall.testMethod()
    }

    //Test whether it call method or closure
    @Test
    void testMethodVsClosureWhenMethodAndClosureBothExist() {
        def methodCall = new WithMethodOnly()
        //it should call method
        assertEquals "Method Calling ... ", methodCall.testMethod()
    }


    //Test  it call method or closure
    @Test
    void testNonExistingMethod() {
        def methodCall = new WithMethodOnly()
        shouldFail MissingMethodException, {
            methodCall.callNonExistingMethod()
        }
    }

    /*
    * Conclusion :
    * 1. It's clear from above two tests that if method exist then it will be called, if not
    *    then it will call a closure having same name
    * 2. If a method is not exist and none of the interceptors then exception is thrown
    * */

    //Test if method exist but invokeMethod() is also defined with dummy implementation.
    @Test
    void testCallInvokeMethod() {
        def invokeCall = new WithInvokeMethod()
        assertEquals "Method Calling ... ", invokeCall.testMethod()
    }

    //Test if method not exist but invokeMethod() is also defined with dummy implementation.
    @Test
    void testCallInvokeMethodWhenMethodNotExist() {
        def invokeCall = new WithInvokeMethod()
        assertEquals "Invoke method ... ", invokeCall.callNonExistingMethod()
    }


    /*
    * Conclusion from testCallInvokeMethod & testCallInvokeMethodWhenMethodNotExist:
    * Request of a non existing method call passes from invokeMethod().
    * */


    //Test if method not exist but missingMethod() is also defined.
    @Test
    void testCallMissingMethod() {
        def missingMethod = new WithMethodMissingAndInvokeMethod()
        assertEquals "Missing called ... ", missingMethod.callNonExistingMethod()
    }


    /*
    * Conclusion from testCallMissingMethod:
    * If a method with Name "missingMethod" is defined then it will not call "invokeMethod"
    * */


    //Test if method exist and class implements the GroovyInterceptable.
    @Test
    void testImplementedGroovyInterceptable() {
        def interceptor = new ImplementsInterceptable()
        assertEquals "Invoke method ... ", interceptor.testMethod()
    }


    //Test if method not exist and class implements the GroovyInterceptable..
    @Test
    void testImplementedGroovyInterceptableWhenMethodNotExist() {
        def interceptor = new ImplementsInterceptable()
        assertEquals "Invoke method ... ", interceptor.callNonExistingMethod()
    }
    
    /*
    * Conclusion from testImplementedGroovyInterceptable and testImplementedGroovyInterceptableWhenMethodNotExist:
    * If GroovyInterceptable is implemented then all method call will pass through "invokeMethod" no matter method exist or not.
    * */
}
