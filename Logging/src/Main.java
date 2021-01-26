import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());

        Inspector inspector = new Inspector();
        Spy spy = new Spy(logger);
        Thief thief = new Thief(10000);
        MailService variousWorkers[] = new MailService[]{spy, thief, inspector};
        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(variousWorkers);

        AbstractSendable correspondence[] = {
                new MailMessage("Oxxxymiron", "Гнойный", "Я здесь чисто по фану, поглумиться над слабым\n" +
                        "Ты же вылез из мамы под мой дисс на Бабана...."),
                new MailMessage("Гнойный", "Oxxxymiron", "....Что? Так болел за Россию, что на нервах терял ганглии.\n" +
                        "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
                new MailMessage("Жриновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
                new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
                new MailPackage("берег", "море", new Package("ВВЖ", 32)),
                new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("рпакетный двигатель ", 2500000)),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("stones", 1000)),
                new MailPackage("Китай", "КНДР", new Package("banned substance", 99)),
                new MailPackage(AUSTIN_POWERS, "ИГИЛ (запрещенная группировка", new Package("tiny bomb", 9000)),
                new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),
        };
        Arrays.stream(correspondence).forEach(parcell -> {
            try {
                worker.processMail(parcell);
            } catch (StolenPackageException e) {
                logger.log(Level.WARNING, "Inspector found stolen package: " + e);
            } catch (IllegalPackageException e) {
                logger.log(Level.WARNING, "Inspector found illegal package: " + e);
            }
        });
    }

    public static class MailMessage extends AbstractSendable {

        private final String message;


        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    public static class UntrustworthyMailWorker implements MailService {

        private final MailService[] mailServices;
        private RealMailService realMailService;


        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices.clone();
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable[] currentSendableObject = new Sendable[mailServices.length];
            currentSendableObject[0] = mail;
            for (int i = 0; i < mailServices.length - 1; i++) {
                System.out.println(i);
                currentSendableObject[i + 1] = mailServices[i + 1].processMail(mailServices[i].processMail(currentSendableObject[i]));
            }
            realMailService = new RealMailService();
            return realMailService.processMail(currentSendableObject[currentSendableObject.length - 1]);
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }

    }

    public static class Spy implements MailService {
        private final Logger LOGGER;

        public Spy(Logger LOGGER) {
//            LOGGER = Logger.getLogger(MailMessage.class.getName());
            this.LOGGER = LOGGER;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                String from = mail.getFrom();
                String to = mail.getTo();
                String m = ((MailMessage) mail).getMessage();
                if (mail.getFrom().equalsIgnoreCase(AUSTIN_POWERS) || mail.getTo().equalsIgnoreCase(AUSTIN_POWERS)) {
                    LOGGER.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"", new Object[]{from, to, m});
                } else {
                    LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[]{from, to});
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private final int priceMin;
        private int stolenAmount = 0;

        public Thief(int priceMin) {
            this.priceMin = priceMin;

        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                if (mailPackage.getContent().getPrice() > priceMin) {
                    stolenAmount += mailPackage.getContent().getPrice();
                    Package packageFake = new Package("stones instead of " + mailPackage.getContent().getContent(), 0);
                    return new MailPackage(mailPackage.from, mailPackage.to, packageFake);
                } else return mailPackage;
            } else return mail;
        }

        public int getStolenAmount() {
            return stolenAmount;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {
            super();
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {
            super();
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                String packageContent = mailPackage.getContent().getContent();
                if (packageContent.equalsIgnoreCase(WEAPONS) || packageContent.equalsIgnoreCase(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                } else if (packageContent.contains("stones")) {
                    throw new StolenPackageException();
                } else return mail;
            } else return mail;
        }
    }


}



