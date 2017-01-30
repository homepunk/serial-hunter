package homepunk.lesson.first.adapter;

public abstract class AViewHolder {
    public static final int GRID_TYPE = 0;
    public static final int LIST_TYPE = 1;

    private String label;

    public AViewHolder(String label) {
        this.label = label;
    }

    // getters and setters here
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    abstract public int getType();
}
