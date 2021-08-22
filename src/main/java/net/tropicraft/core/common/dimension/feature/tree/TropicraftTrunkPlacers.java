package net.tropicraft.core.common.dimension.feature.tree;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.tropicraft.Constants;
import net.tropicraft.core.common.dimension.feature.tree.mangrove.MangroveTrunkPlacer;
import net.tropicraft.core.common.dimension.feature.tree.mangrove.SmallMangroveTrunkPlacer;

public final class TropicraftTrunkPlacers {
    public static final TrunkPlacerType<MangroveTrunkPlacer> MANGROVE = register("mangrove", MangroveTrunkPlacer.CODEC);
    public static final TrunkPlacerType<SmallMangroveTrunkPlacer> SMALL_MANGROVE = register("small_mangrove", SmallMangroveTrunkPlacer.CODEC);
    public static final TrunkPlacerType<CitrusTrunkPlacer> CITRUS = register("citrus", CitrusTrunkPlacer.CODEC);
    public static final TrunkPlacerType<PleodendronTrunkPlacer> PLEODENDRON = register("pleodendron", PleodendronTrunkPlacer.CODEC);

    private static <T extends AbstractTrunkPlacer> TrunkPlacerType<T> register(String name, Codec<T> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, new ResourceLocation(Constants.MODID, name), new TrunkPlacerType<>(codec));
    }
}
