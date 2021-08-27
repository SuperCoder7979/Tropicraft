package net.tropicraft.core.common.dimension.feature.tree.mangrove;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.tropicraft.core.common.Util;
import net.tropicraft.core.common.dimension.feature.tree.TropicraftTreeDecorators;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.minecraft.world.gen.feature.TreeFeature.isReplaceableAt;

public class PneumatophoresTreeDecorator extends TreeDecorator {
    public static final Codec<PneumatophoresTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registry.BLOCK.fieldOf("roots_block").forGetter(c -> c.rootsBlock),
            Codec.INT.fieldOf("min_count").forGetter(c -> c.minCount),
            Codec.INT.fieldOf("max_count").forGetter(c -> c.maxCount),
            Codec.INT.fieldOf("spread").forGetter(c -> c.spread)
    ).apply(instance, PneumatophoresTreeDecorator::new));

    private final Block rootsBlock;
    private final int minCount;
    private final int maxCount;
    private final int spread;

    public PneumatophoresTreeDecorator(Block rootsBlock, int minCount, int maxCount, int spread) {
        this.rootsBlock = rootsBlock;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.spread = spread;
    }

    @Override
    protected TreeDecoratorType<?> getDecoratorType() {
        return TropicraftTreeDecorators.PNEUMATOPHORES.get();
    }

    @Override
    public void func_225576_a_(ISeedReader world, Random random, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> placed, MutableBoundingBox box) {
        BlockPos origin = Util.findLowestBlock(logs);
        if (origin == null) return;

        int spread = this.spread;
        int count = random.nextInt(this.maxCount - this.minCount + 1) + this.minCount;
        int maxTopY = origin.getY() + 3;
        int minBottomY = origin.getY() - 6;

        BlockPos.Mutable mutablePos = origin.toMutable();
        while (MangroveTrunkPlacer.isWaterAt(world, mutablePos) && mutablePos.getY() < maxTopY) {
            mutablePos.move(Direction.UP);
        }

        int topY = mutablePos.getY();

        for (int i = 0; i < count; i++) {
            int dx = random.nextInt(spread) - random.nextInt(spread);
            int dz = random.nextInt(spread) - random.nextInt(spread);
            if (dx == 0 && dz == 0) continue;
            mutablePos.setAndOffset(origin, dx, 0, dz);

            // Don't generate pneumatophores if there isn't a solid block in the column to attach onto
            boolean canGenerate = false;
            int minY = minBottomY;
            for (int y = topY; y >= minBottomY; y--) {
                mutablePos.setY(y);

                if (!isReplaceableAt(world, mutablePos)) {
                    canGenerate = true;
                    minY = y;
                    break;
                }
            }

            if (!canGenerate) {
                continue;
            }

            int y = topY;
            while (y >= minY) {
                mutablePos.setY(y--);
                MangroveTrunkPlacer.setRootsAt(world, mutablePos, this.rootsBlock);
            }
        }
    }
}
