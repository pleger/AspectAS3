package as3compiler.util;

import java.util.List;

import as3compiler.compiler.AS3Visitor;
import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASPackage;
import uk.co.badgersinfoil.metaas.dom.ScriptElement;

public class Traversor {
	
	public static void traverse(ScriptElement node, AS3Visitor visitor) {
	
		if(node instanceof ASCompilationUnit) {
			visitor.enter((ASCompilationUnit)node);
		} else if(node instanceof ASPackage) {
			visitor.enter((ASPackage)node);
		} else if(node instanceof ASClassType) {
			visitor.enter((ASClassType)node);
		} else if(node instanceof ASInterfaceType) {
			visitor.enter((ASInterfaceType)node);
		} else if(node instanceof ASMethod) {
			visitor.enter((ASMethod)node);
		} else if(node instanceof ASField) {
			visitor.enter((ASField)node);
		}		
		
		traverseChildren(node, visitor);
		
		if(node instanceof ASCompilationUnit) {
			visitor.leave((ASCompilationUnit)node);
		} else if(node instanceof ASPackage) {
			visitor.leave((ASPackage)node);
		} else if(node instanceof ASClassType) {
			visitor.leave((ASClassType)node);
		} else if(node instanceof ASInterfaceType) {
			visitor.leave((ASInterfaceType)node);
		} else if(node instanceof ASMethod) {
			visitor.leave((ASMethod)node);
		} else if(node instanceof ASField) {
			visitor.leave((ASField)node);
		}
	}
	
	public static void traverseChildren(ScriptElement node, AS3Visitor visitor) {
		
		if(node instanceof ASCompilationUnit) {
			ASCompilationUnit asunit = (ASCompilationUnit)node;
			traverse(asunit.getType(), visitor);
			traverse(asunit.getPackage(), visitor);			
		} else if(node instanceof ASPackage) {
			// do nothing for now
			
		} else if(node instanceof ASClassType) {
			ASClassType classType = (ASClassType)node;
			List<ScriptElement> fieldList = classType.getFields();
			for(ScriptElement field : fieldList) {
				traverse(field, visitor);
			}
			
			List<ScriptElement> methodList = classType.getMethods();
			for(ScriptElement method : methodList) {
				traverse(method, visitor);
			}						
		} else if(node instanceof ASInterfaceType) {
			ASInterfaceType interfaceType = (ASInterfaceType)node;
			List<ScriptElement> methodList = interfaceType.getMethods();
			for(ScriptElement method : methodList) {
				traverse(method, visitor);
			}
		}
	
	}

}
