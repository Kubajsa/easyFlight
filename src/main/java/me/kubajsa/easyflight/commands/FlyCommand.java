package me.kubajsa.easyflight.commands;

import me.kubajsa.easyflight.EasyFlight;
import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    static EasyFlight plugin;

    public FlyCommand(EasyFlight plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                if (player.hasPermission("easyflight.fly")){
                    FlyUtils.toggleFly(player);
                }else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }
            } if (args.length == 1){
                if (player.hasPermission("easyflight.fly.others")){

                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        FlyUtils.toggleFly(target);
                        player.sendMessage(Utils.getToggleMessageSender(target));
                    }catch (Exception e){
                        player.sendMessage("§cUsage: /fly <player>");
                    }

                }else {player.sendMessage(Utils.getNoPermissionMessage());}
            }

        }else{
            if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null){
                    FlyUtils.toggleFly(target);
                    System.out.println("You have toggled flight for " + target.getName());
                }else{
                    System.out.println("Couldn't find that player");
                }
            }else{
                System.out.println("Usage: fly <player>");
            }
        }

        return true;
    }
}
