package {

    public class JoinPoint {

        private var stack:JoinPoint;

        public function JoinPoint(stack):void {
            this.stack = stack;
        }

        public function proceed() {
            throw "NO PROCEED IMPLEMENTED";
        }
    }

}