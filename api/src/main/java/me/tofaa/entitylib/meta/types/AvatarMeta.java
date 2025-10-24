package me.tofaa.entitylib.meta.types;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import me.tofaa.entitylib.EntityLib;
import me.tofaa.entitylib.meta.Metadata;

public class AvatarMeta extends LivingEntityMeta {
    public static final byte OFFSET = LivingEntityMeta.MAX_OFFSET;
    /// In 1.21.9 the avatar metadata got introduced and shuffled around the offsets
    public static final byte MAX_OFFSET = offset(OFFSET, isVersionNewerOrEquals(ServerVersion.V_1_21_9) ? 2 : 0);

    private static final byte MAIN_HAND_OFFSET = offset(OFFSET, isVersionNewerOrEquals(ServerVersion.V_1_21_9) ? 0 : 3);
    private static final byte SKIN_PART_OFFSET = offset(OFFSET, isVersionNewerOrEquals(ServerVersion.V_1_21_9) ? 1 : 2);

    private final static byte CAPE_BIT = 0x01;
    private final static byte JACKET_BIT = 0x02;
    private final static byte LEFT_SLEEVE_BIT = 0x04;
    private final static byte RIGHT_SLEEVE_BIT = 0x08;
    private final static byte LEFT_LEG_BIT = 0x10;
    private final static byte RIGHT_LEG_BIT = 0x20;
    private final static byte HAT_BIT = 0x40;

    public AvatarMeta(int entityId, Metadata metadata) {
        super(entityId, metadata);
    }


    public boolean isRightHandMain() {
        if (EntityLib.getApi().getPacketEvents().getServerManager().getVersion()
            .isOlderThan(ServerVersion.V_1_9)) {
            return true;
        }
        return super.metadata.getIndex(MAIN_HAND_OFFSET, (byte) 1) == (byte) 1;
    }

    public void setRightHandMain(boolean value) {
        if (EntityLib.getApi().getPacketEvents().getServerManager().getVersion()
            .isOlderThan(ServerVersion.V_1_9)) {
            return;
        }
        super.metadata.setIndex(MAIN_HAND_OFFSET, EntityDataTypes.BYTE, (byte) (value ? 1 : 0));
    }

    public boolean isCapeEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, CAPE_BIT);
    }

    public void setCapeEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, CAPE_BIT, value);
    }

    public boolean isJacketEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, JACKET_BIT);
    }

    public void setJacketEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, JACKET_BIT, value);
    }

    public boolean isLeftSleeveEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, LEFT_SLEEVE_BIT);
    }

    public void setLeftSleeveEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, LEFT_SLEEVE_BIT, value);
    }

    public boolean isRightSleeveEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, RIGHT_SLEEVE_BIT);
    }

    public void setRightSleeveEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, RIGHT_SLEEVE_BIT, value);
    }

    public boolean isLeftLegEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, LEFT_LEG_BIT);
    }

    public void setLeftLegEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, LEFT_LEG_BIT, value);
    }

    public boolean isRightLegEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, RIGHT_LEG_BIT);
    }

    public void setRightLegEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, RIGHT_LEG_BIT, value);
    }

    public boolean isHatEnabled() {
        ensureVersionNewer(ServerVersion.V_1_9);
        return getMaskBit(SKIN_PART_OFFSET, HAT_BIT);
    }

    public void setHatEnabled(boolean value) {
        ensureVersionNewer(ServerVersion.V_1_9);
        setMaskBit(SKIN_PART_OFFSET, HAT_BIT, value);
    }
}
