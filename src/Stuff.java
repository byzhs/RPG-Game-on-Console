public class Stuff {
    private String name;
    private int weight;

    public Stuff(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
