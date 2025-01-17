package net.tropicraft.core.common.dimension.surfacebuilders;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.tropicraft.Constants;
import net.tropicraft.core.common.block.BlockTropicraftSand;
import net.tropicraft.core.common.block.TropicraftBlocks;
import net.tropicraft.core.common.data.WorldgenDataConsumer;

public final class TropicraftConfiguredSurfaceBuilders {
    private static final LazyValue<BlockState> PURIFIED_SAND = new LazyValue<>(() -> TropicraftBlocks.PURIFIED_SAND.get().getDefaultState());
    private static final LazyValue<BlockState> UNDERWATER_PURIFIED_SAND = new LazyValue<>(() -> PURIFIED_SAND.getValue().with(BlockTropicraftSand.UNDERWATER, true));

    public final ConfiguredSurfaceBuilder<?> tropics;
    public final ConfiguredSurfaceBuilder<?> sandy;

    public TropicraftConfiguredSurfaceBuilders(WorldgenDataConsumer<? extends ConfiguredSurfaceBuilder<?>> worldgen) {
        Register surfaceBuilders = new Register(worldgen);

        SurfaceBuilderConfig landConfig = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.STONE.getDefaultState());
        SurfaceBuilderConfig sandyConfig = new SurfaceBuilderConfig(PURIFIED_SAND.getValue(), PURIFIED_SAND.getValue(), UNDERWATER_PURIFIED_SAND.getValue());
        SurfaceBuilderConfig sandyUnderwaterConfig = new SurfaceBuilderConfig(UNDERWATER_PURIFIED_SAND.getValue(), UNDERWATER_PURIFIED_SAND.getValue(), UNDERWATER_PURIFIED_SAND.getValue());

        TropicsSurfaceBuilder.Config tropicsConfig = new TropicsSurfaceBuilder.Config(landConfig, sandyConfig, sandyUnderwaterConfig);

        this.tropics = surfaceBuilders.register("tropics", TropicraftSurfaceBuilders.TROPICS, tropicsConfig);
        this.sandy = surfaceBuilders.register("sandy", TropicraftSurfaceBuilders.UNDERWATER,
                new UnderwaterSurfaceBuilder.Config(sandyConfig, landConfig, sandyUnderwaterConfig)
        );
    }

    static final class Register {
        private final WorldgenDataConsumer<ConfiguredSurfaceBuilder<?>> worldgen;

        @SuppressWarnings("unchecked")
		Register(WorldgenDataConsumer<? extends ConfiguredSurfaceBuilder<?>> worldgen) {
            this.worldgen = (WorldgenDataConsumer<ConfiguredSurfaceBuilder<?>>) worldgen;
        }

        public <C extends ISurfaceBuilderConfig, S extends SurfaceBuilder<C>> ConfiguredSurfaceBuilder<?> register(String id, RegistryObject<S> surfaceBuilder, C config) {
            return this.worldgen.register(new ResourceLocation(Constants.MODID, id), surfaceBuilder.get().func_242929_a(config));
        }
    }
}
