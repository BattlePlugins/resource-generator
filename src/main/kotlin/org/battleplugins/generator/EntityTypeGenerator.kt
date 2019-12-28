package org.battleplugins.generator

import org.bukkit.entity.EntityType

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * Generates the entities for EntityTypes in BattleMcAPI.
 *
 * @author Redned
 */
object EntityTypeGenerator {

    fun generateEntities() {
        println("Generating EntityTypes...")
        try {
            val file = File("EntityTypes.txt")
            if (!file.exists())
                file.createNewFile()

            val writer = BufferedWriter(FileWriter(file))
            for (entity in EntityType.values().sortedBy { it.getName() }) {
                if (entity.getName() == null)
                    continue;

                writer.write(
                    "public static final EntityType ${entity.key.key.toUpperCase()}"
                            + " = getOrDefault(\"${entity.key.key}\");"
                )
                writer.newLine()
            }
            writer.close()
        }
        catch (ex: IOException) {
            System.err.println("Could not create EntityTypes file!")
            ex.printStackTrace()
        }

        println("Successfully generated all the entities for EntityTypes!")
    }
}