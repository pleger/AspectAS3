package  Tests.instrumented.Tests.original{
public class Test1_org {
    public function Test1_org() {
        AspectASRuntime.wrap(this,function () {
        },arguments,"Test1","Test1");
    }
    public function m1() {

        return AspectASRuntime.wrap(this,function () {
            trace('m1');
        },arguments,"Test1","m1");
    }

    public function m2(val1:int) {

        return AspectASRuntime.wrap(this,function () {
            trace("m2");
        },arguments,"Test1","m2");
    }
    public function m3(val1:int):int {

        return AspectASRuntime.wrap(this,function () {
            trace("m3");
            return 0;
        },arguments,"Test1","m3");

    }
    public function m4(val1, val2, val3) {
        return AspectASRuntime.wrap(this,function () {
            return "m4";
        },arguments,"Test1","m4");
    }
    public function m5(val1:int) {
        return AspectASRuntime.wrap(this,function () {
            var v = val1 + 1;
            return v;
        },arguments,"Test1","m5");
    }
}
}