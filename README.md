# Witches' Way
* [CurseForge](https://www.curseforge.com/minecraft/mc-mods/witches-way)

* [Modrinth](https://modrinth.com/mod/witches-way)

# Api
BlockState tags

Adding a block state to heat sources
``data/<namespace>/blockstates/boiling_cauldron_heat_source.json``

```json
{
  "values": [
    {
      "block": "<namespace>:<block>"
    }
  ]
}
```
<br>

With specific properties.
```json
{
  "values": [
    {
      "block": "<namespace>:<block>",
      "properties": {
        "lit": "true"
      }
    }
  ]
}
```
<br>

```kotlin
repositories { 
    maven("https://cursemaven.com")
}

dependencies { 
    modImplementation("curse.maven:witches-way-705233:<file_id>")
}

```