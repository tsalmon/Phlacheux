package model.movable;


import model.movable.circle.Circle;
import model.movable.line.CubicCurve;
import model.movable.line.QuadraticCurve;
import model.movable.line.Segment;
import model.movable.polygon.EquilateralTriangle;
import model.movable.polygon.Rectangle;
import model.movable.polygon.Square;
import model.movable.polygon.Triangle;
import org.jdom2.Document;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Hashtable;

public class MovablePool {

    private Hashtable<String, Movable> movablePool = new Hashtable<String, Movable>();

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

    public static synchronized MovablePool getInstance(){
        if (instance == null){
            instance = new MovablePool();
        }
        return instance;
    }
}
