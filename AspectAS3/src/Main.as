/**
 * Created with IntelliJ IDEA.
 * User: pleger
 * Date: 15-03-13
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
package {


import Tests.instrumented.Tests.original.Test1_org;

import flash.system.SecurityPrivilege;

import org.granite.reflect.Field;

import org.granite.reflect.Method;

//import org.granite.reflect.visitor.handlers.

import org.granite.reflect.Type;


import flash.display.Sprite;
import flash.text.TextField;

public class Main extends Sprite {
    public function Main() {


        var pc = function(jp:JoinPoint):Boolean {
            var jp1:ExecJoinPoint = ExecJoinPoint(jp);
            return jp1.getClassName()== "Test1" && jp1.getMethodName() == "m1"
        };

        var adv =  function(jp:JoinPoint) {
            trace("matchXXXXXX");
        };


        var aspect:Aspect = new Aspect(pc,adv, Aspect.BEFORE);


        /*
        var type:Type = Type.forName("Aspect");
        var m:Field = type.getField(function(f:Field):Boolean {return f.name == "pc"});
        trace("METHOD:"+ m.name);

        trace("Type:"+type.name+" methods:"+type.getMethods().length)
         */



        AspectASRuntime.deploy(aspect);

        var aspected:Boolean = true;
        var test:Test1_org;


        if (aspected) {
            test = new Test1_org();
        }else {
            //do something
        }

        test.m1();


        /*
        var textField:TextField = new TextField();
        textField.text = "Hello, World";
        trace("CHAO!---2");
        addChild(textField);
        */
    }
}
}
