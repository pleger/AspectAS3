package as3compiler.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import as3compiler.compiler.AS3Visitor;
import as3compiler.compiler.AS3Weaver;
import as3compiler.util.AS3Walker;
import as3compiler.util.SimpleAS3Walker;
import as3compiler.util.Traversor;

import uk.co.badgersinfoil.metaas.ActionScriptFactory;
import uk.co.badgersinfoil.metaas.ActionScriptParser;
import uk.co.badgersinfoil.metaas.ActionScriptProject;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;

public class AS3Compiler {

	public static void main(String[] args) {
		new AS3Compiler().compile(new File("Test1.as"));
	}
	
	public void compile(File file) {
		try {
			Reader reader = new FileReader(file);		
			ActionScriptFactory factory = new ActionScriptFactory();
			ActionScriptParser parser = factory.newParser();
			ASCompilationUnit asUnit = parser.parse(reader);
			
			ActionScriptProject project = factory.newEmptyASProject("./output/");
			
						
			AS3Visitor weaver = new AS3Weaver(factory, project);
			
			/* old implementation */
			//Traversor.traverse(asUnit, weaver);
			
			/* new implementation */
			AS3Walker walker = new SimpleAS3Walker(weaver);
			walker.walk(asUnit);
			
			project.writeAll();
			
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException ei) {
			ei.printStackTrace();
		}
		
		
	}
}
