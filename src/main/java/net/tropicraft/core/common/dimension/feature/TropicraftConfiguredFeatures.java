package net.tropicraft.core.common.dimension.feature;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.RegistryObject;
import net.tropicraft.Constants;
import net.tropicraft.core.common.TropicraftTags;
import net.tropicraft.core.common.block.TropicraftBlocks;
import net.tropicraft.core.common.data.WorldgenDataConsumer;
import net.tropicraft.core.common.dimension.feature.block_state_provider.NoiseFromTagBlockStateProvider;
import net.tropicraft.core.common.dimension.feature.config.FruitTreeConfig;
import net.tropicraft.core.common.dimension.feature.config.HomeTreeBranchConfig;
import net.tropicraft.core.common.dimension.feature.config.RainforestVinesConfig;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class TropicraftConfiguredFeatures {
    public final ConfiguredFeature<?, ?> grapefruitTree;
    public final ConfiguredFeature<?, ?> orangeTree;
    public final ConfiguredFeature<?, ?> lemonTree;
    public final ConfiguredFeature<?, ?> limeTree;
    public final ConfiguredFeature<?, ?> normalPalmTree;
    public final ConfiguredFeature<?, ?> curvedPalmTree;
    public final ConfiguredFeature<?, ?> largePalmTree;
    public final ConfiguredFeature<?, ?> rainforestUpTree;
    public final ConfiguredFeature<?, ?> rainforestSmallTualung;
    public final ConfiguredFeature<?, ?> rainforestLargeTualung;
    public final ConfiguredFeature<?, ?> rainforestTallTree;
    public final ConfiguredFeature<?, ?> rainforestVines;
    public final ConfiguredFeature<?, ?> eih;

    public final ConfiguredFeature<?, ?> pineapplePatch;
    public final ConfiguredFeature<?, ?> tropicsFlowers;
    public final ConfiguredFeature<?, ?> rainforestFlowers;
    public final ConfiguredFeature<?, ?> irisFlowers;

    public final ConfiguredFeature<?, ?> coffeeBush;
    public final ConfiguredFeature<?, ?> undergrowth;

    public final ConfiguredFeature<?, ?> undergroundSeagrassOnStone;
    public final ConfiguredFeature<?, ?> undergroundSeagrassOnDirt;
    public final ConfiguredFeature<?, ?> undergroundSeaPickles;

    public final ConfiguredFeature<?, ?> azurite;
    public final ConfiguredFeature<?, ?> eudialyte;
    public final ConfiguredFeature<?, ?> zircon;
    public final ConfiguredFeature<?, ?> manganese;
    public final ConfiguredFeature<?, ?> shaka;

    public final ConfiguredFeature<?, ?> homeTreeBranchSouth;
    public final ConfiguredFeature<?, ?> homeTreeBranchSouthExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchSouthEast;
    public final ConfiguredFeature<?, ?> homeTreeBranchSouthEastExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchEast;
    public final ConfiguredFeature<?, ?> homeTreeBranchEastExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchNorthEast;
    public final ConfiguredFeature<?, ?> homeTreeBranchNorthEastExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchNorth;
    public final ConfiguredFeature<?, ?> homeTreeBranchNorthExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchNorthWest;
    public final ConfiguredFeature<?, ?> homeTreeBranchNorthWestExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchWest;
    public final ConfiguredFeature<?, ?> homeTreeBranchWestExact;
    public final ConfiguredFeature<?, ?> homeTreeBranchSouthWest;
    public final ConfiguredFeature<?, ?> homeTreeBranchSouthWestExact;

    public TropicraftConfiguredFeatures(WorldgenDataConsumer<? extends ConfiguredFeature<?, ?>> worldgen) {
        Register features = new Register(worldgen);

        this.grapefruitTree = features.fruitTree("grapefruit_tree", TropicraftBlocks.GRAPEFRUIT_SAPLING, TropicraftBlocks.GRAPEFRUIT_LEAVES);
        this.orangeTree = features.fruitTree("orange_tree", TropicraftBlocks.ORANGE_SAPLING, TropicraftBlocks.ORANGE_LEAVES);
        this.lemonTree = features.fruitTree("lemon_tree", TropicraftBlocks.LEMON_SAPLING, TropicraftBlocks.LEMON_LEAVES);
        this.limeTree = features.fruitTree("lime_tree", TropicraftBlocks.LIME_SAPLING, TropicraftBlocks.LIME_LEAVES);

        this.normalPalmTree = features.sparseTree("normal_palm_tree", TropicraftFeatures.NORMAL_PALM_TREE, IFeatureConfig.NO_FEATURE_CONFIG, 0.2F);
        this.curvedPalmTree = features.sparseTree("curved_palm_tree", TropicraftFeatures.CURVED_PALM_TREE, IFeatureConfig.NO_FEATURE_CONFIG, 0.2F);
        this.largePalmTree = features.sparseTree("large_palm_tree", TropicraftFeatures.LARGE_PALM_TREE, IFeatureConfig.NO_FEATURE_CONFIG, 0.2F);

        this.rainforestUpTree = features.sparseTree("rainforest_up_tree", TropicraftFeatures.UP_TREE, IFeatureConfig.NO_FEATURE_CONFIG, 0.2F);
        this.rainforestSmallTualung = features.sparseTree("rainforest_small_tualung", TropicraftFeatures.SMALL_TUALUNG, IFeatureConfig.NO_FEATURE_CONFIG, 0.3F);
        this.rainforestLargeTualung = features.sparseTree("rainforest_large_tualung", TropicraftFeatures.LARGE_TUALUNG, IFeatureConfig.NO_FEATURE_CONFIG, 0.4F);
        this.rainforestTallTree = features.sparseTree("rainforest_tall_tree", TropicraftFeatures.TALL_TREE, IFeatureConfig.NO_FEATURE_CONFIG, 0.5F);
        this.rainforestVines = features.register("rainforest_vines", TropicraftFeatures.VINES,
                f -> f.withConfiguration(new RainforestVinesConfig()).square().count(50)
        );

        this.eih = features.noConfig("eih", TropicraftFeatures.EIH,
                f -> f.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                        .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.01F, 1)))
        );

        this.pineapplePatch = features.register("pineapple_patch", Feature.RANDOM_PATCH, feature -> {
            SimpleBlockStateProvider state = new SimpleBlockStateProvider(TropicraftBlocks.PINEAPPLE.get().getDefaultState());
            return feature.withConfiguration(new BlockClusterFeatureConfig.Builder(state, new DoublePlantBlockPlacer())
                    .tries(64)
                    .preventProjection()
                    .build()
            ).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
        });
        this.tropicsFlowers = features.register("tropics_flowers", Feature.FLOWER, feature -> {
            BlockStateProvider stateProvider = new NoiseFromTagBlockStateProvider(TropicraftTags.Blocks.TROPICS_FLOWERS);
            BlockClusterFeatureConfig config = new BlockClusterFeatureConfig.Builder(stateProvider, SimpleBlockPlacer.PLACER).tries(64).build();
            return feature.withConfiguration(config).withPlacement(Features.Placements.VEGETATION_PLACEMENT.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(12));
        });
        this.rainforestFlowers = features.register("rainforest_flowers", Feature.FLOWER, feature -> {
            BlockStateProvider stateProvider = new NoiseFromTagBlockStateProvider(TropicraftTags.Blocks.RAINFOREST_FLOWERS);
            BlockClusterFeatureConfig config = new BlockClusterFeatureConfig.Builder(stateProvider, SimpleBlockPlacer.PLACER).tries(64).preventProjection().build();
            return feature.withConfiguration(config).withPlacement(Features.Placements.VEGETATION_PLACEMENT.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(4));
        });
        this.irisFlowers = features.register("iris_flowers", Feature.RANDOM_PATCH, feature -> {
            BlockStateProvider stateProvider = new SimpleBlockStateProvider(TropicraftBlocks.IRIS.get().getDefaultState());
            BlockClusterFeatureConfig config = new BlockClusterFeatureConfig.Builder(stateProvider, new DoublePlantBlockPlacer()).tries(64).preventProjection().build();
            return feature.withConfiguration(config).withPlacement(Features.Placements.VEGETATION_PLACEMENT.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(10));
        });

        this.coffeeBush = features.noConfig("coffee_bush", TropicraftFeatures.COFFEE_BUSH, feature -> {
            return feature.withPlacement(Features.Placements.VEGETATION_PLACEMENT.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(5));
        });
        this.undergrowth = features.noConfig("undergrowth", TropicraftFeatures.UNDERGROWTH, feature -> {
            return feature.withPlacement(Features.Placements.VEGETATION_PLACEMENT.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(100));
        });

        this.undergroundSeagrassOnStone = features.register("underground_seagrass_on_stone", Feature.SIMPLE_BLOCK, feature -> {
            BlockWithContextConfig config = new BlockWithContextConfig(
                    Blocks.SEAGRASS.getDefaultState(),
                    ImmutableList.of(Blocks.STONE.getDefaultState()),
                    ImmutableList.of(Blocks.WATER.getDefaultState()),
                    ImmutableList.of(Blocks.WATER.getDefaultState())
            );
            return feature.withConfiguration(config).withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F)));
        });
        this.undergroundSeagrassOnDirt = features.register("underground_seagrass_on_dirt", Feature.SIMPLE_BLOCK, feature -> {
            BlockWithContextConfig config = new BlockWithContextConfig(
                    Blocks.SEAGRASS.getDefaultState(),
                    ImmutableList.of(Blocks.DIRT.getDefaultState()),
                    ImmutableList.of(Blocks.WATER.getDefaultState()),
                    ImmutableList.of(Blocks.WATER.getDefaultState())
            );
            return feature.withConfiguration(config).withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.5F)));
        });
        this.undergroundSeaPickles = features.noConfig("underground_sea_pickles", TropicraftFeatures.UNDERGROUND_SEA_PICKLE, feature -> {
            return feature.withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.05F)));
        });

        this.azurite = features.register("azurite", Feature.ORE, f -> {
            return f.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TropicraftBlocks.AZURITE_ORE.get().getDefaultState(), 8))
                    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(100, 0, 128)))
                    .square().count(3);
        });
        this.eudialyte = features.register("eudialyte", Feature.ORE, f -> {
            return f.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TropicraftBlocks.EUDIALYTE_ORE.get().getDefaultState(), 12))
                    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(100, 0, 128)))
                    .square().count(10);
        });
        this.zircon = features.register("zircon", Feature.ORE, f -> {
            return f.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TropicraftBlocks.ZIRCON_ORE.get().getDefaultState(), 14))
                    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(100, 0, 128)))
                    .square().count(15);
        });
        this.manganese = features.register("manganese", Feature.ORE, f -> {
            return f.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TropicraftBlocks.MANGANESE_ORE.get().getDefaultState(), 10))
                    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(32, 0, 32)))
                    .square().count(8);
        });
        this.shaka = features.register("shaka", Feature.ORE, f -> {
            return f.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TropicraftBlocks.SHAKA_ORE.get().getDefaultState(), 8))
                    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 32)))
                    .square().count(6);
        });

        // 0 = south
        // 90 = east
        // 180 = north
        // 270 = west
        this.homeTreeBranchSouth = features.homeTreeBranch("home_tree/branch/south", -30, 30);
        this.homeTreeBranchSouthExact = features.homeTreeBranch("home_tree/branch/south_exact", 0, 0);
        this.homeTreeBranchSouthEast = features.homeTreeBranch("home_tree/branch/southeast", 30, 60);
        this.homeTreeBranchSouthEastExact = features.homeTreeBranch("home_tree/branch/southeast_exact", 45, 45);
        this.homeTreeBranchEast = features.homeTreeBranch("home_tree/branch/east", 60, 120);
        this.homeTreeBranchEastExact = features.homeTreeBranch("home_tree/branch/east_exact", 90, 90);
        this.homeTreeBranchNorthEast = features.homeTreeBranch("home_tree/branch/northeast", 120, 150);
        this.homeTreeBranchNorthEastExact = features.homeTreeBranch("home_tree/branch/northeast_exact", 135, 135);
        this.homeTreeBranchNorth = features.homeTreeBranch("home_tree/branch/north", 150, 210);
        this.homeTreeBranchNorthExact = features.homeTreeBranch("home_tree/branch/north_exact", 180, 180);
        this.homeTreeBranchNorthWest = features.homeTreeBranch("home_tree/branch/northwest", 210, 240);
        this.homeTreeBranchNorthWestExact = features.homeTreeBranch("home_tree/branch/northwest_exact", 225, 225);
        this.homeTreeBranchWest = features.homeTreeBranch("home_tree/branch/west", 240, 300);
        this.homeTreeBranchWestExact = features.homeTreeBranch("home_tree/branch/west_exact", 270, 270);
        this.homeTreeBranchSouthWest = features.homeTreeBranch("home_tree/branch/southwest", 300, 330);
        this.homeTreeBranchSouthWestExact = features.homeTreeBranch("home_tree/branch/southwest_exact", 315, 315);
    }

    public void addFruitTrees(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.grapefruitTree);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.orangeTree);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.lemonTree);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.limeTree);
    }

    public void addPalmTrees(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.normalPalmTree);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.curvedPalmTree);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.largePalmTree);
    }

    public void addRainforestTrees(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.rainforestUpTree);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.rainforestSmallTualung);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.rainforestLargeTualung);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.rainforestTallTree);
    }

    public void addRainforestPlants(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_MELON);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.rainforestVines);
    }

    public void addPineapples(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.pineapplePatch);
    }

    public void addEih(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.eih);
    }

    public void addTropicsFlowers(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.tropicsFlowers);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.irisFlowers);
    }

    public void addTropicsGems(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.azurite);
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.eudialyte);
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.zircon);
    }

    public void addTropicsMetals(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.manganese);
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.shaka);
    }

    public void addUndergroundSeagrass(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.undergroundSeagrassOnStone);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.undergroundSeagrassOnDirt);
    }

    public void addUndergroundPickles(BiomeGenerationSettings.Builder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, this.undergroundSeaPickles);
    }

    static final class Register {
        private final WorldgenDataConsumer<ConfiguredFeature<?, ?>> worldgen;

        @SuppressWarnings("unchecked")
		Register(WorldgenDataConsumer<? extends ConfiguredFeature<?, ?>> worldgen) {
            this.worldgen = (WorldgenDataConsumer<ConfiguredFeature<?, ?>>) worldgen;
        }

        public <F extends Feature<?>> ConfiguredFeature<?, ?> register(String id, F feature, Function<F, ConfiguredFeature<?, ?>> configure) {
            return this.worldgen.register(new ResourceLocation(Constants.MODID, id), configure.apply(feature));
        }

        public <F extends Feature<?>> ConfiguredFeature<?, ?> register(String id, RegistryObject<F> feature, Function<F, ConfiguredFeature<?, ?>> configure) {
            return this.register(id, feature.get(), configure);
        }

        public <F extends Feature<NoFeatureConfig>> ConfiguredFeature<?, ?> noConfig(String id, RegistryObject<F> feature, UnaryOperator<ConfiguredFeature<?, ?>> configure) {
            return this.register(id, feature, f -> configure.apply(f.withConfiguration(NoFeatureConfig.INSTANCE)));
        }

        public ConfiguredFeature<?, ?> fruitTree(String id, Supplier<? extends Block> sapling, Supplier<? extends Block> fruitLeaves) {
            return this.sparseTree(id, TropicraftFeatures.FRUIT_TREE, new FruitTreeConfig(sapling, fruitLeaves), 0.2F);
        }

        public <C extends IFeatureConfig, F extends Feature<C>> ConfiguredFeature<?, ?> sparseTree(String id, RegistryObject<F> feature, C config, float chance) {
            return this.register(id, feature, f -> {
                return f.withConfiguration(config).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                        .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, chance, 1)));
            });
        }

        public <C extends IFeatureConfig, F extends Feature<C>> ConfiguredFeature<?, ?> homeTreeBranch(String id, float minAngle, float maxAngle) {
            return this.register(id, TropicraftFeatures.HOME_TREE_BRANCH,
                    f -> f.withConfiguration(new HomeTreeBranchConfig(minAngle, maxAngle))
            );
        }
    }
}
