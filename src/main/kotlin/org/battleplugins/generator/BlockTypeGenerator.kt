package org.battleplugins.generator

import org.bukkit.Material

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * Generates the blocks for BlockTypes in BattleMcAPI.
 *
 * @author Redned
 */
object BlockTypeGenerator {

    fun generateBlocks() {
        println("Generating BlockTypes...")
        try {
            val file = File("BlockTypes.txt")
            if (!file.exists())
                file.createNewFile()

            val writer = BufferedWriter(FileWriter(file))
            for (mat in Material.values()) {
                if (!mat.isBlock)
                    continue

                if (mat.isLegacy)
                    continue

                writer.write(
                    "public static final BlockType ${mat.key.key.toUpperCase()}"
                            + " = BlockRegistry.REGISTRY.fromKey(NamespacedKey.minecraft(\"${mat.key.key}\").get());"
                )
                writer.newLine()
            }
            writer.close()
        }
        catch (ex: IOException) {
            System.err.println("Could not create BlockTypes file!")
            ex.printStackTrace()
        }

        println("Successfully generated all the blocks for BlockTypes!")
    }
}