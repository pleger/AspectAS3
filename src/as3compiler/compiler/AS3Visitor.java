package as3compiler.compiler;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASPackage;

public interface AS3Visitor {

	public boolean enter(ASCompilationUnit node);
	public void    leave(ASCompilationUnit node);
	
	public boolean enter(ASPackage node);
	public void    leave(ASPackage node);
	
	public boolean enter(ASClassType node);
	public void    leave(ASClassType node);
	
	public boolean enter(ASInterfaceType node);
	public void    leave(ASInterfaceType node);
	
	public boolean enter(ASField node);
	public void leave(ASField node);
	
	public boolean enter(ASMethod node);
	public void    leave(ASMethod node);
}
