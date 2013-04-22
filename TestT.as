package  {
	public class TestT {
		public function ChocolateCake(candles:int) {

			var __wrap__ = function () {
				this.numberOfCandles = candles;
			};
			return __wrap__.apply(this,arguments);
		}
		public function test(val:int):void {
			var __wrap__ = function () {
				trace('Hello world');
			};
			return __wrap__.apply(this,arguments);
		}
		public function test2(val2) {
			var __wrap__ = function () {
				trace("Hola Mundo");
			};
			return __wrap__.apply(this,arguments);
		}
		public function test3(val1, val2, val3) {
			var __wrap__ = function () {
				return "hola";
			};
			return __wrap__.apply(this,arguments);
		}
		public function test4(val1:int) {
			var __wrap__ = function () {
				var v = val1 + 1;
				return v;
			};
			return __wrap__.apply(this,arguments);
		}
	}
}