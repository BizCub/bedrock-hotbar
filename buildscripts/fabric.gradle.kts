plugins {
    multiloader
    alias(libs.plugins.loom)
}

multiloader {
    setBuiltFile(tasks.named<AbstractArchiveTask>(fabricJarTask).get().archiveFile)

    dependencies {
        minecraft("com.mojang:minecraft:${mod.mcExact}")
        if (isObfuscated) "mappings"(loom.officialMojangMappings())
    }

    loom {
        if (isMainCTFileExist())
            accessWidenerPath.set(ctFabricFile)

        runConfigs {
            getByName("client") {
                runDirectory.set(clientRunFile)
            }
            getByName("server") {
                runDirectory.set(serverRunFile)
            }
        }
    }
}
