package as3compiler.util;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASPackage;

public interface AS3Walker {

	public void walk(ASCompilationUnit asCompUnit);
	public void walk(ASPackage asPackage);
	public void walk(ASClassType classType);
	public void walk(ASInterfaceType intType);
	public void walk(ASMethod asMethod);
	public void walk(ASField asField);
}
