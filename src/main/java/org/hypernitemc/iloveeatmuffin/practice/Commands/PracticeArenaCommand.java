package org.hypernitemc.iloveeatmuffin.practice.Commands;

import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;

public class PracticeArenaCommand extends DefaultCommand {
    public PracticeArenaCommand() {
        super(null, "arena", Perm.OWNER, "場地指令", "arena");
    }
}

