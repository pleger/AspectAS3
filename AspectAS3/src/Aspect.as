/**
 * Created with IntelliJ IDEA.
 * User: pleger
 * Date: 14-03-13
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
package {
public class Aspect {

    public static var BEFORE:int = -1;
    public static var AROUND:int = 0;
    public static var AFTER:int = 1;

    public var pc: Function;
    private var adv: Function;
    private var kind: int;

    public function Aspect(pc:Function, adv:Function, kind:int) {
        this.pc = pc;
        this.adv = adv;
        this.kind = kind;
    }

    public function executePC(jp:JoinPoint):Boolean {
        return pc(jp);
    }

    public function executeAdv(jp:JoinPoint) {
        return adv(jp);
    }

    public function getKind(): int{
        return kind;
    }
}
}
