package {


public class AspectASRuntime {

    private static var stack:JoinPoint = null;
    private static var aspectEnv:Vector.<Aspect> = new Vector.<Aspect>();

    public static function weave(jp:JoinPoint, aspectEnv:Vector.<Aspect>):* {

        var val:*;
        for each (var asp:Aspect in aspectEnv) {

            if (asp.executePC(jp)) {

                var kind:int = asp.getKind();

                if (kind == Aspect.BEFORE) {
                    asp.executeAdv(jp);
                    val = jp.proceed();
                }

                if (kind == Aspect.AROUND) {
                    val = asp.executeAdv(jp);
                }

                if (kind == Aspect.AFTER) {
                    jp.proceed();
                    val = asp.executeAdv(jp);
                }
            }

        }

        return  val;
    }

    public static function wrap(obj:*,original:Function, args:Array, clazz:String, nameMethod:String):* {
        var jp:JoinPoint = new ExecJoinPoint(obj, original, args, clazz, nameMethod, stack);
        return weave(jp,aspectEnv);
    }

    public static function deploy(aspect:Aspect):void {
        aspectEnv.push(aspect);
    }

}
}