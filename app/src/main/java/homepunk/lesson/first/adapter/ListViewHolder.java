package homepunk.lesson.first.adapter;

public class ListViewHolder extends AViewHolder {

    public ListViewHolder(String label) {
        super(label);
    }

    @Override
    public int getType() {
        return LIST_TYPE;
    }
}
