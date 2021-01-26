import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class Task4 {

    public static void main(String[] args) {

        String randomFrom = "LETI";
        String randomTo = "STUDENT";
        int randomSalary = 100;

        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        MailMessage fourthMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "fourthMessage"
        );

        MailMessage fifthMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "fifthMessage"
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage, fourthMessage, fifthMessage
        );

        MailService<String> mailService = new MailService<>();

        messages.stream().forEachOrdered(mailService);

        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар.",
                        "fourthMessage",
                        "fifthMessage"
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";

        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);
        MailService<Integer> salaryService = new MailService<>();

        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        System.out.println(salaries);

        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";


    }

    public interface Sendable<T> {
        public String getFrom();

        public String getTo();

        public T getContent();
    }


    public static class MailMessage implements Sendable<String> {
        private final String from;
        private final String to;
        private final String content;

        public MailMessage(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public String getContent() {
            return content;
        }

    }


    public static class MailService<T> implements Consumer<Sendable<T>> {


        private final Map<String, List<T>> map = new HashMap<>() {

            @Override
            public List<T> get(Object recipient) {
                if (super.get(recipient) == null) {
                    return Collections.emptyList();
                } else {
                    return super.get(recipient);
                }


            }
        };

        @Override
        public void accept(Sendable<T> tSendable) {
            map.merge(tSendable.getTo(), Arrays.asList(tSendable.getContent()), (oldValue, newValue) -> {
                List<T> buffList = new ArrayList<>();
                buffList.addAll(oldValue);
                buffList.addAll(newValue);
                return buffList;
            });
        }

        public Map<String, List<T>> getMailBox() {
            return map;
        }


    }

    public static class Salary implements Sendable<Integer> {

        private final String from;
        private final String to;
        private final Integer salary;

        public Salary(String from, String to, Integer salary) {
            this.from = from;
            this.to = to;
            this.salary = salary;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public Integer getContent() {
            return salary;
        }
    }
}
