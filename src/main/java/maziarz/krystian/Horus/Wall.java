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
        return blocks.stream()
                .map(block -> findBlockByColorAndCheckIfItsComposite(block, color))
                .filter(Optional::isPresent)
                .findAny()
                .orElse(Optional.empty());
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {

        List<Block> resultList = new ArrayList<>();

        blocks.forEach(block -> {
            if (isCompositeBlock(block)) {
                ((CompositeBlock) block)
                        .blocks()
                        .stream()
                        .filter(nestedBlock -> material.equals(nestedBlock.getMaterial()))
                        .forEach(resultList::add);
            } else if (material.equals(block.getMaterial())) {
                resultList.add(block);
            }
        });

        return resultList;

    }

    @Override
    public int count() {
        return blocks.stream()
                .map(block -> isCompositeBlock(block) ? ((CompositeBlock) block).blocks().size() : 1)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private Optional<Block> findBlockByColorAndCheckIfItsComposite(Block block, String color) {
        if (color.equals(block.getColor())) {
            return Optional.of(block);
        }
        if (isCompositeBlock(block)) {

            return ((CompositeBlock) block).blocks().stream()
                    .flatMap(nestedBlock -> findBlockByColorAndCheckIfItsComposite(nestedBlock, color).stream())
                    .findAny();
        }
        return Optional.empty();
    }

    private boolean isCompositeBlock(Block block) {
        return block instanceof CompositeBlock;
    }


}
