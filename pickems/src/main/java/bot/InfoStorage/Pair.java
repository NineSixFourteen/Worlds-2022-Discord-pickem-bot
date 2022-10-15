package bot.InfoStorage;

public class Pair implements Comparable<Pair> {

    public Pair(String id, float value){
        ID = id;
        Value = value;
    }

    private String ID; 
    private float Value;

    public String getID() {
        return ID;
    }

    public float getValue() {
        return Value;
    }

    @Override
    public int compareTo(Pair anotherPair) {
        return Float.compare(anotherPair.Value, Value);
    }
}

