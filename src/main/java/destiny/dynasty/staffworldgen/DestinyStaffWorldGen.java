package destiny.dynasty.staffworldgen;
import me.isaiah.multiworld.command.CreateCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import java.util.Collections;
import java.util.Optional;
public final class DestinyStaffWorldGen implements ModInitializer {
    public static final Identifier ID = new Identifier("destiny", "staff_flat");
    @Override public void onInitialize() { ServerLifecycleEvents.SERVER_STARTED.register(this::register); }
    private void register(MinecraftServer server) {
        var biomes = server.getRegistryManager().get(RegistryKeys.BIOME);
        var plains = biomes.getEntry(biomes.getOrThrow(BiomeKeys.PLAINS));
        var config = new FlatChunkGeneratorConfig(Optional.empty(), plains, Collections.emptyList());
        config.getLayers().add(new net.minecraft.world.gen.chunk.FlatChunkGeneratorLayer(2, net.minecraft.block.Blocks.BEDROCK));
        config.getLayers().add(new net.minecraft.world.gen.chunk.FlatChunkGeneratorLayer(10, net.minecraft.block.Blocks.DIRT));
        config.getLayers().add(new net.minecraft.world.gen.chunk.FlatChunkGeneratorLayer(1, net.minecraft.block.Blocks.GRASS_BLOCK));
        config.updateLayerBlocks();
        CreateCommand.registerCustomGenerator(ID, new NoStructuresFlatGenerator(config));
        System.out.println("[DestinyStaffWorldGen] Registered " + ID);
    }
    private static final class NoStructuresFlatGenerator extends FlatChunkGenerator {
        NoStructuresFlatGenerator(FlatChunkGeneratorConfig config) { super(config); }
        @Override public int getMinimumY() { return 0; }
        @Override public int getSeaLevel() { return 0; }
        @Override public void generateFeatures(net.minecraft.world.StructureWorldAccess world, net.minecraft.world.chunk.Chunk chunk, net.minecraft.world.gen.StructureAccessor accessor) { }
    }
}
