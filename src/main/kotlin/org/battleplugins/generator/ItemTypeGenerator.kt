package org.battleplugins.generator

import org.bukkit.Material

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * Generates the items for ItemTypes in BattleMcAPI.
 *
 * @author Redned
 */
object ItemTypeGenerator {

    fun generateItems() {
        println("Generating ItemTypes...")
        try {
            val file = File("ItemTypes.txt")
            if (!file.exists())
                file.createNewFile()

            val writer = BufferedWriter(FileWriter(file))
            for (mat in Material.values()) {
                if (mat.isBlock)
                    continue

                if (mat.isLegacy)
                    continue

                writer.write(
                    "public static final ItemType ${mat.key.key.toUpperCase()}"
                            + " = BlockRegistry.REGISTRY.fromKey(NamespacedKey.minecraft(\"${mat.key.key}\"));"
                )
                writer.newLine()
            }
            writer.close()
        } catch (ex: IOException) {
            System.err.println("Could not create ItemTypes file!")
            ex.printStackTrace()
        }

        println("Successfully generated all the items for ItemTypes!")
    }
}