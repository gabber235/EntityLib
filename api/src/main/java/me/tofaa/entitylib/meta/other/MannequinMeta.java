package me.tofaa.entitylib.meta.other;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.component.builtin.item.ItemProfile;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import me.tofaa.entitylib.meta.Metadata;
import me.tofaa.entitylib.meta.types.AvatarMeta;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MannequinMeta extends AvatarMeta {
    public static final byte OFFSET = AvatarMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = offset(OFFSET, 3);

    public MannequinMeta(int entityId, Metadata metadata) {
        super(entityId, metadata);
        ensureVersionNewerOrEquals(ServerVersion.V_1_21_9);
    }

    public ItemProfile getProfile() {
        return super.metadata.getIndex(OFFSET, null);
    }

    public void setProfile(ItemProfile profile) {
        super.metadata.setIndex(OFFSET, EntityDataTypes.RESOLVABLE_PROFILE, profile);
    }

    public Boolean getImmovable() {
        return super.metadata.getIndex(offset(OFFSET, 1), false);
    }

    public void setImmovable(Boolean value) {
        super.metadata.setIndex(offset(OFFSET, 1), EntityDataTypes.BOOLEAN, value);
    }

    public Optional<Component> getBelowName() {
        return super.metadata.getIndex(offset(OFFSET, 2), Optional.empty());
    }

    public void setBelowName(@Nullable Component value) {
        super.metadata.setIndex(offset(OFFSET, 2), EntityDataTypes.OPTIONAL_ADV_COMPONENT,
            Optional.ofNullable(value));
    }
}
