package Compiler;

import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;

public class CustomSelectColumnsVisitor implements SelectItemVisitor {
    @Override
    public void visit(AllColumns allColumns) {
        System.out.println("in " + CustomSelectColumnsVisitor.class);
        System.out.println(allColumns.toString());
    }






    @Override
    public void visit(AllTableColumns allTableColumns) {
        System.out.println("in " + CustomSelectColumnsVisitor.class);
        System.out.println(allTableColumns.toString());
    }







    @Override
    public void visit(SelectExpressionItem selectExpressionItem) {
        System.out.println("in " + CustomSelectColumnsVisitor.class);
        System.out.println(selectExpressionItem.toString());
    }
}
