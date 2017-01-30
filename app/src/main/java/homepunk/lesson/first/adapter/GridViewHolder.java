package homepunk.lesson.first.adapter;

public class GridViewHolder extends AViewHolder {

    public GridViewHolder(String label) {
        super(label);
    }

    @Override
    public int getType() {
        return GRID_TYPE;
    }
}
