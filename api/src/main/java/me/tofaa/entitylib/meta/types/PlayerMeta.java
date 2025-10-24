package me.tofaa.entitylib.meta.types;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import me.tofaa.entitylib.meta.Metadata;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class PlayerMeta extends AvatarMeta {
    public static final byte OFFSET = AvatarMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = offset(OFFSET, 2);

    public PlayerMeta(int entityId, Metadata metadata) {
        super(entityId, metadata);
    }

    public float getAdditionalHearts() {
        return super.metadata.getIndex(OFFSET, 0F);
    }

    public void setAdditionalHearts(float value) {
        super.metadata.setIndex(OFFSET, EntityDataTypes.FLOAT, value);
    }

    public int getScore() {
        return super.metadata.getIndex(offset(OFFSET, 1), 0);
    }

    public void setScore(int value) {
        super.metadata.setIndex(offset(OFFSET, 1), EntityDataTypes.INT, value);
    }


    public @Nullable NBTCompound getLeftShoulderData() {
        ensureVersionBetween(ServerVersion.V_1_11_2, ServerVersion.V_1_21_8);
        return super.metadata.getIndex(offset(OFFSET, 4), null);
    }

    public Optional<Integer> getOptionalLeftShoulderData() {
        ensureVersionNewerOrEquals(ServerVersion.V_1_21_9);
        return super.metadata.getIndex(offset(OFFSET, 2), Optional.empty());
    }

    public void setLeftShoulderData(@Nullable NBTCompound value) {
        ensureVersionBetween(ServerVersion.V_1_11_2, ServerVersion.V_1_21_8);
        if (value == null) {
            value = new NBTCompound();
        }
        super.metadata.setIndex(offset(OFFSET, 4), EntityDataTypes.NBT, value);
    }

    public void setOptionalLeftShoulderData(@Nullable Integer value) {
        ensureVersionNewerOrEquals(ServerVersion.V_1_21_9);
        super.metadata.setIndex(offset(OFFSET, 2), EntityDataTypes.OPTIONAL_INT, Optional.ofNullable(value));
    }

    public @Nullable NBTCompound getRightShoulderData() {
        ensureVersionBetween(ServerVersion.V_1_11_2, ServerVersion.V_1_21_8);
        return super.metadata.getIndex(offset(OFFSET, 5), null);
    }

    public Optional<Integer> getOptionalRightShoulderData() {
        ensureVersionNewerOrEquals(ServerVersion.V_1_21_9);
        return super.metadata.getIndex(offset(OFFSET, 3), Optional.empty());
    }

    public void setRightShoulderData(@Nullable NBTCompound value) {
        ensureVersionBetween(ServerVersion.V_1_11_2, ServerVersion.V_1_21_8);
        if (value == null) {
            value = new NBTCompound();
        }
        super.metadata.setIndex(offset(OFFSET, 5), EntityDataTypes.NBT, value);
    }

    public void setOptionalRightShoulderData(@Nullable Integer value) {
        ensureVersionNewerOrEquals(ServerVersion.V_1_21_9);
        super.metadata.setIndex(offset(OFFSET, 3), EntityDataTypes.OPTIONAL_INT, Optional.ofNullable(value));
    }
}
