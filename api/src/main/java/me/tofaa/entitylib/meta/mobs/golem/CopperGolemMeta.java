package me.tofaa.entitylib.meta.mobs.golem;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.data.struct.CopperGolemState;
import com.github.retrooper.packetevents.protocol.entity.data.struct.WeatheringCopperState;
import me.tofaa.entitylib.meta.Metadata;
import me.tofaa.entitylib.meta.types.MobMeta;

/**
 * Metadata for the Copper Golem entity.
 */
public class CopperGolemMeta extends MobMeta {
    public static final byte OFFSET = MobMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = offset(OFFSET, 2);

    public CopperGolemMeta(int entityId, Metadata metadata) {
        super(entityId, metadata);
        ensureVersionNewerOrEquals(ServerVersion.V_1_21_9);
    }

    public WeatheringCopperState getWeatheringState() {
        return super.metadata.getIndex(OFFSET, null);
    }

    public void setWeatheringState(WeatheringCopperState value) {
        super.metadata.setIndex(OFFSET, EntityDataTypes.WEATHERING_COPPER_STATE, value);
    }

    public CopperGolemState getGolemState() {
        return super.metadata.getIndex(offset(OFFSET, 1), null);
    }

    public void setGolemState(CopperGolemState value) {
        super.metadata.setIndex(offset(OFFSET, 1), EntityDataTypes.COPPER_GOLEM_STATE, value);
    }
}
