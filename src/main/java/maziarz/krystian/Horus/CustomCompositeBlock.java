package maziarz.krystian.Horus;

import java.util.List;

public final class CustomCompositeBlock implements CompositeBlock {
    private List<Block> blocks;

    public CustomCompositeBlock(List<Block> blocks) {
        this.blocks = blocks;
    }
    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "CompositeBlockClass[" +
                "blocks=" + blocks + ']';
    }

}
