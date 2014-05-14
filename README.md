SimpleThirst
============
Thirst plugin for Bukkit.

Acknowledgements
================
SimpleThirst plugin is based on the source code by steveville (http://dev.bukkit.org/bukkit-plugins/hydrate/).

Description
============
Each player will start out with 10 thirst levels. Every 180 seconds (configurable in config file), the player will lose one thirst level.

If the player's thirst level reaches 0, they will die of dehydration.

Players may replenish thirst by drinking a water bottle (or any potion), a bucket of milk, mushroom stew or eating a melon slice.

Thirst replenishment levels can be set in the config.

Commands
========
/thirst - check your thirst level. You will need to be a player to execute this.

/thirst set <player> <level> - set a player's thirst level between 1 and 10. You will need the permission "simplethirst.admin" to execute this.

Permissions
===========
simplethirst.admin - Allows you to set players' thirst levels.

simplethirst.nothirst - You will not be affected by thirst.

simplethirst.slowthirst - Your thirst speed is halved.
