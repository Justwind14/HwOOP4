package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class generatorCal {
    Random random = new Random();


    public Task rand() {
        return new Mytask(this.hashCode(), st(), st(), bls(), LocalDate.of(random.nextInt(1000, 2000), random.nextInt(1, 11), random.nextInt(1, 11)), LocalTime.of(random.nextInt(0, 24), random.nextInt(0, 59)),
                LocalTime.of(random.nextInt(0, 24), random.nextInt(1, 59)), dodo());

    }


    String st() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 5; i++) {
            sb.append((char) random.nextInt(40, 150));
        }
        return sb.toString();
    }

    boolean bls() {
        return random.nextBoolean();
    }

    Prior dodo() {
        ArrayList<Prior> p = new ArrayList<>();
        p.add(Prior.low);
        p.add(Prior.mid);
        p.add(Prior.high);
        return p.get(random.nextInt(3));
    }
}
