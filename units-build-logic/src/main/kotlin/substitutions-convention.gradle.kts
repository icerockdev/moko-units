/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

subprojects {
    configurations.all {
        resolutionStrategy.dependencySubstitution {
            substitute(module(libs.mokoUnits))
                .with(projects.units)
            substitute(module(libs.mokoUnitsBasic))
                .with(projects.unitsBasic)
        }
    }
}
