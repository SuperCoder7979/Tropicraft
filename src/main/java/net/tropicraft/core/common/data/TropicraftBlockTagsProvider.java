package net.tropicraft.core.common.data;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.tropicraft.Constants;
import net.tropicraft.core.common.TropicraftTags;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.tropicraft.core.common.block.TropicraftBlocks.*;
import static net.tropicraft.core.common.block.TropicraftFlower.*;

public class TropicraftBlockTagsProvider extends BlockTagsProvider {

    public TropicraftBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, Constants.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void registerTags() {
        // Filling vanilla tags
        
        createAndAppend(TropicraftTags.Blocks.SAND, BlockTags.SAND,
                PURIFIED_SAND, CORAL_SAND, FOAMY_SAND, MINERAL_SAND, VOLCANIC_SAND);

        createTag(TropicraftTags.Blocks.MUD, MUD, MUD_WITH_PIANGUAS);

        // Saplings & Leaves
        createAndAppend(TropicraftTags.Blocks.SAPLINGS, BlockTags.SAPLINGS,
                PALM_SAPLING, MAHOGANY_SAPLING, GRAPEFRUIT_SAPLING, LEMON_SAPLING, LIME_SAPLING, ORANGE_SAPLING,
                WHITE_MANGROVE_PROPAGULE, RED_MANGROVE_PROPAGULE
        );
        createAndAppend(TropicraftTags.Blocks.LEAVES, BlockTags.LEAVES,
                MAHOGANY_LEAVES, PALM_LEAVES, KAPOK_LEAVES, FRUIT_LEAVES, GRAPEFRUIT_LEAVES, LEMON_LEAVES, LIME_LEAVES, ORANGE_LEAVES,
                WHITE_MANGROVE_LEAVES, RED_MANGROVE_LEAVES);
        
        // Flowers
        createAndAppend(TropicraftTags.Blocks.SMALL_FLOWERS, BlockTags.SMALL_FLOWERS,
                ACAI_VINE, ANEMONE, BROMELIAD, CANNA, COMMELINA_DIFFUSA, CROCOSMIA, CROTON, DRACAENA, TROPICAL_FERN, FOLIAGE, MAGIC_MUSHROOM, ORANGE_ANTHURIUM, ORCHID, PATHOS, RED_ANTHURIUM);
        // Some extra flower tags for our own worldgen
        createTag(TropicraftTags.Blocks.TROPICS_FLOWERS,
                ACAI_VINE, ANEMONE, BROMELIAD, CANNA, COMMELINA_DIFFUSA, CROCOSMIA, CROTON, DRACAENA, TROPICAL_FERN, FOLIAGE, ORANGE_ANTHURIUM, ORCHID, PATHOS, RED_ANTHURIUM);
        createTag(TropicraftTags.Blocks.RAINFOREST_FLOWERS,
                MAGIC_MUSHROOM);
        createTag(TropicraftTags.Blocks.OVERWORLD_FLOWERS,
                ORCHID, PATHOS, RED_ANTHURIUM, COMMELINA_DIFFUSA, ANEMONE, ORANGE_ANTHURIUM);
        
        // Logs & Planks
        createAndAppend(TropicraftTags.Blocks.LOGS, BlockTags.LOGS,
                PALM_LOG, MAHOGANY_LOG, WHITE_MANGROVE_LOG, RED_MANGROVE_LOG, STRIPPED_MANGROVE_LOG);
        createAndAppend(TropicraftTags.Blocks.PLANKS, BlockTags.PLANKS,
                PALM_PLANKS, MAHOGANY_PLANKS);
        
        // Wooden deco blocks
        createAndAppend(TropicraftTags.Blocks.WOODEN_SLABS, BlockTags.WOODEN_SLABS,
                PALM_SLAB, MAHOGANY_SLAB, MANGROVE_SLAB);
        createAndAppend(TropicraftTags.Blocks.WOODEN_STAIRS, BlockTags.WOODEN_STAIRS,
                PALM_STAIRS, MAHOGANY_STAIRS, MANGROVE_STAIRS);
        createAndAppend(TropicraftTags.Blocks.WOODEN_DOORS, BlockTags.WOODEN_DOORS,
                PALM_DOOR, MAHOGANY_DOOR, MANGROVE_DOOR);
        createAndAppend(TropicraftTags.Blocks.WOODEN_TRAPDOORS, BlockTags.WOODEN_TRAPDOORS,
                PALM_TRAPDOOR, MAHOGANY_TRAPDOOR, MANGROVE_TRAPDOOR);
        createAndAppend(TropicraftTags.Blocks.WOODEN_FENCES, BlockTags.WOODEN_FENCES,
                PALM_FENCE, MAHOGANY_FENCE, MANGROVE_FENCE);
        
        // All deco blocks
        extendAndAppend(TropicraftTags.Blocks.SLABS, TropicraftTags.Blocks.WOODEN_SLABS, BlockTags.SLABS,
                BAMBOO_SLAB, CHUNK_SLAB, THATCH_SLAB);
        extendAndAppend(TropicraftTags.Blocks.STAIRS, TropicraftTags.Blocks.WOODEN_STAIRS, BlockTags.STAIRS,
                BAMBOO_STAIRS, CHUNK_STAIRS, THATCH_STAIRS);
        extendAndAppend(TropicraftTags.Blocks.DOORS, TropicraftTags.Blocks.WOODEN_DOORS, BlockTags.DOORS,
                BAMBOO_DOOR, THATCH_DOOR);
        extendAndAppend(TropicraftTags.Blocks.TRAPDOORS, TropicraftTags.Blocks.WOODEN_TRAPDOORS, BlockTags.TRAPDOORS,
                BAMBOO_TRAPDOOR, THATCH_TRAPDOOR);
        extendAndAppend(TropicraftTags.Blocks.FENCES, TropicraftTags.Blocks.WOODEN_FENCES, BlockTags.FENCES,
                BAMBOO_FENCE, CHUNK_FENCE, THATCH_FENCE);
        createAndAppend(TropicraftTags.Blocks.WALLS, BlockTags.WALLS,
                CHUNK_WALL);

        createAndAppend(TropicraftTags.Blocks.CLIMBABLE, BlockTags.CLIMBABLE, BAMBOO_LADDER);

        // Flower pots
        createAndAppend(TropicraftTags.Blocks.FLOWER_POTS, BlockTags.FLOWER_POTS,
                Stream.concat(BAMBOO_POTTED_TROPICS_PLANTS.stream(), Stream.concat(BAMBOO_POTTED_VANILLA_PLANTS.stream(), VANILLA_POTTED_TROPICS_PLANTS.stream()))
                        .toArray(RegistryObject[]::new));

        createTag(TropicraftTags.Blocks.BONGOS, SMALL_BONGO_DRUM, MEDIUM_BONGO_DRUM, LARGE_BONGO_DRUM);
    }
    
