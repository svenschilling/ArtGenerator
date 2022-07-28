import java.util.ArrayList;

public class Prefab {
    // list of node editor prefabs
    ArrayList<Prefab> prefabConfigs;

    public Prefab() {
        
    }
    
    protected void addPrefabToList(Prefab prefab) {
        prefabConfigs.add(prefab);
    }

    public ArrayList<Prefab> getPrefabConfigs() {
        return prefabConfigs;
    }

    public void setPrefabConfigs(ArrayList<Prefab> prefabConfigs) {
        this.prefabConfigs = prefabConfigs;
    }

    
}
