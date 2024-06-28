package com.farestr06.slip.datagen;

import com.farestr06.slip.SlipMod;
import com.farestr06.slip.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(((RotatedPillarBlock) ModBlocks.KAO_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.KAO_WOOD.get()), blockTexture(ModBlocks.KAO_LOG.get()), blockTexture(ModBlocks.KAO_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_KAO_LOG.get()), blockTexture(ModBlocks.STRIPPED_KAO_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(SlipMod.MOD_ID, "block/stripped_kao_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_KAO_WOOD.get()), blockTexture(ModBlocks.STRIPPED_KAO_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_KAO_LOG.get()));

        blockItem(ModBlocks.KAO_LOG);
        blockItem(ModBlocks.KAO_WOOD);
        blockItem(ModBlocks.STRIPPED_KAO_LOG);
        blockItem(ModBlocks.STRIPPED_KAO_WOOD);

        blockWithItem(ModBlocks.KAO_PLANKS);

        leavesBlock(ModBlocks.KAO_LEAVES);

    }

    private void blockItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(SlipMod.MOD_ID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath()));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.withDefaultNamespace("block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
