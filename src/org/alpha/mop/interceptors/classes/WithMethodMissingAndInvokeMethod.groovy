package org.alpha.mop.interceptors.classes

/**
 * Created by Sheldon on 31/5/15.
 */
class WithMethodMissingAndInvokeMethod extends WithInvokeMethod{
    def methodMissing(String name, args) { 'Missing called ... ' }
}
