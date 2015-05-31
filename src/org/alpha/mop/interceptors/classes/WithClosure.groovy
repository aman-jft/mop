package org.alpha.mop.interceptors.classes

/**
 * Created by Sheldon on 31/5/15.
 */
class WithClosure {

    def testMethod = {
        //it will be not called if method exist.
        "Closure Calling ... "
    }
}
