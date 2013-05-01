package as3compiler.util;

import as3compiler.compiler.AS3Visitor;
import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASPackage;

public class AbstractAS3Walker implements AS3Walker {
	
	protected AS3Visitor visitor;
	
	public AbstractAS3Walker(AS3Visitor visitor) {
		this.visitor = visitor;
	}

	@Override
	public void walk(ASCompilationUnit asCompUnit) {
		visitor.enter(asCompUnit);
	}

	@Override
	public void walk(ASPackage asPackage) {
		visitor.enter(asPackage);
	}

	@Override
	public void walk(ASClassType classType) {
		visitor.enter(classType);
	}

	@Override
	public void walk(ASInterfaceType intType) {
		visitor.enter(intType);
	}

	@Override
	public void walk(ASMethod asMethod) {
		visitor.enter(asMethod);
	}

	@Override
	public void walk(ASField asField) {
		visitor.enter(asField);
	}

}
