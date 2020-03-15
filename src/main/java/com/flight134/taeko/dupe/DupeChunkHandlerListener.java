/*

   EventHandler that scans chunks for chests and checks if they contain valuables.

*/

package com.flight134.taeko.dupe;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public final class DupeChunkHandlerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void searchChests(ChunkLoadEvent load) {
        Chunk chunk = load.getChunk();
        Dupe.searchedChunks += 1;

        for (BlockState b : chunk.getTileEntities()) {

            BlockState currentBlock = b.getBlock().getState();

            if (b.getType().equals(Material.CHEST)) {

                Chest currentChest = (Chest)currentBlock;

                for (Material material : Valuables.valuables) {
                    if (currentChest.getInventory().contains(material)) {
                        String fullLocation = "X: " + currentBlock.getX() + ", Y: " +
                                currentBlock.getY() + ", Z: " + currentBlock.getZ();
                        if (!Dupe.locations.contains(fullLocation)) {
                            Dupe.locations.add(fullLocation);
                            Dupe.chunksHaveValuables += 1;
                        }
                        break;
                    }
                }
            }
        }
    }
}
