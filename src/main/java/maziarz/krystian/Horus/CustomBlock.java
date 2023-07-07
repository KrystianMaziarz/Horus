package maziarz.krystian.Horus;

public final class CustomBlock implements Block {
    private String color;
    private String material;

    public CustomBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "BlockClass[" +
                "color=" + color + ", " +
                "material=" + material + ']';
    }


}
