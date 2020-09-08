/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

subprojects {
    configurations.all {
        resolutionStrategy.dependencySubstitution {
            substitute(module(Deps.Libs.MultiPlatform.mokoUnits.common))
                .with(project(":units"))
            substitute(module(Deps.Libs.MultiPlatform.mokoUnitsBasic.common))
                .with(project(":units-basic"))
        }
    }
}
