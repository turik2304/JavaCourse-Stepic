public class Main {

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        Label resultLabel = null;
        for (int i = 0; i < analyzers.length; i++) {
            if (analyzers[i].processText(text) != Label.OK) {
                resultLabel = analyzers[i].processText(text);
                break;
            } else resultLabel = Label.OK;
        }
        return resultLabel;
    }

    public static void main(String[] args) {
        Main test = new Main();
        String[] keywords = {"bad", "lox"};
        String text = "Ya toptal etu j bad dgbteee";
        Label label1 = test.checkLabels(new TextAnalyzer[]{new SpamAnalyzer(keywords), new TooLongTextAnalyzer(18), new NegativeTextAnalyzer()}, text);
        Label label2 = test.checkLabels(new TextAnalyzer[]{new TooLongTextAnalyzer(18), new SpamAnalyzer(keywords), new NegativeTextAnalyzer()}, text);
        Label label3 = test.checkLabels(new TextAnalyzer[]{new SpamAnalyzer(keywords), new TooLongTextAnalyzer(18), new NegativeTextAnalyzer()}, text);
        System.out.println(label1);
        System.out.println(label2);
        System.out.println(label3);
//        String text = "I can swim and fly :(";
//        System.out.println(new TooLongTextAnalyzer(10).processText(text));
//        String[] spamKey = {" bad ", "lox", "popa"};
//        System.out.println(new SpamAnalyzer(spamKey).processText("I am bad abaddon"));
//        System.out.println(new NegativeTextAnalyzer().processText(text));

    }
}
