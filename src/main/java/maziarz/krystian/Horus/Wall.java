package maziarz.krystian.Horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            Optional<Block> result = findBlockByColor(block, color);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    private Optional<Block> findBlockByColor(Block block, String color) {
        if (color.equals(block.getColor())) {
            return Optional.of(block);
        }
        if (isCompositeBlock(block)) {
            for (Block nestedBlock : ((CompositeBlock)block).blocks()) {
                Optional<Block> result = findBlockByColor(nestedBlock, color);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {

        List<Block> resultList = new ArrayList<>();

        blocks.forEach(block -> {
            if (isCompositeBlock(block)) {
                ((CompositeBlock) block).blocks().stream().filter(nestedBlock -> material.equals(nestedBlock.getMaterial())).forEach(resultList::add);
            } else if (material.equals(block.getMaterial())) {
                resultList.add(block);
            }
        });

        return resultList;

    }


    @Override
    public int count() {
        int result = 0;
        for (Block block : blocks) {
            if (isCompositeBlock(block)){
                result+= ((CompositeBlock)block).blocks().size()-1;
                }
                result++;
            }
        return result;
    }

    private static boolean isCompositeBlock(Block block) {
        return block instanceof CompositeBlock;
    }


}