    @SafeVarargs
    private final <T> T[] resolveAll(IntFunction<T[]> creator, Supplier<? extends T>... suppliers) {
        return Arrays.stream(suppliers).map(Supplier::get).toArray(creator);
    }

    @SafeVarargs
    private final void createTag(INamedTag<Block> tag, Supplier<? extends Block>... blocks) {
        getOrCreateBuilder(tag).add(resolveAll(Block[]::new, blocks));
    }
    
    @SafeVarargs
    private final void appendToTag(INamedTag<Block> tag, INamedTag<Block>... toAppend) {
        getOrCreateBuilder(tag).addTags(toAppend);
    }
    
    @SafeVarargs
    private final void extendTag(INamedTag<Block> tag, INamedTag<Block> toExtend, Supplier<? extends Block>... blocks) {
        getOrCreateBuilder(tag).addTag(toExtend).add(resolveAll(Block[]::new, blocks));
    }
    
    @SafeVarargs
    private final void createAndAppend(INamedTag<Block> tag, INamedTag<Block> to, Supplier<? extends Block>... blocks) {
        createTag(tag, blocks);
        appendToTag(to, tag);
    }

    @SafeVarargs
    private final void extendAndAppend(INamedTag<Block> tag, INamedTag<Block> toExtend, INamedTag<Block> to, Supplier<? extends Block>... blocks) {
        extendTag(tag, toExtend, blocks);
        appendToTag(to, tag);
    }
    
    @Override
    public String getName() {
        return "Tropicraft Block Tags";
    }
}
