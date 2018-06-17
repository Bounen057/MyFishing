package bounen057.myfishing;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public final class MyFishing extends JavaPlugin implements Listener{
    public static FileConfiguration config1;
    CustomConfig fish;
    @Override
    public void onEnable() {
        fish = new CustomConfig(this, "fish.yml");
        //event
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onFish(PlayerFishEvent e){
        ///////////////////////////////////////////////////
        //   魚が釣れた時の処理
        //   Player was fishing then.
        ///////////////////////////////////////////////////
        if(e.getCaught()!=null) {
        ItemStack fish_item = new ItemStack(Material.LAPIS_BLOCK);//*
        ItemMeta fish_itemMeta = fish_item.getItemMeta();

        fish.getConfig().set("test","魚");
        String fish_name=fish.getConfig().getString("test");
        fish_itemMeta.setDisplayName(fish_name);

        fish_item.setItemMeta(fish_itemMeta);
        Bukkit.broadcastMessage("§b§l[§f§lMyFishing§b§l]§f"+fish_name+"が釣れました!");
            e.getCaught().remove();
            e.getPlayer().getInventory().addItem(fish_item);
        }
    }
}
