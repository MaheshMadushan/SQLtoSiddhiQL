package SiddhiApp.Annotation.Common;

public class KeyValue<K,V> implements ICommonAnnotationComposite{
    // self sorting map would be the best
    private final K key;
    private final V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return key.toString() + " = " + value.toString();
    }
}
