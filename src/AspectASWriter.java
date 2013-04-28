/**
 * Created with IntelliJ IDEA.
 * User: Paul Leger
 * Date: 12-03-13
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */

import org.apache.velocity.exception.MethodInvocationException;
import uk.co.badgersinfoil.metaas.ActionScriptFactory;
import uk.co.badgersinfoil.metaas.ActionScriptProject;
import uk.co.badgersinfoil.metaas.dom.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AspectASWriter {

    private String output;
    private String path;
    private boolean enabledFileOutput;
    ActionScriptFactory factory;

    public AspectASWriter() {
        this.path = ".";
        this.output = "output";
        this.enabledFileOutput = true;
        factory = new ActionScriptFactory();
    }


    public void disableFileOutput() {
        this.enabledFileOutput = false;
    }

    public void setDirectoryOutput(String output) {
        this.output = output;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    public void instrument(String file) {

        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(path+"/"+file));
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be open:"+file);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        ASCompilationUnit parser = factory.newParser().parse(reader);
        System.out.print(output);
        ActionScriptProject project = factory.newEmptyASProject(output);
        //project.setOutputLocation(output);

        ASPackage pkg = parser.getPackage();
        ASPackage pkgT;


        ASType type = pkg.getType(); //interface or class


        if (type instanceof ASClassType) {
            pkgT = project.newClass(type.getName()).getPackage();
            instrumentClass((ASClassType)type, (ASClassType)pkgT.getType());
        } else {
            pkgT = project.newInterface(type.getName()).getPackage();
            instrumentInterface((ASInterfaceType) type, (ASInterfaceType) pkgT.getType());
        }


        instrumentImport(pkg, pkgT);
        pkgT.setName(pkg.getName());

        if (enabledFileOutput) {
            try {
                project.writeAll();
            } catch (IOException e) {
                System.out.println("File cannot be written");
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void instrumentInterface(ASInterfaceType inter, ASInterfaceType interT) {
        interT.setVisibility(inter.getVisibility());

        for (Object anInterface : inter.getSuperInterfaces()) {
            inter.addSuperInterface(((ASInterfaceType) anInterface).getName());
        }

        for (Object anMethod : inter.getMethods()) {
            ASMethod method = (ASMethod) anMethod;
            ASMethod methodT = interT.newMethod(method.getName(),method.getVisibility(),method.getType());

            for (Object anArg: method.getArgs()) {
                ASArg arg = (ASArg) anArg;
                ASArg argT = methodT.addParam(arg.getName(),arg.getType());
                argT.setDefault(arg.getDefaultString());
            }
        }

    }

    private void instrumentClass(ASClassType clazz, ASClassType clazzT) {
        clazzT.setDynamic(clazz.isDynamic());
        clazzT.setFinal(clazz.isFinal());
        clazzT.setSuperclass(clazz.getSuperclass());

        List interfaces = clazz.getImplementedInterfaces();
        for (Object anInterface : interfaces) {
            clazzT.addImplementedInterface(((ASInterfaceType) anInterface).getName());
        }

        instrumentFields(clazz, clazzT);
        instrumentMethods(clazz, clazzT);
    }

    private void instrumentFields(ASClassType clazz, ASClassType clazzT) {
        for (Object afield : clazz.getFields()) {
            ASField field = (ASField) afield;
            ASField fieldT = clazzT.newField(field.getName(), field.getVisibility(), field.getType());
            fieldT.setConst(field.isConst());
            fieldT.setStatic(field.isStatic());
            fieldT.setInitializer(field.getInitializer());
        }
    }

    //This method really instruments for now
    private void instrumentMethods(ASClassType clazz, ASClassType clazzT) {
        for (Object anMethod : clazz.getMethods()) {
            ASMethod method = (ASMethod) anMethod;
            ASMethod methodT = clazzT.newMethod(method.getName(),method.getVisibility(),method.getType());
            ASFunctionExpression wrap = factory.newFunctionExpression();

            // TODO: fast and simple cloning function
            for (Object anArg: method.getArgs()) {
                ASArg arg = (ASArg) anArg;
                ASArg argT = methodT.addParam(arg.getName(), arg.getType());
                argT.setDefault(arg.getDefaultString());
            }
            for (Object s: method.getStatementList() ) {
                wrap.addStmt(s.toString());
            }

            List<Expression> wrapArguments = new ArrayList<Expression>(5);
            wrapArguments.add(factory.newSimpleName("this"));
            wrapArguments.add(wrap);
            wrapArguments.add(factory.newSimpleName("arguments"));
            wrapArguments.add(factory.newStringLiteral(clazz.getName()));
            wrapArguments.add(factory.newStringLiteral(method.getName()));

            ASInvocationExpression invocation = factory.newInvocationExpression(
                factory.newExpression("AspectASRuntime.wrap"),
                wrapArguments
            );
            if ("void".equals(method.getType()) || method.getName().equals(clazz.getName())) {
                methodT.newExprStmt(invocation);
            }
            else {
                methodT.newReturn(invocation);
            }
        }
    }

    private void instrumentImport(ASPackage pkg,ASPackage pkgT) {
        pkgT.setName(pkg.getName());
        List imports = pkg.findImports();
        for (Object anImport : imports) {
            pkgT.addImport((String) anImport);
        }
    }
}
