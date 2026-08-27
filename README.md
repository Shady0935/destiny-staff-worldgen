# Destiny Staff WorldGen

Server-only Fabric 1.20.1 mini-mod for Multiworld. It registers `destiny:staff_flat` in Multiworld's own custom-generator map. The generator creates 1 bedrock layer, 10 dirt layers, and 1 grass layer, and makes `generateFeatures` a no-op. It does not modify biome modifiers, structure sets, vanilla dimensions, or any other world.

Use Java 17 and run `gradlew build`; the remapped JAR is in `build/libs/`. Install it beside Multiworld in `mods/`; clients do not install it.

Test: `/mw create staff_test NORMAL -g=destiny:staff_flat -s=12345`, then `/mw tp multiworld:staff_test`.

Existing worlds are not retroactively changed; recreate a test world to regenerate chunks. Do not delete production Staff without a backup.
