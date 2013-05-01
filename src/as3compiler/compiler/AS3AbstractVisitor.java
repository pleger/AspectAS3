package as3compiler.compiler;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASPackage;

public abstract class AS3AbstractVisitor implements AS3Visitor {
	public boolean enter(ASCompilationUnit node) {
		return true;
	}
	public void leave(ASCompilationUnit node) {
	//	System.out.println("leave ASCompilationUnit");
	}
	
	public boolean enter(ASPackage node) {
		return true;
	}
	public void leave(ASPackage node) {		
		//System.out.println("leave ASPackage");
	}
	
	public boolean enter(ASClassType node) {
		return true;
	}
	public void leave(ASClassType node) {
		//System.out.println("leave ASClassType");
	}
	
	public boolean enter(ASInterfaceType node) {
		return true;
	}
	public void leave(ASInterfaceType node) {	
		//System.out.println("leave ASInterfaceType");
	}
	
	public boolean enter(ASField node) {
		return true;
	}
	public void leave(ASField node) {
		//System.out.println("leave ASField");
	}
	
	public boolean enter(ASMethod node) {
		return true;
	}
	public void leave(ASMethod node) {
		//System.out.println("leave ASMethod");
	}
}
