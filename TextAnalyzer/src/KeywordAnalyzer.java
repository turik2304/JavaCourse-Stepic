public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        boolean result = false;
        for (int i = 0; i < getKeywords().length; i++) {
            if (text.toLowerCase().contains(getKeywords()[i].toLowerCase())) {
                result = true;
                break;
            } else result = false;
        }
        if (result) {
            return getLabel();
        } else return Label.OK;
    }
}
