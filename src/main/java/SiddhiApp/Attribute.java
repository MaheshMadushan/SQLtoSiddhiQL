package SiddhiApp;

public class Attribute implements IAttribute, ISiddhiAppComposite{
    private String name;
    private String alias;

    public Attribute(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return getAttributeWithAlias();
    }

    public String toString(boolean withAliases){
        if(withAliases){
            return getAttributeWithAlias();
        }else{
            return " " + getName() + " ";
        }
    }

    private String getAttributeWithAlias(){
        if(alias == null){
            return " " + name + " ";
        }else{
            return " " + name + " AS " + alias + " ";
        }
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        if(alias == null){
            return " " + name + " ";
        }else{
            return " " + name + " AS " + alias + " ";
        }
    }
}
