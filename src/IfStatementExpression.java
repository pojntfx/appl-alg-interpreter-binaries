package appl_alg_interpreter;

import java.util.Hashtable;

public class IfStatementExpression extends Expression {
	
	// if statement
	Expression condition;
	Expression trueBranch;
	Expression falseBranch;
	

	public IfStatementExpression(int line_number, String filename, Expression condition,	Expression trueBranch, Expression falseBranch) {
		super(line_number, filename);
		this.condition = condition;
		this.trueBranch = trueBranch;
		this.falseBranch = falseBranch;
	}

	public static Expression Create(ProgramScanner psc) throws Exception {
		Expression condition = Expression.Create(psc, true);
		psc.nextToken("then");
		Expression trueBranch = Expression.Create(psc, true);
		psc.nextToken("else");
		Expression falseBranch = Expression.Create(psc, true);
		psc.nextToken("endif");
		return new IfStatementExpression(psc.lineNumber, psc.filename, condition, trueBranch, falseBranch);
	}

	public Object evaluate(Hashtable<String,Object> params) throws Exception {
		Object result = null;
		String s = (String) condition.evaluate(params);
		boolean cond = Boolean.parseBoolean(s);
		
		if (cond) {
			result = trueBranch.evaluate(params);
		} else {
			result = falseBranch.evaluate(params);
		}
		return result;
	}
}







