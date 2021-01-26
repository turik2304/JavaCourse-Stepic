import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String[] roles = {
                "Городничий", "Аммос Федорович",
                "Артемий Филиппович",
                "Лука Лукич"};
        String[] textLines = {
                "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
        StringBuilder sbText = new StringBuilder();
        String textRole;
        int endIndex = 0;
        for (int i = 0; i < roles.length; i++) {
            sbText.append(roles[i])
                    .append(":")
                    .append("\n");
            // перебираем текст и в textRole записываем исполнителя текста
            for (int j = 0; j < textLines.length; j++) {
                char[] find = textLines[j].toCharArray();
                for (int k = 0; k < find.length; k++) {
                    if (find[k] == ':') {
                        endIndex = k;
                        break;
                    }
                }
                //в переменной textRole имя исполнителя
                textRole = textLines[j].substring(0, endIndex);
                if (roles[i].equalsIgnoreCase(textRole)) {
                    sbText.append(j + 1)
                            .append(")")
                            .append(textLines[j].substring(endIndex + 1))
                            .append("\n");
                }
            }
            sbText.append("\n");
        }
        System.out.println(sbText);
    }
}
