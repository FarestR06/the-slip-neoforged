package com.farestr06.slip.block;

import com.farestr06.slip.SlipMod;
import com.farestr06.slip.block.custom.SlipLogWoodBlock;
import com.farestr06.slip.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SlipMod.MOD_ID);

    public static final DeferredBlock<Block> KAO_LOG = registerBlock("kao_log",
            SlipLogWoodBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(DyeColor.PURPLE).strength(4f));
    public static final DeferredBlock<Block> KAO_WOOD = registerBlock("kao_wood",
            SlipLogWoodBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(DyeColor.PURPLE).strength(4f));
    public static final DeferredBlock<Block> STRIPPED_KAO_LOG = registerBlock("stripped_kao_log",
            SlipLogWoodBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).mapColor(DyeColor.PURPLE).strength(4f));
    public static final DeferredBlock<Block> STRIPPED_KAO_WOOD = registerBlock("stripped_kao_wood",
            SlipLogWoodBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).mapColor(DyeColor.PURPLE).strength(4f));

    public static final DeferredBlock<Block> KAO_PLANKS = registerBlock("kao_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(DyeColor.PURPLE).strength(4f, 6f)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 10;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 2;
                }
            });

    public static final DeferredBlock<Block> KAO_LEAVES = registerBlock("kao_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(DyeColor.PURPLE).strength(0.4f)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 15;
                }
            });



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties,
            ? extends T> factory, BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, factory, properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> supplier) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, supplier);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
