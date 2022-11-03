package Compiler;

import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.OrderByVisitor;

public class CustomOrderByVisitor implements OrderByVisitor {
    @Override
    public void visit(OrderByElement orderByElement) {
        System.out.println("in OrderByElement:" + this.getClass());
        System.out.println(orderByElement.toString());
    }
}
