package model;

public class zone {
    private int width;
    private int height;
    private ZoneState state;

    public zone(int width, int height){
        this.width = width;
        this.height = height;
        this.state = ZoneState.NORMAL;
    }
    public ZoneState getState() {
        return state;
    }

    // Setter for state (allows you to change the state of the zone)
    public void setState(ZoneState state) {
        this.state = state;
    }

    // toString method to represent the Zone as a string for easy printing
    @Override
    public String toString() {
        return "Zone (" + width + "x" + height + ") - State: " + state;
    }
}
