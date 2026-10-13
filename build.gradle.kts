multiloader {
    setMREnvironment(mrEnvs.clientOnly)
    setCFEnvironment(cfEnvs.client)

    configureLoaders {
        addDependency(
            dependency = getSimpleConfigLibDep(),
            isPublishDepEnabled = true
        )
        if (isFabric) addDependency(
            dependency = "maven.modrinth:smooth-scroll:${getDep("smooth-scroll", true)}"
        )
        if (!isForge && !isCommon) {
            val appleskin = getDep("appleskin").split("+")
            addDependency(
                dependency = "squeek.appleskin:appleskin-${mod.loader}:${appleskin[1]}-${appleskin[0]}",
                repository = "maven.ryanliptak.com",
            )
        }

        if (isFabric) {
            addDependency(
                dependency = "net.fabricmc:fabric-loader:${getDep("fabric")}"
            )
            addDependency(
                dependency = "net.fabricmc.fabric-api:fabric-api:${getDep("fabric-api")}",
                isPublishDepEnabled = true,
                isPublishDepRequired = true
            )
            addDependency(
                dependency = "com.terraformersmc:modmenu:${getDep("modmenu")}",
                repository = "maven.terraformersmc.com/releases",
                isPublishDepEnabled = true
            )
        }
    }
}
