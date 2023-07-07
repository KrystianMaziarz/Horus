package maziarz.krystian.Horus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


class WallTest {



    @Test
    void shouldReturnBlockWithExistingColor () {
        //given
        BlockClass blueBlock = getBlock("Blue", "BlueMaterial");
        BlockClass redBlock = getBlock("Red", "RedMaterial");
        CompositeBlockClass compositeBlock = new CompositeBlockClass(List.of(getBlock("Green", "GreenMaterial"),
                getBlock("Purple", "PurpleMaterial")));
        List<Block> blocks = List.of(blueBlock, redBlock, compositeBlock);
        Wall wall = new Wall(blocks);
        //when
        Optional<Block> blueResult = wall.findBlockByColor("Blue");
        Optional<Block> redResult = wall.findBlockByColor("Red");
        Optional<Block> greenResult = wall.findBlockByColor("Green");
        //then
        Assertions.assertTrue(blueResult.isPresent());
        Assertions.assertEquals(blueBlock, blueResult.get());
        Assertions.assertTrue(redResult.isPresent());
        Assertions.assertEquals(redBlock, redResult.get());
        Assertions.assertTrue(greenResult.isPresent());
        Assertions.assertEquals(compositeBlock.blocks().get(0), greenResult.get());
    }
    @Test
    void shouldReturnEmptyOptionalWhenColorNotExists() {
        //give
        Wall wall = new Wall (Collections.emptyList());
        //when
        Optional<Block> whiteResult = wall.findBlockByColor("White");
        //then
        Assertions.assertFalse(whiteResult.isPresent());
        Assertions.assertEquals(Optional.empty(), whiteResult);

    }

    @Test
    void shouldReturnListOfBlocksWhenMaterialExists () {
        //given
        BlockClass blueBlock = getBlock("Blue", "BlueMaterial");
        BlockClass redBlock = getBlock("SecondBlue", "BlueMaterial");
        CompositeBlockClass compositeBlock = new CompositeBlockClass(List.of(getBlock("ThirdBlue", "BlueMaterial"),
                getBlock("Purple", "PurpleMaterial")));
        List<Block> blocks = List.of(blueBlock, redBlock, compositeBlock);
        Wall wall = new Wall(blocks);
        //when
        List<Block> blueResult = wall.findBlocksByMaterial("BlueMaterial");
        //then
        Assertions.assertEquals(3,blueResult.size());
    }

    @Test
    void shouldReturnEmptyListWhenMaterialNotExists() {
        //given
        Wall wall = new Wall(Collections.emptyList());
        //when
        List<Block> whiteResult = wall.findBlocksByMaterial("WhiteMaterial");
        //then
        Assertions.assertEquals(0, whiteResult.size());
        Assertions.assertTrue(whiteResult.isEmpty());

    }

    @Test
    void shouldReturnValidCountOfBlocks() {
        //given
        BlockClass firstBlock = getBlock("white", "whiteMaterial");
        BlockClass secondBlock = getBlock("blue", "blueMaterial");
        BlockClass thirdBlock = getBlock("green", "greenMaterial");
        CompositeBlockClass compositeBlockWithTwoBlocks = new CompositeBlockClass(List.of(getBlock("blue", "BlueMaterial"),getBlock("black", "BlackMaterial")));
        Wall wall = new Wall(List.of(firstBlock,secondBlock,thirdBlock,compositeBlockWithTwoBlocks));
        //when
        int result = wall.count();
        //then
        Assertions.assertEquals(5, result);

    }

    @Test
    void shouldReturnValidCountOfBlocksWhenCompositeBlocksAreEmpty() {
        //given
        BlockClass firstBlock = getBlock("white", "whiteMaterial");
        BlockClass secondBlock = getBlock("blue", "blueMaterial");
        BlockClass thirdBlock = getBlock("green", "greenMaterial");
        CompositeBlock compositeBlockWithEmptyListOfBlocks = new CompositeBlockClass(List.of());
        CompositeBlock secondCompositeBlockWithEmptyListOfBlocks = new CompositeBlockClass(List.of());
        Wall wall = new Wall(List.of(firstBlock,secondBlock,thirdBlock,compositeBlockWithEmptyListOfBlocks,secondCompositeBlockWithEmptyListOfBlocks));
        //when
        int result = wall.count();
        //then
        Assertions.assertEquals(3, result);

    }

    private BlockClass getBlock(String color, String material) {

        return new BlockClass(color, material);
    }

}