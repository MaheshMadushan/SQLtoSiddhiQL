package SiddhiApp;

public class Column implements IAttribute, ISiddhiAppComposite{

    private String name;
    private String alias;

    public Column(String name, String alias) {
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

    private String getAttributeWithAlias(){
        if(alias == null){
            return " " + name + " ";
        }else{
            return " " + name + " AS " + alias + " ";
        }
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return getAttributeWithAlias();
    }
}
