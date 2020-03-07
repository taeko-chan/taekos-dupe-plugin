/*

Skysucht Dupe by Taeko (quarkparticles)

Designed originally for the skysucht.com Minecraft server.
This code is fully open source and you are welcome to do as you please with it.

*/
package com.skysucht.taeko.dupe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Dupe extends JavaPlugin implements Listener {

    static List<String> found = new ArrayList<>();
    static List<String> locations = new ArrayList<>();
    public static int searchedChunks = 0;
    public static int chunksHaveValuables = 0;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new DupeChunkHandlerListener(), this);
        System.out.println(ChatColor.BLUE + "[Skysucht Dupe] has been activated! Written by Taeko (quarkparticles) Version 2.0.1");
        LogProgress logger = new LogProgress();
        logger.start();

    }

    @Override
    public void onDisable() {

        System.out.println(ChatColor.BLUE + "[Skysucht Dupe] has been deactivated! " +
                "Goodbye! Written by Taeko (quarkparticles)");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        assert cmd != null;
        if (cmd.getName().equalsIgnoreCase("dupe")) {

            boolean shouldExitLoop = false;

            if (args.length != 0) {

                switch (args[0]) {

                case "clear":

                    if (sender.hasPermission("dupe.clear")) {

                        if (args.length == 2) {

                            if ("found".equals(args[1])) {

                                found.clear();
                                sender.sendMessage(ChatColor.BLUE + "[Skysucht Dupe]: Cleared Found List");
                                shouldExitLoop = true;

                            } else {

                                int indexer = Integer.parseInt(args[1]);

                                try {

                                    found.add(locations.get(indexer));
                                    locations.remove(indexer);

                                } catch (ArrayIndexOutOfBoundsException e) {

                                    sender.sendMessage(ChatColor.RED + "[Skysucht Dupe]: There aren't that many listed dupes.");

                                } catch (NumberFormatException e) {

                                    sender.sendMessage(ChatColor.RED + "[Skysucht Dupe]: Given arg cannot be converted to an Integer.");

                                }

                                shouldExitLoop = true;
                                sender.sendMessage(ChatColor.BLUE + "[Skysucht Dupe]: Cleared Item " + indexer + " From Dupe List.");
                            }

                        }

                        if (shouldExitLoop) { break; }

                        locations.clear();
                        sender.sendMessage(ChatColor.BLUE + "[Skysucht Dupe]:" + ChatColor.WHITE + " List cleared.");

                    } else {

                        sender.sendMessage(ChatColor.RED + "[Skysucht Dupe]: Insuffiient Permissions.");

                    }
                    break;

                case "info":

                    sender.sendMessage(ChatColor.BLUE + "Skysucht Dupe - Dupe Checker");
                    sender.sendMessage("Version: 2.0.1");
                    sender.sendMessage("Author: Taeko (quarkparticles)");
                    break;

                default:

                    sender.sendMessage(ChatColor.RED + "[Skysucht Dupe]: This subcommand is not registered.");

                }

            } else {

                if (sender instanceof Player) {

                    if (sender.hasPermission("dupe.show")) {

                        sender.sendMessage(ChatColor.GREEN + "-----[Skysucht - Dupe]-----");
                        for (String location : locations) {

                            sender.sendMessage("==== " + location + " ==== " + locations.indexOf(location));

                        }
                        sender.sendMessage(ChatColor.RED + "-----[Skysucht - Dupe]-----");

                    } else {

                        sender.sendMessage(ChatColor.RED + "[Skysucht - Dupe]: Insufficient Permissions.");

                    }
                }
            }

            return true;

        } else if (cmd.getName().equalsIgnoreCase("taekoisgreat")) {

            sender.sendMessage(ChatColor.BLUE + "NAME EXPLINATION: " + ChatColor.WHITE + "it's from the movie " +
                    "\"Only Yesterday\" by Studio Ghibli." +
                    " It tells the story of a girl called Taeko who goes to Yamagata (Japanese Contryside) on vacation " +
                    "and keeps thinking about herself in 5th grade and wonders if she should rethink her work life because " +
                    "as a kid she always wanted to go to the contry and she now works in Tokyo. " +
                    "WATCH IT NOW it's a great film. Studio Ghibli masterpiece. It's on Netflix. " +
                    "Oh and yes, the pics from the Skysucht Tower are from that movie xD " +
                    "Oh, and congrats on finding this easter egg! There'll be more in my upcoming plugins =)");
            //If this has happened the function will return true.
        }
        // If this hasn't happened the value of false will be returned.
        return false;

    }
}
