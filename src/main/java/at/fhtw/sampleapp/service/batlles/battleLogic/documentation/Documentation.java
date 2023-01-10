package at.fhtw.sampleapp.service.batlles.battleLogic.documentation;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Documentation {
    public volatile static Documentation documentation;
    private List<String> battleLog;
    private Documentation () {
       battleLog = new ArrayList<>();
    }

    public static Documentation getDocumentation(){
        if(documentation == null) {
            documentation = new Documentation();
        }
        return documentation;
    }

    public List<String> getBattleLog() {
        return documentation.battleLog ;
    }

    public boolean isListEmpty() {
        return battleLog.isEmpty();
    }

    public void addBattleLog(String message) {
        battleLog.add(message);
    }
}
