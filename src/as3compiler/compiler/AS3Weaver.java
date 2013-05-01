package as3compiler.compiler;

import java.util.List;

import as3compiler.util.Instrument;
import uk.co.badgersinfoil.metaas.ActionScriptFactory;
import uk.co.badgersinfoil.metaas.ActionScriptProject;
import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASFunctionExpression;
import uk.co.badgersinfoil.metaas.dom.ASInterfaceType;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.ASPackage;

public class AS3Weaver extends AS3AbstractVisitor {
	
	private enum ASType {
		CLASS, INTERFACE,
	}
	
	private ActionScriptFactory factory;
	private ActionScriptProject project;
	private ASType currentType;
	private String asName;
	private ASCompilationUnit newUnit;
	private ASClassType clazz;
	private ASInterfaceType interzz;
	
	
	public AS3Weaver(ActionScriptFactory factory, ActionScriptProject project) {
		this.factory = factory;
		this.project = project;
	}

	public boolean enter(ASCompilationUnit node) {
		//System.out.println("ASCompilationUnit");
		return true;
	}
	
	public void leave(ASCompilationUnit node) {
		//System.out.println("leave ASCompilationUnit");
		//System.out.println(orgunit.toString());
	}
	
	public boolean enter(ASPackage node) {
		//System.out.println("ASPackage");
		
		ASPackage newPkg = newUnit.getPackage();
		newPkg.setName(node.getName());
		
		List<String> list = node.findImports();
		for(String s : list) {
			newPkg.addImport(s);
		}
		return true;
	}
	public void leave(ASPackage node) {
		//System.out.println("leave ASPackage");		
	}
	
	public boolean enter(ASClassType node) {		
		//System.out.println("ASClassType");
		
		currentType = ASType.CLASS;
		newUnit = project.newClass(node.getName());
		clazz = (ASClassType)newUnit.getType();
		clazz.setDescription(node.getDescriptionString());
		clazz.setDynamic(node.isDynamic());
		clazz.setFinal(node.isFinal());
		clazz.setSuperclass(node.getSuperclass());
		clazz.setVisibility(node.getVisibility());
		
		return true;
	}
	public void leave(ASClassType node) {
		//System.out.println("leave ASClassType");		
	}
	
	public boolean enter(ASInterfaceType node) {
		//System.out.println("ASInterfaceType");
		currentType = ASType.INTERFACE;
		newUnit = project.newInterface(node.getName());
		interzz = (ASInterfaceType)newUnit.getType();
		interzz.setDescription(node.getDescriptionString());
		interzz.setVisibility(node.getVisibility());		
		
		return true;
	}
	
	public boolean enter(ASMethod node) {
		//System.out.println("ASMethod");

		switch(currentType) {
		case CLASS:
			boolean isConstructor = false;
			if(clazz.getName().equals(node.getName())) {
				isConstructor = true;
			}
			ASMethod newMethod = clazz.newMethod(node.getName(), node.getVisibility(), node.getType());
			newMethod.setStatic(node.isStatic());			
			ASFunctionExpression wrap = Instrument.createFunctionStatement(factory, node);
			String stmtStr = Instrument.createStatament(wrap.toString(), isConstructor, clazz.getName(), node.getName());
			newMethod.addStmt(stmtStr);
			//System.out.println(node);
			//System.out.println(newMethod);
			break;
		case INTERFACE:
			break;
		default:
			break;
		}

		
		return true;
	}
	
	public boolean enter(ASField node) {		
		//System.out.println("ASField");
		
		if(currentType == ASType.CLASS) {			
			ASField newField = clazz.newField(node.getName(), node.getVisibility(), node.getType());
			newField.setConst(node.isConst());
			newField.setStatic(node.isStatic());
			//newField.setInitializer(node.getInitializer());
		}
		
		return true;
	}
}
