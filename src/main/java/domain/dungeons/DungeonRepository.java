package domain.dungeons;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DungeonRepository {
    private final List<Dungeon> dungeons;

    DungeonRepository() {
        dungeons = new ArrayList<>();
    }

    public void add(Dungeon dungeon) {
        for (Dungeon d : dungeons) {
            if (Objects.equals(d.getDungeonName(), dungeon.getDungeonName())) {
                throw new IllegalArgumentException("Dungeon name already exists");
            }
        }
        dungeons.add(dungeon);
    }

    public Dungeon getDungeon(String dungeonName) {
        if (dungeonName == null || dungeonName.isBlank()) throw new IllegalArgumentException("dungeonName is empty");
        for (Dungeon dungeon : dungeons) {
            if (Objects.equals(dungeon.getDungeonName(), dungeonName)) return dungeon;
        }
        return null;
    }
}
