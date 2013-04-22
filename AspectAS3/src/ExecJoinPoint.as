package {
import flash.sampler.getSavedThis;


public class ExecJoinPoint extends JoinPoint {

        private var obj;
        private var original:Function;
        private var args;
        private var nameClazz;
        private var nameMethod;

        public function ExecJoinPoint(obj, original:Function, args:Array , nameClazz:String, nameMethod:String, stack:JoinPoint) {
            super(stack);
            this.args = args;
            this.original = original;
            this.obj = obj;
            this.nameClazz = nameClazz;
            this.nameMethod = nameMethod;
        }

        public override function proceed() {
            if (obj == null) {
                throw "No obj";
            }

            return original.apply(obj, args);
        }

        public function getClassName():String {
            return this.nameClazz;
        }

        public function getMethodName():String {
            return this.nameMethod;
        }

}
}

