package XML;

import org.jdom2.Element;

public class AnimationXML extends Element {
    public enum Easing {
        easing1, easing2, easing3
    }

    private static String easingString(Easing e){
        switch(e){
            case easing1:{
                return "easing1";
            }

            case easing2:{
                return "easing2";
            }

            case easing3:{
                return "easing3";
            }

            default: {
                return "unknown";
            }
        }
    }

    private AnimationXML(int startTime, int endTime, Easing easing){
        super("animation");
        setAttribute("startTime", Integer.toString(startTime));
        setAttribute("endTime", Integer.toString(endTime));
        setAttribute("easing", easingString(easing));
    }

    public static Element tranlsationAnimation(int startX, int startY, int endX, int endY, int startTime, int endTime, Easing easing){
        Element animation = new AnimationXML(startTime, endTime, easing);

        animation.setAttribute("type", "translation");
        animation.setAttribute("startX", Integer.toString(startX));
        animation.setAttribute("endX", Integer.toString(endX));
        animation.setAttribute("startY", Integer.toString(startY));
        animation.setAttribute("endY", Integer.toString(endY));

        return animation;
    }

    public static Element rotationAnimation(int angle, int pointX, int pointY, int startTime, int endTime, Easing easing){
        Element animation = new AnimationXML(startTime, endTime, easing);

        animation.setAttribute("type", "rotation");
        animation.setAttribute("angle", Integer.toString(angle));
        animation.setAttribute("pointX", Integer.toString(pointX));
        animation.setAttribute("pointY", Integer.toString(pointY));

        return animation;
    }

    public static Element colorAnimation(int destinationR, int destinationG, int destinationB, int startTime, int endTime, Easing easing){
        Element animation = new AnimationXML(startTime, endTime, easing);

        animation.setAttribute("type", "color");
        animation.setAttribute("destinationR", Integer.toString(destinationR));
        animation.setAttribute("destinationG", Integer.toString(destinationG));
        animation.setAttribute("destinationB", Integer.toString(destinationB));

        return animation;
    }

    public static Element borderAnimation(int destinationR, int destinationG, int destinationB, int size, int startTime, int endTime, Easing easing){
        Element animation = new AnimationXML(startTime, endTime, easing);

        animation.setAttribute("type", "border");
        animation.setAttribute("destinationR", Integer.toString(destinationR));
        animation.setAttribute("destinationG", Integer.toString(destinationG));
        animation.setAttribute("destinationB", Integer.toString(destinationB));
        animation.setAttribute("size", Integer.toString(size));

        return animation;
    }

    public static Element scaleAnimation(int scaleRatio, int startTime, int endTime, Easing easing){
        Element animation = new AnimationXML(startTime, endTime, easing);

        animation.setAttribute("type", "scale");
        animation.setAttribute("scaleRatio", Integer.toString(scaleRatio));

        return animation;
    }
}
