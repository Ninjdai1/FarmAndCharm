package net.satisfy.farm_and_charm.registry;

import de.cristelknight.doapi.Util;
import de.cristelknight.doapi.common.block.FacingBlock;
import de.cristelknight.doapi.common.block.StackableEatableBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.satisfy.farm_and_charm.FarmAndCharm;
import net.satisfy.farm_and_charm.block.*;
import net.satisfy.farm_and_charm.block.crops.*;
import net.satisfy.farm_and_charm.item.*;
import net.satisfy.farm_and_charm.item.food.EffectBlockItem;
import net.satisfy.farm_and_charm.item.food.EffectItem;
import net.satisfy.farm_and_charm.util.FarmAndCharmIdentifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(FarmAndCharm.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final RegistrySupplier<Item> FERTILIZER = registerItem("fertilizer", () -> new FertilizerItem(getSettings()));
    public static final RegistrySupplier<Item> PITCHFORK = registerItem("pitchfork", () -> new HoeItem(Tiers.IRON, -1, -1.0F, new Item.Properties()));
    public static final RegistrySupplier<Item> SUPPLY_CART = registerItem("supply_cart", () -> new CartItem(getSettings()));
    public static final RegistrySupplier<Item> PLOW = registerItem("plow", () -> new CartItem(getSettings()));
    public static final RegistrySupplier<Item> YEAST = registerItem("yeast", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> BUTTER = registerItem("butter", () -> new Item(getSettings().food(Foods.CHICKEN)));
    public static final RegistrySupplier<Item> DOUGH = registerItem("dough", () -> new Item(getSettings().food(Foods.SWEET_BERRIES)));
    public static final RegistrySupplier<Item> RAW_PASTA = registerItem("raw_pasta", () -> new Item(getSettings().food(Foods.SWEET_BERRIES)));
    public static final RegistrySupplier<Item> FLOUR = registerItem("flour", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> MINCED_BEEF = registerItem("minced_beef", () -> new Item(getSettings().food(Foods.BEEF)));
    public static final RegistrySupplier<Item> LAMB_HAM = registerItem("lamb_ham", () -> new Item(getSettings().food(Foods.MUTTON)));
    public static final RegistrySupplier<Item> BACON = registerItem("bacon", () -> new Item(getSettings().food(Foods.PORKCHOP)));
    public static final RegistrySupplier<Item> CHICKEN_PARTS = registerItem("chicken_parts", () -> new Item(getSettings().food(Foods.CHICKEN)));
    public static final RegistrySupplier<Item> CORN = registerItem("corn", () -> new Item(getSettings().food(Foods.SWEET_BERRIES)));
    public static final RegistrySupplier<Item> BARLEY = registerItem("barley", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> OAT = registerItem("oat", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> STRAWBERRY = registerItem("strawberry", () -> new Item(getSettings().food(Foods.BEETROOT)));
    public static final RegistrySupplier<Item> LETTUCE = registerItem("lettuce", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> TOMATO = registerItem("tomato", () -> new Item(getSettings().food(Foods.APPLE)));
    public static final RegistrySupplier<Item> ROTTEN_TOMATO = registerItem("rotten_tomato", () -> new RottenTomatoItem(getSettings().food(Foods.POISONOUS_POTATO)));
    public static final RegistrySupplier<Item> FARMER_SALAD = registerItem("farmer_salad", () -> new EffectItem(getFoodItemSettings(7, 0.6f, MobEffectRegistry.SATIATION.get(), 4800), 4800));
    public static final RegistrySupplier<Item> GOULASH = registerItem("goulash", () -> new EffectItem(getFoodItemSettings(8, 0.9f, MobEffectRegistry.SATIATION.get(), 6000), 6000));
    public static final RegistrySupplier<Item> SIMPLE_TOMATO_SOUP = registerItem("simple_tomato_soup", () -> new EffectItem(getFoodItemSettings(6, 0.6f, MobEffectRegistry.RESTED.get(), 1800), 1800));
    public static final RegistrySupplier<Item> BARLEY_SOUP = registerItem("barley_soup", () -> new EffectItem(getFoodItemSettings(5, 0.8f, MobEffectRegistry.RESTED.get(), 3000), 3000));
    public static final RegistrySupplier<Item> ONION_SOUP = registerItem("onion_soup", () -> new EffectItem(getFoodItemSettings(7, 0.6f, MobEffectRegistry.RESTED.get(), 2400), 2400));
    public static final RegistrySupplier<Item> POTATO_SOUP = registerItem("potato_soup", () -> new EffectItem(getFoodItemSettings(5, 0.6f, MobEffectRegistry.RESTED.get(), 2400), 2400));
    public static final RegistrySupplier<Item> PASTA_WITH_ONION_SAUCE = registerItem("pasta_with_onion_sauce", () -> new EffectItem(getFoodItemSettings(6, 0.7f, MobEffectRegistry.SATIATION.get(), 3600), 3600));
    public static final RegistrySupplier<Item> CORN_GRITS = registerItem("corn_grits", () -> new EffectItem(getFoodItemSettings(6, 0.5f, MobEffectRegistry.SATIATION.get(), 2400), 2400));
    public static final RegistrySupplier<Item> OATMEAL_WITH_STRAWBERRIES = registerItem("oatmeal_with_strawberries", () -> new EffectItem(getFoodItemSettings(4, 0.8f, MobEffectRegistry.FARMERS_BLESSING.get(), 900), 6000));
    public static final RegistrySupplier<Item> SAUSAGE_WITH_OAT_PATTY = registerItem("sausage_with_oat_patty", () -> new EffectItem(getFoodItemSettings(8, 0.9f, MobEffectRegistry.SUSTENANCE.get(), 2400), 2400));
    public static final RegistrySupplier<Item> LAMB_WITH_CORN = registerItem("lamb_with_corn", () -> new EffectItem(getFoodItemSettings(8, 0.8f, MobEffectRegistry.SATIATION.get(), 3600), 3600));
    public static final RegistrySupplier<Item> BEEF_PATTY_WITH_VEGETABLES = registerItem("beef_patty_with_vegetables", () -> new EffectItem(getFoodItemSettings(6, 0.8f, MobEffectRegistry.SUSTENANCE.get(), 4800), 6000));
    public static final RegistrySupplier<Item> BARLEY_PATTIES_WITH_POTATOES = registerItem("barley_patties_with_potatoes", () -> new EffectItem(getFoodItemSettings(5, 0.9f, MobEffectRegistry.SATIATION.get(), 4800), 4800));
    public static final RegistrySupplier<Item> BACON_WITH_EGGS = registerItem("bacon_with_eggs", () -> new EffectItem(getFoodItemSettings(6, 0.7f, MobEffectRegistry.SUSTENANCE.get(), 3600), 3600));
    public static final RegistrySupplier<Item> CHICKEN_WRAPPED_IN_BACON = registerItem("chicken_wrapped_in_bacon", () -> new EffectItem(getFoodItemSettings(9, 0.9f, MobEffectRegistry.SUSTENANCE.get(), 6000), 6000));
    public static final RegistrySupplier<Item> COOKED_SALMON = registerItem("cooked_salmon", () -> new EffectItem(getFoodItemSettings(7, 0.9f, MobEffectRegistry.SATIATION.get(), 4800), 4800));
    public static final RegistrySupplier<Item> COOKED_COD = registerItem("cooked_cod", () -> new EffectItem(getFoodItemSettings(7, 0.9f, MobEffectRegistry.SUSTENANCE.get(), 4800), 4800));
    public static final RegistrySupplier<Item> ROASTED_CHICKEN = registerItem("roasted_chicken", () -> new EffectItem(getFoodItemSettings(5, 0.8f, MobEffectRegistry.SATIATION.get(), 4800), 4800));
    public static final RegistrySupplier<Item> STRAWBERRY_TEA_CUP = registerItem("strawberry_tea_cup", () -> new EffectItem(getFoodItemSettings(1, 0.05f, MobEffects.DIG_SPEED, 120), 120));
    public static final RegistrySupplier<Item> NETTLE_TEA_CUP = registerItem("nettle_tea_cup", () -> new EffectItem(getFoodItemSettings(1, 0.05f, MobEffects.HEAL, -0), -0));
    public static final RegistrySupplier<Item> RIBWORT_TEA_CUP = registerItem("ribwort_tea_cup", () -> new EffectItem(getFoodItemSettings(1, 0.05f, MobEffects.REGENERATION, 60), 60));
    public static final RegistrySupplier<Item> CAT_FOOD = registerItem("cat_food", () -> new CatFoodItem(getSettings()));
    public static final RegistrySupplier<Item> HORSE_FODDER = registerItem("horse_fodder", () -> new HorseFodderItem(getSettings()));
    public static final RegistrySupplier<Item> DOG_FOOD = registerItem("dog_food", () -> new DogFoodItem(getSettings()));
    public static final RegistrySupplier<Item> CHICKEN_FEED = registerItem("chicken_feed", () -> new ChickenFeedItem(getSettings()));
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(FarmAndCharm.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();
    public static final RegistrySupplier<Block> TOMATO_CROP = registerWithoutItem("tomato_crop", () -> new TomatoCropHeadBlock(getBushSettings().randomTicks()));
    public static final RegistrySupplier<Item> TOMATO_SEEDS = registerItem("tomato_seeds", () -> new ItemNameBlockItem(TOMATO_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> TOMATO_CROP_BODY = registerWithoutItem("tomato_crop_body", () -> new TomatoCropBodyBlock(getBushSettings().randomTicks()));
    public static final RegistrySupplier<Block> LETTUCE_CROP = registerWithoutItem("lettuce_crop", () -> new LettuceCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistrySupplier<Item> LETTUCE_SEEDS = registerItem("lettuce_seeds", () -> new ItemNameBlockItem(LETTUCE_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> STRAWBERRY_CROP = registerWithoutItem("strawberry_crop", () -> new StrawberryCropBlock(getBushSettings()));
    public static final RegistrySupplier<Item> STRAWBERRY_SEEDS = registerItem("strawberry_seeds", () -> new ItemNameBlockItem(STRAWBERRY_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> OAT_CROP = registerWithoutItem("oat_crop", () -> new OatCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistrySupplier<Item> OAT_SEEDS = registerItem("oat_seeds", () -> new ItemNameBlockItem(OAT_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> BARLEY_CROP = registerWithoutItem("barley_crop", () -> new BarleyCropBlock(getBushSettings()));
    public static final RegistrySupplier<Item> BARLEY_SEEDS = registerItem("barley_seeds", () -> new ItemNameBlockItem(BARLEY_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> CORN_CROP = registerWithoutItem("corn_crop", () -> new CornCropBlock(getBushSettings()));
    public static final RegistrySupplier<Item> KERNELS = registerItem("kernels", () -> new ItemNameBlockItem(CORN_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> ONION_CROP = registerWithoutItem("onion_crop", () -> new OnionCropBlock(getBushSettings()));
    public static final RegistrySupplier<Item> ONION = registerItem("onion", () -> new ItemNameBlockItem(ONION_CROP.get(), getSettings().food(Foods.SWEET_BERRIES)));
    public static final RegistrySupplier<Block> WILD_RIBWORT = registerWithItem("wild_ribwort", () -> new FlowerBlock(MobEffects.REGENERATION, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_NETTLE = registerWithItem("wild_nettle", () -> new FlowerBlock(MobEffects.DIG_SPEED, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_EMMER = registerWithItem("wild_emmer", () -> new FlowerBlock(MobEffects.JUMP, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_CORN = registerWithItem("wild_corn", () -> new TallFlowerBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> WILD_BARLEY = registerWithItem("wild_barley", () -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_OAT = registerWithItem("wild_oat", () -> new FlowerBlock(MobEffects.NIGHT_VISION, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_CARROTS = registerWithItem("wild_carrots", () -> new FlowerBlock(MobEffects.WATER_BREATHING, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_BEETROOTS = registerWithItem("wild_beetroots", () -> new FlowerBlock(MobEffects.INVISIBILITY, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_POTATOES = registerWithItem("wild_potatoes", () -> new FlowerBlock(MobEffects.POISON, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_TOMATOES = registerWithItem("wild_tomatoes", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WILD_LETTUCE = registerWithItem("wild_lettuce", () -> new FlowerBlock(MobEffects.LEVITATION, 1, BlockBehaviour.Properties.copy(Blocks.POPPY)));
    public static final RegistrySupplier<Block> WILD_ONIONS = registerWithItem("wild_onions", () -> new FlowerBlock(MobEffects.DAMAGE_BOOST, 1, BlockBehaviour.Properties.copy(Blocks.LILY_OF_THE_VALLEY)));
    public static final RegistrySupplier<Block> WILD_STRAWBERRIES = registerWithItem("wild_strawberries", () -> new FlowerBlock(MobEffects.LUCK, 1, BlockBehaviour.Properties.copy(Blocks.AZURE_BLUET)));
    public static final RegistrySupplier<Block> STRAWBERRY_BAG = registerWithItem("strawberry_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> CARROT_BAG = registerWithItem("carrot_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> POTATO_BAG = registerWithItem("potato_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> BEETROOT_BAG = registerWithItem("beetroot_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> LETTUCE_BAG = registerWithItem("lettuce_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> TOMATO_BAG = registerWithItem("tomato_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> CORN_BAG = registerWithItem("corn_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> ONION_BAG = registerWithItem("onion_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> FLOUR_BAG = registerWithItem("flour_bag", () -> new FacingBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> OAT_BALL = registerWithItem("oat_ball", () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistrySupplier<Block> BARLEY_BALL = registerWithItem("barley_ball", () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistrySupplier<Block> FERTILIZED_SOIL_BLOCK = registerWithItem("fertilized_soil", () -> new FertilizedSoilBlock(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT).strength(2.0F, 3.0F).sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> FERTILIZED_FARM_BLOCK = registerWithItem("fertilized_farmland", () -> new FertilizedFarmlandBlock(BlockBehaviour.Properties.of().randomTicks().strength(0.6F).sound(SoundType.GRAVEL)));
    public static final RegistrySupplier<Block> FEEDING_TROUGH = registerWithItem("feeding_trough", () -> new FeedingTroughBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> WATER_SPRINKLER = registerWithItem("water_sprinkler", () -> new WaterSprinklerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistrySupplier<Block> SILO_WOOD = registerWithItem("silo_wood", () -> new SiloBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion().pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> SILO_COPPER = registerWithItem("silo_copper", () -> new SiloBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).noOcclusion().pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> STOVE = registerWithItem("stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> MINCER = registerWithItem("mincer", () -> new MincerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).forceSolidOn()));
    public static final RegistrySupplier<Block> CRAFTING_BOWL = registerWithItem("crafting_bowl", () -> new CraftingBowlBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).instabreak().forceSolidOn()));
    public static final RegistrySupplier<Block> COOKING_POT = registerWithItem("cooking_pot", () -> new CookingPotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistrySupplier<Block> ROASTER = registerWithItem("roaster", () -> new RoasterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistrySupplier<Block> WINDOW_SILL = registerWithItem("window_sill", () -> new WindowSillBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noCollission()));
    public static final RegistrySupplier<Block> TOOL_RACK = registerWithItem("tool_rack", () -> new ToolRackBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noCollission()));
    public static final RegistrySupplier<Block> SCARECROW = registerWithItem("scarecrow", () -> new ScarecrowBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistrySupplier<Block> OAT_PANCAKE_BLOCK = registerWithoutItem("oat_pancake_block", () -> new StackableEatableBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), 7));
    public static final RegistrySupplier<Item> OAT_PANCAKE = registerItem("oat_pancake", () -> new EffectBlockItem(OAT_PANCAKE_BLOCK.get(), getFoodItemSettings(5, 0.6f, MobEffectRegistry.SATIATION.get(), 2400)));
    public static final RegistrySupplier<Block> ROASTED_CORN_BLOCK = registerWithoutItem("roasted_corn_block", () -> new StackableEatableBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), 4));
    public static final RegistrySupplier<Item> ROASTED_CORN = registerItem("roasted_corn", () -> new EffectBlockItem(ROASTED_CORN_BLOCK.get(), getFoodItemSettings(5, 0.5f, MobEffectRegistry.FEAST.get(), 3600)));
    public static final RegistrySupplier<Block> POTATO_WITH_ROAST_MEAT = registerWithoutItem("potato_with_roast_meat_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.SUSTENANCE.get(), 3600, 1), 7, 0.7f));
    public static final RegistrySupplier<Item> POTATO_WITH_ROAST_MEAT_ITEM = registerItem("potato_with_roast_meat", () -> new EffectBlockItem(POTATO_WITH_ROAST_MEAT.get(), getFoodItemSettings(7, 0.7f, MobEffectRegistry.SUSTENANCE.get(), 3600)));
    public static final RegistrySupplier<Block> BAKED_LAMB_HAM = registerWithoutItem("baked_lamb_ham_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.FEAST.get(), 4800, 1), 8, 0.9f));
    public static final RegistrySupplier<Item> BAKED_LAMB_HAM_ITEM = registerItem("baked_lamb_ham", () -> new EffectBlockItem(BAKED_LAMB_HAM.get(), getFoodItemSettings(8, 0.9f, MobEffectRegistry.FEAST.get(), 4800)));
    public static final RegistrySupplier<Block> FARMERS_BREAKFAST = registerWithoutItem("farmers_breakfast_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.FARMERS_BLESSING.get(), 9600, 2), 12, 1.2f));
    public static final RegistrySupplier<Item> FARMERS_BREAKFAST_ITEM = registerItem("farmers_breakfast", () -> new EffectBlockItem(FARMERS_BREAKFAST.get(), getFoodItemSettings(12, 1.2f, MobEffectRegistry.FARMERS_BLESSING.get(), 9600)));
    public static final RegistrySupplier<Block> STUFFED_CHICKEN = registerWithoutItem("stuffed_chicken_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.FEAST.get(), 9600, 2), 8, 0.8f));
    public static final RegistrySupplier<Item> STUFFED_CHICKEN_ITEM = registerItem("stuffed_chicken", () -> new EffectBlockItem(STUFFED_CHICKEN.get(), getFoodItemSettings(8, 0.8f, MobEffectRegistry.FEAST.get(), 9600)));
    public static final RegistrySupplier<Block> STUFFED_RABBIT = registerWithoutItem("stuffed_rabbit_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.FEAST.get(), 9600, 2), 9, 0.9f));
    public static final RegistrySupplier<Item> STUFFED_RABBIT_ITEM = registerItem("stuffed_rabbit", () -> new EffectBlockItem(STUFFED_RABBIT.get(), getFoodItemSettings(9, 0.9f, MobEffectRegistry.FEAST.get(), 9600)));
    public static final RegistrySupplier<Block> GRANDMOTHERS_STRAWBERRY_CAKE = registerWithoutItem("grandmothers_strawberry_cake_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.GRANDMAS_BLESSING.get(), 2400, 1), 4, 0.7f));
    public static final RegistrySupplier<Item> GRANDMOTHERS_STRAWBERRY_CAKE_ITEM = registerItem("grandmothers_strawberry_cake", () -> new EffectBlockItem(GRANDMOTHERS_STRAWBERRY_CAKE.get(), getFoodItemSettings(4, 0.7f, MobEffectRegistry.FEAST.get(), 2400)));
    public static final RegistrySupplier<Block> FARMERS_BREAD = registerWithoutItem("farmers_bread_block", () -> new FoodBlock(Block.Properties.of(), new MobEffectInstance(MobEffectRegistry.FEAST.get(), 3600, 1), 6, 0.8f));
    public static final RegistrySupplier<Item> FARMERS_BREAD_ITEM = registerItem("farmers_bread", () -> new EffectBlockItem(FARMERS_BREAD.get(), getFoodItemSettings(6, 0.8f, MobEffectRegistry.FARMERS_BLESSING.get(), 3600)));
    public static final RegistrySupplier<Block> STRAWBERRY_TEA = registerTea("strawberry_tea", () -> new TeaJugBlock(getTeaSettings()), MobEffects.DIG_SPEED, 240);
    public static final RegistrySupplier<Block> NETTLE_TEA = registerTea("nettle_tea", () -> new TeaJugBlock(getTeaSettings()), MobEffects.HEAL, -0);
    public static final RegistrySupplier<Block> RIBWORT_TEA = registerTea("ribwort_tea", () -> new TeaJugBlock(getTeaSettings()), MobEffects.REGENERATION, 120);

    public static void init() {
        FarmAndCharm.LOGGER.debug("Registering Mod Block and Items for " + FarmAndCharm.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
    }

    public static BlockBehaviour.Properties properties(float strength) {
        return properties(strength, strength);
    }

    public static BlockBehaviour.Properties properties(float breakSpeed, float explosionResist) {
        return BlockBehaviour.Properties.of().strength(breakSpeed, explosionResist);
    }


    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }

    private static BlockBehaviour.Properties getLogBlockSettings() {
        return BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties getSlabSettings() {
        return getLogBlockSettings().explosionResistance(3.0F);
    }

    private static BlockBehaviour.Properties getBushSettings() {
        return BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH);
    }

    private static Item.Properties getFoodItemSettings(int nutrition, float saturationMod, MobEffect effect, int duration) {
        return getFoodItemSettings(nutrition, saturationMod, effect, duration, true, false);
    }

    @SuppressWarnings("all")
    private static Item.Properties getFoodItemSettings(int nutrition, float saturationMod, MobEffect effect, int duration, boolean alwaysEat, boolean fast) {
        return getSettings().food(createFood(nutrition, saturationMod, effect, duration, alwaysEat, fast));
    }

    private static FoodProperties createFood(int nutrition, float saturationMod, MobEffect effect, int duration, boolean alwaysEat, boolean fast) {
        FoodProperties.Builder food = new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod);
        if (alwaysEat) food.alwaysEat();
        if (fast) food.fast();
        if (effect != null) food.effect(new MobEffectInstance(effect, duration), 1.0f);
        return food.build();
    }

    private static FoodProperties teaFoodComponent(MobEffect effect, int duration) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(1).saturationMod(1);
        if (effect != null) component.effect(new MobEffectInstance(effect, duration), 1.0f);
        return component.build();
    }


    private static BlockBehaviour.Properties getTeaSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak();
    }

    private static RegistrySupplier<Block> registerTea(String name, Supplier<Block> blockSupplier, MobEffect effect, int duration) {
        RegistrySupplier<Block> toReturn = registerWithoutItem(name, blockSupplier);
        registerItem(name, () -> new TeaJugItem(toReturn.get(), getSettings().food(teaFoodComponent(effect, duration))));
        return toReturn;
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return Util.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, new FarmAndCharmIdentifier(name), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return Util.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, new FarmAndCharmIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return Util.registerItem(ITEMS, ITEM_REGISTRAR, new FarmAndCharmIdentifier(path), itemSupplier);
    }
}
