package model;

public class zone {
    private int width;
    private int height;
    private ZoneState state;

    public zone(){
        this.width = 10;
        this.height = 10;
        this.state = ZoneState.NORMAL;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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
