package Tests.original{
	public class Test1 {

        public function Test1() {

        }

		public function m1() {
			trace('m1');
		}

        public function m2(val1:int):void {
            trace("m2");
        }

        public function m3(val1:int):int {
            trace("m3");
            return 0;
        }

        public function m4(val1,val2,val3) {
            return "m4";
        }

        public function m5(val1: int) {
            var v = val1 + 1;
            return v;

        }
	}
}