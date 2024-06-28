package com.farestr06.slip.item;

import com.farestr06.slip.SlipMod;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SlipMod.MOD_ID);

    public static final DeferredItem<Item> ELPPA = ITEMS.register(
            "elppa",
            () -> new Item(new Item.Properties().food(Foods.APPLE))
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
