package as3compiler.util;

import java.util.List;

import uk.co.badgersinfoil.metaas.ActionScriptFactory;
import uk.co.badgersinfoil.metaas.dom.ASFunctionExpression;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.Statement;

public class Instrument {
	
	public static String createStatament(String statements, 
			boolean isConstructor, String className, String methodName) {
		
		String stmtStr = "";
		if(!isConstructor) {
			stmtStr = "return ";
		}
		stmtStr += "AspectASRuntime.wrap(this,";
		stmtStr += statements;
		stmtStr += ", arguments, \"";
		stmtStr += className;
		stmtStr += "\", \"";
		stmtStr += methodName;
		stmtStr += "\");";
		
		
		return stmtStr;
	}

	public static ASFunctionExpression createFunctionStatement(
			ActionScriptFactory factory, ASMethod method) {

		ASFunctionExpression wrap = factory.newFunctionExpression();
		List<Statement> stmtList = method.getStatementList();
		for(Statement stmt : stmtList) {
			wrap.addStmt(stmt.toString());
		}
		
		return wrap;
	}

}
