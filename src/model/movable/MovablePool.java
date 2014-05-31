package model.movable;


import java.util.HashMap;

public class MovablePool {

    private HashMap<String, Movable> movablePool = new HashMap<>();

    private static MovablePool instance;


    public Movable getMovable(String name){
        return movablePool.get(name);
    }

    public void removeMovable(String name){
        movablePool.remove(name);
    }

    public Movable storeMovable(Movable m){
        if (m.getName() == null){
            m.setName(getFreshLabel());
        }

        return movablePool.put(m.getName(), m);
    }

    private MovablePool(){
    }

    public String getFreshLabel(){
        String potentialKey = "";
        for (int i = 0; i<Integer.MAX_VALUE; ++i){
            potentialKey = "figure"+Integer.toString(i);
            if (!movablePool.containsKey(potentialKey)) {
                break;
            }
        }

        return potentialKey;
    }

    public HashMap<String, Movable> getMovablePool() {
        return movablePool;
    }
    

    public static synchronized MovablePool getInstance(){
        if (instance == null){
            instance = new MovablePool();
        }
        return instance;
    }
}
