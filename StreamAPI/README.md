***Task1***
---
Напишите метод, возвращающий стрим псевдослучайных целых чисел. Алгоритм генерации чисел следующий:

1.  Берется какое-то начальное неотрицательное число (оно будет передаваться в ваш метод проверяющей системой).
2.  Первый элемент последовательности устанавливается равным этому числу.
3.  Следующие элементы вычисляются по рекуррентной формуле R_{n+1}=\mathrm{mid}(R_{n}^2)Rn+1​=mid(Rn2​), где mid --- это функция, выделяющая второй, третий и четвертый разряд переданного числа. Например, \mathrm{mid}(123456) = 345mid(123456)=345.

Алгоритм, конечно, дурацкий и не выдерживающий никакой критики, но для практики работы со стримами сойдет :)

Пример

pseudoRandomStream(13) должен вернуть стрим, состоящий из следующих чисел:

13, 16, 25, 62, 384, 745, 502, 200, 0, ... (дальше бесконечное количество нулей)

***Task2***
---
Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным Comparator'ом.

Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:

```
minMaxConsumer.accept(min, max);
```

Если стрим не содержит элементов, то вызовите

```
minMaxConsumer.accept(null, null);
```

***Task3***
---
Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем частоту появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.

Словом будем считать любую непрерывную последовательность символов, состоящую только из букв и цифр. Например, в строке "Мама мыла раму 33 раза!" ровно пять слов: "Мама", "мыла", "раму", "33" и "раза".

Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" --- это одно и то же слово. Выводите слова в нижнем регистре.

Если в тексте меньше 10 уникальных слов, то выводите сколько есть.

Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно упорядочить только по частоте, то дополнительно упорядочите слова с одинаковой частотой в лексикографическом порядке.

Задача имеет красивое решение через стримы без циклов и условных операторов. Попробуйте придумать его.

Sample Input 1:

Мама мыла-мыла-мыла раму!

Sample Output 1:

мыла
мама
раму

Sample Input 2:

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.

Sample Output 2:

consectetur
faucibus
ipsum
lorem
adipiscing
amet
dolor
eget
elit
mi

***Task3***
---
В этой задаче вам предстоит самостоятельно написать набор классов таким образом, чтобы данный код успешно компилировался и выполнялся.

Вам предстоит использовать новые знания про generics, коллекции и Stream API.

В приведенном коде используется оператор assert. Этот оператор используется для того, чтобы проверять определенные инварианты в коде. С помощью него возможно писать небольшие тесты и следить за корректностью своей программы (в обычной ситуации предпочтительно для этих целей использовать библиотеки для модульного тестирования, которые выходят за рамки базового курса).

Оператор выглядит следующим образом:\
assert *предикат*: *сообщение*;\
Предикат -- выражение с типом boolean. Если выражение является ложным, то в программе возникает исключение AssertionError с соответствующим сообщением.

По-умолчанию данная функциональность отключена. Чтобы ее включить, необходимо передать специальный ключ -ea в параметры виртуальной машины. Сделать это можно прямо при запуске в консоли с помощью программы java, либо указав этот ключ в настройках запуска программы в вашей IDE. В случае IntellijIDEA, например, эта опция указывается в поле Run -> Edit Configurations... -> конфигурация запуска вашей программы -> VM Options.

Код, который необходимо заставить успешно работать:

```
// Random variables
String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

// Создание списка из трех почтовых сообщений.
MailMessage firstMessage = new MailMessage(
        "Robert Howard",
        "H.P. Lovecraft",
        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
);

assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";

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

List<MailMessage> messages = Arrays.asList(
        firstMessage, secondMessage, thirdMessage
);

// Создание почтового сервиса.
MailService<String> mailService = new MailService<>();

// Обработка списка писем почтовым сервисом
messages.stream().forEachOrdered(mailService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список сообщений, которые были ему отправлены
Map<String, List<String>> mailBox = mailService.getMailBox();

assert mailBox.get("H.P. Lovecraft").equals(
        Arrays.asList(
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        )
): "wrong mailService mailbox content (1)";

assert mailBox.get("Christopher Nolan").equals(
        Arrays.asList(
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                "Я так и не понял Интерстеллар."
        )
): "wrong mailService mailbox content (2)";

assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";

// Создание списка из трех зарплат.
Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

// Создание почтового сервиса, обрабатывающего зарплаты.
MailService<Integer> salaryService = new MailService<>();

// Обработка списка зарплат почтовым сервисом
Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список зарплат, которые были ему отправлены.
Map<String, List<Integer>> salaries = salaryService.getMailBox();
assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
```

В конечном итоге, вам нужно реализовать классы MailService, MailMessage и Salary (и, вероятно, вспомогательные классы и интерфейсы) и отправить их код в форму. Все классы должны быть публичными и статическими (ваши классы подставятся во внешний класс для тестирования).

В идеологически правильном решении не должно фигурировать ни одного оператора instanceof.

В классе для тестирования объявлены следующие импорты:

```
import java.util.*;
import java.util.function.*;
```
