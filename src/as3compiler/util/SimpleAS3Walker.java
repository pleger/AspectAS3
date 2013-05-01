package as3compiler.util;

import java.util.Iterator;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASType;
import as3compiler.compiler.AS3Visitor;

public class SimpleAS3Walker extends AbstractAS3Walker {
	
	public SimpleAS3Walker(AS3Visitor visitor) {
		super(visitor);
	}
	
	@Override
	public void walk(ASCompilationUnit asCompUnit) {
		super.walk(asCompUnit);
		
		ASType asType = asCompUnit.getType();
		if(asType instanceof ASClassType) {
			walk((ASClassType)asType); 
		} else if(asType instanceof ASInterfaceType) {
			walk((ASInterfaceType)asType);
		}
		walk(asCompUnit.getPackage());
	}

	@Override
	public void walk(ASClassType classType) {
		super.walk(classType);
		for(Iterator<ASField> i = classType.getFields().iterator(); i.hasNext();) walk(i.next());
		for(Iterator<ASMethod> m = classType.getMethods().iterator(); m.hasNext();) walk(m.next());
	}

	@Override
	public void walk(ASInterfaceType intType) {
		super.walk(intType);		
		for(Iterator<ASMethod> m = intType.getMethods().iterator(); m.hasNext();) walk(m.next());
	}

}
