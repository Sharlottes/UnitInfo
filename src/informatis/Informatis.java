package informatis;

import arc.input.KeyCode;
import informatis.core.OverDrawer;
import informatis.core.Setting;
import informatis.draws.OverDraws;
import informatis.ui.fragments.FragmentManager;
import informatis.ui.windows.*;
import arc.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

import static informatis.SVars.*;
import static arc.Core.*;
import static informatis.SUtils.*;
import static informatis.ui.windows.WindowManager.windows;

public class Informatis extends Mod {
    @Override
    public void init(){
        Core.app.post(() -> {
            Mods.ModMeta meta = Vars.mods.locateMod("informatis").meta;
            meta.displayName = "[#B5FFD9]Informatis[]";
            meta.author = "[#B5FFD9]Sharlotte[lightgray]#0018[][]";
            meta.description = bundle.get("shar-description");
        });

        Events.run(Trigger.update, () -> {
            if((input.keyDown(KeyCode.shiftRight) || input.keyDown(KeyCode.shiftLeft))) {
                if(input.keyTap(KeyCode.r)) {
                    windows.get(UnitWindow.class).each(window -> ((UnitWindow) window).locked = !((UnitWindow) window).locked);
                }
            }
        });

        Events.on(ClientLoadEvent.class, e -> {
            Windows.load();

            Setting.init();
            WindowManager.init();
            FragmentManager.init();
            OverDraws.init();
            OverDrawer.init();

            SVars.pathfinder = new informatis.core.Pathfinder();
        });
    }
}
