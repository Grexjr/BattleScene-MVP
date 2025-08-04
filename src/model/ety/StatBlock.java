package model.ety;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class StatBlock {

    // TODO: Should eventually be a Hash Map

    // === VARIABLES AND FIELDS ===
    private final EnumMap<Stats,Integer> statsMap;


    // === CONSTRUCTOR FOR STAT BLOCK ===
    public StatBlock(int level, int health, int attack, int defense, int speed){

        this.statsMap = new EnumMap<>(Stats.class){{
            put(Stats.LEVEL,level);
            put(Stats.MAX_HEALTH,health);
            put(Stats.CURRENT_HEALTH,health);
            put(Stats.ATTACK,attack);
            put(Stats.DEFENSE,defense);
            put(Stats.SPEED,speed);
            put(Stats.TEMP_HEALTH_BONUS,0);
            put(Stats.TEMP_ATTACK_BONUS,0);
            put(Stats.TEMP_DEFENSE_BONUS,0);
            put(Stats.TEMP_SPEED_BONUS,0);
        }};

    }

    // ===
    @Override
    public String toString(){
        ArrayList<String> rawStatsStrings = new ArrayList<>();
        ArrayList<String> rawStatsValues = new ArrayList<>();

        for(Stats stat : this.statsMap.keySet()){
            String statString = stat.toString(); // NOTE: May need to add this in the enum

            rawStatsStrings.add(statString);
        }


        for(Stats stat : this.statsMap.keySet()){
            String valueString = this.statsMap.get(stat).toString();

            rawStatsValues.add(valueString);
        }

        String[] finalDisplay = new String[]{
                rawStatsStrings.get(0) + rawStatsValues.get(0),                      //Level
                "HEALTH: " + rawStatsValues.get(2) + "/" + rawStatsValues.get(1),           //Health
                rawStatsStrings.get(3) + rawStatsValues.get(3),                      //Attack
                rawStatsStrings.get(4) + rawStatsValues.get(4),                      //Defense
                rawStatsStrings.get(5) + rawStatsValues.get(5),                      //Speed
        };

        return Arrays.toString(finalDisplay)
                .replace(",","\n")
                .replace("[","")
                .replace("]","")
                .trim();

    }

}
